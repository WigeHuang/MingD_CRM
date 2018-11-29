package com.mindao.utils.interceptor;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
/* 版权所有： 广州敏道科技有限公司
/*
/* 功能描述：访问地址log打印
/*
/* 创 建 人：李国才
/* 创建时间：2016年7月17日 下午12:27:26 	
 **/
@Component//("accessLogInterceptor")
public class AccessLogInterceptor extends HandlerInterceptorAdapter{  
	static Logger log = LoggerFactory.getLogger(AccessLogInterceptor.class);

	
	@Override    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url =request.getRequestURL().toString();
		log.info("访问地址："+url+"  ["+request.getMethod()+"]");
		Map<String, String[]> map =request.getParameterMap();
		if (map.size()>0){
			StringBuffer param=new StringBuffer("");
			for (java.util.Map.Entry entry :map.entrySet()){
				param.append("&"+(String)entry.getKey()+"=");
				String[] val=(String[])entry.getValue();
				if (val.length==1){
					param.append(val[0]);
				}else{
					param.append(Arrays.toString(val));
				}
			}
			log.info("访问参数："+param.toString());
		}
		return true;
	}
	
}
