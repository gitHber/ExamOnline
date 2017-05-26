jQuery(document).ready(function($) {
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
});
