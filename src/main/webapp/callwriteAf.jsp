<%@ page import="static com.example.model2.util.CallUtil.two" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.example.model2.dao.CalendarDao" %>
<%@ page import="com.example.model2.dto.CalendarDto" %><%--
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

    String rdate = year + two(month) + two(day) + two(hour);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    CalendarDao dao = CalendarDao.getInstance();

    boolean isS = dao.addCalendar(new CalendarDto(id, title, content, rdate));
    if (isS) {
%>
  <script type="text/javascript">
      alert('add');
      location.href = "Calendar.jsp";

  </script>
<%
} else {
%>
<script type="text/javascript">
    alert('fail');
    location.href = "Calendar.jsp";

</script>

<%

    }

%>

</body>
</html>
