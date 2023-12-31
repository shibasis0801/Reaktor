package dev.shibasis.reaktor.framework

import app.mehmaan.core.framework.AtomicInt

interface DependencyModule {
    fun createId(): Int
    fun <T> storeDependency(id: Int, dependency: T)
    fun <T> fetchDependency(id: Int): T?
}

object Feature: DependencyModule {
    // should be atomicInt
    private var moduleIdx = AtomicInt(0)
    private val moduleMap = hashMapOf<Int, Any>()
    override fun createId() = moduleIdx.getAndIncrement()
    override fun <T> storeDependency(id: Int, dependency: T) {
        moduleMap[id] = dependency as Any
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> fetchDependency(id: Int): T? {
        return moduleMap[id]?.let {
            it as? T ?: throw ClassCastException("The module is not of the expected type.")
        }
    }
}