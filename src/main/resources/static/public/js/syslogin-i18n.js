// install plugin
Vue.use(VueI18n);

const i18n = new VueI18n({
	   locale: global_i18n_locale,
	   messages: {
	     cn:  jQuery.extend(global_i18n_cn , {
		       welcome: '欢迎登录',
		       title:'客户关系管理系统',
		       languageSelect:'语言',
		       login:'登录',
		       account:'账号',
		       password:'密码',
		       captcha:'验证码',
		       refresh:'点击刷新'
		     }),	     
	     en:  jQuery.extend(global_i18n_en , {
		       welcome: 'welcome',
		       title:'CRM',
		       languageSelect:'Language',
		       login:'Log In',
		       account:'account',
		       password:'password',
		       captcha:'captcha',		       
		       refresh:'Refresh'
	     	})
	   }
})