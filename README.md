# 一个用于Aes和Rsa加解密的flutter插件
## 目前支持的模式
  Aes: "AES/ECB/PKCS5Padding"加解密  
  Rsa:公钥加密

## How To Use?
 import 'package:encrypt_plugin/encrypt_plugin.dart';

 EncryptPlugin.rsaEntryByPublicKey(content, publicKey);
 EncryptPlugin.aesEncrypt(plainText, psw);
 EncryptPlugin.aesDecrypt(plainText, psw);
