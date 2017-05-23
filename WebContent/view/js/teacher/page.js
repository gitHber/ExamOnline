
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
var courses = "";
var pages="";
var newPageId="";
jQuery(document).ready(function($) {
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
			tr = $(".page").clone();
			// 删除模板
			$(".page").remove();
			// 查询数据
			initSearch(1);
			$("#pageCount").text(pageCount);

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
	//新增试卷
	$("#new").click(function(event) {
		$('#addPage').modal('show');
	});
	// 提交试卷基本信息
	$("#submit").click(function(event) {
		$('#addPage').modal('hide');
		$("#total").text($("[name='total_cent']").val());
		var pageInfo=getFormFileds('newPageInfo');//试卷信息
		if ($("#newPageInfo [name='assembly_type']").val()=='1') {
			$.ajax({
				url: '/ExamOnline/addPage.do',
				type: 'post',
				dataType: 'json',
				async:false,
				data: pageInfo,
				success:function(data){
					console.log(data);
					if (data.code==200) {
						newPageId= data.page.id;//新生成试卷id
						$('#construct').modal('show');
					}else {
						alert(data.msg);
					}
				}
			});
		}else {
			$.ajax({
				url: '/ExamOnline/addPage.do',
				type: 'post',
				dataType: 'json',
				async:false,
				data: pageInfo,
				success:function(data){
					console.log(data);
					if (data.code==200) {
						alert(data.msg);
						location.href = "pageInfo.html?id="
						+ data.page.id;
					}else {
						alert(data.msg);
					}
				}
			});
		}
	});
	$("#submit2").click(function(event) {
		var ttcent=0;
		$(".tcent").each(function(index, el) {
			ttcent=ttcent+($(this).text()-0);
		});
		var total=$("#total").text()-0;
		var pageParameter=getFormFileds("newPageQuestionInfo");
		pageParameter.pageId=newPageId;
		if (ttcent<total) {
			alert('分数未满！');
		}else if(ttcent==total){
			$.ajax({
				url: '/ExamOnline/generatePage.do',
				type: 'post',
				dataType: 'json',
				data: pageParameter,
				success:function(data){
					if (data.code==200) {
						alert(data.msg);
						$('#construct').modal('hide');
					}else {
						alert(data.msg);
						$('#construct').modal('hide');
					}
				}
			});
			
		}

	});
	// 总分变化
	$(".num").change(function(event) {
		$(this).next().text(($(this).find('input').val()-0)*($(this).siblings('#cent').text()-0));
		var ttcent=0;
		$(".tcent").each(function(index, el) {
			ttcent=ttcent+($(this).text()-0);
		});
		var total=$("#total").text()-0;
		if (ttcent>total) {
			alert('已超过总分，请认真设置！');
			$("#ttcent").text(ttcent-($(this).find('input').val()-0)*($(this).siblings('#cent').text()-0));
			$(this).find('input').val('0');
			$(this).next().text('0');
		}else{
			$("#ttcent").text(ttcent);
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
		url : '/ExamOnline/getPageByPageIndex.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : comcondition,
		success : function(data) {
			console.log(data);
			pages = data.pages;
			$(".page").remove();
			if (data.code == 200) {
				$.each(data.pages, function(index, val) {
					var tr1 = tr.clone();
					tr1.find('.id').text(val.id);
					tr1.find('.name').text(val.name);
					tr1.find('.course').text(getCourseNameById(val.courseId))
					var typename = val.page_type==1?'期中':(val.page_type==2?'期末':'测试');
					tr1.find('.type').text(typename);
					tr1.find('.diff').text(
						val.diff == 1 ? '简单'
						: (val.diff == 2 ? '一般' : '困难'));
					$('#pagetable tbody').append(tr1);
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
			var page_type = $(this).siblings('.page_type').text();
			var page = getPageById(id);
			$("#name").text(page.name);
			$("#courseName").text(getCourseNameById(page.courseId));
			$("#total_cent").text(page.total_cent);
			$("#diff").text((page.diff==1?'简单':(page.diff==2?'一般':'困难')));
			$("#chapter_start").text(page.chapter_start);
			$("#chapter_end").text(page.chapter_end);
			var crea_time=new Date(page.crea_time).Format("yyyy-MM-dd HH:mm:ss");
			$("#crea_time").text(crea_time);
			var upda_time=new Date(page.upda_time).Format("yyyy-MM-dd HH:mm:ss");
			$("#upda_time").text(upda_time);
			$("#ans_time").text(page.ans_time);
			$("#page_type").text((page.page_type==1?'期中':(page.page_type==2?'期末':'测试')));
			$("#assembly_type").text((page.assembly_type==1?'自动组卷':'手动组卷'));
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
			location.href = "pageInfo.html?id="
			+ $(this).parent("td").siblings('.id').text();
		});
	// 删除
	$(".delete").click(
		function(event) {
			var id=$(this).parent("td").siblings('.id').text();
			if(confirm("确认删除？")){
				$.post('/ExamOnline/deletePageById.do', {'id': id}, function(data) {
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
//根据试卷id得到试卷
function getPageById(id) {
	var page = "";
	$.each(pages, function(index, val) {
		if (val.id == id) {
			page = val;
		}
	});
	return page;
}