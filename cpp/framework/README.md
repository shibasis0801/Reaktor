To build a platform independent library, we build on common interfaces

Platform <> React and Platform <> C++ has two major parts
1. Function Invokation
2. Argument/Result Type Conversion

We do this through common interfaces PlatformInvoker and PlatformConverter

We also need to find a way to easily register functions on C++ to be called from Kotlin
Kotlin does not have interop with C++, only with C/ObjectiveC
Have to figure out that part
Some combination of HybridClasses and Cinterops
 


