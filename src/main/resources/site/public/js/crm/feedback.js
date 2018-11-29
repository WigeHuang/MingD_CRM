var vm = new Vue({
	i18n:i18n,
	el:'#rrapp',
	data:{
		q:{
			keyname: null
		},
		states: [
		          
		          { text: '待处理(pending)', value: '100' },
		          { text: '处理中(handling)', value: '200' },
		          { text: '无效(invalid)', value: '250' },
		          { text: '已处理(solved)', value: '300' }
		        ],
		showList: true,
		showAdd: false,
		title: null,
		feedback: {},
		url:'',
		lable: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.showAdd = true;
			vm.title = vm.$t("add");
			vm.feedback = {};
		},
		handle: function (pid) {
			//alert(pid)
			//pid = getSelectedRow();
			//alert(pid)
			if(pid == null){
				return ;
			}
			vm.showList = false;
			vm.showAdd = false;
            vm.title = vm.$t("handleTitle");
            
            vm.getInfo(pid)
		},
		update: function (event) {
			var pid = getSelectedRow();
			if(pid == null){
				return ;
			}
			vm.showList = false;
			
            vm.title = vm.$t("edit");
            
            vm.getInfo(pid)
		},
		saveOrUpdate: function (event) {
			if(!$("#contentForm").valid()){
		    	 return;
			}			
			var url = vm.feedback.pid == null ? crmServer+"api/feedback/save" : "../api/feedback/update";
			$.ajax({
				type: "POST",
			    url: url,
			    headers:{'token':localStorage.getItem("token")},
			    contentType: "application/json",
			    data: JSON.stringify(vm.feedback),
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
			var pids = getSelectedRows();
			if(pids == null){
				return ;
			}
			
			confirm(vm.$t("deleteConfirm"), function(){
				$.ajax({
					type: "POST",
				    url: crmServer+"api/feedback/delete",
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
			$.get(crmServer+"api/feedback/info/"+pid, function(r){
                vm.feedback = r.feedback;
                vm.url="../attachment/download/"+r.feedback.logFile;
                 
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'keyname': vm.q.keyname,'token':localStorage.getItem("token")},
                page:page
            }).trigger("reloadGrid");
		},

	}
});

function datePicker(ctrlId){
	var locale=vm.$i18n.locale;
	 WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:locale});
}

 var lables=['submit','back'];
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
		 //var PostData={'token':localStorage.getItem("token")};
	    $("#jqGrid").jqGrid({
	        url: crmServer+'api/feedback/list?token='+localStorage.getItem("token"),
	        datatype: "json",
	        //headers:{'token':localStorage.getItem("token")},
	        colModel: [			
						{ label: 'pid', name: 'pid', index: 'pid', width: 50, key: true,hidden:true },
							{ label: vm.$t('email'), name: 'email', index: 'email', width: 80 }, 
							{ label: vm.$t('mobile'), name: 'mobile', index: 'mobile', width: 80 }, 
							{ label: vm.$t('nickname'), name: 'nickname', index: 'nick_name', width: 80 }, 		
							{ label: vm.$t('contact'), name: 'contact',  width: 80,index: 'contact', width: 80 }	,
							{ label: vm.$t('feedback'), name: 'feedback', index: 'feedback', width: 80 }, 				
							{ label: vm.$t('submitDate'), name: 'submitDate', index: 'submit_date', width: 80 }, 				
							{ label: vm.$t('handlerName'), name: 'handlerName', index: 'handlerName', width: 80 }, 				
							{ label: vm.$t('handleResult'), name: 'handleResult', index: 'handle_result', width: 80 }, 				
							{ label: vm.$t('handleDate'), name: 'handleDate', index: 'handle_date', width: 80 }	,
							{ label: vm.$t('state'), name: 'state', width: 80, formatter: function(value, options, row){
								if  (value == 100){ 
									return '<span class="label label-danger">'+vm.$t('state'+value)+'</span>';
								}else if  (value == 200){ 		
									return '<span class="label label-info">'+vm.$t('state'+value)+'</span>';
								}else if  (value == 300){ 		
									return '<span class="label label-success">'+vm.$t('state'+value)+'</span>';
								}else{
									return vm.$t('state'+value);
								}	
							}} 							
			        ],
			viewrecords: true,
			//PostData: PostData,
			height: $(window).height() - 150,  
	        rowNum: 10,
			rowList : [10,30,50],
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