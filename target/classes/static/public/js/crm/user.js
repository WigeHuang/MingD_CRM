
function changeStatus (uid,status) {
	vm.changeStatus(uid,status);
}	
 

var vm = new Vue({
	i18n:i18n,
	el:'#rrapp',
	data:{
		q:{
			keyname: null
		},		
		showList: true,
		showDetail: false,
		showDeviceList:false,
		showAvatar:false,
		title: null,
		user: {} ,
		devices:{},
		lable: {} 
	},
	methods: {
		openDevice:function(uid){
			//vm.showDeviceList = false;
			//iframe窗

	 
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

		    $("#deviceGrid").jqGrid({
		        url: '../crm/userdevice/list?uid='+uid,
		        datatype: "json",
		        colModel: [			
					{ label: 'pid', name: 'pid', index: 'pid', width: 50, key: true,hidden:true },
					{ label: vm.$t('userId'), name: 'userId', index: 'user_id', width: 80 ,hidden:true},
					{ label: vm.$t('email'), name: 'email', index: 'email', width: 80 }, 
					{ label: vm.$t('mobile'), name: 'mobile', index: 'mobile', width: 80 }, 
					{ label: vm.$t('nickname'), name: 'nickname', index: 'nickname', width: 80 }, 
					{ label: vm.$t('deviceName'), name: 'deviceName', index: 'device_name', width: 80 }, 			
					{ label: vm.$t('modelCode'), name: 'modelCode', index: 'model_code', width: 80 }, 			
					{ label: vm.$t('deviceId'), name: 'deviceId', index: 'device_id', width: 80 }, 			
					//{ label: vm.$t('qrCode'),name: 'qrCode', index: 'qr_code', width: 80 }, 			
					{ label: vm.$t('bindTime'), name: 'bindTime', index: 'bind_time', width: 80 }, 	
					{ label: vm.$t('loginTime'), name: 'loginTime', index: 'login_time', width: 80 }
					/**
					{ label: vm.$t('delete'), name: 'delete',  width: 80,formatter:function(value, options, row){
						return  '<a onclick="vm.del(\''+row.pid+'\')" href="javascript:">'+vm.$t('delete')+'</a>';
					}}	
					**/			
		        ],
				viewrecords: true,
		        //height: $(window).height() - 150,  
		        rowNum: 100,
				rowList : [10,20,30,50],
		        rownumbers: true, 
		        rownumWidth: 25, 
		        autowidth:true,
		        multiselect: false,
		        //pager: "#jqGridPager",
		        jsonReader : {
		            root: "page.list",
		            page: "page.currPage",
		            total: "page.totalPage",
		            records: "page.totalCount"
		        },
		        prmNames : {
		            page:"page", 
		            rows:"limit", 
		            order: "order"
		        },
		        gridComplete:function(){
		        	 
		        	var html=$("#deviceGridParent").html().replace('读取中...','');
		        	//隐藏grid底部滚动条
		        	$("#deviceGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
					//iframe窗
					layer.open({
						  type: 1,
						  skin: 'layui-layer-rim', //加上边框
						  area: ['600px', '360px'], //宽高
						  content: html
					});
		        }
		    });
		    
		    
		    

 				
		},
		closeDevice:function(){
			
		},	
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = vm.$t("add");
			vm.user = {};
		},
		detail: function(uid){
			var encodeUid = encodeURIComponent(uid);
			 
			$.get("../crm/user/detail/"+encodeUid, function(r){
                vm.user = r.user;
                vm.showAvatar=( vm.user.avatar!=null && vm.user.avatar!="" )?true:false;
            });
			vm.showList = false;
			vm.showDetail = true;
			vm.title = vm.$t("detail");
			
 
		},		
		update: function (event) {
			var uid = getSelectedRow();
			if(uid == null){
				return ;
			}
			vm.showList = false;
            vm.title = vm.$t("edit");
            
            vm.getInfo(uid)
		},
		saveOrUpdate: function (event) {
			if(!$("#contentForm").valid()){
		    	 return;
			}			
			var url = vm.user.uid == null ? "../crm/user/save" : "../crm/user/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	if(r.code === 0){
						alert(vm.$t("success"), function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var uids = getSelectedRows();
			if(uids == null){
				return ;
			}

			confirm(vm.$t("deleteConfirm"), function(){
				$.ajax({
					type: "POST",
				    url: "../crm/user/delete",
				    contentType: "application/json",
				    data: JSON.stringify(uids),
				    success: function(r){
						if(r.code == 0){
							alert(vm.$t("success"), function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},

		changeStatus: function (uid,status) {
			confirm(vm.$t('changeStatusConfirm'), function(){
				$.ajax({
					type: "POST",
				    url: "../crm/user/updateStatus",
				    dataType: "json",
				    data: {"uid":uid,"status":status},
				    success: function(r){
						if(r.code == 0){
							alert(vm.$t("success"), function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},	
		
		getInfo: function(uid){
	 
			var encodeUid = encodeURIComponent(uid);
			 
			$.get("../crm/user/info/"+encodeUid, function(r){
                vm.user = r.user;
                vm.showAvatar=( vm.user.avatar!=null && vm.user.avatar!="" )?true:false;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'keyname': vm.q.keyname},
                page:page
            }).trigger("reloadGrid");
			
		},
		returnList: function (event) {
			vm.showList = true;
			vm.showDetail = false;
		}	
	}
});
 

 var lables=['ok','back'];
 for (var i=0;i<lables.length;i++){
	 var attr=lables[i];
	 vm.$data.lable[attr]=vm.$t(attr);
 }
 $(".holderClz").each(function(){
	  var ctrlId=$(this).attr("id")+"Holder";
	  if (vm.$t(ctrlId)!=null){
		  $(this).attr('placeholder',vm.$t(ctrlId))
	  }
 })
 
 
 $(function () {
	 var locale=vm.$i18n.locale;
	 var gridScriptUrl='../public/plugins/jqgrid/grid.locale-'+locale+'.js';
	 if (locale=='cn'){
	 	 var validScriptUrl='../public/plugins/jquery-validate-1.13.0/jquery.validate.'+locale+'.js';
		 $.getScript(validScriptUrl).done(function(script, textStatus) {
		 })
		 .fail(function(jqxhr, settings, exception) {
		   $( "div.log" ).text( "Triggered ajaxError handler." );
		 });
	 }
	 $.getScript(gridScriptUrl).done(function(script, textStatus) {
		    if (textStatus=="success"){
			    $("#jqGrid").jqGrid({
			        url: '../crm/user/list',
			        datatype: "json",
			        colModel: [			
						{ label: 'uid', name: 'uid', index: 'uid',   key: true ,hidden:true},
						
						{ label: vm.$t('email'), name: 'email', index: 'email', width: 80 }, 			
						{ label: vm.$t('mobile'), name: 'mobile', index: 'mobile', width: 80 }, 
						 
						{ label: vm.$t('avatar'), name: 'avatar' , width: 80 , formatter: function(value, options, row){
							return (row.avatarPath!=null && row.avatarPath != "" && row.avatarPath != "null") ? 
									'<img src="'+row.avatarPath+'" width="50px" height="50px"></img>' : 
									'';
						}},
						 			
						{ label: vm.$t('nickName'), name: 'nickName', index: 'nick_name', width: 80 }, 					
						{ label: vm.$t('registerTime'), name: 'registerTime', index: 'register_time', width: 80 },
						{ label: vm.$t('loginTime'), name: 'loginTime', index: 'login_time', width: 80 }, 	
						//{ label: vm.$t('registerRegion'), name: 'registerRegion', index: 'register_region', width: 80 }, 
						{ label: vm.$t('status'), name: 'status', width: 80, formatter: function(value, options, row){
							return value == 0 ? 
								'<span class="label label-danger">'+vm.$t('inactive')+'</span>' : 
								'<span class="label label-success">'+vm.$t('active')+'</span>';
						}},
						{ label: vm.$t('operator'), name: 'status',  width: 80,formatter:function(value, options, row){
							return value == 0 ? 
									'<a onclick="changeStatus(\''+row.uid+'\',1)" href="javascript:">'+vm.$t('Reactivate')+'</a>' : 
									'<a onclick="changeStatus(\''+row.uid+'\',0)" href="javascript:">'+vm.$t('Inactivate')+'</a>';
						}},
						//{ label: vm.$t('device'), name: 'device',  width: 80,formatter:function(value, options, row){
						//	return  '<a onclick="vm.openDevice(\''+row.uid+'\',0)" href="javascript:">'+vm.$t('device')+'</a>';
						//}},
						{ label: vm.$t('detail'), name: 'detail',  width: 80,formatter:function(value, options, row){
							return  '<a onclick="vm.detail(\''+row.uid+'\')" href="javascript:">'+vm.$t('detail')+'</a>';
						}}
						
			        ],
					viewrecords: true,
			        height: $(window).height() - 150,  
			        rowNum: 10,
					rowList : [10,20,30,50],
			        rownumbers: true, 
			        rownumWidth: 25, 
			        autowidth:true,
			        multiselect: false,
			        pager: "#jqGridPager",
			        jsonReader : {
			            root: "page.list",
			            page: "page.currPage",
			            total: "page.totalPage",
			            records: "page.totalCount"
			        },
			        prmNames : {
			            page:"page", 
			            rows:"limit", 
			            order: "order" 
			        },
			        gridComplete:function(){
			        	//隐藏grid底部滚动条
			        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
			        	//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-y" : "hidden" }); 
			        	//$("#jqGrid").("auto");
			        	//alert($("#jqGrid").attr("height"));
			        }
			    });
		    }
	 })
	 .fail(function(jqxhr, settings, exception) {
	   $( "div.log" ).text( "Triggered ajaxError handler." );
	 });
	 

});