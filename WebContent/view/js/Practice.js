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



//显示试卷
jQuery(document).ready(function($) {
	var parameter = $_GET['parameter'];
	parameter=JSON.parse(parameter.replace(/%22/g,'"'));
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
	$.ajax({
		url: '/ExamOnline/generatePractice.do',
		type: 'post',
		dataType: 'json',
		data: parameter,
		success:function(data){
			console.log(data);
			if (data.code==200) {
				var i=1;
				$.each(data.singleQuestions, function(index, val) {
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
					div.find('.answer').text(val.answer)
					$('.singleQuestions').append(div);
				});
				i=1;
				$.each(data.multipleQuestions, function(index, val) {
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
					div.find('.answer').text(val.answer)
					$('.multipleQuestions').append(div);
				});
				i=1;
				$.each(data.blankQuestions, function(index, val) {
					var li=$("<li><a></a></li>");
					li.find('a').attr('href', "#"+val.id);
					li.find('a').text(i);

					$("#collapseThree ul").append(li);
					var div = blank.clone();
					div.find('h5').text(i+"."+val.content);
					i=i+1;
					div.find('h5').attr('id', val.id);
					div.find('input').attr('name', val.id);
					div.find('.answer').text(val.answer)
					$('.blankQuestions').append(div);
				});
				i=1;
				$.each(data.subjectiveQuestions, function(index, val) {
					var li=$("<li><a></a></li>");
					li.find('a').attr('href', "#"+val.id);
					li.find('a').text(i);

					$("#collapseFour ul").append(li);
					var div = subjective.clone();
					div.find('h5').text(i+"."+val.content);
					i=i+1;
					div.find('h5').attr('id', val.id);
					div.find('textarea').attr('name', val.id);
					div.find('.answer').text(val.answer)
					$('.subjectiveQuestions').append(div);
				});
				$(".toggle").click(function(event) {
					$(this).next().toggle();
				});
			}else {
				alert(data.msg);
				location.href="index.html"
			}
		}
	});
			// // 加载本试卷单选题
			// $.ajax({
			// 	url: '/ExamOnline/getQuestionsByPageIdAndQuestionType.do',
			// 	type: 'post',
			// 	dataType: 'json',
			// 	async:false,
			// 	data: {"pageId": pageId,"questionType":1},
			// 	success:function(data){
			// 		if (data.code==200) {
			// 			singleQuestions=data.questions;
			// 			var i=1;
			// 			$.each(singleQuestions, function(index, val) {
			// 				var li=$("<li><a></a></li>");
			// 				li.find('a').attr('href', "#"+val.id);
			// 				li.find('a').text(i);

			// 				$("#collapseOne ul").append(li);
			// 				var div = single.clone();
			// 				div.find('h4').text(i+"."+val.content);
			// 				i=i+1;
			// 				div.find('h4').attr('id', val.id);
			// 				div.find(':radio').attr('name',val.id);
			// 				div.find('.option1').text("A、"+val.option1);
			// 				div.find('.option2').text("B、"+val.option2);
			// 				div.find('.option3').text("C、"+val.option3);
			// 				div.find('.option4').text("D、"+val.option4);
			// 				$('.singleQuestions').append(div);
			// 			});
			// 		}
			// 	}
			// });
			// // 加载本试卷多选题
			// $.ajax({
			// 	url: '/ExamOnline/getQuestionsByPageIdAndQuestionType.do',
			// 	type: 'post',
			// 	dataType: 'json',
			// 	data: {"pageId": pageId,"questionType":2},
			// 	success:function(data){
			// 		if (data.code==200) {
			// 			multipleQuestions=data.questions;
			// 			var i=1;
			// 			$.each(multipleQuestions, function(index, val) {
			// 				var li=$("<li><a></a></li>");
			// 				li.find('a').attr('href', "#"+val.id);
			// 				li.find('a').text(i);

			// 				$("#collapseTwo ul").append(li);
			// 				var div = multiple.clone();
			// 				div.find('h4').text(i+"."+val.content);
			// 				i=i+1;
			// 				div.find('h4').attr('id', val.id);
			// 				div.find(':checkbox').attr('name',val.id);
			// 				div.find('.option1').text("A、"+val.option1);
			// 				div.find('.option2').text("B、"+val.option2);
			// 				div.find('.option3').text("C、"+val.option3);
			// 				div.find('.option4').text("D、"+val.option4);
			// 				$('.multipleQuestions').append(div);
			// 			});
			// 		}
			// 	}
			// });
			// // 加载本试卷填空题
			// $.ajax({
			// 	url: '/ExamOnline/getQuestionsByPageIdAndQuestionType.do',
			// 	type: 'post',
			// 	dataType: 'json',
			// 	data: {"pageId": pageId,"questionType":3},
			// 	success:function(data){
			// 		if (data.code==200) {
			// 			blankQuestions=data.questions;
			// 			var i=1;
			// 			$.each(blankQuestions, function(index, val) {
			// 				var li=$("<li><a></a></li>");
			// 				li.find('a').attr('href', "#"+val.id);
			// 				li.find('a').text(i);

			// 				$("#collapseThree ul").append(li);
			// 				var div = blank.clone();
			// 				div.find('h5').text(i+"."+val.content);
			// 				i=i+1;
			// 				div.find('h5').attr('id', val.id);
			// 				div.find('input').attr('name', val.id);
			// 				$('.blankQuestions').append(div);
			// 			});
			// 		}
			// 	}
			// });
			// // 加载本试卷简答题
			// $.ajax({
			// 	url: '/ExamOnline/getQuestionsByPageIdAndQuestionType.do',
			// 	type: 'post',
			// 	dataType: 'json',
			// 	data: {"pageId": pageId,"questionType":4},
			// 	success:function(data){
			// 		if (data.code==200) {
			// 			subjectiveQuestions=data.questions;
			// 			i=1;
			// 			$.each(subjectiveQuestions, function(index, val) {
			// 				var li=$("<li><a></a></li>");
			// 				li.find('a').attr('href', "#"+val.id);
			// 				li.find('a').text(i);

			// 				$("#collapseFour ul").append(li);
			// 				var div = subjective.clone();
			// 				div.find('h5').text(i+"."+val.content);
			// 				i=i+1;
			// 				div.find('h5').attr('id', val.id);
			// 				div.find('textarea').attr('name', val.id);
			// 				$('.subjectiveQuestions').append(div);
			// 			});
			// 		}
			// 	}
			// });
		});
