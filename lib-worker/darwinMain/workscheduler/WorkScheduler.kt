package app.mehmaan.worker.workscheduler

import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.memScoped
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.BackgroundTasks.BGAppRefreshTaskRequest
import platform.BackgroundTasks.BGProcessingTaskRequest
import platform.BackgroundTasks.BGTaskRequest
import platform.BackgroundTasks.BGTaskScheduler
import platform.Foundation.NSError
import platform.UIKit.UIViewController

/**
https://developer.android.com/topic/libraries/architecture/workmanager
https://developer.apple.com/documentation/uikit/app_and_environment/scenes/preparing_your_ui_to_run_in_the_background/using_background_tasks_to_update_your_app
https://developer.mozilla.org/en-US/docs/Web/API/Prioritized_Task_Scheduling_API
https://github.com/fluttercommunity/flutter_workmanager
https://developer.apple.com/documentation/uikit/uiapplicationdelegate/1623125-application
 */


class DarwinWorkScheduler(controller: UIViewController): WorkScheduler<UIViewController>(controller) {

}
