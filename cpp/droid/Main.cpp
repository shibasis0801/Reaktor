#include <JSIInstaller.h>
#include <droid/Installer.h>
#include <droid/bindings/JHashMap.h>

#define JAVA_DESCRIPTOR(fqcn) static auto constexpr kJavaDescriptor = fqcn;

struct ReaktorModule: jni::JavaClass<ReaktorModule> {
    JAVA_DESCRIPTOR("Lapp/mehmaan/react/jsi/ReaktorModule;");
};

struct JReaktor: public jni::JavaClass<JReaktor> {
    JAVA_DESCRIPTOR("Lapp/mehmaan/react/jsi/Reaktor;");

    static void install(
            jni::alias_ref<JReaktor> _,
            jlong reactPointer,
            jni::alias_ref<react::CallInvokerHolder::javaobject> jsCallInvokerHolder,
            jni::alias_ref<react::CallInvokerHolder::javaobject> nativeCallInvokerHolder
    ) {
        Reaktor::Log.Verbose("Inside Native install");
        auto runtime = reinterpret_cast<jsi::Runtime *>(reactPointer);
        auto jsCallInvoker = jsCallInvokerHolder->cthis();
        GUARD(jsCallInvoker);
        auto nativeCallInvoker = nativeCallInvokerHolder->cthis();
        GUARD(nativeCallInvoker);
        Reaktor::install(runtime, jsCallInvoker->getCallInvoker(), nativeCallInvoker->getCallInvoker());
    }


    static void addModule(
            jni::alias_ref<JReaktor> _,
            const jni::alias_ref<ReaktorModule>& instance,
            jni::JString name
    ) {
        Reaktor::addModule(instance, name.toStdString());
    }

    static void registerNatives() {
        javaClassStatic()->registerNatives({
             makeNativeMethod("install", JReaktor::install),
             makeNativeMethod("addModule", JReaktor::addModule)
       });
    }
};

jint JNI_OnLoad(JavaVM* vm, void*) {
    return jni::initialize(vm, [] {
        JReaktor::registerNatives();
        Reaktor::registerNatives();
    });
}

// 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
