<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<style type="text/css">
.center{
	margin: auto;
	width: 60%;
	border: 3px solid #00ff00;
	padding: 10px;
}
</style>

</head>
<body>

<h2>회원가입</h2>

<div class="center">

<form action="<%=request.getContextPath() %>/member" method="post">
<input type="hidden" name="param" value="regiAf">

<table border="1">
<tr>
	<th>아이디</th>
	<td>
		<input type="text" id="id" name="id" size="20"><br>
		<p id="idcheck" style="font-size: 8px"></p>
		<input type="button" id="idBtn" value="아이디확인">
	</td>
</tr>
<tr>
	<th>패스워드</th>
	<td>
		<input type="text" name="pwd" size="20">
	</td>
</tr>
<tr>
	<th>이름</th>
	<td>
		<input type="text" name="name" size="20">
	</td>
</tr>
<tr>
	<th>이메일</th>
	<td>
		<input type="text" name="email" size="20">
	</td>
</tr>

<tr>
	<td colspan="2">
		<input type="submit" value="회원가입">
	</td>
</tr>

</table>
</form>
</div>

<script type="text/javascript">
$(function () {
	
	$("#idBtn").click(function () {
	//	alert("btn click");
	
		$.ajax({
			type:"post",
			url:"../member?param=idcheck",
			data:{ "id":$("#id").val() },
			success:function( data ){
				if(data.msg.trim() == "YES"){
					$("#idcheck").css("color", "#0000ff");
					$("#idcheck").html('사용할 수 있는 id입니다');
				}else{
					$("#idcheck").css("color", "#ff0000");
					$("#idcheck").html('사용 중인 id입니다');
					$("#id").val("");
				}			
			},
			error:function(){
				alert("error");
			}
		});
		
	});
	
});
</script>

</body>
</html>






