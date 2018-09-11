<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Insert title here</title>  
<script type="text/javascript" src="js/comet4j.js"></script>  
<script type="text/javascript">  
    function init() {  
        var nowTime = document.getElementById('nowTime');  
          
        // 建立连接，conn 即web.xml中 CometServlet的<url-pattern>  
        JS.Engine.start('comet');  
          
        // 监听后台某个频道  
        JS.Engine.on({  
            timer : function(num1) {  
                nowTime.innerHTML = num1;  
            },  
        });  
    }  
</script>  
  
</head>  
<body onload="init()">  
    现在时间是：<span id="nowTime">...</span>  
</body>  
</html>  