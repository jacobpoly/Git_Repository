<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title> 룰렛 이벤트 </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script type="text/javascript" src="Require/Js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="Require/Js/jquery.rotate.js"></script>
	<script type="text/javascript" src="Require/Js/jquery.cookie.js"></script>
	<script type="text/javascript" src="Require/Js/deBugSystem.js"></script>
	<script type="text/javascript" src="Require/Js/neoGfw.js"></script>
	<script type="text/javascript" src="Require/Js/actionForm.js"></script>
	<script type="text/javascript" src="Require/Js/jsFunction.js"></script>
</head>
<body>
	<form id="event_form" class="event_form">
	<input type="hidden" id="event_yn" class="event_yn" name="event_yn" value="${event_yn }">
	<input type="hidden" id="result" class="result" name="result">
	<input type="hidden" id="result_no" class="result_no" name="result_no">
	<input type="hidden" id="result_txt" class="result_txt" name="result_txt">
	<script type="text/javascript">
	

	var Base64 = {

		// private property
		_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

		// public method for encoding
		encode : function (input) {
			var output = "";
			var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
			var i = 0;

			input = Base64._utf8_encode(input);

			while (i < input.length) {

				chr1 = input.charCodeAt(i++);
				chr2 = input.charCodeAt(i++);
				chr3 = input.charCodeAt(i++);

				enc1 = chr1 >> 2;
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
				enc4 = chr3 & 63;

				if (isNaN(chr2)) {
					enc3 = enc4 = 64;
				} else if (isNaN(chr3)) {
					enc4 = 64;
				}

				output = output +
				this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
				this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

			}

			return output;
		},

		// public method for decoding
		decode : function (input) {
			var output = "";
			var chr1, chr2, chr3;
			var enc1, enc2, enc3, enc4;
			var i = 0;

			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

			while (i < input.length) {

				enc1 = this._keyStr.indexOf(input.charAt(i++));
				enc2 = this._keyStr.indexOf(input.charAt(i++));
				enc3 = this._keyStr.indexOf(input.charAt(i++));
				enc4 = this._keyStr.indexOf(input.charAt(i++));

				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;

				output = output + String.fromCharCode(chr1);

				if (enc3 != 64) {
					output = output + String.fromCharCode(chr2);
				}
				if (enc4 != 64) {
					output = output + String.fromCharCode(chr3);
				}

			}

			output = Base64._utf8_decode(output);

			return output;

		},

		// private method for UTF-8 encoding
		_utf8_encode : function (string) {
			string = string.replace(/\r\n/g,"\n");
			var utftext = "";

			for (var n = 0; n < string.length; n++) {

				var c = string.charCodeAt(n);

				if (c < 128) {
					utftext += String.fromCharCode(c);
				}
				else if((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				}
				else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}

			}

			return utftext;
		},

		// private method for UTF-8 decoding
		_utf8_decode : function (utftext) {
			var string = "";
			var i = 0;
			var c = c1 = c2 = 0;

			while ( i < utftext.length ) {

				c = utftext.charCodeAt(i);

				if (c < 128) {
					string += String.fromCharCode(c);
					i++;
				}
				else if((c > 191) && (c < 224)) {
					c2 = utftext.charCodeAt(i+1);
					string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
					i += 2;
				}
				else {
					c2 = utftext.charCodeAt(i+1);
					c3 = utftext.charCodeAt(i+2);
					string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
					i += 3;
				}

			}

			return string;
		}

	}
	
	</script>
	
	<div id="wrap">

		<!-- Header -->
		<div id="header">POLY 재학생 감사 이벤트 : POLY를 생각하고 행운도 잡고! 여러분께 폴리는 어떤 의미인가요? POLY의 의미를 적고 행운도 잡으세요.</div>

		<!-- Step 1 -->
		<div id="step1">
			<!-- 입력형역 -->
			<div class="step1_input">
				<input type="text" id="memo" name="memo" />
			</div>
			<!-- BT -->
			<p  class="btn_join">참여하기</p>
		</div>

		<!-- 룰렛-->
		<div id="roulette_wrap">
			<div id="point_arrow"></div>
			<div id="bt_start" class="${bt_start}">Start</div><!-- Step1 참여하기 클릭 후 Start 버튼 활성화 class="on" 추가 -->

			<!-- 기본 룰렛 -->
			<canvas width="245" height="245" class="rouletcanvas_1" id="rouletcanvas_1"></canvas>

			<!-- 당첨 -->
			<div class="rouletcanvas_2" id="rouletcanvas_2">
				<p><span><!-- 당첨삼품 별 변경 적용 영역 -->"KPSmall 5만원권"</span><br />당첨되셨습니다.</p>
			</div>
			<!-- 꽝 -->
			<div class="rouletcanvas_3" id="rouletcanvas_3">
				아쉽지만 또 다른 이벤트를 기대해주세요~
			</div>
			<!-- 재접속 -->
			<div class="rouletcanvas_4" id="rouletcanvas_3">
				이미 이벤트에 참여하셨습니다. 다음에 새로운 이벤트로 찾아뵙겠습니다.
			</div>
		</div>

		<!-- Footer -->
		<div id="footer">
			응모기간 4월 28일 ~ 5월 19일. 당첨되신 상품은 이벤트 완료 후 소속캠퍼스로 일괄 배송됩니다. 1인 1회만 참여하실 수 있습니다.
		</div>

	</div>
</form>	
</body>
</html>