<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<title> 룰렛 이벤트 </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta id="X-UA-Compatible" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script type="text/javascript" src="Require/Js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="Require/Js/jquery.rotate.js"></script>
	<script type="text/javascript" src="Require/Js/jquery.cookie.js"></script>
	<script type="text/javascript" src="Require/Js/deBugSystem.js"></script>
	<script type="text/javascript" src="Require/Js/neoGfw.js"></script>
	<script type="text/javascript" src="Require/Js/actionForm.js"></script>
	<script type="text/javascript" src="Require/Js/jsFunction.js"></script>
	<script type="text/javascript">
	var name = navigator.appName, 
	ver = navigator.appVersion,
	ver_int = parseInt(navigator.appVersion), 
	ua = navigator.userAgent, infostr;
//alert("버전 :: "+ ver);

if(name == "Microsoft Internet Explorer"){

	if(ver.indexOf("MSIE 8.0") != -1 || ver.indexOf("MSIE 7.0") != -1)  {		// 하위 브라우저
		//$(window).load(function(){
		$(document).ready(function() {	
			$("#wrap").remove();
			$("#ie_ver").val("down");
			document.getElementById("X-UA-Compatible").setAttribute("content", "IE=7;IE7;chrome=1");
			//console.log("하위");
		});

 
	}else{		// 지원 가능 브라우저 

		//$(window).load(function(){
		$(document).ready(function() {
			$("#wrap_down").remove();
			$("#ie_ver").val("up");
			document.getElementById("X-UA-Compatible").setAttribute("content", "IE=edge;chrome=1");
			//alert("상위");
		});

	}
}
else{

	//$(window).load(function(){
	$(document).ready(function() {

		alert("Internet Explorer 에서만 지원이 가능합니다.");
		window.close();
	
		$("#wrap_down").remove();
		$("#wrap").remove();
		//$("#ie_ver").val("up");
		//document.getElementById("X-UA-Compatible").setAttribute("content", "IE=edge;chrome=1");
		//alert("IE 외 브라우저");
	});

}
</script>
</head>
<body  onBlur="window.focus()">
	<form id="event_form" class="event_form" onsubmit="return false;">
		<input type="hidden" id="event_yn" class="event_yn" name="event_yn" value="${event_yn }">
		<input type="hidden" id="event_btn" class="event_btn" name="event_btn">
		<input type="hidden" id="result" class="result" name="result">
		<input type="hidden" id="result_no" class="result_no" name="result_no">
		<input type="hidden" id="result_txt" class="result_txt" name="result_txt">
		<input type="hidden" id="ie_ver" class="ie_ver" name="ie_ver">
	
		<input type="hidden" id="client_code" class="client_code" name="client_code" value="${client_code}">
		<input type="hidden" id="client_mem_code" class="client_mem_code" name="client_mem_code" value="${client_mem_code}">
		<input type="hidden" id="student_stt_code" class="student_stt_code" name="student_stt_code" value="${student_stt_code}">
		<input type="hidden" id="in_college_yn" class="in_college_yn" name="in_college_yn" value="${in_college_yn}">
	
<div id="wrap">
	<jsp:include page="header.jsp"/>
	<jsp:include page="ie_edge.jsp"/>
	<jsp:include page="footer.jsp"/>
</div>
<div id="wrap_down">
	<jsp:include page="header.jsp"/>
	<jsp:include page="ie_down.jsp"/>
	<jsp:include page="footer.jsp"/>
</div>		
</form>	
</body>
</html>