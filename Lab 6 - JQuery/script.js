$(document).ready(function(){
    var picWidth = 200;
    var poz = 0;
    let sliding = 1;		
    $("li").each(function() {
        poz += picWidth;
        $(this).css("left",poz);
    });

    $('.slider').click(function(){
        sliding = 0;
        $('.popup').css("visibility", "visible");
        $('#popupimage').attr('src', $(this).attr('src'));
    });

    $(".popup").click(function(){
        sliding = 1;
        slide2();
        $(".popup").css("visibility", "hidden");
    })

    function slide2(){

        $("li").each(function(){
        var left = $(this).parent().offset().left + $(this).offset().left;
        if (left >= 1750) {
            $(this).css("left",left - 1750);
        }
        });

        
        $("li").animate({"left":"+=10px"}, 100);
        if(sliding == 1){
            setTimeout(slide2, 100);
        }
    }

    slide2();
});