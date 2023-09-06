package app.mehmaan.core.framework

import org.koin.core.component.KoinComponent

// This is similar to Adapter, except no controller reference
interface Component: KoinComponent

interface AdapterContract<Controller: Any, ConcreteAdapter: AdapterContract<Controller, ConcreteAdapter>>: Component {
    val ref: WeakRef<Controller>
    operator fun<Result> invoke(function: Controller.(adapter: ConcreteAdapter) -> Result): Result? = ref.get()?.run {
        function(this@AdapterContract as ConcreteAdapter)
    }
    suspend fun<Result> invokeSuspend(function: suspend Controller.(adapter: ConcreteAdapter) -> Result): Result? = ref.get()?.run {
        function(this@AdapterContract as ConcreteAdapter)
    }
}

abstract class Adapter<Controller: Any, ConcreteAdapter: Adapter<Controller, ConcreteAdapter>>(
    controller: Controller
): AdapterContract<Controller, ConcreteAdapter> {
    override val ref = WeakRef(controller)
}

