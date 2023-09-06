#pragma once
#include <Base.h>
#include <ReaktorHostObject.h>
#include <types/Async.h>

namespace Reaktor {
    struct NetworkModule : public ReaktorHostObject {

        Flow flow;
        std::atomic<long> count = 100;

        jsi::Value operator()(const char *name, const jsi::Value *args,
                              const FunctionDescriptor &descriptor) override {
//        auto argCount = descriptor.argumentCount;
            auto returnType = descriptor.returnType;

            if (returnType == DataType::FlowType) {
                flow = Flow();
                return flow.create();
            }

            return {};
        }

        explicit NetworkModule(std::shared_ptr<PlatformInvoker> platformInvoker)
                : ReaktorHostObject(platformInvoker) {
            platformFunctions = {
                    descriptor("get", {
                            DataType::FlowType, {}
                    })
            };

            nativeFunctions = {
                    descriptor("getFlow", {
                            DataType::FlowType, {}
                    })
            };
        }
    };
}