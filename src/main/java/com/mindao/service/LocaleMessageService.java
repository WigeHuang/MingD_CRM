package com.mindao.service;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
/**
 * I18n设置语言和读取资源文件工具类
 * 资源文件放在classpath根目录的messages.properties   messages_en_US.properties messages_zh_CN.properties 中，缺省messages.properties不可少。
 * @author ligc
 *
 */
@Component
public class LocaleMessageService {
	
    @Resource
    private MessageSource messageSource;
   
    /**
     * @param code ：对应messages配置的key.
     * @return
     */
    public String getMessage(String code){
       return getMessage(code,null);
    }
   
    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String code,Object[] args){
       return getMessage(code, args,"");
    }
   
 
    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
       //这里使用比较方便的方法，不依赖request.
       Locale locale = LocaleContextHolder.getLocale();
       return messageSource.getMessage(code, args, defaultMessage, locale);
    }
    
    public void setLocale(HttpServletRequest request,String language){
    	request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("zh", "CN")); 
        if("en".equals(language)){ 
            //代码中即可通过以下方法进行语言设置
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en", "US")); 
        }
    }
    public Locale getLocale(HttpServletRequest request){
    	Locale locale = (Locale)request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    	if (locale==null){
    		locale = LocaleContextHolder.getLocale();
    	}
    	return locale;
    }
}