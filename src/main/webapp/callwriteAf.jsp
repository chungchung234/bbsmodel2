<%--
  Created by IntelliJ IDEA.
  User: chung
  Date: 2022/07/06
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    String hour = request.getParameter("hour");
    String min = request.getParameter("min");


%>

<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
