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

var tr = "";
var exams = "";
var types = "";
var courses = "";

var notExamStudents;
var examingStudents;
var examedStudents;
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
			
			// 克隆模板
			tr = $(".exam").clone();
			// 删除模板
			$(".exam").remove();
			studenttr=$(".student").clone();
			$(".student").remove();
			$.ajax({
				url: '/ExamOnline/getExamPage.do',
				type: 'post',
				async:false,
				dataType: 'json',
				success:function(data){
					examPages=data.examPages;
					$.each(examPages, function(index, val) {
						var tr1=tr.clone();
						tr1.find('.id').text(val.id);
						tr1.find('.name').text(val.id);
						tr1.find('.grade').text(val.grade);
						tr1.find('.time').text(new Date(val.time).Format("yyyy-MM-dd HH:mm:ss"));
						$("#examtable tbody").append(tr1); 
					});
					
				}
			});
			$(".spy").click(function(event) {
				var examId=$(this).parent("td").siblings('.id').text();
				$.ajax({
					url: '/ExamOnline/spyExamById.do',
					type: 'post',
					dataType: 'json',
					data: {"examId": examId},
					success:function(data){
						console.log(data)
						if (data.code==200) {
							notExamStudents=data.notExamStudents;
							examingStudents=data.examingStudents;
							examedStudents=data.examedStudents;
							var type=$("#type").val();
							$(".student").remove();
							if (type==0) {
								alert(1)
								$.each(notExamStudents, function(index, val) {
									var tr1=studenttr.clone();
									tr1.find('.no').text(val.no);
									tr1.find('.name').text(val.name);
									$("#studenttable tbody").append(tr1);
								});
							}else if(type==1){
								$.each(examingStudents, function(index, val) {
									var tr1=studenttr.clone();
									tr1.find('.no').text(val.no);
									tr1.find('.name').text(val.name);
									$("#studenttable tbody").append(tr1);
								});
							}else if(type==2){
								$.each(examedStudents, function(index, val) {
									var tr1=studenttr.clone();
									tr1.find('.no').text(val.no);
									tr1.find('.name').text(val.name);
									$("#studenttable tbody").append(tr1);
								});
							}
						}
					}
				});
				
				$("#infoModal").modal('show');
			});
			$("#type").change(function(event) {
				var type=$(this).val();
				$(".student").remove();
				if (type==0) {
					$.each(notExamStudents, function(index, val) {
						var tr1=studenttr.clone();
						tr1.find('.no').text(val.no);
						tr1.find('.name').text(val.name);
						$("#studenttable tbody").append(tr1);
					});
				}else if(type==1){
					$.each(examingStudents, function(index, val) {
						var tr1=studenttr.clone();
						tr1.find('.no').text(val.no);
						tr1.find('.name').text(val.name);
						$("#studenttable tbody").append(tr1);
					});
				}else if(type==2){
					$.each(examedStudents, function(index, val) {
						var tr1=studenttr.clone();
						tr1.find('.no').text(val.no);
						tr1.find('.name').text(val.name);
						$("#studenttable tbody").append(tr1);
					});
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

