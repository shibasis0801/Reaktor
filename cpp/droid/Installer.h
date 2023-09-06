#pragma once
#include <droid/AndroidBase.h>
#include <droid/Invoker.h>
#include <modules/NetworkModule.h>
#include <droid/bindings/Functions.h>
#include <modules/MehmaanConfig.h>
#include <droid/bindings/JHashMap.h>

namespace Reaktor {
    jsi::Object createHostObject(
            jni::alias_ref<jni::JObject> instance,
            const std::string &name
    ) {
        auto platformInvoker = make_shared<AndroidInvoker>(instance);

        if (name == "NetworkModule") {
            auto hostObject = make_shared<NetworkModule>(std::move(platformInvoker));
            return getLink().createFromHostObject(hostObject);
        }
        else if (name == "MehmaanConfig") {
            auto hostObject = make_shared<MehmaanConfig>(std::move(platformInvoker));
            return getLink().createFromHostObject(hostObject);
        }
        else if (name == "FeedViewModel") {
            auto hostObject = make_shared<ReaktorHostObject>(std::move(platformInvoker));
            hostObject->platformFunctions = {
                    descriptor("getFeed", {
                        DataType::FlowType, {}
                    })
            };
            return getLink().createFromHostObject(hostObject);
        }

        throw ReaktorException("Add cpp support in Reaktor for: " + name);
    }

    // WIP TODO REACTDESCRIPTORS
//    void addModule(
//            jni::alias_ref<jni::JObject> instance,
//            const std::string &name,
//            JHashMap<jstring, jstring> descriptors
//    ) {
//        auto platformInvoker = make_shared<AndroidInvoker>(instance);
//        auto hostObject = make_shared<ReaktorHostObject>(std::move(platformInvoker));
//
//        for (const auto& entry : descriptors) {
//            auto key = entry.first->toString();
//            auto value = entry.second;
//            auto jsiValue = convertObject(value.get());
//            result.setProperty(getLink().getRuntime(), key.c_str(), jsiValue);
//        }
//
//        hostObject->platformFunctions = {
//                descriptor("getFeed", {
//                        DataType::FlowType, {}
//                })
//        };
//
//
//        getLink().createGlobalProperty(name, getLink().createFromHostObject(hostObject));
//    }

    void addModule(
            jni::alias_ref<jni::JObject> instance,
            const std::string &name
    ) {
        getLink().createGlobalProperty(name, createHostObject(instance, name));
    }

    void registerNatives() {
        NoArgNativeFunction::registerNatives();
        SingleArgNativeFunction::registerNatives();
    }
}
