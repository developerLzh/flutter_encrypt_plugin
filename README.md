# 一个用于Aes和Rsa加解密的flutter插件
## 目前支持的模式
  Aes: "AES/ECB/PKCS5Padding"加解密

  Rsa:公钥加密


## How To Use?
 import 'package:encrypt_plugin/encrypt_plugin.dart';

 String s1 = await EncryptPlugin.rsaEntryByPublicKey(content, publicKey);//Rsa公钥加密

  String s2 = await EncryptPlugin.aesEncrypt(plainText, psw);//aes加密

  String s3 = await EncryptPlugin.aesDecrypt(plainText, psw);//aes解密

