<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function getJson() {     
  $.ajax({
   url : "/jsonTest.do",
   type : 'POST',    
   dataType: "json",     
   success : function(data){
   
   var jsondata = JSON.stringify(data);
   
   $("#obj").append(jsondata);
   },
   error: function(data){},
   beforeSend: function() {},
   complete: function() {}       
  });     
 }
</script>
</head>
<body>
<a href="#getJson" onclick="javascript:getJson()">getJson</a>
<br/>
<br/>
<div id="obj"></div>
</body>
</html>