package dev.gerasimova;

/**
 * Демонстрация примитивных типов данных в Java + тип String.
 * Выводит информацию о размерах и диапазонах значений
 * всех восьми примитивных типов, а также типа String.
 */
public class Main {
    public static void main(String[] args) {

        int x = 5;
        int xMin = Integer.MIN_VALUE; //-2147483648
        int xMax = Integer.MAX_VALUE; //2147483647

        short s = 4;
        short sMin = Short.MIN_VALUE; //-32768
        short sMax = Short.MAX_VALUE; //32767

        long l = 200;
        long lMin = Long.MIN_VALUE; //-9223372036854775808L
        long lMax = Long.MAX_VALUE; //9223372036854775807L

        byte b = 120;
        byte bMin = Byte.MIN_VALUE; //-128
        byte bMax = Byte.MAX_VALUE; //127

        double d = 1.0;
        double dMin = Double.MIN_VALUE; //4.94065645841246E324
        double dMax = Double.MAX_VALUE; //1.7976931348623157E308

        float f = 1.2f;
        float fMin = Float.MIN_VALUE; //1.401298E45f
        float fMax = Float.MAX_VALUE; //3.4028235E38f

        boolean bl_true = Boolean.TRUE;
        boolean bl_false = Boolean.FALSE;

        char c = 'H';
        char cMin = Character.MIN_VALUE; //'\u0000'
        char cMax = Character.MAX_VALUE; //'\uFFFF'

        String str = "Hello, World!";


        System.out.printf("int x: %d, x_Size: %d байта\n", x, Integer.BYTES);
        System.out.printf("int xMin: %d, xMin_Size: %d байта\n", xMin, Integer.BYTES);
        System.out.printf("int xMax: %d, xMax_Size: %d байта\n", xMax, Integer.BYTES);
        System.out.println("---------------------------------------------------");

        System.out.printf("short s: %d, short_Size: %d байта\n", s, Short.BYTES);
        System.out.printf("short sMin: %d, sMin_Size: %d байта\n", sMin, Short.BYTES);
        System.out.printf("short sMax: %d, sMax_Size: %d байта\n", sMax, Short.BYTES);
        System.out.println("---------------------------------------------------");

        System.out.printf("long l: %d, long_Size: %d байт\n", l, Long.BYTES);
        System.out.printf("long lMin: %d, lMin_Size: %d байт\n", lMin, Long.BYTES);
        System.out.printf("long lMax: %d, lMax_Size: %d байт\n", lMax, Long.BYTES);
        System.out.println("---------------------------------------------------");

        System.out.printf("byte b: %d, byte_Size: %d байт\n", b, Byte.BYTES);
        System.out.printf("byte bMin: %d, bMin_Size: %d байт\n", bMin, Byte.BYTES);
        System.out.printf("byte bMax: %d, bMax_Size: %d байт\n", bMax, Byte.BYTES);
        System.out.println("---------------------------------------------------");

        System.out.printf("double d: %f, double_Size: %d байт\n", d, Double.BYTES);
        System.out.printf("double dMin: %e, dMin_Size: %d байт\n", dMin, Double.BYTES);
        System.out.printf("double dMax: %e, dMax_Size: %d байт\n", dMax, Double.BYTES);
        System.out.println("---------------------------------------------------");

        System.out.printf("float f: %f, float_Size: %d байта\n", f, Float.BYTES);
        System.out.printf("float fMin: %e, fMin_Size: %d байта\n", fMin, Float.BYTES);
        System.out.printf("float fMax: %e, fMax_Size: %d байта\n", fMax, Float.BYTES);
        System.out.println("---------------------------------------------------");

        System.out.printf("boolean bl_true: %b, bl_Size: ~%d байт\n", bl_true, 1);
        System.out.printf("boolean bl_false: %b, bl_Size: ~%d байт\n", bl_false, 1);
        System.out.println("---------------------------------------------------");

        System.out.printf("char c: %c, char_Size: %d байта\n", c, Character.BYTES);
        System.out.printf("char cMin: %c, cMin_Size: %d байта\n", cMin, Character.BYTES);
        System.out.printf("char cMax: %c, cMax_Size: %d байта\n", cMax, Character.BYTES);
        System.out.println("---------------------------------------------------");

        System.out.printf("String str: %s\n", str);
        System.out.println("---------------------------------------------------");
    }
}