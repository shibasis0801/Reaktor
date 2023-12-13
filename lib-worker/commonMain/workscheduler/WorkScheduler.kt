package app.mehmaan.worker.workscheduler

import app.mehmaan.core.framework.Adapter


// Umbrella class to specify requirements before we run the task
data class TaskModifiers(
    val networkNeeded: Boolean = false,
    val isLongRunning: Boolean = false
)

data class WorkRequest<T>(
    val taskModifiers: TaskModifiers,
//    val dispatchType: DispatchType = DispatchType.Background,
    val task: suspend () -> Result<T>
)


abstract class WorkScheduler<Controller>(controller: Controller): Adapter<Controller>(controller) {
    interface ScheduleOneTimeWork {
        fun schedule()
    }


}

