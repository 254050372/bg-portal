package com.boot.core.common.util;/**
 * @description
 * @autor xbwu on 2017/10/27.
 */

import java.util.UUID;

/**
 * 主键生成工具
 * @author xbwu
 * @create 2017-10-27 
 **/
public class UUIDGeneratorUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
