#pragma once
#include <Base.h>
#include <ReaktorHostObject.h>
#include <types/Async.h>

namespace Reaktor {
    struct MehmaanConfig : public ReaktorHostObject {
        explicit MehmaanConfig(std::shared_ptr<PlatformInvoker> platformInvoker)
                : ReaktorHostObject(platformInvoker) {
            platformFunctions = {
                    descriptor("getThemeString", {
                            DataType::String, {}
                    })
            };
        }
    };
}