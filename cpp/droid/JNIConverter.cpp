// source https://github.com/mrousavy/react-native-vision-camera/blob/main/android/src/main/cpp/JSIJNIConversion.cpp
#include <droid/JNIConverter.h>
#include <droid/bindings/JHashMap.h>
#include <droid/bindings/JArrayList.h>

namespace Reaktor {
    JNIResult toJNI(const jsi::Value &value) {
        if (value.isBool()) {
            return { {.z = value.getBool()}, Boolean };
        }
        else if (value.isNumber()) {
            return {{.d = value.getNumber()}, Number };
        }
        else if (value.isNull() || value.isUndefined()) {
            return {{.l = nullptr}, Undefined};

        } else if (value.isString()) {
            auto string = jni::make_jstring(getLink().getString(value));
            return {{.l = string.release()}, String} ;
        }

        auto stringRepresentation = getLink().getString(value);
        auto message = "Received unknown JSI value! (" + stringRepresentation +
                       ") Cannot convert to a JNI value.";
        Log.Error(message);
        throw std::runtime_error(message);
    }

    template<class T>
    bool checkType(jniRef ref) {
        return ref->isInstanceOf(T::javaClassStatic());
    }

    jsi::Value convertObject(jni::alias_ref<jobject> ref) {
        if (checkType<jni::JBoolean>(ref)) {
            static const auto getBooleanFunc = jni::findClassLocal(
                    "java/lang/Boolean")->getMethod<jboolean()>("booleanValue");
            auto boolean = getBooleanFunc(ref.get()) == true; // convert byte to bool
            return {boolean};
        }

        if (checkType<jni::JDouble>(ref)) {
            static const auto getDoubleFunc = jni::findClassLocal(
                    "java/lang/Double")->getMethod<jdouble()>("doubleValue");
            auto d = getDoubleFunc(ref.get());
            return {d};
        }

        if (checkType<jni::JInteger>(ref)) {
            static const auto getIntegerFunc = jni::findClassLocal(
                    "java/lang/Integer")->getMethod<jint()>("intValue");
            auto i = getIntegerFunc(ref.get());
            return {i};

        }

        if (checkType<jni::JString>(ref)) {
            auto value = ref->toString();
            return getLink().createFromUTF8String(value);
        }

        if(checkType<JHashMap<jstring, jobject>>(ref)) {
            auto map = static_ref_cast<JHashMap<jstring, jobject>>(ref);
            auto result = jsi::Object(getLink().getRuntime());

            for (const auto& entry : *map) {
                auto key = entry.first->toString();
                auto value = entry.second;
                auto jsiValue = convertObject(value.get());
                result.setProperty(getLink().getRuntime(), key.c_str(), jsiValue);
            }
            return result;
        }

        if(checkType<JArrayList<jobject>>(ref)){

            auto arrayList = static_ref_cast<JArrayList<jobject>>(ref);
            auto size = arrayList->size();

            auto result = jsi::Array(getLink().getRuntime(), size);
            size_t i = 0;
            for (const auto& item : *arrayList) {
                result.setValueAtIndex(getLink().getRuntime(), i, convertObject(item.get()));
                i++;
            }
            return result;
        }


        auto type = ref->getClass()->toString();
        auto message = "Received unknown JNI type \"" + type + "\"! Cannot convert to jsi::Value.";
        Log.Error(message);
        throw std::runtime_error(message);
    }

    jsi::Value convertPrimitive(const jvalue &value) {
        jdouble number = value.d;
        return {number};
    }

    jsi::Value fromJNI(const jvalue &value) {
        return convertPrimitive(value);
    }

    jsi::Value fromJNI(const jobject &obj) {
        return convertObject(jni::make_local(obj));
    }

    jsi::Value fromJNI(jni::global_ref<jobject> ref) {
        return convertObject(ref);
    }

}