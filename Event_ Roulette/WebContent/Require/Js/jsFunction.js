/**
 * 
 */

getIDfocus = function() {
	document.getElementById('memo').focus();
},

emptyRoulette = function() {

	$(".btn_join").unbind("click");
	$("#rouletcanvas_1").hide();
	$("#bt_start").hide();
	$("#point_arrow").show();
},

$(document).ready(function() {

	var event_yn = $("#event_yn").val();

	if (event_yn == 'N') {

		$("#rouletcanvas_1").show();
		$("#bt_start").show();
		$("#point_arrow").show();
		$("#rouletcanvas_3").hide();

		$('.btn_join').click(function() { // 참여하기

			if (confirm("정말 참여 하시겠습니까?") == true) { // 확인

				var memo_len = $.trim($("#memo").val()).length;

				if (memo_len == 0) {
					alert("네모를 입력해주세요");
					memoClick(memo_len);
					return false;
				} else {
					$("#memo").attr("readonly", true); // 데이터 전송 할때는 readonly로
					// 사용
					$("#memo").css("background-color", "#cccccc");
					$(".btn_join").unbind("click");

					ActionSubmit('e_participation', 'event_form');

					$("#bt_start").attr('class', 'on');

					$("#bt_start").click(function() { // 시작버튼

						InitRoulet();
						$("#bt_start").attr('class', '');
						$("#bt_start").unbind("click");

						ActionSubmit('e_start_btn', 'event_form'); // 확률 연산

						rouletGame_start();

					});
				}

			} else { // 취소
				return false;
			}

		});

	} else if (event_yn == 'Y') {

		emptyRoulette();
		$("#rouletcanvas_3").show();
	}

	memoClick = function(memo_len) {
		if (memo_len == 0) {
			$("#memo").click(function() {
				$(this).val("");
			});
		}

	}
});
