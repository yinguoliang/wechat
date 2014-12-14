<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.my.weichat.domain.*" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<form name="fm" action="/weichat/beauticianReservation.do" method="post">
<div data-role="page" id="pageone">
	<div data-role="header">
		<h1>用户绑定</h1>
	</div>
	<div data-role="content" >
		<div class="container_12" style="border-top:solid;border-width:1px;margin-bottom:2px">
			<div class="grid_12">
				<div class="grid_4" style="width:30%; height:30%;">
					手机号码：
				</div>
				<div class="grid_8" style="width:30%; height:30%;">
					<input type="text" name="cellPhone" />
				</div>
			</div>
			<div class="grid_12">
			<input type="submit" value="绑定"/>
			</div>
		</div>
	</div>
	<div data-role="footer">
		<div data-role="navbar">
			<ul>
				<li>技术支持 XXX科技有限公司</li>
			</ul>
		</div>
	</div>
</div>
</form>
</body>
</html>
