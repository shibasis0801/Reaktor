#pragma once

#include <droid/AndroidBase.h>
#include <fbjni/NativeRunnable.h>
#include <droid/bindings/Functions.h>

namespace jni = facebook::jni;
namespace Reaktor {
    void install(
            jsi::Runtime *_runtime,
            std::shared_ptr<react::CallInvoker> jsCallInvoker,
            std::shared_ptr<react::CallInvoker> nativeCallInvoker
    ) {
        getLink().install(_runtime, jsCallInvoker, nativeCallInvoker);
    }
}