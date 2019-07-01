import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:encrypt_plugin/encrypt_plugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('encrypt_plugin');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await EncryptPlugin.platformVersion, '42');
  });
}
