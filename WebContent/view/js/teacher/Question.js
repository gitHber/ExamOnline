// 初始化
var pageCount = 1;// 总页数
var pageSize = 10;// 每页显示多少条数据
var pageCurrent = 1;// 当前页数
var itemCount = ""// 总计路数
var condition = "";// 分页条件

var tr = "";
var questions = "";
var types = "";
var courses = "";

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
						$(".name").text(teacher.name);
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
					types = data.questionTypes;
					if (data.code == 200) {
						$.each(data.questionTypes, function(index, val) {
							var option = $("<option></option>");
							option.text(val.name);
							option.attr('value', val.id);
							$("[name='type']").append(option);
						});
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
						$.each(data.courses, function(index, val) {
							var option = $("<option></option>");
							option.text(val.name);
							option.attr('value', val.id);
							$("[name='courseId']").append(option);
						});
					}
				}
			});

			// 克隆模板
			tr = $(".question").clone();
			// 删除模板
			$(".question").remove();
			// 查询数据
			initSearch(1);
			$("#pageCount").text(pageCount);

			
			// 新增试题
			$("#new").click(function(event) {
				location.href = "QuestionInfo.html";
			});

			// 搜索
			$("#search").click(function(event) {
				condition="";
				var values = getFormFileds("searchCondition");
				$.each(values, function(index, val) {
					if (val != "") {
						condition = condition + index + "=" + val + "&";
					}
				});
				condition = condition.substr(0, condition.length - 1);
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
	var comcondition = "";
	if (condition != "") {
		comcondition = condition + "&pageCurrent=" + p + "&pageSize="
		+ pageSize;
	} else {
		comcondition = "pageCurrent=" + p + "&pageSize=" + pageSize;
	}
	$.ajax({
		url : '/ExamOnline/getQuestionByPageIndex.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : comcondition,
		success : function(data) {
			console.log(data);
			questions = data.questions;
			$(".question").remove();
			if (data.code == 200) {
				$.each(data.questions, function(index, val) {
					var tr1 = tr.clone();
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
	// 点击显示详细信息
	$(".content")
	.click(
		function(event) {
			var id = $(this).siblings('.id').text();
			var type = $(this).siblings('.type').text();
			var question = getQuestionById(id);
			var answers="";
			switch (type) {
				case "单选题":
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
							break;
							case "多选题":
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
							break;
							case "填空题":
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
							break;
							case "简答题":
							$("#infoModal .modal-body").children('div').css(
								'display', 'none');
							$("#infoModal #subjective").css('display', 'block');
							$("#infoModal #subjective h4").text(
								id + "." + question.content);
							$("#infoModal #subjective textarea").val(question.answer);
							break;
							default:
							break;
						}
						$('#infoModal').modal('show');
					});
	// 首页
	$("#firstPage").click(
		function(event) {
			comcondition = condition + "&pageCurrent=" + 1 + "&pageSize="
			+ pageSize;
			pageCurrent = 1;
			initSearch(1);
		});
	// 上一页
	$("#beforPage").click(
		function(event) {
			if (pageCurrent > 1) {
				pageCurrent -= 1;
				comcondition = condition + "&pageCurrent=" + pageCurrent
				+ "&pageSize=" + pageSize;
				initSearch(pageCurrent);
			}
		});
	// 下一页
	$("#nextPage").click(
		function(event) {
			if ((pageCurrent - 0) < pageCount) {
				pageCurrent += 1;
				comcondition = condition + "&pageCurrent=" + pageCurrent
				+ "&pageSize=" + pageSize;
				initSearch(pageCurrent);
			}
		});
	// 尾页
	$("#lastPage").click(
		function(event) {
			comcondition = condition + "&pageCurrent=" + pageCount
			+ "&pageSize=" + pageSize;
			pageCurrent = pageCount;
			initSearch(pageCount);
		});
	// 修改
	$(".update").click(
		function(event) {
			location.href = "QuestionInfo.html?id="
			+ $(this).parent("td").siblings('.id').text();
		});
	// 删除
	$(".delete").click(
		function(event) {
			var id=$(this).parent("td").siblings('.id').text();
			if(confirm("确认删除？")){
				$.post('/ExamOnline/deleteQuestionById.do', {'id': id}, function(data) {
					if (data.code=200) {
						alert(data.msg);
						history.go(0);
					}else{
						alert(data.msg);
					}
				},'json');
			}
		});
}

//根据试题类型id得到试题类型名
function getTypeNameById(id) {
	var typeName = "";
	$.each(types, function(index, val) {
		if (val.id == id) {
			typeName = val.name;
		}
	});
	return typeName;
}
//根据课程id得到课程名
function getCourseNameById(id) {
	var courseName = "";
	$.each(courses, function(index, val) {
		if (val.id == id) {
			courseName = val.name;
		}
	});
	return courseName;
}
function getQuestionById(id) {
	var question = "";
	$.each(questions, function(index, val) {
		if (val.id == id) {
			question = val;
		}
	});
	return question;
}