package com.honoka.common;

public class Trimmer {

    public static String distance(Double dist){
        String result;
        // 对结果保留两位小时
        dist = dist * 100.0 / 100.0;
        // 转换单位
        if(dist > 1000.0){
            // 转换成公里
            result = Double.toString(Math.round(dist / 1000.0 * 100.0) / 100.0) + " 公里";
        }
        else{
            // 保留米
            result = Double.toString(dist) + " 米";
        }
        return result;
    }
}
