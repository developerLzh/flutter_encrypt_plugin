package com.rvakva.encrypt_plugin

import android.os.Build
import com.rvakva.encrypt_plugin.utils.Base64
import com.rvakva.encrypt_plugin.utils.Base64Utils
import com.rvakva.encrypt_plugin.utils.RsaUtils
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException

import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class EncryptPlugin : MethodCallHandler {
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "encrypt_plugin")
            channel.setMethodCallHandler(EncryptPlugin())
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when {
            call.method == "aesEncrypt" -> {
                val content = call.argument<String>("content")
                val psw = call.argument<String>("password")
                if (content != null && psw != null) {
                    result.success(aesEncrypt(content, psw))
                } else {
                    result.error("参数不能为空", null, null)
                }
            }
            call.method == "aesDecrypt" -> {
                val content = call.argument<String>("content")
                val psw = call.argument<String>("password")
                if (content != null && psw != null) {
                    result.success(aesDecrypt(content, psw))
                } else {
                    result.error("参数不能为空", null, null)
                }
            }
            call.method == "rsaEntryByPublicKey" -> {
                val content = call.argument<String>("content")
                val publicKey = call.argument<String>("publicKey")
                if (content != null && publicKey != null) {
                    result.success(rsaEntryByPublicKey(content, publicKey))
                } else {
                    result.error("参数不能为空", null, null)
                }
            }
            else -> result.notImplemented()
        }
    }

    val bm = "utf-8"

    /**
     * AES 加密
     *
     * @param content  明文
     * @param password 生成秘钥的关键字
     * @return
     */
    private fun aesEncrypt(content: String, password: String): String? {
        try {
            val zeroIv = IvParameterSpec(password.toByteArray())
            val key = SecretKeySpec(password.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val encryptedData = cipher.doFinal(content.toByteArray(charset(bm)))

            return Base64.encode(encryptedData)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * AES 解密
     *
     * @param content  密文
     * @param password 生成秘钥的关键字
     * @return
     */

    private fun aesDecrypt(content: String, password: String): String? {
        try {
            val byteMi = Base64.decode(content)
            val zeroIv = IvParameterSpec(password.toByteArray())
            val key = SecretKeySpec(password.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, key)
            val decryptedData = cipher.doFinal(byteMi)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String(decryptedData, StandardCharsets.UTF_8)
            } else {
                return null
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * rsa公钥加密
     */
    private fun rsaEntryByPublicKey(content: String, publicKey: String): String {
        return Base64Utils.encode(RsaUtils.encryptByPublicKey(
                content.toByteArray(charset("UTF-8")),
                publicKey))
    }


}
