#pragma once
#include <Base.h>
#include <types/Types.h>
#include <framework/Converter.h>
#include <framework/Invoker.h>
#include <Reaktor.h>

namespace Reaktor {
    /*
     * A Reaktor Host Object is the C++ controller object
     * Extends HostObject so that this is exposed to JSI
     * It is attached to the global object during installation
     *
     * Extends PlatformInvoker to allow for pure C++ calls
     * Allowing you to directly call things like tensorflow/compression/etc
     *
     * Pure C++ also allows you to add WebAssembly support later
     * Just we will need to create a type like jsi::Value (ReaktorValue)
     * And then use that here instead.
     *
     * It holds a PlatformInvoker which allows you to decouple your Android/iOS code
     * from how this module's API
     *
     * This is platform agnostic and to support a new platform (ex: Windows/Mac),
     * we just need to implement the PlatformInvoker and all other parts will just work.
     *
     * PlatformInvoker as the name suggests handles all platform invokation/conversion
     */
    class ReaktorHostObject: public jsi::HostObject, public PlatformInvoker {
        std::shared_ptr<PlatformInvoker> platformInvoker;

    public:
        unordered_map<std::string, FunctionDescriptor> platformFunctions;
        unordered_map<std::string, FunctionDescriptor> nativeFunctions;


        explicit ReaktorHostObject(std::shared_ptr<PlatformInvoker> platformInvoker): platformInvoker(std::move(platformInvoker)) {}
        ~ReaktorHostObject() override { platformInvoker.reset(); }

        jsi::Value operator()(const char *name, const jsi::Value *args,
                              const FunctionDescriptor &descriptor) override {
            throw ReaktorException("Native Invoker not implemented but you have specified native functions");
        }

        inline jsi::Value invokePlatform(
                const char *name,
                const jsi::Value *args,
                const FunctionDescriptor &descriptor
        ) {
            GUARD_THROW(platformInvoker, "Platform Invoker not supplied");
            return platformInvoker->operator()(name, args, descriptor);
        }

        // FB also constructs Functions on every call
        jsi::Value get(jsi::Runtime &runtime, const jsi::PropNameID &name) override {
            auto fnName = name.utf8(getLink().getRuntime());

            if (in(platformFunctions, fnName)) {
                auto fnDescriptor = platformFunctions[fnName];
                return getLink().createFunction(
                        name,
                        fnDescriptor.argumentCount,
                        [this, fnName, fnDescriptor] (jsi::Runtime& runtime, const jsi::Value &self, const jsi::Value *args, size_t count) -> jsi::Value {
                            return invokePlatform(fnName.c_str(), args, fnDescriptor);
                        }
                );
            }

            if (in(nativeFunctions, fnName)) {
                auto fnDescriptor = nativeFunctions[fnName];
                return getLink().createFunction(
                        name,
                        fnDescriptor.argumentCount,
                        [this, fnName, fnDescriptor] (jsi::Runtime& runtime, const jsi::Value &self, const jsi::Value *args, size_t count) -> jsi::Value {
                            return this->operator()(fnName.c_str(), args, fnDescriptor);
                        }
                );
            }

            return jsi::Value();
        }

        vector<jsi::PropNameID> getPropertyNames(jsi::Runtime &rt) override {
            vector<jsi::PropNameID> properties;

            for (auto [key, value]: platformFunctions) {
                properties.push_back(getLink().createPropName(key));
            }

            for (auto [key, value]: nativeFunctions) {
                properties.push_back(getLink().createPropName(key));
            }

            return properties;
        }

        void set(jsi::Runtime &runtime, const jsi::PropNameID &name, const jsi::Value &value) override {
            throw ReaktorException("Modifying HostObjects from JS is not supported yet.");
        }
    };
}
