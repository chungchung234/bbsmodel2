<%@ page import="com.example.model2.dto.MemberDto" %><%--
  Created by IntelliJ IDEA.
  User: chung
  Date: 2022/07/07
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object obj = session.getAttribute("login");
    MemberDto mem = null;
    if(obj == null){
%>
<script>
    alert('로그인 해 주십시오');
    location.href = "login.jsp";
</script>
<%
    }
    mem = (MemberDto)obj;
%>
<html>
<head>
    <title>pdswriter</title>
</head>
<body>

<h2>자료올리기</h2>

<form action="pdsupload.jsp" method="post" enctype="multipart/form-data">






</form>

</body>
</html>
