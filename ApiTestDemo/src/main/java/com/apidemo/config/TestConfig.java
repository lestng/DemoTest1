package com.apidemo.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestConfig {
    public static String loginURL;
    public static String updateuserinfoURL;
    public static String getuserlistURL;
    public static String getuserinfoUrl;
    public static String adduserUrl;

    public static DefaultHttpClient defaultHttpClient;
    public static CookieStore store;
}
