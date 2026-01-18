package com.simpleTherapy.web.utils;

public class PhoneUtil {

    public static String normalize(String phone) {
        if (phone == null) return "";
        return phone.replaceAll("[^0-9]", "")
                .replaceFirst("^1", "");
    }
}
