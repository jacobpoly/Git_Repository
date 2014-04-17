<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="jquery_mask/jquery.loadmask.css" />
<script type="text/javascript" src="Require/Js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="Require/Js/jquery.rotate.js"></script>
<script type="text/javascript" src="Require/Js/jquery.cookie.js"></script>
<script type="text/javascript" src="jquery_mask/jquery-latest.pack.js"></script>
<script type="text/javascript" src="jquery_mask/jquery.loadmask.js"></script>
<script type="text/javascript" src="Require/Js/actionForm.js"></script>

<title>관리자 페이지</title>
</head>
<style type="text/css" >
#excel_div {
    width:360px;
    height:200px; 
    margin : 0 auto ;
    border:1px solid #000;
}
</style>
<body>
	<form id="execl_form">
		<div id="excel_div">
			<h3>관리자 화면 엑셀 다운로드 </h3>


			<a href="${pageContext.request.contextPath}/excel.do" >
				<img src="images/Excel.jpg"> 이벤트 룰렛 총 결과 
			</a>
				
		</div>
	</form>
</body>
</html>