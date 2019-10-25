package com.wchkong.common.utils;

import java.util.BitSet;

/**
 * @author dbh on 2018/7/30.
 */
public class ByteUtils {
    /**
     * bytes to BitSet
     *
     * @param bytes
     * @return
     */
    public static BitSet byteArray2BitSet(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return new BitSet();
        }
        BitSet bitSet = new BitSet(bytes.length * 8);
        int index = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 7; j >= 0; j--) {
                bitSet.set(index++, (bytes[i] & (1 << j)) >> j == 1);
            }
        }
        return bitSet;
    }

    public static BitSet byteArray2BitSetReverse(final byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return new BitSet();
        }
        final BitSet bits = new BitSet();
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[i / 8] & (1 << (7 - (i % 8)))) != 0) {
                bits.set(i);
            }
        }
        return bits;
    }

    public static void main(String[] args) {
        System.out.println("a".getBytes());
        BitSet bitSet = byteArray2BitSet("a".getBytes());
        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(2));
    }

}
