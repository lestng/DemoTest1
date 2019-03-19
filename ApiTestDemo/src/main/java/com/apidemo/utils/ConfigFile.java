package com.apidemo.utils;

import com.apidemo.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

//拼接url地址
public class ConfigFile {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri="";
        String testUrl;
        if(name == InterfaceName.ADDUSERINFO){
            uri=bundle.getString("adduser.uri");
        }
        if(name == InterfaceName.GETUSERINFO){
            uri=bundle.getString("getuserinfo.uri");
        }
        if(name == InterfaceName.GETUSERLIST){
            uri=bundle.getString("getuserlist.uri");
        }
        if(name == InterfaceName.UPDATEUSERINFO){
            uri=bundle.getString("updateuserinfo.uri");
        }
        if (name == InterfaceName.LOGIN){
            uri=bundle.getString("login.uri");
        }
        testUrl = address + uri;
        return testUrl;

    }

}
