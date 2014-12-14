<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<form name="fm" method="post" action="createOrder.do" data-ajax="false">
<input type="hidden" name="beauticianID" value="${beautician.beauticianID }"/>
<input type="hidden" name="beauticianName" value="${beautician.beauticianName }"/>
<input type="hidden" name="customerID" value="${customerID }"/>
<input type="hidden" name="orderStatus" value="0"/>
<div data-role="page" id="pageone">
	<div data-role="header">
	<a href="#" data-icon="back" data-rel="back">&nbsp</a>
		<h1>预约下单</h1>
	</div>
	<div data-role="contentx" >
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_12">
			预约美容师：${beautician.beauticianName }
			</div>
		</div>
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_4">预约时间：</div>
			<div class="grid_8">
			<input type="date" name="orderDate" value="${currentDate }" />
			<select name="orderTime" id="id_orderTime">
				<option value="9:00">9:00</option>
				<option value="9:30">9:30</option>
				<option value="10:00">10:00</option>
				<option value="10:30">10:30</option>
				<option value="11:00">11:00</option>
				<option value="13:00">13:00</option>
				<option value="13:30">13:30</option>
				<option value="14:00">14:00</option>
				<option value="14:30">14:30</option>
				<option value="15:00">15:00</option>
				<option value="15:30">15:30</option>
			</select>
			</div>
		</div>
		<div class="container_12" style="margin:5px">
			<div class="grid_4">备选时间：</div>
			<div class="grid_8">
			<input type="date" name="bakOrderDate" value="${currentDate }" />
			<select name="bakOrderTime" id="id_bakOrderTime">
				<option value="9:00">9:00</option>
				<option value="9:30">9:30</option>
				<option value="10:00">10:00</option>
				<option value="10:30">10:30</option>
				<option value="11:00">11:00</option>
				<option value="13:00">13:00</option>
				<option value="13:30">13:30</option>
				<option value="14:00">14:00</option>
				<option value="14:30">14:30</option>
				<option value="15:00">15:00</option>
				<option value="15:30">15:30</option>
			</select>
			</div>
		</div>
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_4">宠物信息：</div>
			<div class="grid_4">
			<select name="pet" id="id_pet">
				<option value="哈士奇">哈士奇</option>
				<option value="泰迪">泰迪</option>
				<option value="金毛">金毛</option>
				<option value="雪奈瑞">雪奈瑞</option>
				<option value="龙猫">龙猫</option>
				<option value="其他">其他</option>
			</select>
			</div>
			<div class="grid_4">
			<select name="petType" id="id_petType">
				<option value="small">30厘米以下</option>
				<option value="mid">30~50厘米</option>
				<option value="big">50厘米以上</option>
			</select>
			</div>
		</div>
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
		您需要花费的美容费用为：<span style="color:blue" id="id_span_orderPrice">200</span>元
		<input type="hidden" name="orderPrice" id="id_orderPrice" value="200"/>
		</div>
		<div class="container_12" style="border-top:solid;border-width:1px;margin:5px">
			<div class="grid_4">手机号</div>
			<div class="grid_8"><input type="text" name="cellPhone" value="${customer.customerCellPhone }"/></div>
			<div class="grid_4">地址</div>
			<div class="grid_8"><input type="text" name="addr" value="${customer.customerAddr }"/></div>
			<div class="grid_4">门牌号</div>
			<div class="grid_8"><input type="text" name="door" value="${customer.customerDoor }"/></div>
			<div class="grid_4">备注</div>
			<div class="grid_8"><input type="text" name="remark" value="${customer.customerRemark }"/></div>
		</div>
	</div>
	<div data-role="footer">
		<div data-role="navbar">
			<ul>
				<li><a href="#" id="createOrderTap">生成订单</a></li>
			</ul>
		</div>
	</div>
</div>
</form>
<script type="text/javascript">
var map = {
	"哈士奇-small":100,
	"哈士奇-mid":200,
	"哈士奇-big":300,
	"泰迪-small":350,
	"泰迪-mid":230,
	"泰迪-big":330,
	"金毛-small":120,
	"金毛-mid":220,
	"金毛-big":320,
	"雪奈瑞-small":153,
	"雪奈瑞-mid":203,
	"雪奈瑞-big":303
};
$("#createOrderTap").on("tap",function(){
	fm.action = "createOrder.do";
	fm.submit();
});
$("#id_pet,#id_petType").on("change",function(){
	var pet = $("#id_pet").val();
	var petType = $("#id_petType").val();
	var price = map[pet+"-"+petType];
	if(!price) price=400;
	$("#id_span_orderPrice").html(price);
	$("#id_orderPrice").val(price);
});
</script>
</body>
</html>
