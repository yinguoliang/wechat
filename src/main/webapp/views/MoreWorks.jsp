<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,com.my.weichat.domain.*" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<div data-role="page" id="pageone">
	<div data-role="header">
	<a href="#" data-icon="back" data-rel="back">&nbsp</a>
		<h1>TA的美容作品</h1>
	</div>
	<%
	ArrayList<BeauticianWorks> beauticianWorksList = (ArrayList<BeauticianWorks>)request.getAttribute("beauticianWorksList");
	%>
	<div data-role="content" >
		<div class="container_12" style="margin-bottom:2px">
			<%
			BeauticianWorks b = null;
			while(beauticianWorksList.size()>0){
			%>
			<div class="container_12">
				<%
				for(int i=0;i<3;i++)//每行显示3个
				if(beauticianWorksList.size()>0){
				b = beauticianWorksList.remove(0);
				%>
				<div class="grid_4" style="height:33%;width:33%" >
					<div>
					<a href="worksDetail.do?works_id=<%=b.getWorksID()%>"><img alt="man" src="<%=b.getWorksImg() %>" height="80%" width="80%">
					</a></div>
					<div>
						<strong>耗时：<%=b.getWorksSpendTime() %><br>保持：<%=b.getWorksDuration() %></strong>
					</div>
				</div>
				<%} %>
		</div>
		<%} %>
	</div>
	<div data-role="footer">
		<div data-role="navbar">
			<ul>
				<li><a href="#">前一页</a></li>
				<li><a href="#">后一页</a></li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>
