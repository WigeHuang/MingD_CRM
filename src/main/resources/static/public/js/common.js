//var crmServer='http://localhost:8000/crm/';
var crmServer='';

//jqGrid的配置信息
if ($.jgrid!=null){
	$.jgrid.defaults.width = 1000;
	$.jgrid.defaults.responsive = true;
	$.jgrid.defaults.styleUI = 'Bootstrap';
}
//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false
});

function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}

//重写alert
window._alert = window.alert; //临时保存一下；






window.alert = function(msg, callback){
	if (callback==null){
		return window._alert(msg);
	}
	var ok=(vm!=null && vm.$t!=null)?vm.$t("ok"):"确定";
	var systemAlert=(vm!=null && vm.$t!=null)?vm.$t("systemAlert"):"系统消息";
	parent.layer.alert(msg,{btn: ok,title:systemAlert }, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window._confirm = window.alert; //临时保存一下；
window.confirm = function(msg, callback){
	if (callback==null){
		return window._confirm(msg);
	}
	var ok=(vm!=null && vm.$t!=null)?vm.$t("ok"):"确定";
	var cancel=(vm!=null && vm.$t!=null)?vm.$t("cancel"):"取消";
	var systemConfirm=(vm!=null && vm.$t!=null)?vm.$t("systemConfirm"):"系统消息";
	
	parent.layer.confirm(msg, {btn: [ok,cancel],title:systemConfirm},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//选择一条记录
function getSelectedRow() {
	var rowSelectAlert=(vm!=null && vm.$t!=null)?vm.$t("rowSelectAlert"):"请选择一行记录";
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert(rowSelectAlert);
    	return ;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert(rowSelectAlert);
    	return ;
    }
    
    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
	var rowSelectAlert=(vm!=null && vm.$t!=null)?vm.$t("rowSelectAlert"):"请选择一行记录";
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert(rowSelectAlert);
    	return ;
    }
    
    return grid.getGridParam("selarrrow");
}

$(window).resize(function(){      
    if ($("#jqGrid")!=null && $("#jqGrid").length==1){
    	$(window).unbind("onresize");  
    	$("#jqGrid").setGridHeight($(window).height() - 150);
    	$(window).bind("onresize", this);  
    }   
});

function getDefaultLocale(){
	var type=navigator.appName;
	var lang ;
	if (type=="Netscape"){
	    lang = navigator.language
	}else{
	    lang = navigator.userLanguage
	}
	//取得浏览器语言的前两个字母
	lang = lang.substr(0,2)
	// 英语
	if (lang == "zh"){
		return "cn";	
	}else{
		return "en";	
	}
}

var global_i18n_locale=localStorage.getItem("lang")|| getDefaultLocale();

var global_i18n_cn={
			true: '是',
			false:'否',		
			query: '查询',
			add:'新增',
			edit:'修改',
			delete: '删除',
			ok:'确定',
			submit:'确定',
			back:'返回',
			cancel:'取消',
			success:'操作成功',
			systemAlert:'系统消息',
			systemConfirm:'系统消息',
			deleteConfirm:'确定要删除吗?',
			rowSelectAlert:'请选择一条记录！'
			
		 }

var global_i18n_en={
			true: 'true',
			false:'false',			
			query: 'Query',
			add:'Add',
			edit:'Edit',
			delete: 'Delete',
			ok:'Ok',
			submit:'Submit',
			back:'Back',
			cancel:'Cancel',
			success:'Success',
			systemAlert:'System Message',
			systemConfirm:'System Message',
			deleteConfirm:'Are you sure to delete?',
			rowSelectAlert:'Please select one record！'
		 }
 
