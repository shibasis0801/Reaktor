#pragma once

#include <string>
#include <vector>
#include <string>
#include <queue>
#include <utility>
#include <unordered_map>
#include <stdexcept>
#include <thread>
#include <atomic>
#include <chrono>
#include <variant>
#include <jsi/jsi.h>
#include <ReactCommon/CallInvoker.h>

using std::string;
using std::vector;
using std::unordered_map;
using std::make_shared;
using std::shared_ptr;
using std::function;

#define repeat(i, n) for (size_t i = 0; (i) < (n); ++(i))
#define GUARD(ptr) if ((ptr) == nullptr) return
#define GUARD_THROW(ptr, errorMessage) if ((ptr) == nullptr) throw ReaktorException(errorMessage)
#define GUARD_DEFAULT(ptr, fallback) if ((ptr) == nullptr) return fallback
#define in(container, element) (container).find(element) != (container).end()
#define all(container) (container).begin(), (container).end()

#ifndef __ANDROID__
#define __DARWIN__
#endif

typedef long long Long;

namespace jsi = facebook::jsi;
namespace react = facebook::react;

namespace Reaktor {
    struct Logger {
        std::string TAG;
        Logger(std::string TAG): TAG(TAG) {};
        virtual inline void Verbose(const std::string &message) {};
        virtual inline void Error(const std::string &errorMessage) {};
    };

    struct ReaktorException: std::exception {
        std::string message;
        ReaktorException(std::string message);
        const char *what() const noexcept override;
    };


    class Link {
        // Maybe weak_ptr
        jsi::Runtime *runtime;

    public:
        jsi::Runtime& getRuntime() const;

        std::shared_ptr<react::CallInvoker> jsCallInvoker;
        std::shared_ptr<react::CallInvoker> nativeCallInvoker;

        explicit Link(): runtime(nullptr), jsCallInvoker(nullptr), nativeCallInvoker(nullptr) {}
        explicit Link(jsi::Runtime *runtime): runtime(runtime), jsCallInvoker(nullptr), nativeCallInvoker(nullptr) {}

        ~Link();
        void install(
                jsi::Runtime *_runtime,
                std::shared_ptr<react::CallInvoker> _jsCallInvoker,
                std::shared_ptr<react::CallInvoker> _nativeCallInvoker
        );

        jsi::PropNameID createPropName(const std::string &propName) const;

        jsi::Function createFunction(const jsi::PropNameID &name, int argCount, jsi::HostFunctionType &&fn) const;
        jsi::Function createFunction(const std::string &name, int argCount, jsi::HostFunctionType &&fn) const;

        jsi::Value createFromUTF8String(const std::string &contents) const;
        jsi::Value createFromAsciiString(const std::string &contents) const;

        void createGlobalProperty(const std::string &name, jsi::Object object) const;
        jsi::Object createFromHostObject(std::shared_ptr<jsi::HostObject> hostObject) const;

        jsi::Value getProperty(const std::string &name) const;
        jsi::Function getFunction(const std::string &name) const;
        std::string getString(const jsi::Value &value) const;
    };

    Link& getLink();
    jsi::Runtime& getRuntime();

    // Wrap a raw JSI reference
    struct FunctionHolder {
        void invoke() {}
        void construct() {}
    };

}

namespace jsi = facebook::jsi;