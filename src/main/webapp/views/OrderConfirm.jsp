<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>订单确认</h1>
  </div>
  <div data-role="content" >
    <p style="border:solid;border-width: 1px">
    H您的订单已经生成，我们的工作人员会在24小时内与您电话沟通确认订单。请您保持电话畅通，谢谢！</p>
    <div align="center"><a href="showMyOrder.do">确定</a></div>
  </div>
</div>
</body>
</html>
