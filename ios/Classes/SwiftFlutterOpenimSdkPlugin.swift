import Flutter
import UIKit

public class SwiftFlutterOpenimSdkPlugin: NSObject, FlutterPlugin {
  
  static var channel:FlutterMethodChannel?
    
  public static func register(with registrar: FlutterPluginRegistrar) {
    channel = FlutterMethodChannel(name: "flutter_openim_sdk", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterOpenimSdkPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel!)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }

}
