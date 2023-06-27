package org.com.util;

import java.util.HashMap;
import java.util.Map;

public final class HelperUtil {
  static Double getDoubleValyeFromPercentage(String token) {
        String removedPercentage = token.substring(0, token.length() - 1);
        return Double.parseDouble(removedPercentage);
    }

    public static Map<String, Integer> getMonths() {
        Map<String, Integer> months = new HashMap<String, Integer>();
        months.put("JANUARY", 1);
        months.put("FEBRUARY", 2);
        months.put("MARCH", 3);
        months.put("APRIL", 4);
        months.put("MAY", 5);
        months.put("JUNE", 6);
        months.put("JULY", 7);
        months.put("AUGUST", 8);
        months.put("SEPTEMBER", 9);
        months.put("OCTOBER", 10);
        months.put("NOVEMBER", 11);
        months.put("DECEMBER", 12);
        return months;
    }

    public static Map<Integer, String> reverseMonths() {
        Map<Integer, String> months = new HashMap<Integer, String>();
        months.put(1, "JANUARY");
        months.put(2, "FEBRUARY");
        months.put(3, "MARCH");
        months.put(4, "APRIL");
        months.put(5, "MAY");
        months.put(6, "JUNE");
        months.put(7, "JULY");
        months.put(8, "AUGUST");
        months.put(9, "SEPTEMBER");
        months.put(10, "OCTOBER");
        months.put(11, "NOVEMBER");
        months.put(12, "DECEMBER");
        return months;
    }


}
