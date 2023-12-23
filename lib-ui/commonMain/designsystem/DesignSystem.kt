package app.mehmaan.ui.designsystem

import app.mehmaan.core.framework.Adapter
import dev.shibasis.reaktor.framework.Feature

abstract class DesignSystem<Controller>(controller: Controller): Adapter<Controller>(controller) {

}

private val designsystemId = Feature.createId()
var Feature.DesignSystem: Database<*>?
    get() = fetchDependency(designsystemId)
    set(DesignSystem) = storeDependency(databaseId, DesignSystem)