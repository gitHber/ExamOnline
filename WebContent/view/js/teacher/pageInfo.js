// 初始化
var pageCount = 1;// 总页数
var pageSize = 4;// 每页显示多少条数据
var pageCurrent = 1;// 当前页数
var itemCount = ""// 总计路数

var tr = "";
var trAdd="";//新增显示的
var questions = "";
var types = "";
var courses = "";
var pageId = $_GET['id'];
var page="";// 当前试卷信息
var singleQuestions="";
var multipleQuestions="";
var blankQuestions="";
var subjectiveQuestions="";
var currentType="";// 当前类型(分页时使用)
jQuery(document).ready(
	function($) {
		// 克隆模板
		tr = $(".question").clone();
		trAdd=$(".questionAdd").clone();
			// 删除模板
			$(".question").remove();
			$(".questionAdd").remove();
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
			// 获取试卷信息
			$.ajax({
				url: '/ExamOnline/getPageById.do',
				type: 'post',
				dataType: 'json',
				async:false,
				data: {"id": pageId},
				success:function(data){
					if (data.code==200) {
						page=data.page;
						$("#total_cent").text(page.total_cent);
						getCurrCent();
					}
				}
			});
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
						$.each(singleQuestions, function(index, val) {
							var tr1 = tr.clone();
							tr1.find('.id').text(val.id);
							tr1.find('.content').text(val.content);
							var typename = getTypeNameById(val.type);
							tr1.find('.type').text(typename);
							tr1.find('.diff').text(
								val.diff == 1 ? '简单'
								: (val.diff == 2 ? '一般' : '困难'));
							$('#singleQuestion tbody').append(tr1);
						});
						// 点击显示详细信息
						$("#singleQuestion .content")
						.click(
							function(event) {
								var id = $(this).siblings('.id').text();
								var type = $(this).siblings('.type').text();
								var question = getQuestionById(id,'single');
								var answers="";

								$("#infoModal .modal-body").children('div').css(
									'display', 'none');
								$("#infoModal #single").css('display', 'block');
								$("#infoModal #single h4").text(
									id + "." + question.content);
								$("#infoModal #single .option1").text(
									"A、" + question.option1);
								$("#infoModal #single .option2").text(
									"B、" + question.option2);
								$("#infoModal #single .option3").text(
									"C、" + question.option3);
								$("#infoModal #single .option4").text(
									"D、" + question.option4);
								switch (question.answer) {
									case "A":
									$("#infoModal #single .option1").siblings(
										"input").attr('checked', 'checked');
									break;
									case "B":
									$("#infoModal #single .option2").siblings(
										"input").attr('checked', 'checked');
									break;
									case "C":
									$("#infoModal #single .option3").siblings(
										"input").attr('checked', 'checked');
									break;
									case "D":
									$("#infoModal #single .option4").siblings(
										"input").attr('checked', 'checked');
									break;
									default:
								// statements_def
								break;
							}
							
							$('#infoModal').modal('show');
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
						$.each(multipleQuestions, function(index, val) {
							var tr1 = tr.clone();
							tr1.find('.id').text(val.id);
							tr1.find('.content').text(val.content);
							var typename = getTypeNameById(val.type);
							tr1.find('.type').text(typename);
							tr1.find('.diff').text(
								val.diff == 1 ? '简单'
								: (val.diff == 2 ? '一般' : '困难'));
							$('#multipleQuestion tbody').append(tr1);
						});
						// 点击显示详细信息
						$("#multipleQuestion .content")
						.click(
							function(event) {
								var id = $(this).siblings('.id').text();
								var type = $(this).siblings('.type').text();
								var question = getQuestionById(id,'multiple');
								var answers="";
								
								$("#infoModal .modal-body").children('div').css(
									'display', 'none');
								$("#infoModal #multiple").css('display', 'block');
								$("#infoModal #multiple h4").text(
									id + "." + question.content);
								$("#infoModal #multiple .option1").text(
									"A、" + question.option1);
								$("#infoModal #multiple .option2").text(
									"B、" + question.option2);
								$("#infoModal #multiple .option3").text(
									"C、" + question.option3);
								$("#infoModal #multiple .option4").text(
									"D、" + question.option4);
								answers=question.answer.split(",");
								for(var i=0;i<answers.length;i++){
									if (answers[i]=="A") {
										$("#infoModal #multiple .option1").siblings(
											"input").attr('checked', 'checked');
									}else if(answers[i]=="B"){
										$("#infoModal #multiple .option2").siblings(
											"input").attr('checked', 'checked');
									}else if (answers[i]=="C") {
										$("#infoModal #multiple .option3").siblings(
											"input").attr('checked', 'checked');
									}else if (answers[i]=="D") {
										$("#infoModal #multiple .option4").siblings(
											"input").attr('checked', 'checked');
									}
								}
								$('#infoModal').modal('show');
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
						$.each(blankQuestions, function(index, val) {
							var tr1 = tr.clone();
							tr1.find('.id').text(val.id);
							tr1.find('.content').text(val.content);
							var typename = getTypeNameById(val.type);
							tr1.find('.type').text(typename);
							tr1.find('.diff').text(
								val.diff == 1 ? '简单'
								: (val.diff == 2 ? '一般' : '困难'));
							$('#blankQuestion tbody').append(tr1);
						});
						// 点击显示详细信息
						$("#blankQuestion .content")
						.click(
							function(event) {
								var id = $(this).siblings('.id').text();
								var type = $(this).siblings('.type').text();
								var question = getQuestionById(id,'blank');
								var answers="";
								$("#infoModal .modal-body").children('div').css(
									'display', 'none');
								$("#infoModal #blank").css('display', 'block');
								$("#infoModal #blank h4").text(
									id + "." + question.content);
								answers=question.answer.split(",");
								for (var i = 0; i < answers.length; i++) {
									var input=$("<input type='text' readonly='true'>");
									input.val(answers[i]);
									$("#infoModal #blank").append(input);
								}
								$('#infoModal').modal('show');
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
						$.each(subjectiveQuestions, function(index, val) {
							var tr1 = tr.clone();
							tr1.find('.id').text(val.id);
							tr1.find('.content').text(val.content);
							var typename = getTypeNameById(val.type);
							tr1.find('.type').text(typename);
							tr1.find('.diff').text(
								val.diff == 1 ? '简单'
								: (val.diff == 2 ? '一般' : '困难'));
							$('#subjectiveQuestion tbody').append(tr1);
						});
						// 删除试题
						$(".deleteQuestion").click(function(event) {
							var questionId = $(this).parent("td").siblings('.id').text();
							$.ajax({
								url: '/ExamOnline/deleteQuestionFromPage.do',
								type: 'post',
								dataType: 'json',
								data: {"pageId": pageId,"questionId":questionId},
								success:function(data){
									if (data.code==200) {
										alert(data.msg);
										history.go(0);
									}else{
										alert(data.msg);
									}
								}
							});
						});
						// 点击显示详细信息
						$("#subjectiveQuestion .content")
						.click(
							function(event) {
								var id = $(this).siblings('.id').text();
								var type = $(this).siblings('.type').text();
								var question = getQuestionById(id,'subjective');
								var answers="";
								$("#infoModal .modal-body").children('div').css(
									'display', 'none');
								$("#infoModal #subjective").css('display', 'block');
								$("#infoModal #subjective h4").text(
									id + "." + question.content);
								$("#infoModal #subjective textarea").val(question.answer);
								$('#infoModal').modal('show');
							});
					}
				}
			});
			// 加载试题类别
			$.ajax({
				url : '/ExamOnline/getAllQuestionType.do',
				type : 'post',
				async : false,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						types = data.questionTypes;
					}
				}
			});
			// 加载课程
			$.ajax({
				url : '/ExamOnline/getAllCourse.do',
				type : 'post',
				dataType : 'json',
				async : false,
				success : function(data) {
					if (data.code == 200) {
						courses = data.courses;
					}
				}
			});
			// 新增试题
			$("#singleAdd").click(function(event) {
				currentType=1;
				initSearch(1);
				$('#chooseQuestion').modal('show');

			});
			$("#multipleAdd").click(function(event) {
				currentType=2;
				initSearch(1);
				$('#chooseQuestion').modal('show');
			});
			$("#blankAdd").click(function(event) {
				currentType=3;
				initSearch(1);
				$('#chooseQuestion').modal('show');
			});
			$("#subjectiveAdd").click(function(event) {
				currentType=4;
				initSearch(1);
				$('#chooseQuestion').modal('show');
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
			$('#chooseQuestion').on('hidden.bs.modal', function () {
				history.go(0); 
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
	$.ajax({
		url : '/ExamOnline/getQuestionNeedToAdd.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : {"pageCurrent":p,"pageSize":pageSize,"questionType":currentType,"diff":page.diff,
		"chapter_start":page.chapter_start,"chapter_end":page.chapter_end,"courseId":page.courseId,"pageId":pageId},
		success : function(data) {
			console.log(data);
			questions = data.questions;
			$(".questionAdd").remove();
			if (data.code == 200) {
				$.each(data.questions, function(index, val) {
					var tr1 = trAdd.clone();
					tr1.find('.id').text(val.id);
					tr1.find('.content').text(val.content);
					var typename = getTypeNameById(val.type);
					tr1.find('.type').text(typename);
					tr1.find('.diff').text(
						val.diff == 1 ? '简单'
						: (val.diff == 2 ? '一般' : '困难'));
					$('#questiontable tbody').append(tr1);
				});
				pageCount = data.page.pageCount;
				itemCount=data.page.itemCount;
				$("#pageCount").text(pageCount);
				$("#itemCount").text(itemCount);
			}
		}
	});
	//显示页信息
	$("#pageCount").text(pageCount);
	//添加试题
	$(".addQuestion").click(function(event) {
		var type=$(this).parent("td").siblings('.type').text();
		var question_cent="";
		if (type="单选题") {
			question_cent=2;
		}else if (type="多选题"){
			question_cent=5;
		}else if (type="填空题"){
			question_cent=5;
		}else if (type="简答题"){
			question_cent=10;
		}
		var questionId=$(this).parent("td").siblings('.id').text();
		if (getCurrCent()>(page.total_cent-question_cent)) {
			alert("分数会超过在总分！");
		}else{
			$.ajax({
				url: '/ExamOnline/insertQuestionToPage.do',
				type: 'post',
				dataType: 'json',
				data: {"pageId": pageId,"questionId":questionId},
				success:function(data){
					if (data.code==200) {
						alert(data.msg);
						initSearch(pageCurrent);
					}else{
						alert(data.msg);
					}
				}
			});
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
			pageCurrent = pageCount;
			initSearch(pageCount);
		});
}

// 根据试题类型id得到试题类型名
function getTypeNameById(id) {
	var typeName = "";
	$.each(types, function(index, val) {
		if (val.id == id) {
			typeName = val.name;
		}
	});
	return typeName;
}
// 根据课程id得到课程名
function getCourseNameById(id) {
	var courseName = "";
	$.each(courses, function(index, val) {
		if (val.id == id) {
			courseName = val.name;
		}
	});
	return courseName;
}
function getQuestionById(id,type) {
	var question = "";
	var qs="";
	switch (type) {
		case 'single':
		qs=singleQuestions;
		break;
		case 'multiple':
		qs=multipleQuestions;
		break;
		case 'blank':
		qs=blankQuestions;
		break;
		case 'subjective':
		qs=subjectiveQuestions;
		break;
		default:
		qs=questions;
		break;
	}
	$.each(qs, function(index, val) {
		if (val.id == id) {
			question = val;
		}
	});
	return question;
}
function getCurrCent(){
	var currCent="";
	$.ajax({
		url: '/ExamOnline/getCurrCentByPageId.do',
		type: 'post',
		async:false,
		dataType: 'json',
		data: {"pageId": pageId},
		success:function(data){
			$("#curr_cent").text(data.msg);
			currCent=data.msg;
		}
	});
	return currCent;
}