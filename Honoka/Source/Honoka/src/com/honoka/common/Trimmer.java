package com.honoka.common;

public class Trimmer {

    /**
     * 距离数据格式化
     *
     * @param meter 原始数据
     * @return 格式化后数据
     */
    public static String distance(Double meter) {
        String result;
        // 对结果保留两位小数
        meter = meter * 100.0 / 100.0;
        // 转换单位
        if (meter > 1000.0) {
            // 转换成公里
            result = Double.toString(Math.round(meter / 1000.0 * 100.0) / 100.0) + " 公里";
        } else {
            // 保留米
            result = Double.toString(meter) + " 米";
        }
        return result;
    }

    /**
     * 时间数据格式化
     *
     * @param second 原始数据
     * @return 格式化后数据
     */
    public static String time(Integer second) {
        String result;
        // 转换单位
        if (second <= 60) {
            // 保留秒
            result = second.toString() + " 秒";
        } else if (second > 60 && second < 3600) {
            // 转换成分钟
            result = Double.toString(Math.round(second / 60.0 * 100.0) / 100.0) + " 分钟";
        } else {
            // 转换成小时
            result = Double.toString(Math.round(second / 3600.0 * 100.0) / 100.0) + " 小时";
        }
        return result;
    }
}
