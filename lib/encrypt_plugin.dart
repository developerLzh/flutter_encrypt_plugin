import 'dart:async';

import 'package:flutter/services.dart';

class EncryptPlugin {
  static const MethodChannel _channel = const MethodChannel('encrypt_plugin');

  static Future<String> aesEncrypt(String content, String password) async {
    final Map<String, dynamic> params = <String, dynamic>{
      'content': content,
      'password': password,
    };
    final String version = await _channel.invokeMethod('aesEncrypt', params);
    return version;
  }

  static Future<String> aesDecrypt(String content, String password) async {
    final Map<String, dynamic> params = <String, dynamic>{
      'content': content,
      'password': password,
    };
    final String version = await _channel.invokeMethod('aesDecrypt', params);
    return version;
  }
}
