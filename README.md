# reaktor
Kotlin Multiplatform Framework with bi-directional type safe communications to React and C++

# Usage
1. React Native Applications - Easy to write high performance native modules
2. Native Applications - Easy access to C++ libraries from common Kotlin (Version 2)
3. Flutter Applications - Same as React Native 

# Motivation
Android, iOS, Web and possibly Desktop and VR, we have multiple platforms to support.

Instead of writing repeated code for platforms / screen sizes / operating systems, 
I believe that we should invest in a Platform Abstraction Layer exposing common functionalities to apps

As our apps are mostly in React Native, I also want seamless access to this framework from JS

Kotlin and C++ both are cross platform languages and a combination of both should serve 90% of cases. 
For the 10% of cases, we support dependendency injection into the layer.


# Version 1
1. Single Native Module for React Native across Platforms (Reaktor Module)
2. Richer Type Support than Turbo Modules (allow normal arrays, hashmaps and kotlin flows)
3. Allow C++ Native Modules for pure C++ implementations
4. Allow Reaktor Module registration from Kotlin
5. Automatic JSI Bindings
6. Platform specific Invoker with common interface, support a new invoker -> support a new platform

# Version 2
1. Allow arbitary serializable class support using FlatBuffers.
2. Decoupled Invoker (KMM <> CPP, CPP <> JSI). Currently the Invoker directly uses JSI types
3. Support Flutter
4. Compiler plugin based code generation (TypeScript / Dart)
5. Crashlytics and Logging across points
6. Flipper plugin to see function calls and timings
7. Safer memory management
8. Easy interop with arbitary C++ code (using the invoker)
9. C++ and Kotlin unit tests
10. Tester app integration tests
11. Performance benchmarking tests
