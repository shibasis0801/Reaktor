package framework

import kotlinx.coroutines.Dispatchers

actual val Dispatchers.Async get() = IO