package di

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.mp.KoinPlatformTools

object Injector {
    val context = KoinPlatformTools.defaultContext().get()

    /*
    initModule contains the dependencies needed by all your other modules
    Ex: Activity / ViewController classes
     */
    fun init(initModule: Module) {
        startKoin {
            loadKoinModules(initModule)
        }
    }
    fun inject(vararg module: Module) {
        context.loadModules(module.toList())
//        context.unloadModules(module.toList())
    }
}