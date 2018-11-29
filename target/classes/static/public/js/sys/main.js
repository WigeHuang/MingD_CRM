	var vm = new Vue({
		i18n:i18n,
		el:'#rrapp',
		data:{
			thisMonthUser:0,
			allUser:0,
			thisMonthDevice:0,
			allDevice:0,
			thisMonthFd:0,
			allFd:0
			
		},
		beforeCreate: function(){
 
		},
		methods: {
			 
		}
		
	});
	
	function drawChart(monthArr,userArr,deviceArr,fdArr){
		 // 基于准备好的dom，初始化echarts实例
		var locale=vm.$i18n.locale;
 
	    var userChart = echarts.init(document.getElementById("userChart"));
	
	    // 指定图表的配置项和数据
		option = {
		    title : {
		        text:vm.$i18n.messages[locale].lastHalfMonthAdd,
		        subtext: ''
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:[vm.$i18n.messages[locale].regUser,vm.$i18n.messages[locale].bindDevice,vm.$i18n.messages[locale].feedback]
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data :monthArr // ['周一','周二','周三','周四','周五','周六' ]
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} '
		            }
		        }
		    ],
		    series : [
		        {
		            name:vm.$i18n.messages[locale].regUser,
		            type:'line',
		            data:userArr 
		        },
		        {
		            name:vm.$i18n.messages[locale].bindDevice,
		            type:'line',
		            data:deviceArr 
		        },
		        {
		            name:vm.$i18n.messages[locale].feedback,
		            type:'line',
		            data:fdArr
		        }				        
		    ]
		};
	
	    // 使用刚指定的配置项和数据显示图表。
	    userChart.setOption(option);
	}
	
	
	 $(function () {
		 $.get("../crm/dashboard/stat/", function(r){
			 vm.thisMonthUser=r.data.thisMonthUser;
			 vm.allUser=r.data.allUser;
			 
			 vm.thisMonthDevice=r.data.thisMonthDevice;
			 vm.allDevice=r.data.allDevice;
			 
			 
			 vm.thisMonthFd=r.data.thisMonthFd;
			 vm.allFd=r.data.allFd;
			 
			 drawChart(r.data.last6Month,r.data.last6MonthUser,r.data.last6MonthDevice,r.data.last6MonthFd)
		 })
		
	 })