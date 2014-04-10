/**
 * 
 */
/*
$(window).load(function() { InitRoulet(); 
alert("1");
	});
*/	
$(document).ready(function() { 	InitRoulet();

	var event_yn = $("#event_yn").val();
	
	if (event_yn == 'N') {

		$("#rouletcanvas_1").show();
		$("#bt_start").show();
		$("#point_arrow").show();
		$("#rouletcanvas_3").hide();

		$('.btn_join').click(function() {
			if (confirm("정말 참여 하시겠습니까?") == true){    //확인

				$("#memo").attr("disabled",true);
				$(".btn_join").unbind("click");
				
				ActionSubmit('e_participation', 'event_form');

					$("#bt_start").attr('class', 'on');

				$("#bt_start").click(function(){
				
						$("#bt_start").attr('class', '');
						
						$("#bt_start").unbind("click"); 
				
						rouletGame_start();
						
				});
				
			}else{   //취소
			    return false;
			}
			
		});

	} else if (event_yn == 'Y') {

		emptyRoulette();
		$("#rouletcanvas_3").show();

	}

});

function test (){
	alert("${event_yn}");  // el 테스트
}

function emptyRoulette(){
	
	$(".btn_join").unbind("click");
	$("#rouletcanvas_1").hide();
	$("#bt_start").hide();
	$("#point_arrow").show();
}
