$(document).ready(function(){

	$('.link-page').on('click', function() {
		$('.link-page').removeClass("active");
		$(this).addClass('active');
		var page_id = $(this).children('a').attr('href');
		$('.content-wrapper').addClass('hidden');
		$('.content-wrapper'+page_id).removeClass('hidden');

	});

});