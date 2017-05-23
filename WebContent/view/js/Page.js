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

var left_time="";

//显示试卷
jQuery(document).ready(function($) {
	
	var examId = $_GET['id'];
	var examPage="";
	var page="";
	var pageId="";
	$.ajax({
		url: '/ExamOnline/addToServletContext.do',
		type: 'post',
		data:{"examId":examId}
	});

	
	//通过考试id获得试卷信息
	$.ajax({
		url: '/ExamOnline/getExamPageById.do',
		type: 'post',
		dataType: 'json',
		async:false,
		data: {"id": examId},
		success:function(data){
			if (data.code==200) {
				examPage=data.examPage;
				page=examPage.page;
				pageId=examPage.pageId;
			}
			
		}
	});
	//计时
	var left_time=start(new Date(examPage.time),page.ans_time);

	var time=setInterval("clock()", 1000);
	setTimeout("finish()", left_time*1000);
	// 克隆模板
	single = $(".single").clone();
	// 删除模板
	$(".single").remove();
	// 克隆模板
	multiple = $(".multiple").clone();
	// 删除模板
	$(".multiple").remove();
	// 克隆模板
	blank = $(".blank").clone();
	// 删除模板
	$(".blank").remove();
	// 克隆模板
	subjective = $(".subjective").clone();
	// 删除模板
	$(".subjective").remove();
	
			// 加载本试卷单选题
			$.ajax({
				url: '/ExamOnline/getQuestionsByPageIdAndQuestionType.do',
				type: 'post',
				dataType: 'json',
				async:false,
				data: {"pageId": pageId,"questionType":1},
				success:function(data){
					if (data.code==200) {
						singleQuestions=data.questions;
						var i=1;
						$.each(singleQuestions, function(index, val) {
							var li=$("<li><a></a></li>");
							li.find('a').attr('href', "#"+val.id);
							li.find('a').text(i);

							$("#collapseOne ul").append(li);
							var div = single.clone();
							div.find('h4').text(i+"."+val.content);
							i=i+1;
							div.find('h4').attr('id', val.id);
							div.find(':radio').attr('name',val.id);
							div.find('.option1').text("A、"+val.option1);
							div.find('.option2').text("B、"+val.option2);
							div.find('.option3').text("C、"+val.option3);
							div.find('.option4').text("D、"+val.option4);
							$('.singleQuestions').append(div);
						});
					}
				}
			});
			// 加载本试卷多选题
			$.ajax({
				url: '/ExamOnline/getQuestionsByPageIdAndQuestionType.do',
				type: 'post',
				dataType: 'json',
				data: {"pageId": pageId,"questionType":2},
				success:function(data){
					if (data.code==200) {
						multipleQuestions=data.questions;
						var i=1;
						$.each(multipleQuestions, function(index, val) {
							var li=$("<li><a></a></li>");
							li.find('a').attr('href', "#"+val.id);
							li.find('a').text(i);

							$("#collapseTwo ul").append(li);
							var div = multiple.clone();
							div.find('h4').text(i+"."+val.content);
							i=i+1;
							div.find('h4').attr('id', val.id);
							div.find(':checkbox').attr('name',val.id);
							div.find('.option1').text("A、"+val.option1);
							div.find('.option2').text("B、"+val.option2);
							div.find('.option3').text("C、"+val.option3);
							div.find('.option4').text("D、"+val.option4);
							$('.multipleQuestions').append(div);
						});
					}
				}
			});
			// 加载本试卷填空题
			$.ajax({
				url: '/ExamOnline/getQuestionsByPageIdAndQuestionType.do',
				type: 'post',
				dataType: 'json',
				data: {"pageId": pageId,"questionType":3},
				success:function(data){
					if (data.code==200) {
						blankQuestions=data.questions;
						var i=1;
						$.each(blankQuestions, function(index, val) {
							var li=$("<li><a></a></li>");
							li.find('a').attr('href', "#"+val.id);
							li.find('a').text(i);

							$("#collapseThree ul").append(li);
							var div = blank.clone();
							div.find('h5').text(i+"."+val.content);
							i=i+1;
							div.find('h5').attr('id', val.id);
							div.find('input').attr('name', val.id);
							$('.blankQuestions').append(div);
						});
					}
				}
			});
			// 加载本试卷简答题
			$.ajax({
				url: '/ExamOnline/getQuestionsByPageIdAndQuestionType.do',
				type: 'post',
				dataType: 'json',
				data: {"pageId": pageId,"questionType":4},
				success:function(data){
					if (data.code==200) {
						subjectiveQuestions=data.questions;
						i=1;
						$.each(subjectiveQuestions, function(index, val) {
							var li=$("<li><a></a></li>");
							li.find('a').attr('href', "#"+val.id);
							li.find('a').text(i);

							$("#collapseFour ul").append(li);
							var div = subjective.clone();
							div.find('h5').text(i+"."+val.content);
							i=i+1;
							div.find('h5').attr('id', val.id);
							div.find('textarea').attr('name', val.id);
							$('.subjectiveQuestions').append(div);
						});
					}
				}
			});
			//交卷
			$("#submit").click(function(event) {
				var rs=confirm("提交后不得更改,确认提交？");
				var answers=getFormFileds("questions");
				answers.examId=examId;
				// 加载中
				$("#hidebg").css("display","block");  //显示隐藏层
   				$("#hidebg").height($(document).height());//设置隐藏层的高度为当前页面高度
   				$(".main").css("display","block");  //显示隐藏层
   				$(".main").css('top', $(document).height()-600);
   				$.ajax({
   					url: '/ExamOnline/handInExam.do',
   					type: 'post',
   					async:false,
   					dataType: 'json',
   					data: answers,
   					success:function(data){
   						alert(data.msg);
   					}
  				 });
   				$.ajax({
					url: '/ExamOnline/deleteFromServletContext.do',
					type: 'post',
					async:false
				});
				location.href="index.html";
			});
		});



 function clock(){ 
 	var hour=$("#hour").text()-0;
 	var min=$("#min").text()-0;
 	var sec=$("#sec").text()-0;
 	if (sec==0) {
 		if (min==0) {
 			if (hour==0) {
 				$("#hour").text("00");
 				clearInterval(time);
 			}else {
 				if (hour<10) {
 					$("#hour").text("0"+(hour-1));
 				}else {
 					$("#hour").text(hour-1);
 				}
 			}
 			$("#min").text(59);
 		}else {
 			min=min-1;
 			if (min<10) {
 				$("#min").text("0"+min);
 			}else {
 				$("#min").text(min);
 			}
 		}
 		$("#sec").text(59);
 	}else {
 		sec=sec-1;
 		if (sec<10) {
 			$("#sec").text("0"+sec);
 		}else {
 			$("#sec").text(sec);
 		}
 	}
 }
