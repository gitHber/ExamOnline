// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, //月份 
		"d+" : this.getDate(), //日 
		"H+" : this.getHours(), //小时 
		"m+" : this.getMinutes(), //分 
		"s+" : this.getSeconds(), //秒 
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
		"S" : this.getMilliseconds()
	//毫秒 
};
if (/(y+)/.test(fmt))
	fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
		.substr(4 - RegExp.$1.length));
for ( var k in o)
	if (new RegExp("(" + k + ")").test(fmt))
		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
			: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

jQuery(document).ready(function($) {
	var examId=$_GET['examId'];
	var studentIds="";
	var index=0;

	// 克隆模板
	subjective = $(".subjective").clone();
	// 删除模板
	$(".subjective").remove();
	// 加载用户信息
	$.ajax({
		url : '/ExamOnline/getTeacherInfoFromSession.do',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				var teacher = data.teacher;
				$(".name").text(teacher.name);
			}
		},
		error:function(){
			location.href="teacher-login.html";
		}
	});
	//获得所有未评分的学生
	$.ajax({
		url: '/ExamOnline/getAllStudentWithNoCent.do',
		type: 'post',
		dataType: 'json',
		async:false,
		data: {"examId": examId},
		success:function(data){
			if (data.code==200) {
				studentIds=data.studentIds;
			}
		}
	});
	var stuId=studentIds[index];
	//下一张试卷
	$("#next").click(function(event) {
		//评分
		var cents=getFormFileds("questionCents");
		cents.examId=examId;
		cents.stuId=stuId;
		$.ajax({
			url: '/ExamOnline/score.do',
			type: 'post',
			async:false,
			data: cents
		});
		$.ajax({
			url: '/ExamOnline/setScore.do',
			type: 'post',
			async:false,
			data: {"examId": examId,"stuId":stuId}
		});
		
		
		index++;
		if (index<studentIds.length) {
			if(index==studentIds.length-1){
				$(this).text("完成");
			}
			$(".subjective").remove();
			stuId=studentIds[index];
			load(stuId,examId);
		}else{
			alert("评分结束！");
			//将考试状态改成阅卷完毕
			$.ajax({
				url: '/ExamOnline/updateExamStatus.do',
				type: 'post',
				data: {"id": examId,"flag":2}
			});
			
			location.href="examToScore.html";
		}
	});
	load(stuId,examId);
	
});
function load(stuId,examId){
	// 加载本试卷简答题
	$.ajax({
		url: '/ExamOnline/getQuestionNoScore.do',
		type: 'post',
		dataType: 'json',
		data: {"stuId": stuId,"examId":examId},
		success:function(data){
			if (data.code==200) {
				questions=data.questions;
				i=1;
				$.each(questions, function(index, val) {
					var div = subjective.clone();
					div.find('h5').text(i+"."+val.content);
					i=i+1;
					div.find('textarea').val(val.answer);
					div.find('input').attr('name', val.id);
					$('.subjectiveQuestions').append(div);
				});
			}
		}
	});
}
