#pragma once

#include <droid/AndroidBase.h>

namespace Reaktor {
    struct NoArgNativeFunction: public jni::HybridClass<NoArgNativeFunction> {
        using FnType = std::function<jobject()>;
        static auto constexpr kJavaDescriptor = "Lapp/mehmaan/react/jsi/NoArgNativeFunction;";

        NoArgNativeFunction(FnType&& runnable) : runnable(std::move(runnable)) {}

        static void registerNatives() {
            registerHybrid({
                makeNativeMethod("nativeInvoke", NoArgNativeFunction::operator())
            });
        }

        jobject operator()() {
            return runnable();
        }

    private:
        FnType runnable;
    };

    struct SingleArgNativeFunction: public jni::HybridClass<SingleArgNativeFunction> {
        using FnType = std::function<jobject(jobject)>;
        static auto constexpr kJavaDescriptor = "Lapp/mehmaan/react/jsi/SingleArgNativeFunction;";

        SingleArgNativeFunction(FnType &&consumer) : consumer(std::move(consumer)) {}

        static void registerNatives() {
            registerHybrid({
                makeNativeMethod("nativeInvoke", SingleArgNativeFunction::operator())
            });
        }

        jobject operator()(jobject object) {
            return consumer(object);
        }

    private:
        FnType consumer;
    };
}
