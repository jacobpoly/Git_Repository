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