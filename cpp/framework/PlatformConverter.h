#pragma once
#include <framework/Base.h>
#include <fbjni/detail/References.h>

namespace Reaktor {
    using PrimitiveType = std::variant<bool, int, double, std::string>;

    template<class PlatformType>
    using NativeType = std::variant<
            PlatformType,
            PrimitiveType,
            vector<PrimitiveType>,
            unordered_map<PrimitiveType, PrimitiveType>
    >;

    template<class PlatformType>
    struct PlatformConverter {
        // use std::variant instead of folly::dynamic
        virtual NativeType<PlatformType> toObject(const PlatformType &data) const = 0;
        virtual PlatformType fromObject(const NativeType<PlatformType> &data) const = 0;

        virtual PrimitiveType toPrimitive(const PlatformType &data) const = 0;
        virtual PlatformType fromPrimitive(const PrimitiveType &data) const = 0;
    };


}