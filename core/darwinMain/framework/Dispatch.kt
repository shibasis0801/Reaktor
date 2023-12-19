package framework

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual val Dispatchers.Async get() = IO