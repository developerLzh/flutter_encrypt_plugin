// Copyright (C), 2012-2018, Sichuan Xiaoka Technology Co., Ltd.
package com.rvakva.encrypt_plugin.utils;

/**
 * Base64编码工具
 *
 * @author 肖波
 * @since 1.0
 */
public class Base64Utils {

    /**
     * <p>
     * BASE64字符串解码为二进制数据
     * </p>
     *
     * @param base64 base64
     * @return 源二进制数据
     * @throws
     */
    public static byte[] decode(String base64) {
        return Base64.decode(base64);
    }

    /**
     * <p>
     * 二进制数据编码为BASE64字符串
     * </p>
     *
     * @param bytes base64
     * @return BASE64后的二进制数据
     * @throws
     */
    public static String encode(byte[] bytes) {
        return Base64.encode(bytes);
    }

    public static String encodeToString(byte[] toByteArray) {
        return new String(toByteArray);
    }
}