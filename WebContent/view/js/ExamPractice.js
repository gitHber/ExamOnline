	jQuery(document).ready(function($) {
		$(".examtitle a").click(function(event) {
			$(".examtitle li").removeClass('active');
			$(".examtitle li").removeAttr('style');
			$(this).parent("li").addClass('active');
			$(this).parent("li").css('border-bottom', '3px solid red');
		});
		//参加考试
		$("#start").click(function(event) {
			window.open("examInfo.html");
		});
	});

	