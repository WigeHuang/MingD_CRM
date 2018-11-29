package com.mindao.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;  
  
@Configuration  
public class FreemarkerConfig {  
    @Autowired    
    protected com.mindao.config.MyI18nDirective myI18nDirective;   
    @Autowired    
    protected freemarker.template.Configuration configuration;    
    @Autowired    
    protected org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver resolver;    
    @Autowired    
    protected org.springframework.web.servlet.view.InternalResourceViewResolver springResolver;    
    @Autowired    
    protected  org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer freeMarkerConfigurer;  
 
      
    @PostConstruct    
    public void  setSharedVariable(){    
        resolver.setSuffix(".ftll");     
        resolver.setCache(false);   
        resolver.setRequestContextAttribute("request"); //为模板调用时，调用request对象的变量名</span>    
        resolver.setOrder(0);    
        resolver.setExposeRequestAttributes(true);  
        resolver.setExposeSessionAttributes(true);  
        
        //configuration.setSharedVariable("i18n", myI18nDirective);
        Map fv=new HashMap();
        fv.put("i18n", myI18nDirective);
        freeMarkerConfigurer.setFreemarkerVariables(fv);
        
    }    
 
 

}