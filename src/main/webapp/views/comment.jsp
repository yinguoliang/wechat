<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List,com.my.weichat.domain.*" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="pubhead.jsp"/>
</head>
<body>
<%
List<Comment> commentList = (List<Comment>)request.getAttribute("commentList");
%>
<div data-role="page" id="pageone">
	<div data-role="content" >
		<div class="container_12" style="margin-bottom:2px;border-top:solid;border-width:1px">
		全部评论(<%=commentList.size() %>条)
		<span style="float:right">95%好评</span>
		</div>
		<%for(Comment c : commentList){ %>
		<div class="container_12" style="margin-bottom:2px;border-top:solid;border-width:1px">
			<div class="grid_7">
				<ul class='star-rating'>
					 <li><a href='#' title='一星' class='one-star'>1</a></li>
					 <li><a href='#' title='二星' class='two-stars'>2</a></li>
					 <li><a href='#' title='三星' class='three-stars'>3</a></li>
					 <li><a href='#' title='四星' class='four-stars'>4</a></li>
					 <li><a href='#' title='五星' class='five-stars'>5</a></li>
				</ul>
			</div>
			<div class="grid_5"><%=c.getOrderCode() %></div>
		</div>
		<div class="container_12" style="margin-bottom:2px">
			<%=c.getCommentDetail() %>
		</div>
		<div class="container_12" style="margin-bottom:2px">
			<div style="float:right">
				<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(c.getCommentTime()) %>
			</div>
		</div>
		<%} %>
	</div>
	<div data-role="footer">
		<div data-role="navbar">
			<ul>
				<li><a href="#">上一页</a></li>
				<li><a href="#">下一页</a></li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>
