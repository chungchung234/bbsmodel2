<%@ page import="com.example.model2.dao.PdsDao" %>
<%@ page import="com.example.model2.dto.PdsDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: chung
  Date: 2022/07/07
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    PdsDao dao = PdsDao.getInstance();
    List<PdsDto> dtoList = dao.getPdsList();

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>pdslist</title>
</head>
<body>

<h2>자료실</h2>
<table border="1">
    <col width="50">
    <col width="100">
    <col width="400">
    <col width="50">
    <col width="50">
    <col width="50">
    <col width="100">

    <tr>
        <th>
            번호
        </th>
        <th>
            작성자
        </th>
        <th>
            제목
        </th>
        <th>
            다운로드
        </th>
        <th>
            조회수
        </th>
        <th>
            다운수
        </th>
        <th>
            작성일
        </th>
    </tr>
    <%
        for (int i = 0; i < dtoList.size(); i++) {
            PdsDto pds = list.get(i);
    %>
    <tr>
        <th><%=i + 1%>
        </th>
        <td><%=pds.getId()%>
        </td>
        <td>
            <a href="pdsdetai;.jsp?seq=<%=pds.getSeq()%>"><%=pds.getTitle()%>
            </a>
                </td>
        <td>
            <input type="button" name="btndown" value="다운로드" onclick="filedownLoad('<%=pds.getNewfilename()%>',<%=pds.getSeq()%>>)">
        </td>
        <td>
            <%=pds.getReadcount()%>
        </td>
        <td>
            <%=pds.getDowncount()%>
        </td>
        <td><%=pds.getRegdate()%></td>
    </tr>
    <%
        }
    %>
</table>
<br><br>
<a href="pdswrite.jsp">자료올리기</a>
</body>
</html>
