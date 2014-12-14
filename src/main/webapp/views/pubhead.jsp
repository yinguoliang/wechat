<%
String webappUrl = request.getSession().getServletContext().getContextPath();
%>
<meta name="viewport" content="width=device-width,user-scalable=yes,minimum-scale=1.0,maximum-scale=1.0"/>
<link rel="stylesheet" href="<%=webappUrl%>/css/jquery-mobile-960.css"/>
<link rel="stylesheet" href="<%=webappUrl%>/css/my.css"/>
<link rel="stylesheet" href="<%=webappUrl%>/css/jquery.mobile-1.4.5.min.css">
<script src="<%=webappUrl%>/js/jquery.min.js"></script>
<script src="<%=webappUrl%>/js/jquery.mobile-1.4.5.min.js"></script>
<script>
$.mobile.ajaxEnabled = false;
</script>