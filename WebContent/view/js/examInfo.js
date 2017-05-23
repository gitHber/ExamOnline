
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

jQuery(document).ready(function($) {
	var examId=$_GET['id'];
	var pageId;
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
				$("#name").text(examPage.name);
				$("#time").text(new Date(examPage.time).Format("yyyy-MM-dd HH:mm"));
				$("#page_type").text(page.page_type==1?'期中考试':(page.page_type==2?'期末考试':'测试'));
				$("#total_cent").text(page.total_cent);
				$("#ans_time").text(page.ans_time);
				$("#grade").text(examPage.grade);
			}
			
		}
	});
	
	$("#start").click(function(event) {
		//判断是不是已经考了
		$.ajax({
			url: '/ExamOnline/isExamed.do',
			type: 'post',
			async:false,
			dataType: 'json',
			data: {"examId": examId},
			success:function(data){
				if (data.code!=200) {
					alert(data.msg);
					location.href="index.html";
				}
			}
		});
		location.href="Page.html?id="+examId;
	});

});