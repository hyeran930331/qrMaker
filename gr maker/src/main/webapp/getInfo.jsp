<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    	<h1>zxing Code 만들기</h1>
	    	<input id="name" type="text" name="name"/>
	    	<input id="phone" type="text" name="phone"/>
	    	<input id="IP" type="text" name="IP"/>
	    	<input id="GPS" type="text" name="GPS"/>
	    	
       		<input type="button" id="execute" value="QR코드생성"/>
    </div>    
        
    <div>
    	<h1>Code 결과</h1>
        <img id="img" style="display:none" onload="this.style.display='block'"/>
    </div>
</body>
</html>