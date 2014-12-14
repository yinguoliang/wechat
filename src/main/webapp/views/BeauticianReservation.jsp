<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.my.weichat.domain.*,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<div data-role="page" id="pageone">
	<div data-role="header">
		<h1>美容师</h1>
	</div>
	<div data-role="content" >
		<%
		List<Beautician> list = (List<Beautician>)request.getAttribute("beauticianList");
		for(Beautician b : list){ %>
		<div class="container_12" style="border:solid;border-width:1px;margin-bottom:2px">
			<div class="grid_12">
				<div class="grid_8">
					<div class="grid_4">
						<a href="comment.do?beauticianID=<%=b.getBeauticianID()%>">
						<img alt="man" src="image/man.jpg" height="80%" width="80%">
						</a>
					</div>
					<div class="grid_4">
						<%=b.getBeauticianName() %><br>
						100单
						<input type="hidden" name="beauticianName" value="<%=b.getBeauticianName() %>"/>
						<input type="hidden" name="beauticiaionID" value="<%=b.getBeauticianID() %>"/>
					</div>
				</div>
				<div class="grid_4">
					<div style="margin-top:10%">
						<span style="border:solid;border-width:1px"><strong>
						<a href="beauticianDetail.do?beauticianID=<%=b.getBeauticianID()%>">预约</a>
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
			<strong>特色：<%=b.getBeauticianFeature() %></strong><br>
			<strong>服务区域：<%=b.getBeauticianArea() %></strong><br>
			<strong>简介：</strong><%=b.getBeauticianRemark() %>
			<div style="float:right"><a href="comment.do?beauticianID=<%=b.getBeauticianID()%>">更多信息</a></div>
			</div>
		</div>
		<%} %>
	</div>
	<div data-role="footer">
		<div data-role="navbar">
			<ul>
				<li><a href="#">预约美容</a></li>
				<li><a href="showMyOrder.do">我的订单</a></li>
				<li><a href="#">费用咨询</a></li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>
<script>
function doTest(f){
	alert(f.value);
	$("input").each(function(){alert(this.name+":"+this.value);});
}
</script>
