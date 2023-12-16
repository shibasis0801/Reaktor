package dev.shibasis.reaktor.framework

import app.mehmaan.core.framework.AtomicInt

interface DependencyModule {
    fun createId(): Int
    fun <T> storeModule(id: Int, module: T)
    fun <T> fetchModule(id: Int): T?
}


object Feature: DependencyModule {
    // should be atomicInt
    private var moduleIdx = AtomicInt(0)
    private val moduleMap = hashMapOf<Int, Any>()
    override fun createId() = moduleIdx.getAndIncrement()
    override fun <T> storeModule(id: Int, module: T) {
        moduleMap[id] = module as Any
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> fetchModule(id: Int): T? {
        return moduleMap[id]?.let {
            it as? T ?: throw ClassCastException("The module is not of the expected type.")
        }
    }
}