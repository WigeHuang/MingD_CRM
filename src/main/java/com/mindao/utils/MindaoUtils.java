package com.mindao.utils;

import java.util.UUID;

public class MindaoUtils {
	public static String getUuid(){
		//生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
}
