package com.bkhech.home.practice.javacore.concurrent.concnt_hash_map;

import sun.misc.Unsafe;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link java.util.concurrent.ConcurrentHashMap} 示例
 *
 * @author guowm
 * @date 2021/12/15
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMapDemo concurrentHashMapDemo = new ConcurrentHashMapDemo();
//        writeReadObject();

        concurrentHashMapDemo.initSegmentShiftAndMask();

    }

    private Integer concurrencyLevel = 16;
    /**
     * Mask value for indexing into segments. The upper bits of a
     * key's hash code are used to choose the segment.
     */
    private int segmentMask;

    /**
     * Shift value for indexing within segments.
     */
    private int segmentShift;

    /**
     * The segments, each of which is a specialized hash table.
     */
    private Segment[] segments;

    /**
     * 前提：都是在默认情况下，concurrencyLevel = 16,segmentCount = 16,sShift = 4
     * <p>
     * 那么 this.segmentShift = 32 - sShift = 28;
     * 而 this.segmentMask = segmentCount - 1 = 15; 二进制就是 1111;
     * 那么定位 Segment 所使用的hash算法为：int segmentIndex = (hash >>> segmentShift) & segmentMask;
     * 定位 HashEntry 所使用的hash算法为：int index = hash & (tab.length - 1)
     */
    private void initSegmentShiftAndMask() {
        int sShift = 0;
        int segmentCount = 1;
        while (segmentCount < concurrencyLevel) {
            ++sShift;
            segmentCount <<= 1;
        }
        this.segmentShift = 32 - sShift;
        this.segmentMask = segmentCount - 1;
        this.segments = new Segment[segmentCount];
    }

    /**
     * 定位 Segment（分段）所使用的hash算法
     *
     *  1. 只取高位的几位：hash >>> segmentShift
     *
     *  2.与上数组长度: & segmentMask (segmentMask = arr.len - 1)
     *
     * @param hash
     * @return
     */
    private int segmentIndexFor(int hash) {
        return (hash >>> segmentShift) & segmentMask;
    }

    /**
     * 分段锁对象
     *
     * @param <K>
     * @param <V>
     */
    static final class Segment<K, V> extends ReentrantLock implements Serializable {
        private static final long serialVersionUID = 2249069246763182397L;

        /**
         * The per-segment table. Elements are accessed via
         * entryAt/setEntryAt providing volatile semantics.
         */
        transient volatile HashEntry<K, V>[] table;

        Segment(HashEntry<K, V>[] tab) {
            this.table = tab;
        }

        /**
         * 定位 HashEntry 所使用的hash算法
         *
         * @param hash
         * @param length
         * @return
         */
        private int indexFor(int hash, int length) {
            return hash & (length - 1);
        }
    }

    /**
     * ConcurrentHashMap list entry. Note that this is never exported
     * out as a user-visible Map.Entry.
     */
    static final class HashEntry<K, V> {
        final int hash;
        final K key;
        volatile V value;
        volatile ConcurrentHashMap1_7.HashEntry<K, V> next;

        HashEntry(int hash, K key, V value, ConcurrentHashMap1_7.HashEntry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Sets next field with volatile write semantics.  (See above
         * about use of putOrderedObject.)
         */
        final void setNext(ConcurrentHashMap1_7.HashEntry<K, V> n) {
            UNSAFE.putOrderedObject(this, nextOffset, n);
        }

        // Unsafe mechanics
        static final Unsafe UNSAFE;
        static final long nextOffset;

        static {
            try {
                UNSAFE = Unsafe.getUnsafe();
                Class k = ConcurrentHashMap1_7.HashEntry.class;
                nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }


    /**
     * 从 ConcurrentHashMap 源码中看对象的序列化和反序列化
     */
    private static void writeReadObject() {
        ConcurrentHashMap<String, String> person = new ConcurrentHashMap<>(16);
        person.put("id", "1");
        person.put("name", "guowm");
        System.out.println("序列化前：" + person);

        String personPath = "person.obj";
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            // 创建一个 ObjectOutputStream 输出流
            outputStream = new ObjectOutputStream(new FileOutputStream(personPath));
            // 将对象序列化文件写入文件
            outputStream.writeObject(person);
            outputStream.writeObject(person);

            // 创建一个 ObjectInputStream 输入流
            inputStream = new ObjectInputStream(new FileInputStream(personPath));
            // 将输入流反序列化为对象
            // 写两次才能读两次
            final ConcurrentHashMap<String, String> personObj1 = (ConcurrentHashMap<String, String>) inputStream.readObject();
            final ConcurrentHashMap<String, String> personObj2 = (ConcurrentHashMap<String, String>) inputStream.readObject();
            System.out.println(personObj1 == personObj2); // 同一个对象 true
            System.out.println("反序列化后：" + personObj1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
