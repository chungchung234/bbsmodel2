<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String msg = request.getParameter("msg");
%>    

<%
if(msg.equals("OK")){
	%>
	<script type="text/javascript">
	alert("회원가입되었습니다");
	location.href = "./member?param=login";
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
	alert("가입되지 않았습니다. 다시 작성해 주십시오");
	location.href = "./member?param=regi";
	</script>
	<%
}
%>




