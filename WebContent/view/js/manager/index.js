jQuery(document).ready(function($) {
	//加载用户信息
	$.post('/ExamOnline/getManagerInfoFromSession.do', function(data) {
		console.log(data);
		if(data.code==200){
			var manager=data.manager;
			$(".name").text(manager.name);
		}
	},'json');
});
