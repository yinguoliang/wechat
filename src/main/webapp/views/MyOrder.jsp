<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List,com.my.weichat.domain.Order" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<%!
String translateStatus(Order order){
	if("0".equals(order.getOrderStatus())) return "待确认";
	if("1".equals(order.getOrderStatus())) return "已确认，待上门服务";
	if("2".equals(order.getOrderStatus())) return "已完成";
	return "未知";
}
%>
<%
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	List<Order> orderList = (List<Order>)request.getAttribute("orderList");
%>
<div data-role="page" id="pageone">
	<div data-role="header">
	<a href="#" data-icon="back" data-rel="back">&nbsp</a>
		<h1>我的订单</h1>
	</div>
	<div data-role="content" >
		<%
		if(orderList.size()>0)
		for(Order order : orderList){ %>
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_4">预约时间：</div>
			<div class="grid_8">
			<%=sdf.format(order.getOrderDate())+" "+order.getOrderTime() %>
			</div>
			<div class="grid_4">备选时间：</div>
			<div class="grid_8">
			<%=sdf.format(order.getBakOrderDate())+" "+order.getBakOrderTime() %>
			</div>
			<div class="grid_4">预约地址：</div>
			<div class="grid_8">
			<%=order.getAddr()+" "+order.getDoor() %>
			</div>
			<div class="grid_4">订单状态：</div>
			<div class="grid_8">
			<%=translateStatus(order) %>
			<div style="float:right"><span style="border: solid;border-width: 1px">
			<%if("0".equals(order.getOrderStatus())){ %>
			<a href="Payment.do?orderID=<%=order.getOrderID()%>">支付</a></span>
			<%}else{%>
			<a href="InputComment.do?orderID=<%=order.getOrderID()%>">评价</a></span></div>
			<%} %>
			</div>
			</div>
		</div>
		<%}
		else{
		%>
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
		<div class="grid_12">Hi，亲，你还没有下单哦，点击<a href="index.do">这里</a>下单</div>
		</div>
		<%} %>
	</div>
</div>
</body>
</html>
