<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<form method="post" name="fm">
<div data-role="page" id="pageone">
	<div data-role="header">
	<a href="#" data-icon="back" data-rel="back">&nbsp</a>
		<h1>订单支付</h1>
	</div>
	<div data-role="content" >
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_12" style="border:solid;border-width:1px;margin:5px">
			支付金额：200元
			</div>
			<div class="grid_12" style="border:solid;border-width:1px;margin:5px">
			<input type="hidden" name="orderID" value="${orderID }"/>
			<input type="button" onclick="doPaid('WP')" value="微信支付"/>
			<input type="button" onclick="doPaid('OP')" value="我已线下支付"/>
			</div>
		</div>
	</div>
</div>
</form>
</body>
<script>
	function doPaid(type){
		if(type=='OP'){
			fm.action="doOfflinePaid.do?type='OP'";
			fm.submit();
		}else{
			alert("还未实现微信支付!");
			return false;
		}
	}
</script>
</html>
