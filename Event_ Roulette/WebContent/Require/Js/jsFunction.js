/**
 * 
 */

$(document).ready(function() { 	InitRoulet();

	var event_yn = $("#event_yn").val();
	
	if (event_yn == 'N') {

		$("#rouletcanvas_1").show();
		$("#bt_start").show();
		$("#point_arrow").show();
		$("#rouletcanvas_3").hide();

		$('.btn_join').click(function() {		// 참여하기 
			if (confirm("정말 참여 하시겠습니까?") == true){    //확인

				$("#memo").attr("readonly",true);  // 데이터 전송 할때는 readonly로 사용
				$(".btn_join").unbind("click");
				
				ActionSubmit('e_participation', 'event_form');

					$("#bt_start").attr('class', 'on');

				$("#bt_start").click(function(){		// 시작버튼
				
						$("#bt_start").attr('class', '');
						$("#bt_start").unbind("click"); 
						
						ActionSubmit('e_start_btn', 'event_form');  // 확률 연산 
				
					
						/*
						 * 
						 * 응답 데이터 입력 구간
				
							*/
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

function emptyRoulette(){
	
	$(".btn_join").unbind("click");
	$("#rouletcanvas_1").hide();
	$("#bt_start").hide();
	$("#point_arrow").show();
}
