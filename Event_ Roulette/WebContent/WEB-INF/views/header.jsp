	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<!-- Header -->
		<div id="header">POLY 재학생 감사 이벤트 : POLY를 생각하고 행운도 잡고! 여러분께 폴리는 어떤 의미인가요? POLY의 의미를 적고 행운도 잡으세요.</div>

		<!-- Step 1 -->
		<div id="step1">
			<!-- 입력형역 -->
			<div class="step1_input">
				<input type="text" id="memo" name="memo" maxlength="30" />
			</div>
			<!-- BT -->
			<p  class="btn_join">참여하기</p>
		</div>

		<!-- 룰렛-->
		<div id="roulette_wrap">
			<div id="point_arrow"></div>
			<div id="bt_start" class="${bt_start}">Start</div><!-- Step1 참여하기 클릭 후 Start 버튼 활성화 class="on" 추가 -->
