cmake_minimum_required(VERSION 3.22.1)

project(reaktor)

set(NODE_MODULES "../../node_modules")
set(REACT_NATIVE "${NODE_MODULES}/react-native")


set(CMAKE_VERBOSE_MAKEFILE ON)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

file(GLOB JNI_SRC "droid/*")
file(GLOB TYPE_SRC "types/*")
file(GLOB MODULE_SRC "modules/*")
file(GLOB COMMON_SRC "*")
file(GLOB JNI_BINDINGS "droid/bindings/*")

include_directories(
        ${REACT_NATIVE}/React
        ${REACT_NATIVE}/React/Base
        ${REACT_NATIVE}/ReactCommon
        ${REACT_NATIVE}/ReactCommon/jsi
        ${REACT_NATIVE}/ReactCommon/callinvoker
        ${REACT_NATIVE}/ReactCommon/runtimeexecutor
        ${REACT_NATIVE}/ReactCommon/yoga
        ${REACT_NATIVE}/ReactAndroid/src/main/jni/react/turbomodule
        ${REACT_NATIVE}/ReactAndroid/src/main/jni/react/turbomodule/ReactCommon
        ../../modules/core/commonMain
)

add_compile_options(-Wall -Werror -fexceptions -frtti -fPIC)
add_library(
        reaktor
        SHARED
        ${JNI_BINDINGS}
        ${JNI_SRC}
        ${TYPE_SRC}
        ${MODULE_SRC}
        ${COMMON_SRC}
        ${REACT_NATIVE}/ReactCommon/jsi/jsi/jsi.cpp
)

include("${NODE_MODULES}/react-native/ReactAndroid/cmake-utils/folly-flags.cmake")
add_compile_options(${folly_FLAGS})
find_package(fbjni REQUIRED CONFIG)
find_package(ReactAndroid REQUIRED CONFIG)

set_target_properties(
        reaktor PROPERTIES
        CXX_STANDARD 17
        CXX_EXTENSIONS OFF
        POSITION_INDEPENDENT_CODE ON
)

find_library(
        log-lib
        log
)

target_link_libraries(
        reaktor
        android
        ${log-lib}
        ReactAndroid::folly_runtime
        ReactAndroid::glog
        ReactAndroid::jsi
        ReactAndroid::reactnativejni
        fbjni::fbjni
)