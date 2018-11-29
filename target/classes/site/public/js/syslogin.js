//1. 定义（路由）组件。
//可以从其他文件 import 进来
const Foo = { template: '<div>foo</div>' }
const Bar = { template: '<div>bar</div>' }

//2. 定义路由
//每个路由应该映射一个组件。 其中"component" 可以是
//通过 Vue.extend() 创建的组件构造器，
//或者，只是一个组件配置对象。
//我们晚点再讨论嵌套路由。
const routes = [
{ path: '/foo', component: Foo },
{ path: '/bar', component: Bar }
]
const router = new VueRouter({
	routes: routes // （缩写）相当于 routes: routes
})


var vm = new Vue({
	router:router,
	i18n:i18n,
	el:'#rrapp',
	data:{
		username: '153277817@qq.com',
		password: '123456',
		captcha: '',
		error: false,
		errorMsg: '',
		lang: localStorage.getItem("lang")||'cn',
		isEn:((localStorage.getItem("lang")||'cn') =="en")?true:false,
		isCn:((localStorage.getItem("lang")||'cn') =="cn")?true:false,
		src: crmServer+'captcha.jpg',
		isFirstLoad:true
	},
	beforeCreate: function(){
		if(self != top){
			top.location.href = self.location.href;
		}
	},
	methods: {
		refreshCode: function(){
			this.src = crmServer+"captcha.jpg?t=" + $.now();
		},
		changeLanguage: function(lang){
	 
			if (!vm.isFirstLoad){
				localStorage.setItem("lang",lang);
				parent.location.href ='syslogin.html?num='+Math.random();
			}else{
				vm.isFirstLoad=false;
			}

		},
		login: function (event) {
			
			var data = "account="+vm.username+"&password="+vm.password;//+"&captcha="+vm.captcha;
			$.ajax({
				type: "POST",
			    url: crmServer+"api/login",
			    data: data,
			    dataType: "json",
			    success: function(result){
					if(result.code == 0){//登录成功
						var token=result.token;
						var expire=result.expire;
						localStorage.setItem("token",token);
						parent.location.href = 'sysindex.html';
					}else{
						vm.error = true;
						vm.errorMsg = result.msg;
						
						vm.refreshCode();
					}
				}
			});
		}
	}
	
});

$(".holderClz").each(function(){
	  var ctrlId=$(this).attr("id");
	  if (vm.$t(ctrlId)!=null){
		  $(this).attr('placeholder',vm.$t(ctrlId))
	  }
})
 
 
$(function () {
	//从js本地存储得到语言设置，配置到Session
	var lang=localStorage.getItem("lang")||'cn';
 
	vm.changeLanguage(lang);
})