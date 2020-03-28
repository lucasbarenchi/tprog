package com.uytube.utils;

public class UrlUtils {

	public static String getVideoUrl(String url) {
        String[] parsedWithBe = url.split("be/");
        if (parsedWithBe.length > 1) {
            return parsedWithBe[1];
        } else {
            String[] parsedUrl = url.split("=");
            if (parsedUrl.length > 1) {
                return parsedUrl[1];
            }
        }
        return "";
    }
}
