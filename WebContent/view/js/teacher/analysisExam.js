  // 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { 
	var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}  

// 初始化
var pageCount = 1;// 总页数
var pageSize = 2;// 每页显示多少条数据
var pageCurrent = 1;// 当前页数
var itemCount = ""// 总计路数
var condition = "";// 分页条件

var tr = "";
var exams = "";
var types = "";
var courses = "";

var myOption={
	title : {
		text: '学生成绩分布图',
		subtext: '柱状图'
	},
	tooltip: {
		show: true
	},
	legend: {
		data:['人数']
	},
	toolbox: {
		show : true,
		feature : {
			mark : {show: true},
			dataView : {show: true, readOnly: true},
			magicType : {show: true, type: ['line', 'bar']},
			restore : {show: true},
			saveAsImage : {show: true}
		}
	},
	xAxis : [
	{
		type : 'category',
		data : ["0-60","60-70","70-80","80-90","90-100"]
	}
	],
	yAxis : [
	{
		type : 'value'
	}
	],
	series : [
	{
		name:"成绩",
		type:"bar",
		data:[5, 15, 20, 8,2],
		markPoint : {
			symbol:'emptyCircle',
			symbolSize : function (v){
				return 10 + v/10
			},
			effect : {
				show: true,
				shadowBlur : 0
			},
			itemStyle:{
				normal:{
					label:{show:false}
				},
				emphasis: {
					label:{position:'top'}
				}
			},
			data : [
			{name : '最高成绩', value : 98, xAxis: 4, yAxis: 2, symbolSize:18},
			{name : '最低成绩', value : 29, xAxis: 0, yAxis: 5}
			]
		},
		markLine : {
			effect : {
				show: true,
				shadowBlur : 0
			},
			data : [
			[
			{name: '平均分', value: 98, xAxis: 0, yAxis: 10},      
			{name: '平均分', xAxis: 4, yAxis: 10},             
			]
			]
		}
	}
	]
};
jQuery(document).ready(
	function($) {
			// 加载用户信息
			$.ajax({
				url : '/ExamOnline/getTeacherInfoFromSession.do',
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						var teacher = data.teacher;
						$(".info .name").text(teacher.name);
					}
				}
			});
			//加载班级
			$.ajax({
				url: '/ExamOnline/getAllClass.do',
				type: 'post',
				dataType: 'json',
				success:function(data){
					if (data.code==200) {
						$.each(data.classes, function(index, val) {
							var option=$("<option></option>");
							option.text(val.name);
							option.attr('value', val.id);
							$("#class").append(option);
							$("#addExam [name='grade']").append(option.clone());
							$("#upExam [name='grade']").append(option.clone());
						});
					}
				}
			});
			//加载所有试卷
			$.ajax({
				url: '/ExamOnline/getAllPage.do',
				type: 'post',
				dataType: 'json',
				success:function(data){
					$.each(data.pages, function(index, val) {
						var option=$("<option></option>");
						option.text(val.name);
						option.attr('value', val.id);
						$("#addExam [name='pageId']").append(option);
						$("#upExam [name='pageId']").append(option.clone());
					});
				}
			});
			
			// 克隆模板
			tr = $(".exam").clone();
			// 删除模板
			$(".exam").remove();
			// 查询数据
			initSearch(1);
			$("#pageCount").text(pageCount);

			// 搜索
			$("#search").click(function(event) {
				condition=getFormFileds("searchCondition");;
				$("#firstPage").unbind("click");
				$("#beforPage").unbind('click');
				$("#nextPage").unbind('click');
				$("#lastPage").unbind('click');
				initSearch(1);
			});
			// 跳转
			$("#turn").click(function(event) {
				var pageNum = $("#pageNum").val();
				if (pageNum > pageCount) {
					alert("没有这么多页！");
				} else {
					initSearch(pageNum - 0);
					$(".active a").text(pageNum);
				}
			});	
		});

