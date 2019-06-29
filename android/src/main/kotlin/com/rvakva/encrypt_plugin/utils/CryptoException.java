package com.rvakva.encrypt_plugin.utils;

/**
 * Created by liuzihao on 2018/11/27.
 */

public class CryptoException extends RuntimeException {

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }
}
