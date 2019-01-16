$(document).ready(function() {
	
	$('.header .btn-toggle-menu').on('click', function() {
		$('.js-sidebar').toggleClass('is-toggled');
		$('.js-content').toggleClass('is-toggled');
		$(this).children('i').toggleClass('rotate');
	});
	
	
	$('.menu > ul li a').on('click', function(){
		$('.menu ul li a').not(this).removeClass('active');
		console.log($(this));
		
		if($(this).parent().hasClass('has-children')){
			
			var teste = $(this).parent().children('ul');
			if($(this).parent().children('ul').hasClass('opened'))
			{
				console.log('if');
				$(this).parent().children('ul').removeClass('opened');
			}
			else
			{
				console.log('else');
				$('ul').removeClass('opened');
				$(this).parent().children('ul').addClass('opened');
			}
			$(this).addClass('active');
		}
		
		else if($(this).parent().parent().parent().hasClass('has-children')){
			$(this).parent().parent().parent().children('a').addClass('active');
		}
		else {
			$(this).addClass('active');
			$('ul').removeClass('opened');
		}
	})
	
})

