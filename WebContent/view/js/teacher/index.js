jQuery(document).ready(function($) {
	function fixHeight() {
		var headerHeight = $("#switcher").height();
		$("#iframe").attr("height", $(window).height()+20+"px");
	}
	$(window).resize(function () {
		fixHeight();
	}).resize();
});
jQuery(document).ready(function($) {
	//加载用户信息
	$.post('/ExamOnline/getTeacherInfoFromSession.do', function(data) {
		console.log(data);
		if(data.code==200){
			var teacher=data.teacher;
			$(".name").text(teacher.name);
		}
	},'json');
});
