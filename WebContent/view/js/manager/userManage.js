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
var pageSize = 5;// 每页显示多少条数据
var pageCurrent = 1;// 当前页数
var itemCount = ""// 总计路数
var condition = "";// 分页条件

var tr = "";
var students = "";
var types = "";
var courses = "";
var upStudentId="";

jQuery(document).ready(
	function($) {
		//加载用户信息
		$.ajax({
			url : '/ExamOnline/getManagerInfoFromSession.do',
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if(data.code==200){
					var manager=data.manager;
					$(".name").text(manager.name);
				}
			},
			error:function(){
				location.href="manager-login.html";
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
							$("#addStudent [name='grade']").append(option.clone());
							$("#upStudent [name='grade']").append(option.clone());
						});
					}
				}
			});
			
			// 克隆模板
			tr = $(".student").clone();
			// 删除模板
			$(".student").remove();
			// 查询数据
			initSearch(1);
			$("#pageCount").text(pageCount);

			
			// 新增学生
			$("#new").click(function(event) {
				$('#addStudent').modal('show');
			});

			// 搜索
			$("#search").click(function(event) {
				condition=getFormFileds("searchCondition");
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
					url: '/ExamOnline/addStudent.do',
					type: 'post',
					dataType: 'json',
					data: parameters,
					success:function(data){
						if (data.code==200) {
							$('#addStudent').modal('hide');
						}
						alert(data.msg);
					}
				});
				
			});
			$("#up").click(function(event) {
				var parameters=getFormFileds("upForm");
				parameters.id=upStudentId;
				$.ajax({
					url: '/ExamOnline/updateStudentById.do',
					type: 'post',
					dataType: 'json',
					data: parameters,
					success:function(data){
						if (data.code==200) {
							$('#upStudent').modal('hide');
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
	var comcondition = {};
	if (condition != "") {
		comcondition = JSON.parse(JSON.stringify(condition));
		comcondition.pageCurrent=p;
		comcondition.pageSize=pageSize;
	} else {
		comcondition.pageCurrent=p;
		comcondition.pageSize=pageSize;
	}
	$.ajax({
		url : '/ExamOnline/getStudentByPageIndex.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : comcondition,
		success : function(data) {
			students = data.students;
			$(".student").remove();
			if (data.code == 200) {
				$.each(data.students, function(index, val) {
					var tr1 = tr.clone();
					tr1.find('.id').text(val.id);
					tr1.find('.name').text(val.name);
					tr1.find('.no').text(val.no);
					tr1.find('.grade').text(val.grade);
					tr1.find('.password').text(val.password);
					tr1.find('.email').text(val.email);
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
			upStudentId=$(this).parent("td").siblings('.id').text();
			$("#upForm [name='name']").val($(this).parent("td").siblings('.name').text());
			$("#upForm [name='password']").val($(this).parent("td").siblings('.password').text());
			$("#upForm [name='email']").val($(this).parent("td").siblings('.email').text());
			$('#upStudent').modal('show');
		});
	// 删除
	$(".delete").click(
		function(event) {
			var id=$(this).parent("td").siblings('.id').text();
			if(confirm("确认删除？")){
				$.post('/ExamOnline/deleteStudent.do', {'id': id}, function(data) {
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
