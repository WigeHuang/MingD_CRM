// install plugin
Vue.use(VueI18n);
 

const i18n = new VueI18n({
	   locale: global_i18n_locale,
	   messages: {
	     cn:  jQuery.extend(global_i18n_cn , {
		       keynameHolder: '用户账号或昵称、设备名称或ID',
		       email: '邮件',
		       mobile:'手机号码',
		       nickname:'昵称',
		       userId:'用户id',
		       deviceName:'设备名称',
		       modelCode:'型号代码',
		       deviceId:'设备ID',
		       qrCode:'设备二维码',
		       city:'城市',
		       region:'地区',
		       timezone:'时区',
		       dst:'使用夏令时',
		       bindTime:'绑定时间'
		     }),	     
		  en: jQuery.extend(global_i18n_en , {	
	    	   keynameHolder: 'key word',
		       email: 'email',
		       mobile:'mobile',
		       nickname:'nickname',	       
		       userId:'userId',
		       deviceName:'deviceName',
		       modelCode:'modelCode',
		       deviceId:'deviceId',
		       qrCode:'qrCode',
		       city:'city',
		       region:'region',
		       timezone:'timezone',
		       dst:'daySaveTime',		       
		       bindTime:'bindTime'
	     })
	   }
})

 