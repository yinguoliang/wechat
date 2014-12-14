<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.my.weichat.domain.*" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<form action="addComment.do" method="post">
<%
Order order = (Order)request.getAttribute("order");
Beautician beautician = (Beautician)request.getAttribute("beautician");
%>
<input type="hidden" name="beauticianID" value="<%=order.getBeauticianID()%>"/>
<input type="hidden" name="orderID" value="<%=order.getOrderID()%>"/>
<input type="hidden" name="orderCode" value="<%=order.getOrderCode()%>"/>
<input type="hidden" name="customerID" value="${customerID }"/>
<div data-role="page" id="pageone">
	<div data-role="header">
	<a href="#" data-icon="back" data-rel="back">&nbsp</a>
		<h1>发表评价</h1>
	</div>
	<div data-role="content" >
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_4">美容师：</div>
			<div class="grid_8"><%=beautician.getBeauticianName() %></div>
		</div>
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div>
			评价：
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
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
		<div data-role="fieldcontain">
		    <label for="info">评价内容</label>
		    <textarea name="commentDetail" id="info"></textarea>
		</div>
		</div>
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_4">
			<label for="blue">匿名</label>
          	<input type="checkbox" name="isAnonymous" value="1">
			</div>
			<div class="grid_8">
			<input type="submit" value="发表"/>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>