//倒计时的控制
function start(start_time,time_length){
	var curr_date=new Date();
	var hour=time_length/60;
	var min=time_length%60;
	var end_time=new Date(start_time.toString());;
	end_time.setHours(end_time.getHours()+hour,end_time.getMinutes()+min);
	if(curr_date<start_time){
		alert("考试还没开始！");
		location.href="index.html";
	}else if(curr_date>end_time){
		alert("考试已经结束！");
		location.href="index.html";
	}else{
		var s1=end_time.getHours()*3600+end_time.getMinutes()*60+end_time.getSeconds();
		var s2=curr_date.getHours()*3600+curr_date.getMinutes()*60+curr_date.getSeconds();
		hour_length=parseInt((s1-s2)/3600);
		min_length=parseInt((s1-s2-hour_length*3600)/60);
		second_length=s1-s2-hour_length*3600-min_length*60;
		$("#hour").text(hour_length);
		$("#min").text(min_length);
		$("#sec").text(second_length);
	}
	return s1-s2;
}
function finish(){
	alert('考试结束！');
	//加载中
	$("#hidebg").css("display","block");  //显示隐藏层
   $("#hidebg").height($(document).height());//设置隐藏层的高度为当前页面高度
   $(".main").css("display","block");  //显示隐藏层
   $(".main").css('top', $(document).height()-600);
   //从servletContext删除
   $.ajax({
		url: '/ExamOnline/deleteFromServletContext.do',
		type: 'post',
		async:false
	});
	//设置为已考
	$.ajax({
		url: '/ExamOnline/updateExamStatus.do',
		type: 'post',
		dataType: 'json',
		data: {"id": examId,'flag':1},
		success:function(data){
			if (data.code==200) {
				console.log("成功");
			}else {
				console.log("失败");
			}
		}
	});
	//往数据库存入成绩
	$.ajax({
		url: '/ExamOnline/addStudentScore.do',
		type: 'json',
		dataType: 'json',
		data: {"examId": examId},
		success:function(datd){
			console.log(data.msg);
		}
	});
	
	//交卷
	var answers=getFormFileds("questions");
	answers.examId=examId;
	console.log(answers);
	$.ajax({
		url: '/ExamOnline/handInExam.do',
		type: 'post',
		dataType: 'json',
		data: answers,
		success:function(data){
			location.href="index.html";
		}
	});

	location.href="index.html";
}
