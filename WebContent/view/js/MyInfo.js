jQuery(document).ready(function($) {
	$("#retie").click(function(event) {
		$("#email").attr("disabled",false);
		$(this).text("激活邮箱");
	});
});