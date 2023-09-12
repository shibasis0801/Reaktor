#pragma once
#include <framework/Base.h>

namespace Reaktor {
    using PrimitiveType = std::variant<bool, int, double, std::string>;

    template<class From, class To>
    struct Converter {
        virtual To from(From data) = 0;
        virtual From to(To data) = 0;
    };
}