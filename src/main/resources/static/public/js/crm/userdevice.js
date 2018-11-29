

var vm = new Vue({
	i18n:i18n,
	el:'#rrapp',
	data:{
		q:{
			keyname: null
		},		
		showList: true,
		title: null,
		userDevice: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = vm.$t("add");;
			vm.userDevice = {};
		},
		update: function (event) {
			var pid = getSelectedRow();
			if(pid == null){
				return ;
			}
			vm.showList = false;
            vm.title = vm.$t("edit");;
            
            vm.getInfo(pid)
		},
		saveOrUpdate: function (event) {
			var url = vm.userDevice.pid == null ? "../crm/userdevice/save" : "../crm/userdevice/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.userDevice),
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
		del: function (pid) {
			//var pids = getSelectedRows();
			if(pid == null){
				return ;
			}
			var pids=[];
			pids.push(pid);
			
			confirm(vm.$t("deleteConfirm"), function(){
				$.ajax({
					type: "POST",
				    url: "../crm/userdevice/delete",
				    contentType: "application/json",
				    data: JSON.stringify(pids),
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
		getInfo: function(pid){
			$.get("../crm/userdevice/info/"+pid, function(r){
                vm.userDevice = r.userDevice;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'keyname': vm.q.keyname},
                page:page
            }).trigger("reloadGrid");
		}
	}
});

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
	    $("#jqGrid").jqGrid({
	        url: '../crm/userdevice/list',
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
				
				{ label: vm.$t('city'), name: 'city', index: 'city', width: 80 }, 
				{ label: vm.$t('region'), name: 'region', index: 'region', width: 80 }, 
				{ label: vm.$t('timezone'), name: 'timezone', index: 'timezone', width: 80 }, 
				{ label: vm.$t('dst'), name: 'dst', index: 'dst', width: 80 ,formatter:function(value, options, row){
					return  (value==1)?vm.$t('true'):vm.$t('false');
				}},
				//{ label: vm.$t('qrCode'),name: 'qrCode', index: 'qr_code', width: 80 }, 			
				{ label: vm.$t('bindTime'), name: 'bindTime', index: 'bind_time', width: 80 }
				/**
				{ label: vm.$t('delete'), name: 'delete',  width: 80,formatter:function(value, options, row){
					return  '<a onclick="vm.del(\''+row.pid+'\')" href="javascript:">'+vm.$t('delete')+'</a>';
				}}	
				**/			
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
	        }
	    });
	 })
	 .fail(function(jqxhr, settings, exception) {
	   $( "div.log" ).text( "Triggered ajaxError handler." );
	 });	    
});