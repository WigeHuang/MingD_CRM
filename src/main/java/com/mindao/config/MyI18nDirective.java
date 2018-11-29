package com.mindao.config;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
/* 版权所有： 广州敏道科技有限公司
/*
/* 功能描述：
 * 参考地址：http://osacar.iteye.com/blog/1059421
/*
/* 创 建 人：李国才
/* 创建时间：2016年9月23日 下午10:54:24 	
 **/
@Component 
public class MyI18nDirective  implements TemplateDirectiveModel {
//	@Autowired
//	private BaseService baseService;
	
	private String languageSelect;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		//languageSelect = (String) EaCommonUtils.getSessionObject(EaConstant.SESSION_LANG);
		languageSelect=(languageSelect==null)?"cn":languageSelect;
		for (TemplateModel templateModel : loopVars) {
			templateModel.toString();
		}

		// 真正开始处理输出内容
		Writer out = env.getOut();

		if (body != null) {
			out.write(getContent(params));
			body.render(out);
		}
	}  
	/**
	 * 实际生产环境，可考虑分布缓存中读取
	 * @param params
	 * @return
	 */
	private String getContent(Map<?,?> params) {  
        String key =  params.get("key").toString();  
        if (null != key) {  
            //List<String> dataLIst = this.baseService.find("select "+ languageSelect+"Val from I18nData where myKey=?",key);
            //return dataLIst.isEmpty()?key:dataLIst.get(0);
        	return "我的测试";
        } else{ 
        	return "aaaaaa";
        }
        //return null;  
    }  
}
