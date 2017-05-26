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
var upExamId="";

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
			//加载班级
			$.ajax({
				url: '/ExamOnline/getAllClass.do',
				type: 'post',
				dataType: 'json',
				success:function(data){
					console.log(data)
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
				},
				error:function(){
					location.href="teacher-login.html";
				}
			});
			//加载所有试卷
			$.ajax({
				url: '/ExamOnline/getAllPage.do',
				type: 'post',
				dataType: 'json',
				success:function(data){
					console.log(data)
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

			
			// 新增试题
			$("#new").click(function(event) {
				$('#addExam').modal('show');
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
			$("#add").click(function(event) {
				var parameters=getFormFileds("addForm");
				$.ajax({
					url: '/ExamOnline/addExam.do',
					type: 'post',
					dataType: 'json',
					data: parameters,
					success:function(data){
						if (data.code==200) {
							$('#addExam').modal('hide');
						}
						alert(data.msg);
					}
				});
				
			});
			$("#up").click(function(event) {
				var parameters=getFormFileds("upForm");
				parameters.id=upExamId;
				$.ajax({
					url: '/ExamOnline/updateExamById.do',
					type: 'post',
					dataType: 'json',
					data: parameters,
					success:function(data){
						if (data.code==200) {
							$('#upExam').modal('hide');
						}
						alert(data.msg);
					}
				});
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
	// 点击显示详细信息
	$(".name")
	.click(
		function(event) {
			var id = $(this).siblings('.id').text();
			var name = $(this).text();
			var time = $(this).siblings('.time').text();
			var grade = $(this).siblings('.grade').text();
			var flag = $(this).siblings('.flag').text();
			var pageId=getPageIdByExamId(id);
			var pageName=getPageNameById(pageId);
			$("#name").text(name);
			$("#time").text(time);
			$("#grade").text(grade);
			$("#flag").text(flag);
			$("#pageName").text(pageName);
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
			upExamId=$(this).parent("td").siblings('.id').text();
			var upExamName=$(this).parent("td").siblings('.name').text();
			$("#upExam [name='name']").val(upExamName);
			$('#upExam').modal('show');
		});
	// 删除
	$(".delete").click(
		function(event) {
			var id=$(this).parent("td").siblings('.id').text();
			if(confirm("确认删除？")){
				$.post('/ExamOnline/deleteExamById.do', {'id': id}, function(data) {
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