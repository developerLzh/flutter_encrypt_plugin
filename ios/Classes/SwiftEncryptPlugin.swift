import Flutter
import UIKit


public class SwiftEncryptPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "encrypt_plugin", binaryMessenger: registrar.messenger())
    let instance = SwiftEncryptPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
	
	if call.method == FunctionName.AESENCRYPT || call.method == FunctionName.AESDECRYPT {
		guard let dict = call.arguments as? NSDictionary else {
			result(FlutterError(code: "参数不能为空", message: nil, details: nil))
			return
		}
		guard let content = dict["content"] as? String,let password = dict["password"] as? String else {
			result(FlutterError(code: "参数不能为空", message: nil, details: nil))
			return
		}
		if call.method == FunctionName.AESENCRYPT {
			if let encode = AESEncrypt(origin: content, key: password) {
				result(encode)
			}
			result(FlutterError(code: "AES加密失败", message: nil, details:nil))
		}else{
			if let origin = AESDecrypt(decode: content, key: password){
				result(origin)
			}
			result(FlutterError(code: "AES解密失败", message: nil, details: nil))
		}
		
	}
	if call.method == FunctionName.RSAENCRYPT {
		guard let dict = call.arguments as? NSDictionary else{
			result(FlutterError(code: "参数不能为空", message: nil, details: nil))
			return
		}
		guard let content = dict["content"] as? String ,let publicKey = dict["publicKey"] as? String else {
			result(FlutterError(code: "参数不能为空", message: nil, details: nil))
			return
		}
		if let encode = RSAEncrypt(origin: content, publicKey: publicKey){
			result(encode)
			return
		}
		result(FlutterError(code: "RSA加密失败", message: nil, details: nil))
		
	}else{
		result(FlutterMethodNotImplemented)
	}
	
  }
	
}
private func AESEncrypt(origin:String,key:String) -> String? {
	return LanAES.aes128enAES(origin, key: key)
}
private func AESDecrypt(decode:String,key:String) -> String? {
	return LanAES.aes128Decrypt(decode, key: key)
}
private func RSAEncrypt(origin:String,publicKey:String) -> String? {
	return RSA.encryptString(origin, publicKey: publicKey)
}

class FunctionName {
	static let AESENCRYPT = "aesEncrypt"
	static let AESDECRYPT = "aesDecrypt"
	static let RSAENCRYPT = "rsaEntryByPublicKey"
}

