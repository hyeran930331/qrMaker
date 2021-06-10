<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>egovTest</title>
    <script type="text/javaScript" language="javascript" defer="defer">
    
        $(document).ready(function(){
            console.log('$(document).ready');
            $("#execute").click(function(){
                url = "/createCode.do";
                var content = $("#content").val();
                  
                $("#img").attr("src", url+"?content="+content);
            });
        });
        
    </script>
</head>
<body>
   
        
    <div>
    	<!-- form으로 감싸야지 넘어가지! -->
    	<form method="post" action="${pageContext.request.contextPath}/getUserInfo">
    	<h1>zxing Code 만들기</h1>
    		<!-- 오류  https://aljjabaegi.tistory.com/187 https://hunit.tistory.com/399 -->
	    	<input id="name" type="text" name="name"/>
	    	<input id="phone" type="text" name="phone"/>
	    	<input id="IP" type="text" name="IP"/>
	    	<input id="GPS" type="text" name="GPS"/>
	    	
       		<input type="button" id="execute" value="QR코드생성"/>
       	</form>
    </div>    
        
    <div>
    	<h1>Code 결과</h1>
        <img id="img" style="display:none" onload="this.style.display='block'"/>
    </div>
   
   <c:if test="${qrName == 'a'}">
      <h1>입력 폼</h1>
      <form method="post" action="${pageContext.request.contextPath}/QRCheck">
         username : <input type="text" name="username">
         gender : <input type="text" name="gender">
         age : <input type="text" name="age">
         address : <input type="text" name="address">
         gps : <input type="text" name="gps">
         <button>제출</button>
      </form>
   </c:if>
   
   <c:if test="${qrName != 'a'}">
      <img src="${pageContext.request.contextPath}/img/${qrName}">
   </c:if>

</body>
</html>