function initSearch(p) {
	$("#firstPage").unbind("click");
	$("#beforPage").unbind('click');
	$("#nextPage").unbind('click');
	$("#lastPage").unbind('click');
	$(".update").unbind('click');
	$(".delete").unbind('click');
	pageCurrent = p;
	$(".active a").text(pageCurrent);
	var comcondition = {};
	if (condition != "") {
		comcondition = JSON.parse(JSON.stringify(condition));
		comcondition.pageCurrent=p;
		comcondition.pageSize=pageSize;
		comcondition.flag=2;
	} else {
		comcondition.pageCurrent=p;
		comcondition.pageSize=pageSize;
		comcondition.flag=2;
	}
	$.ajax({
		url : '/ExamOnline/getExamByPageIndex.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : comcondition,
		success : function(data) {
			exams = data.exams;
			$(".exam").remove();
			if (data.code == 200) {
				$.each(data.exams, function(index, val) {
					var tr1 = tr.clone();
					tr1.find('.id').text(val.id);
					tr1.find('.name').text(val.name);
					var time=new Date(val.time).Format("yyyy-MM-dd HH:mm");
					tr1.find('.time').text(time);
					tr1.find('.grade').text(val.grade);
					tr1.find('.flag').text(val.flag==0?'未考':(val.flag==1?'未评分':'已结束'));
					$('#examtable tbody').append(tr1);
				});
				pageCount = data.page.pageCount;
				itemCount=data.page.itemCount;
				$("#pageCount").text(pageCount);
				$("#itemCount").text(itemCount);
			}
		}
	});
	// 首页
	$("#firstPage").click(
		function(event) {
			comcondition.pageCurrent=1;
			comcondition.pageSize=pageSize;
			comcondition.flag=2;
			pageCurrent = 1;
			initSearch(1);
		});
	// 上一页
	$("#beforPage").click(
		function(event) {
			if (pageCurrent > 1) {
				pageCurrent -= 1;
				comcondition.pageCurrent=pageCurrent;
				comcondition.pageSize=pageSize;
				comcondition.flag=2;
				initSearch(pageCurrent);
			}
		});
	// 下一页
	$("#nextPage").click(
		function(event) {
			if ((pageCurrent - 0) < pageCount) {
				pageCurrent += 1;
				comcondition.pageCurrent=pageCurrent;
				comcondition.pageSize=pageSize;
				comcondition.flag=2;
				initSearch(pageCurrent);
			}
		});
	// 尾页
	$("#lastPage").click(
		function(event) {
			comcondition.pageCurrent=pageCount;
			comcondition.pageSize=pageSize;
			comcondition.flag=2;
			pageCurrent = pageCount;
			initSearch(pageCount);
		});
	// 评分
	$(".analysis").click(
		function(event) {
			var examId = $(this).parent().siblings('.id').text();
			var name = $(this).parent().siblings('.name').text();
			var time = $(this).parent().siblings('.time').text();
			var grade = $(this).parent().siblings('.grade').text();
			var flag = $(this).parent().siblings('.flag').text()
			var pageId=getPageIdByExamId(examId);
			var pageName=getPageNameById(pageId);
			$("#examId").text(examId);
			$("#name").text(name);
			$("#time").text(time);
			$("#grade").text(grade);
			$("#flag").text(flag);
			$("#pageName").text(pageName);
			$.ajax({
				url: '/ExamOnline/getScoreByGradeAndExam.do',
				type: 'post',
				dataType: 'json',
				data: {"examId":examId,"grade":grade},
				success:function(data){
					var result=analysis(data.scores);
					myOption.series[0].data=[result.num1,result.num2,result.num3,result.num4,result.num5];
					myOption.series[0].markPoint.data[0].value=result.maxScore;
					myOption.series[0].markPoint.data[1].value=result.minScore;
					myOption.series[0].markLine.data[0][0].value=result.averageScore;
					myOption.series[0].markPoint.data[0].yAxis=result.num5;
					myOption.series[0].markPoint.data[1].yAxis=result.num1;
					$("#studentNum").text(data.studentNum);
					$("#scoreNum").text(result.scoreNum);
				}
			});
			echars();
			$('#infoModal').modal('show');
		});
	
}

//根据考试id得到试卷id
function getPageIdByExamId(id) {
	var pageId = "";
	$.each(exams, function(index, val) {
		if (val.id == id) {
			pageId = val.pageId;
		}
	});
	return pageId;
}
//根据试卷id获得试卷名
function getPageNameById(id){
	var pageName="";
	$.ajax({
		url: '/ExamOnline/getPageById.do',
		type: 'post',
		dataType: 'json',
		async:false,
		data: {'id': id},
		success:function(data){
			if (data.code==200) {
				pageName=data.page.name;
			}
		}
	});
	return pageName;
}
function analysis(scores){
	var result={};
	var scoreNum=0;
	var maxScore=scores[0].score;
	var minScore=scores[0].score;
	var averageScore=0;
	var totalScore=0;
	var num1=0;
	var num2=0;
	var num3=0;
	var num4=0;
	var num5=0;
	$.each(scores, function(index, val) {
		if (val.score<60) {
			num1++;
		}else if(val.score<70){
			num2++;
		}else if(val.score<80){
			num3++;
		}else if(val.score<90){
			num4++;
		}else if(val.score<100){
			num5++;
		}
		totalScore+=val.score;
		scoreNum++;
		if (maxScore<val.score) {
			maxScore=val.score;
		}
		if (minScore>val.minScore) {
			minScore=val.score;
		}
	});
	averageScore=totalScore/scoreNum;
	result.maxScore=maxScore;
	result.minScore=minScore;
	result.averageScore=averageScore;
	result.scoreNum=scoreNum;
	result.num1=num1;
	result.num2=num2;
	result.num3=num3;
	result.num4=num4;
	result.num5=num5;
	return result;
}
function echars(){
	// 路径配置
	require.config({
		paths: {
			echarts: 'http://echarts.baidu.com/build/dist'
		}
	});

        // 使用
        require(
        	[
        	'echarts',
                'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/pie'
                ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('echarsView')); 
                
                var option = myOption;
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );
    }
