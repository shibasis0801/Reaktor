#pragma once

namespace Reaktor {
    struct FunctionDescriptor {
        std::string jvmSignature = ""; // is empty string on iOS
        std::string iosSelector = ""; // is empty string on android
        int argumentCount;
        DataType returnType;

        struct Input {
            DataType returnType;
            FunctionParamList parameterList;
        };
    };


    std::pair<std::string, FunctionDescriptor> descriptor(
            const std::string &name,
            const FunctionDescriptor::Input &input
    );
}