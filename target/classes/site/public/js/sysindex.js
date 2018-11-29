//生成菜单
var menuItem = Vue.extend({
	name: 'menu-item',
	props:{item:{}},
	template:[
	          '<li>',
	          '<a v-if="item.type === 0" href="javascript:;">',
	          '<i v-if="item.icon != null" :class="item.icon"></i>',
	          '<span>{{item.name}}</span>',
	          '<i class="fa fa-angle-left pull-right"></i>',
	          '</a>',
	          '<ul v-if="item.type === 0" class="treeview-menu">',
	          '<menu-item :item="item" v-for="item in item.list"></menu-item>',
	          '</ul>',
	          '<a v-if="item.type === 1" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
	          '</li>'
	].join('')
});

//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

//注册菜单组件
Vue.component('menuItem',menuItem);

var vm = new Vue({
	i18n:i18n,
	el:'#rrapp',
	data:{
		user:{},
		menuList:{},
		main:"sys/main.html",
		password:'',
		newPassword:'',
		newPasswordAgain:'',
        navTitle:''
	},
	methods: {
		getMenuList: function (event) {
			//$.getJSON(crmServer+"api/user/menu?_"+$.now(), function(r){
			//	vm.menuList = r.menuList;
            //    window.permissions = r.permissions;
			//});
			var data = "";
			$.ajax({
				type: "POST",
			    url: crmServer+"api/user/menu?_"+$.now(),
			    headers:{'token':localStorage.getItem("token")},
			    data: data,
			    dataType: "json",
			    success: function(result){
					if(result.code == 0){
						vm.menuList = result.menuList;
					}else{
						layer.alert(result.msg);
					}
				}
			});				
		},
		getUser: function(){
			var data = "";
			$.ajax({
				type: "POST",
			    url: crmServer+"api/user/info?_"+$.now(),
			    headers:{'token':localStorage.getItem("token")},
			    data: data,
			    dataType: "json",
			    success: function(result){
					if(result.code == 0){
						vm.user = result.user;
					}else{
						layer.alert(result.msg);
					}
				}
			});			
		},
		logout: function(){
			var data = "";
			$.ajax({
				type: "POST",
			    url: crmServer+"api/logout?_"+$.now(),
			    headers:{'token':localStorage.getItem("token")},
			    data: data,
			    dataType: "json",
			    success: function(result){
					if(result.code == 0){
						window.location="syslogin.html";
					}else{
						layer.alert(result.msg);
					}
				}
			});				
			 		
		},		
		updatePassword: function(){
			layer.open({
				type: 1,
				//skin: 'layui-layer-molv',
				title: vm.$t("modifyPwd"),
				area: ['550px', '320px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: [vm.$t("submit"),vm.$t("cancel")],
				btn1: function (index) {
					var locale=vm.$i18n.locale;
 
					if (vm.newPassword!=vm.newPasswordAgain){
						layer.alert(vm.$i18n.messages[locale].differentPwd);
						return;
					}
					
					var data = "password="+vm.password+"&newPassword="+vm.newPassword;
					$.ajax({
						type: "POST",
					    url: crmServer+"api/user/updatePassword",
					    headers:{'token':localStorage.getItem("token")},
					    data: data,
					    dataType: "json",
					    success: function(result){
							if(result.code == 0){
								layer.close(index);
								layer.alert(vm.$t("success"), function(index){
									location.reload();
								});
							}else{
								layer.alert(result.msg);
							}
						}
					});
	            }
			});
		}
	},
	created: function(){
		this.getMenuList();
		this.getUser();
	},
	updated: function(){
		//路由
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	}
});

vm.navTitle=vm.$t("console");


function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.type == 0){
			routerList(router, menu.list);
		}else if(menu.type == 1){
			router.add('#'+menu.url, function() {
				var url = window.location.hash;
				
				//替换iframe的url
			    vm.main = url.replace('#', '');
			    
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
			    
			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}
