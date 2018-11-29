var vm = new Vue({
    i18n,
	el:'#rrapp',
	data:{
		q:{
			keyname: null
		},		
		showList: true,
		title: null,
		lable: {},
		tDeskcontactTxl: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = vm.$t("add");
			vm.tDeskcontactTxl = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = vm.$t("edit");
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			if(!$("#contentForm").valid()){
		    	 return;
			}			
			var url = vm.tDeskcontactTxl.id == null ? "../tdeskcontacttxl/save" : "../tdeskcontacttxl/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tDeskcontactTxl),
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
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm(vm.$t("deleteConfirm"), function(){
				$.ajax({
					type: "POST",
				    url: "../tdeskcontacttxl/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
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
		getInfo: function(id){
			$.get("../tdeskcontacttxl/info/"+id, function(r){
                vm.tDeskcontactTxl = r.tDeskcontactTxl;
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

 
 vm.$data.lable['ok']=vm.$t('ok');
 vm.$data.lable['back']=vm.$t('back');
 
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
	        url: '../tdeskcontacttxl/list',
	        datatype: "json",
	        colModel: [			
							{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
							{ label: '', name: 'name', index: 'NAME', width: 80 }, 				
							{ label: '', name: 'address', index: 'ADDRESS', width: 80 }, 				
							{ label: '', name: 'tel', index: 'TEL', width: 80 }, 				
							{ label: '', name: 'contactname', index: 'CONTACTNAME', width: 80 }, 				
							{ label: '', name: 'ccode', index: 'CCODE', width: 80 }, 				
							{ label: '', name: 'email', index: 'EMAIL', width: 80 }, 				
							{ label: '', name: 'bz', index: 'BZ', width: 80 }, 				
							{ label: '', name: 'floorname', index: 'FLOORNAME', width: 80 }, 				
							{ label: '', name: 'deptname', index: 'DEPTNAME', width: 80 }				
			        ],
			viewrecords: true,
	        height: $(window).height() - 150,  
	        rowNum: 10,
			rowList : [10,20,30,50],
	        rownumbers: true, 
	        rownumWidth: 25, 
	        autowidth:true,
	        multiselect: true,
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