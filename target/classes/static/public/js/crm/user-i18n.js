// install plugin
Vue.use(VueI18n);
 

const i18n = new VueI18n({
	   locale: global_i18n_locale,
	   messages: {
	     cn:  jQuery.extend(global_i18n_cn , {
		       keynameHolder: '账号或昵称',
		       email: '邮件',
		       mobile:'手机号码',
		       nickName:'昵称',
		       status:'状态',
		       avatar:'头像',
		       registerRegion:'注册地区',
		       registerTime:'注册时间',
		       operator:'操作',
		       inactive:'未激活',
		       active:'正常',
		       Reactivate:'重新激活',
		       Inactivate:'禁止使用',
		       changeStatusConfirm:'确定要修改状态吗?',
		       statusHolder:'0-未激活 1-激活',
		       emailHolder:'电子邮箱',
		       device:'设备',
		       detail:'详情',
		       deviceName:'设备名称',
		       modelCode:'型号代码',
		       deviceId:'设备Id',
		       bindTime:'绑定时间',
		       loginTime:'最后登录时间',
		       regionHolder:'CN-中国 USA-美国'
		     }),	     
		  en: jQuery.extend(global_i18n_en , {	
	    	   keynameHolder: 'key word',
	    	   email: 'email',
		       mobile:'mobile',
		       nickName:'nickname',
		       status:'status',
		       avatar:'avatar',
		       registerRegion:'registerRegion',
		       registerTime:'registerTime',
		       loginTime:'lastLogin',
		       operator:'Action',
		       inactive:'inactive',
		       active:'active',
		       Reactivate:'Reactivate',
		       Inactivate:'Inactivate',	    
		       changeStatusConfirm:'Are you sure to change the status?',
		       statusHolder:'0-inactive 1-active',
		       emailHolder:'email',
		       device:'device',
		       detail:'detail',
		       deviceName:'deviceName',
		       modelCode:'modelCode',
		       deviceId:'deviceId',
		       bindTime:'bindTime',
		       regionHolder:'CN,USA' 
	       })
	   }
})