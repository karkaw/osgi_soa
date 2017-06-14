$(function() {
    $('body').on('click', '.page-scroll a', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 600, 'easeInOutExpo');
        event.preventDefault();
    });
    $('body').on('click', '.backTop', function(event) {
        $('html, body').stop().animate({
            scrollTop: 0
        }, 600, 'easeInOutExpo');
        event.preventDefault();
    });
});

$(function () { 
    $(window).scroll(function () {
        if($(window).scrollTop() > 0){
            $('#navbar-fixed-top').stop().fadeOut(600);$('.backTop').show();
        } else{
            $('#navbar-fixed-top').stop().fadeIn(600);$('.backTop').hide();
        } 
        if($('#bs-example-navbar-collapse-1').hasClass('in') && ($('.backTop').css("display") == "block")){
            $('.navbar-toggle').click();$('.navbar-toggle').tap();
        }
    });
});

$('body').scrollspy({
    target: '.navbar-fixed-top'
})

// $('.navbar-collapse ul li a').click(function() {
//     $('.navbar-toggle:visible').click();
// });
