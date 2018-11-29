// install plugin
Vue.use(VueI18n);
 



const i18n = new VueI18n({
	   locale: global_i18n_locale,
	   messages: {
	     cn:  jQuery.extend(global_i18n_cn , {
		       keynameHolder: '用户信息或反馈内容',
		       uid:'用户id',
		       handleTitle:'处理反馈',
		       mobile:'手机号码',
		       email: '邮件',
		       nickname:'昵称',
		       feedback:'反馈内容',
		       submitDate:'反馈日期',
		       handler:'处理人',
		       handle:'处理',
		       handlerName:'处理人',
		       handleResult:'处理结果',
		       handleDate:'处理日期',
		       contact:'联系方式',
		       log:'系统日志',  
		       logFile:'日志文件',
		       state:'状态',
		       state100:'待处理',
		       state200:'处理中',
		       state250:'无效',
		       state300:'已处理',		       
		       effecitve:'是否有效'
		     }),	     
	     en: jQuery.extend(global_i18n_en , {	
	    	   keynameHolder: 'user info or feedback content',
		       uid:'userId',
		       handleTitle:'Handle Record',
		       mobile:'mobile',
		       email: 'email',
		       nickname:'nickname',
		       feedback:'feedback',
		       submitDate:'submitDate',
		       handler:'handler',
		       handle:'Handle',
		       handlerName:'handler',
		       handleResult:'handleResult',
		       handleDate:'handleDate',
		       contact:'contactInfo',
		       log:'APP log',
		       logFile:'log file',
		       state:'state',	
		       state100:'pending',
		       state200:'handling',
		       state250:'invalid',
		       state300:'solved',
		       effecitve:'valid'
	         })
	   }
})



 