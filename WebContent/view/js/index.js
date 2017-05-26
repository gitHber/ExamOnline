
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

var pageCount1 = 1;// 总页数
var pageSize1 = 2;// 每页显示多少条数据
var pageCurrent1 = 1;// 当前页数
var itemCount1 = ""// 总计路数

var tr = "";
var courses = "";

var tr1 = "";

jQuery(document).ready(function($) {
	//加载用户信息
	$.post('/ExamOnline/getStudentInfoFromSession.do', function(data) {
		if(data.code==200){
			var student=data.student;
			$(".name").text(student.name);
			$("#headImg").attr("src",student.img);
		}
	},'json');
	// 加载课程
	$.ajax({
		url : '/ExamOnline/getAllCourse.do',
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(data) {
			if (data.code == 200) {
				courses = data.courses;
				$.each(data.courses, function(index, val) {
					var option = $("<option></option>");
					option.text(val.name);
					option.attr('value', val.id);
					$("[name='courseId']").append(option);
				});
			}
		},
		error:function(){
			location.href="../student/student-login.html";
		}
	});
			// 克隆模板
			tr = $(".examInfo").clone();
			// 删除模板
			$(".examInfo").remove();
			// 查询数据
			initSearch(1);
			// 克隆模板
			tr1 = $(".scoreInfo").clone();
			// 删除模板
			$(".scoreInfo").remove();
			// 查询数据
			initSearch1(1);

			// 跳转
			$("#turn").click(function(event) {
				var pageNum = $("#pageNum").val();
				if (pageNum > pageCount) {
					alert("没有这么多页！");
				} else {
					initSearch(pageNum - 0);
					$("#myExam .active a").text(pageNum);
				}
			});

			// 跳转
			$("#turn1").click(function(event) {
				var pageNum1 = $("#pageNum1").val();
				if (pageNum1 > pageCount1) {
					alert("没有这么多页！");
				} else {
					initSearch1(pageNum1 - 0);
					$("#myScore .active a").text(pageNum1);
				}
			});
			//创建练习
			$("#new").click(function(event) {
				$('#newPractice').modal('show');
			});
	// 提交练习基本信息
	$("#submit").click(function(event) {
		$('#newPractice').modal('hide');
		$('#construct').modal('show');
	});
	$("#submit2").click(function(event) {
		var practiceParameter=getFormFileds("newPracticeQuestionInfo");
		var practiceInfo=getFormFileds("newPracticeInfo");
		var parameter=$.extend({}, practiceParameter, practiceInfo);
		location.href="Practice.html?parameter="+JSON.stringify(parameter);
	});
});

function initSearch(p) {
	$("#firstPage").unbind("click");
	$("#beforPage").unbind('click');
	$("#nextPage").unbind('click');
	$("#lastPage").unbind('click');
	$("button.startExam").unbind('click');
	pageCurrent = p;
	$("#myExam .active a").text(pageCurrent);
	$.ajax({
		url : '/ExamOnline/getStudentExam.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : {"pageCurrent":p,"pageSize":pageSize,"flag":0},
		success : function(data) {
			exams = data.exams;
			$(".examInfo").remove();
			if (data.code == 200) {
				$.each(exams, function(index, val) {
					var tr1 = tr.clone();
					tr1.find('.examId').text(val.id);
					tr1.find('.examName').text(val.name);
					tr1.find('.examTime').text(new Date(val.time).Format("yyyy-MM-dd HH:mm"));
					tr1.find('.grade').text(val.grade);
					$('#myExam tbody').append(tr1);
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
			pageCurrent = 1;
			initSearch(1);
		});
	// 上一页
	$("#beforPage").click(
		function(event) {
			if (pageCurrent > 1) {
				pageCurrent -= 1;
				initSearch(pageCurrent);
			}
		});
	// 下一页
	$("#nextPage").click(
		function(event) {
			if ((pageCurrent - 0) < pageCount) {
				pageCurrent += 1;
				initSearch(pageCurrent);
			}
		});
	// 尾页
	$("#lastPage").click(
		function(event) {
			initSearch(pageCount);
		});
	//开始考试
	$("button.startExam").click(function(event) {
		var examId=$(this).parents("tr").find('.examId').text();
		location.href="examInfo.html?id="+examId;
	});
}

function initSearch1(p) {
	$("#firstPage1").unbind("click");
	$("#beforPage1").unbind('click');
	$("#nextPage1").unbind('click');
	$("#lastPage1").unbind('click');
	pageCurrent1 = p;
	$("#myScore .active a").text(pageCurrent1);
	$.ajax({
		url : '/ExamOnline/getStudentExamScore.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : {"pageCurrent":p,"pageSize":pageSize1,"flag":2},
		success : function(data) {
			console.log(data);
			exams = data.exams;
			$(".scoreInfo").remove();
			if (data.code == 200) {
				$.each(exams, function(index, val) {
					var tr2 = tr1.clone();
					tr2.find('.examId').text(val.id);
					tr2.find('.examName').text(val.name);
					tr2.find('.examTime').text(new Date(val.time).Format("yyyy-MM-dd HH:mm"));
					tr2.find('.grade').text(val.grade);
					tr2.find('#score').text(val.score);
					$('#myScore tbody').append(tr2);
				});
				pageCount1 = data.page.pageCount;
				itemCount1=data.page.itemCount;
				$("#pageCount1").text(pageCount);
				$("#itemCount1").text(itemCount);
			}
		}
	});
	// 首页
	$("#firstPage1").click(
		function(event) {
			pageCurrent1 = 1;
			initSearch1(1);
		});
	// 上一页
	$("#beforPage1").click(
		function(event) {
			if (pageCurrent1 > 1) {
				pageCurrent1 -= 1;
				initSearch1(pageCurrent1);
			}
		});
	// 下一页
	$("#nextPage1").click(
		function(event) {
			if ((pageCurrent1 - 0) < pageCount1) {
				pageCurrent1 += 1;
				initSearch1(pageCurrent1);
			}
		});
	// 尾页
	$("#lastPage1").click(
		function(event) {
			initSearch1(pageCount1);
		});
}
