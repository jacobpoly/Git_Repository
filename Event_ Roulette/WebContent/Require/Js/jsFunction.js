/**
 * 
 */

getIDfocus = function() {
	document.getElementById('memo').focus();
},

disEnter = function() {
	$("#memo").on("keyup", function(e) {
		if (e.which == 13) {
			return false;
		}
	});
},

charByteSize = function (msg) {

    if (msg == null || msg.length == 0) return 0;
	var i,size = 0;
	var charCode,chr = null;

	for(i=0;i<msg.length;i++)
	{

		chr = msg.charAt(i);

		charCode = chr.charCodeAt(0);

		if (charCode <= 0x00007F) size += 1; else 

		if (charCode <= 0x0007FF) size += 2; else 

		if (charCode <= 0x00FFFF) size += 3; 

		else size += 4;

	}

	return size;
  },

emptyRoulette = function() {

	 //alert("진입 2");
	// disEnter();
	//$("#memo").attr("disabled", true);
	 document.getElementById('memo').disabled="true"; 
	//$("#memo").css("background-color", "#cccccc"); 
	 document.getElementById('memo').style.backgroundColor = "#cccccc";
	$(".btn_join").css("background-position-y", "0px");
	$(".btn_join").unbind("click");
	$("#rouletcanvas_1").hide();
	$("#bt_start").hide();
	$("#point_arrow").show();
},

$(document).ready(function() {

	var event_yn = $("#event_yn").val();
	
	//console.log("!! :: "+event_yn);

	if (event_yn == 'N') {
		$("#rouletcanvas_1").show();
		$("#bt_start").show();
		$("#point_arrow").show();
		$("#rouletcanvas_3").hide();

		$('#memo').keypress(function() { // 글자 수 체크 20 바이트
			var memo_cnt = charByteSize($(this).val());
			// console.log(memo_cnt );
			if (memo_cnt > 20) {
				alert("영어 20자 , 한글은 20자  입력이 가능합니다.");
				return false;
			}
		});

		$('.btn_join').click(function() { // 참여하기

			// if (confirm("정말 참여 하시겠습니까?") == true) { // 확인

			// var memo_len = $.trim($("#memo").val()).length;

			var memo_len = charByteSize($("#memo").val());

			if (memo_len == 0) {
				alert("POLY의 의미를 입력해주세요");
				// memoClick(memo_len);
				return false;
			} else {
				$("#memo").attr("readonly", true); // 데이터 전송 할때는 readonly로  사용
				$("#memo").css("background-color", "#cccccc");
				$(".btn_join").css("background-position-y", "0px");
				$(".btn_join").unbind("click");

				ActionSubmit('e_participation', 'event_form');

				$("#bt_start").click(function() { // 시작버튼

					var ie_ver = $("#ie_ver").val();

					ActionSubmit($("#event_btn").val(), 'event_form'); // 확률 연산
					$("#bt_start").attr('class', '');
					$("#bt_start").unbind("click");
					InitRoulet();

					// alert(ie_ver);
					if (ie_ver == "up") {
						rouletGame_start("up");
					//	document.embeds[0].play();
					} else {
						rouletGame_start("down");
					//	document.embeds[0].play();
					}

				});
			}

			// } else { // 취소
			// return false;
			// }

		});

	} else if (event_yn == 'Y') {
		
		emptyRoulette();
		$(".rouletcanvas_4").show();
	} else {
		emptyRoulette();
		$(".rouletcanvas_3").show();
	}

	memoClick = function(memo_len) {
		if (memo_len == 0) {
			$("#memo").click(function() {
				$(this).val("");
			});
		}

	}

});
