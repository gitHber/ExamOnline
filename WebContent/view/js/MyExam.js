	jQuery(document).ready(function($) {
		$(".examtitle a").click(function(event) {
			$(".examtitle li").removeClass('active');
			$(".examtitle li").removeAttr('style');
			$(this).parent("li").addClass('active');
			$(this).parent("li").css('border-bottom', '3px solid red');
		});
	});

	