var id = "";
jQuery(document).ready(
		function($) {
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
				},
				error:function(){
					location.href="teacher-login.html";
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
			$("[name='type']").change(function(event) {
				var typeId = $("[name='type']").val();
				var type = getTypeNameById(typeId);
				switch (type) {
				case "单选题":
					$(".multiple").css('display', 'none');
					$(".blank").css('display', 'none');
					$(".single").css('display', 'table-row');
					break;
				case "多选题":
					$(".multiple").css('display', 'table-row');
					$(".blank").css('display', 'none');
					$(".single").css('display', 'none');
					break;
				case "填空题":
					$(".multiple").css('display', 'none');
					$(".blank").css('display', 'table-row');
					$(".single").css('display', 'none');
					break;
				case "简答题":
					$(".multiple").css('display', 'none');
					$(".blank").css('display', 'table-row');
					$(".single").css('display', 'none');
					break;
				default:
					// statements_def
					break;
				}

			});
			id = $_GET['id'];
			if (id != undefined) {
				$.ajax({
					url : '/ExamOnline/getQuestionById.do',
					type : 'post',
					dataType : 'json',
					data : {
						"id" : id
					},
					success : function(data) {
						console.log(data);
						if (data.code == 200) {
							question = data.question;
							$("[name='type']").val(question.type);
							var type = getTypeNameById(question.type);
							switch (type) {
							case "单选题":
								$(".multiple").css('display', 'none');
								$(".blank").css('display', 'none');
								$(".single").css('display', 'table-row');
								$("#singleOption1").val(question.option1);
								$("#singleOption2").val(question.option2);
								$("#singleOption3").val(question.option3);
								$("#singleOption4").val(question.option4);
								break;
							case "多选题":
								$(".multiple").css('display', 'table-row');
								$(".blank").css('display', 'none');
								$(".single").css('display', 'none');
								$("#multipleOption1").val(question.option1);
								$("#multipleOption2").val(question.option2);
								$("#multipleOption3").val(question.option3);
								$("#multipleOption4").val(question.option4);
								break;
							case "填空题":
								$(".multiple").css('display', 'none');
								$(".blank").css('display', 'table-row');
								$(".single").css('display', 'none');
								$("#answer").val(question.answer);
								break;
							case "简答题":
								$(".multiple").css('display', 'none');
								$(".blank").css('display', 'table-row');
								$(".single").css('display', 'none');
								$("#answer").val(question.answer);
								break;
							default:
								break;
							}
							$("[name='diff']").val(question.diff);
							$("[name='courseId']").val(question.courseId);
							$("[name='chapter']").val(question.chapter);
							$("#title").val(question.content);
						}
					}
				});
			}
			$("#submit").click(
					function(event) {
						var type = $("[name='type']").val();
						var diff = $("[name='diff']").val();
						var courseId = $("[name='courseId']").val();
						var chapter = $("[name='chapter']").val();
						var content = $("#title").val()
						var answer = "";
						var info = "";
						var option1 = "";
						var option2 = "";
						var option3 = "";
						var option4 = "";
						switch (getTypeNameById(type)) {
						case "单选题":
							option1 = $("#singleOption1").val();
							option2 = $("#singleOption2").val();
							option3 = $("#singleOption3").val();
							option4 = $("#singleOption4").val();
							answer = $("[type='radio']:checked").val();
							break;
						case "多选题":
							option1 = $("#multipleOption1").val();
							option2 = $("#multipleOption2").val();
							option3 = $("#multipleOption3").val();
							option4 = $("#multipleOption4").val();
							$("[type='checkbox']:checked").each(function(i) {
								answer += $(this).val() + ",";
							});
							answer = answer.substring(0, answer.length - 1);
							break;
						case "填空题":
						case "简答题":
							answer = $("#answer").val();
							break;
						default:
							break;
						}
						if (id == undefined) {
							// 添加
							$.ajax({
								url : '/ExamOnline/addQuestion.do',
								type : 'post',
								dataType : 'json',
								data : {
									"courseId" : courseId,
									"content" : content,
									"option1" : option1,
									"option2" : option2,
									"option3" : option3,
									"option4" : option4,
									"answer" : answer,
									"chapter":chapter,
									"type":type,
									"diff":diff
								},
								success : function(data) {
									if (data.code == 200) {
										alert(data.msg);
										location.href = "Question.html";
									} else {
										alert(data.msg);
									}
								}
							});
						} else {
							// 更新
							info = info + "&id=" + id;
							$.ajax({
								url : '/ExamOnline/updateQuestionById.do',
								type : 'post',
								dataType : 'json',
								data : {
									"id":id,
									"courseId" : courseId,
									"content" : content,
									"option1" : option1,
									"option2" : option2,
									"option3" : option3,
									"option4" : option4,
									"answer" : answer,
									"chapter":chapter,
									"type":type,
									"diff":diff
								},
								success : function(data) {
									if (data.code == 200) {
										alert(data.msg);
										location.href = "Question.html";
									} else {
										alert(data.msg);
									}
								}
							});

						}
					});
		});
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
	$.each(types, function(index, val) {
		if (val.id == id) {
			courseName = val.name;
		}
	});
	return courseName;
}