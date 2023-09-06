import UIKit
import React
import pod_user

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        // Create a new UIWindow instance with the same size as the main screen
        window = UIWindow(frame: UIScreen.main.bounds)
        
//        let url = RCTBundleURLProvider.sharedSettings().jsBundleURL(forBundleRoot: "index")!

//        let bridge = RCTBridge.init(bundleURL: url, moduleProvider: nil)
//        let rootView = RCTRootView(bridge: bridge!, moduleName: "Mehmaan", initialProperties: nil)

      
        // Create an instance of your view controller
//        let viewController = UIViewController()
//        viewController.view = rootView
        
//      FeedPodKt.add()
      
      
        // Set your view controller as the window's root view controller
        window?.rootViewController = MehmaanUIControllerKt.getViewController()
      
        
        // Make the window visible
        window?.makeKeyAndVisible()
        
        return true
    }
  
     func applicationWillResignActive(_ application: UIApplication) {}
     func applicationDidEnterBackground(_ application: UIApplication) {}
     func applicationWillEnterForeground(_ application: UIApplication) {}
     func applicationDidBecomeActive(_ application: UIApplication) {}
     func applicationWillTerminate(_ application: UIApplication) {}
}
