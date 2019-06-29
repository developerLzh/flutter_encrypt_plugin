package com.rvakva.encrypt_plugin.utils;

/**
 * 键值对
 *
 * @param <K> 键类型
 * @param <V> 值类型
 * @author 肖波
 * @since 1.0
 */
public final class Pair<K, V> {

    public final K key;

    public final V value;

    /**
     * 构造函数
     *
     * @param key   键
     * @param value 值
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取键
     *
     * @return 键
     */
    public K first() {
        return key;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public V second() {
        return value;
    }

    /**
     * 数据转换为键值类型
     *
     * @param k   键
     * @param v   值
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 实例对象
     */
    public static <K, V> Pair<K, V> of(K k, V v) {
        return new Pair<>(k, v);
    }
}
