//package com.ren.springboot.test;
//
//import java.lang.reflect.Array;
//import java.util.Arrays;
//
///**
// * @author : renjiahui
// * @date : 2020/8/10 23:18
// * @desc : 反射转换数组
// */
//public class ReflectTest {
//
//    public static void main(String[] args) {
//        int[] a = {1, 2, 3};
//        a = (int[]) goodCopyOf(a, 10);
//        System.out.println(Arrays.toString(a));
//
//        String[] b = {"Tom", "Dick", "Harry"};
//        b = (String[]) goodCopyOf(b, 10);
//        System.out.println(Arrays.toString(b));
//
//        System.out.println("The following call will generate an exception.");
////        b = (String[]) badCopuOf(b, 10);
//    }
//
//
//    /**
//     * 复制数组到一个新的数组中
//     *
//     * @param a         原数组
//     * @param newLength 新数组的长度
//     * @return 新数组
//     */
//    public static Object[] badCopuOf(String[] a, int newLength) {
//        Object[] newArray = new Object[newLength];
//        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
//        return newArray;
//    }
//
//    /**
//     * 复制数组到一个新的数组中
//     *
//     * @param a         原数组
//     * @param newLength 新数组的长度
//     * @return 新数组
//     */
//    private static Object goodCopyOf(Object a, int newLength) {
//        Class cl = a.getClass();
//        if (!cl.isArray()) {
//            return null;
//        }
//
//        Class componentType = cl.getComponentType();
//        int length = Array.getLength(a);
//        Object newArray = Array.newInstance(componentType, newLength);
//        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
//        return newArray;
//    }
//
//}
