package com.company.zwh_rfid.util;

/**
 * Base加密工具类
 *
 * @author zhr
 */
public class Base64 {
    private static final String CVT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String encode(String data) {
        if (data == null) {
            return null;
        }
        return getString(encode(getBinaryBytes(data)));
    }

    public static String encodeBytes(byte[] data) {
        if (data == null) {
            return null;
        }
        return getString(encode(data));
    }

    public static byte[] encode(byte[] data) {
        if (null == data) {
            return null;
        }
        int len = data.length;
        StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
        for (int i = 0; i < len; i++) {
            int c = data[i] >> 2 & 0x3F;
            ret.append(CVT.charAt(c));
            c = data[i] << 4 & 0x3F;
            i++;
            if (i < len) {
                c |= data[i] >> 4 & 0xF;
            }
            ret.append(CVT.charAt(c));
            if (i < len) {
                c = data[i] << 2 & 0x3F;
                i++;
                if (i < len) {
                    c |= data[i] >> 6 & 0x3;
                }
                ret.append(CVT.charAt(c));
            } else {
                i++;
                ret.append('=');
            }
            if (i < len) {
                c = data[i] & 0x3F;
                ret.append(CVT.charAt(c));
            } else {
                ret.append('=');
            }
        }
        return getBinaryBytes(ret.toString());
    }

    public static String decode(String data) {
        if (data == null) {
            return null;
        }
        return getString(decode(getBinaryBytes(data)));
    }

    public static byte[] decodeBytes(String data) {
        if (data == null) {
            return null;
        }
        return decode(getBinaryBytes(data));
    }

    public static byte[] decode(byte[] data) {
        if (null == data) {
            return null;
        }
        int len = data.length;
        StringBuffer ret = new StringBuffer(len * 3 / 4);
        for (int i = 0; i < len; i++) {
            int c = CVT.indexOf(data[i]);
            i++;
            int c1 = CVT.indexOf(data[i]);
            c = c << 2 | c1 >> 4 & 0x3;
            ret.append((char) c);
            i++;
            if (i < len) {
                c = data[i];
                if (61 == c) {
                    break;
                }
                c = CVT.indexOf((char) c);
                c1 = c1 << 4 & 0xF0 | c >> 2 & 0xF;
                ret.append((char) c1);
            }
            i++;
            if (i < len) {
                c1 = data[i];
                if (61 == c1) {
                    break;
                }
                c1 = CVT.indexOf((char) c1);
                c = c << 6 & 0xC0 | c1;
                ret.append((char) c);
            }
        }
        return getBinaryBytes(ret.toString());
    }

    public static String getString(byte[] arr) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            buf.append((char) arr[i]);
        }
        return buf.toString();
    }

    private static byte[] getBinaryBytes(String str) {
        byte[] b = new byte[str.length()];
        for (int i = 0; i < b.length; i++) {
            b[i] = ((byte) str.charAt(i));
        }
        return b;
    }
}