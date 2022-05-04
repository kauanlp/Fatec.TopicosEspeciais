package br.com.fatecmogidascruzes.topicosbackend1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Convert {

    public static int toInt(String value) {
        return Integer.valueOf(value);
    }

    public static int toInt(double value) {
        return (int) value;
    }

    public static int toInt(float value) {
        return (int) value;
    }

    public static int toInt(boolean value) {
        return value ? 1 : 0;
    }

    public static String toString(int value) {
        return String.valueOf(value);
    }

    public static String toString(double value) {
        return String.valueOf(value);
    }

    public static String toString(float value) {
        return String.valueOf(value);
    }

    public static String toString(LocalDate value) {
        return toString(value, "dd/MM/yyyy");
    }

    public static String toString(LocalDate value, String format) {
        return DateTimeFormatter.ofPattern(format).format(value);
    }

}
