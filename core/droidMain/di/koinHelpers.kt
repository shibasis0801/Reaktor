package dev.reaktor.core.di

import androidx.compose.runtime.Composable
import org.koin.mp.KoinPlatform.getKoin
import org.koin.androidx.compose.getKoin as getKoinCompose


@Composable
inline fun <reified T: Any> Get() = getKoinCompose().get<T>()

inline fun <reified T: Any> GetDependency() = getKoin().get<T>()

