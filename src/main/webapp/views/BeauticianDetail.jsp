<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.my.weichat.domain.*" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<div data-role="page" id="pageone">
	<div data-role="header">
	<a href="#" data-icon="back" data-rel="back">&nbsp</a>
		<h1>美容师详情</h1>
	</div>
	<%
	Beautician beautician = (Beautician)request.getAttribute("beautician");
	%>
	<div data-role="content" >
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_12">
				<div class="grid_8">
					<div class="grid_4">
						<img alt="man" src="image/man.jpg" height="80%" width="80%">
					</div>
					<div class="grid_4">
						<%=beautician.getBeauticianName() %><br>
						100单
					</div>
				</div>
				<div class="grid_4">
					<div style="margin-top:10%">
						<span style="border:solid;border-width:1px"><strong>
						<a href="dateDashboard.do">时间表</a>
						</strong></span>
					</div>
					<div>
						<ul class='star-rating'>
						  <li><a href='#' title='一星' class='one-star'>1</a></li>
						  <li><a href='#' title='二星' class='two-stars'>2</a></li>
						  <li><a href='#' title='三星' class='three-stars'>3</a></li>
						  <li><a href='#' title='四星' class='four-stars'>4</a></li>
						  <li><a href='#' title='五星' class='five-stars'>5</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="grid_12">
			<strong>特色：<%=beautician.getBeauticianFeature() %></strong><br>
			<strong>服务区域：<%=beautician.getBeauticianArea() %></strong><br>
			<strong>简介：</strong><%=beautician.getBeauticianRemark() %>
			</div>
		</div>
		<div class="container_12" style="border-top:solid;border-width:1px;margin-bottom:2px">
			<div class="grid_12">
			<strong>美容作品</strong>
			<div style="float:right"><a href="moreWorks.do?beauticianID=<%=beautician.getBeauticianID()%>">查看更多</a></div>
			</div>
			<div class="grid_12">
				<div class="grid_4" style="width:30%; height:30%;">
					<img alt="man" src="image/work.1.jpg" height="100%" width="80%">
				</div>
				<div class="grid_4" style="width:30%; height:30%;">
					<img alt="man" src="image/work.2.jpg" height="100%" width="80%">
				</div>
				<div class="grid_4" style="width:30%; height:30%;">
					<img alt="man" src="image/work.3.jpg" height="100%" width="80%">
				</div>
			</div>
		</div>
		<div class="container_12" style="border-top:solid;border-width:1px;margin-bottom:2px">
			<div class="grid_12">
				<strong>价位表：</strong>
			</div>
			<div class="grid_12">
				<div class="grid_9"><strong>小型犬(体长&lt30CM) 美容</strong></div>
				<div class="grid_3"><strong>200元</strong></div>
			</div>
			<div class="grid_12">
				<div class="grid_9"><strong>中型犬(30&lt体长&lt50CM) 美容</strong></div>
				<div class="grid_3"><strong>300元</strong></div>
			</div>
			<div class="grid_12">
				<div class="grid_9"><strong>大型犬(体长&gt30CM) 美容</strong></div>
				<div class="grid_3"><strong>500元</strong></div>
			</div>
		</div>
	</div>
	<div data-role="footer">
		<div data-role="navbar">
			<ul>
				<li><a href="reservePage.do?beauticianID=<%=beautician.getBeauticianID()%>">马上预约TA</a></li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>
