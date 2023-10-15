package dev.reaktor.core.di

import org.koin.mp.KoinPlatform.getKoin

inline fun <reified T: Any> GetDependency() = getKoin().get<T>()

