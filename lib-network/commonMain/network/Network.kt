package app.mehmaan.network.network

import app.mehmaan.core.framework.Adapter
import dev.shibasis.reaktor.framework.Feature

abstract class Network<Controller>(controller: Controller): Adapter<Controller>(controller) {

}

private val networkId = Feature.createId()
var Feature.Network: Network<*>?
    get() = fetchDependency(networkId)
    set(network) = storeDependency(networkId, network)