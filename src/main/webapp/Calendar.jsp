<%@ page import="com.example.model2.dto.MemberDto" %>
<%@ page import="com.example.model2.dto.CalendarDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.example.model2.dao.CalendarDao" %>
<%@ page import="static com.example.model2.util.CallUtil.nvl" %>
<%@ page import="static com.example.model2.util.CallUtil.two" %>
<%@ page import="static com.example.model2.util.CallUtil.*" %><%--
  Created by IntelliJ IDEA.
  User: chung
  Date: 2022/07/06
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>

<%
    Object obj = session.getAttribute("login");
    MemberDto mem = null;
    if (obj == null) {
%>
<script>
    alert('로그인 해 주십시오');
    location.href = "login.jsp";
</script>
<%
    }
    mem = (MemberDto) obj;
%>
<%--<%!--%>
<%--    public boolean nvl(String msg) {--%>
<%--        return msg == null || msg.trim().equals("") ? true : false;--%>
<%--    }--%>

<%--    public String two(String msg) {--%>
<%--        return msg.trim().length() < 2 ? "0" + msg.trim() : msg.trim();--%>
<%--    }--%>

<%--    public String callist(int year, int month, int day) {--%>
<%--        String str = "";--%>

<%--        str += String.format("$nbsp:<a href='callist.jsp?year=%d&month=%d&day=%d'>", year, month, day);--%>
<%--        str += String.format("%2d", day);--%>
<%--        str += String.format("</a>");--%>

<%--        return str;--%>

<%--    }--%>

<%--    public String showPen(int year, int month, int day) {--%>
<%--        String str = "";--%>

<%--        String image = "<img src='image/pen2.png' width='18px' height='18px'>";--%>
<%--        str = String.format("<a href='calwrite.jsp?year=%d&month=%d&day=%d'>%s</a>", year, month, day, image);--%>

<%--        return str;--%>

<%--    }--%>

<%--    public String dot3(String msg) {--%>
<%--        String str = "";--%>
<%--        if (msg.length() >= 10) {--%>
<%--            str = msg.substring(0, 10);--%>
<%--            str += "...";--%>
<%--        } else {--%>
<%--            str = msg.trim();--%>
<%--        }--%>

<%--        return str;--%>

<%--    }--%>

<%--    public String makeTable(int year, int month, int day, List<CalendarDto> list) {--%>
<%--        String str = "";--%>

<%--        String dates = (year + "") + two(month + "") + two(day + "");--%>

<%--        str += "<table>";--%>
<%--        for (CalendarDto dto : list) {--%>
<%--            if (dto.getRdate().substring(0, 8).equals(dates)) {--%>
<%--                str += "<tr>" +--%>
<%--                        "<td style='padding:0px;border:1px;background-color:white;border-color:blue;radius:3'>" +--%>
<%--                        "<a href='caldetail.jsp?seq=" + dto.getSeq() + "'>" +--%>
<%--                        "<font style='font-size:10px'>" +--%>
<%--                        dot3(dto.getTitle()) +--%>
<%--                        "</font></a>";--%>


<%--                str += "</td>";--%>
<%--                str += "</tr>";--%>


<%--            }--%>


<%--        }--%>
<%--        str += "</table>";--%>
<%--        return str;--%>
<%--    }--%>

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
            integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
            crossorigin="anonymous"></script>
    <title>Calendar</title>
</head>
<body>
<h2>일정관라</h2>

<%
    Calendar cal = Calendar.getInstance();

    cal.set(Calendar.DATE, 1);

    String syear = request.getParameter("year");
    String smonth = request.getParameter("month");

    int year = cal.get(Calendar.YEAR);
    if (nvl(syear) == false) {
        year = Integer.parseInt(syear);
    }

    int month = cal.get(Calendar.MONTH) + 1;
    if (nvl(smonth) == false) {
        month = Integer.parseInt(smonth);
    }

    if (month < 1) {
        month = 12;
        year--;

    }
    if (month > 12) {
        month = 1;
        year++;
    }

    cal.set(year, month - 1, 1);

    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    //year--
    String pp = String.format("<a href='%s?year=%d&month=%d'><img src='image/left.gif' witdh='20px' height='20px'></a>",
            "calendar.jsp", year - 1, month
    );
    //month--
    String p = String.format("<a href='%s?year=%d&month=%d'><img src='image/prec.gif' witdh='20px' height='20px'></a>",
            "calendar.jsp", year, month - 1
    );

    //month ++
    String n = String.format("<a href='%s?year=%d&month=%d'><img src='image/next.gif' witdh='20px' height='20px'></a>",
            "calendar.jsp", year, month + 1
    );

    //year++
    String nn = String.format("<a href='%s?year=%d&month=%d'><img src='image/last.gif' witdh='20px' height='20px'></a>",
            "calendar.jsp", year + 1, month
    );

    // DB
    CalendarDao dao = CalendarDao.getInstance();

    List<CalendarDto> list = dao.getCalendarList(mem.getId(), year + two(month + ""));

%>
<div align="center">

    <table class="table table-bordered" style="width: 65%">
        <col width="100">
        <col width="100">
        <col width="100">
        <col width="100">
        <col width="100">
        <col width="100">
        <col width="100">

        <tr>
            <td colspan="7" align="center">
                <%=pp %>&nbsp;&nbsp;<%=p%>&nbsp;&nbsp;&nbsp;&nbsp;
                <font color="black" style="font-size: 50px; font-family: fantasy">
                    <%= String.format("%d냔&nbsp;&nbsp;%d월", year, month)%>
                </font>
                &nbsp;&nbsp;
                <%=n%>&nbsp;&nbsp;<%=nn%>&nbsp;&nbsp;
            </td>
        </tr>
        <tr height="50" style="background-color: #f0f0f0; color: white;">
            <th class="text-center">일</th>
            <th class="text-center">월</th>
            <th class="text-center">화</th>
            <th class="text-center">수</th>
            <th class="text-center">목</th>
            <th class="text-center">금</th>
            <th class="text-center">토</th>

        </tr>
        <tr hright="100" align="left" valign="top">
            <%
                for (int i = 1; i < dayOfWeek; i++) {
            %>
            <td style="background-color: #cecece">&nbsp;</td>
            <%

                }
                int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int i = 1; i <= lastday; i++) {
            %>
            <td>
                <%= callist(year, month, i) %> &nbsp;&nbsp;<%= showPen(year, month, i) %>
                <%= makeTable(year, month, i, list)%>
            </td>
            <%
                if ((i + dayOfWeek - 1) % 7 == 0) {
            %>
        </tr>
        <tr hright="100" align="left" valign="top">

            <%

                }

            %>

            <%

                }


                cal.set(Calendar.DATE, lastday);
                int weekday = cal.get(Calendar.DAY_OF_WEEK);
                for (int i = 1; i < 7 - weekday; i++) {
            %>
            <td style="background-color: #cecece">&nbsp;</td>
            <%
                }
            %>

        </tr>
    </table>


</div>

</body>
</html>
