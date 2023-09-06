//
//  JSIManager.swift
//  Mehmaan
//
//  Created by ovd on 24/07/23.
//

import Foundation


@objc(CalendarManager)
class CalendarManager: NSObject {

 @objc
 func getData(_ name: String, location: String, date: NSNumber) -> Any {
   return 1
 }

 @objc
 func constantsToExport() -> [String: Any]! {
   return ["someKey": "someValue"]
 }

}
