Vue.use(VueI18n)

const i18n = new VueI18n({
	   locale: global_i18n_locale,
	   messages: {
	    	 cn:  jQuery.extend(global_i18n_cn , {
 
			       welcome: '欢迎您',
			       title:'控制台',
			       lastHalfMonthAdd:'最近半年新增',
			       regUser:'注册用户',
			       bindDevice:'绑定设备',
			       feedback:'用户反馈',
			       thisMonth:'本月',
			       add:'新增',
			       sum:'总数' 			       
			     }),	     
			   en:  jQuery.extend(global_i18n_en , {
			       welcome: 'Welcome you',
			       title:'Console',
			       lastHalfMonthAdd:'Increase in last 6 months',
			       regUser:'Users',
			       bindDevice:'Devices',
			       feedback:'Feedbacks',
			       thisMonth:'This Month',
			       add:'Increase',
			       sum:'Sum'
			      
		       } )
		   }
	})