#pragma once
#include <framework/Base.h>

namespace Reaktor {
    /**
     *
     */
    struct PlatformInvoker {
        virtual jsi::Value operator()(
                const char *name,
                const jsi::Value *args,
                const FunctionDescriptor &descriptor
        ) = 0;

        // You may need to nullify any references to native objects
        virtual ~PlatformInvoker() = default;
    };
}