<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width"/>
<title>版本控制</title>
<script type="text/javascript">
window.onload = function(){
  //检测是否支持js
  try{//检测是否支持html5
    document.getElementById("c").getContext("2d");
    //document.location = '支持html5版的链接';
alert('支持html5版的链接');
  }catch(e){//否则跳到支持js版
    //document.location = '支持js版';
alert('支持js版');
  }
};
</script>
</head>
 
<body>
<canvas id='c'></canvas>
普通版
</body>
</html>