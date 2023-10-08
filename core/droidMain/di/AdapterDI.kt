package app.mehmaan.core.di

import app.mehmaan.core.adapters.*
import org.koin.dsl.module

// Probably use this in future
// https://github.com/JetBrains-Research/reflekt

val AdapterModule = module {
    single { StorageAdapter(get()) }
    single { PermissionAdapter(get()) }
    single { FileAdapter(get()) }
}

/*

dev a screen
mock apis
breaking down and handling data
component structure
architecture

*/
