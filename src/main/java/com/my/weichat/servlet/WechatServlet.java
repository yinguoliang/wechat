package com.my.weichat.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.h2.tools.RunScript;
import org.json.JSONObject;

public class WechatServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  public void init() throws ServletException {
  }
  public void destroy() {
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    StringBuffer sb = new StringBuffer();
    InputStream is = request.getInputStream();
    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
    BufferedReader br = new BufferedReader(isr);
    String s = "";
    while ((s = br.readLine()) != null) {
      sb.append(s);
    }
    String xml = sb.toString();
    System.out.println("******Recive Msg*******:" + xml);
    String from = null;
    String to = null;
    String content = null;
    try {
      Document document = DocumentHelper.parseText(xml);
      Element root = document.getRootElement();
      Iterator iter = root.elementIterator();
      while (iter.hasNext()) {
        Element ele = (Element)iter.next();
        if ("ToUserName".equals(ele.getName()))
          to = ele.getText();
        else if ("FromUserName".equals(ele.getName()))
          from = ele.getText();
        else if ("Content".equals(ele.getName()))
          content = ele.getText();
      }
    }
    catch (DocumentException e1) {
      System.out.println("error occurs:" + e1.getMessage());
    }
    String result = "";

    String echostr = request.getParameter("echostr");
    if ((echostr != null) && (echostr.length() > 1)) {
      result = echostr;
    } else {
      long createTime = System.currentTimeMillis() / 1000L;
      String returnMsg = "";
      //getContentFromTuling(content);
      returnMsg = returnMsg+String.format("HI，猛戳<a href='http://myyglah.duapp.com?wid=%s'> 这里 </a>进入HTML5测试页面！",
    		  from );
      result = "<xml><ToUserName><![CDATA[" + 
        from + "]]></ToUserName>" + 
        "<FromUserName><![CDATA[" + to + "]]></FromUserName>" + 
        "<CreateTime>" + createTime + "</CreateTime>" + 
        "<MsgType><![CDATA[text]]></MsgType>" + 
        "<Content><![CDATA[" + returnMsg + "]]></Content>" + 
        "</xml>";
    }
    System.out.println("====return msg====" + result);
    try {
      OutputStream os = response.getOutputStream();
      os.write(result.getBytes("UTF-8"));
      os.flush();
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
  public String getContentFromTuling(String content) {
    try {
      String tuling123Url = "http://www.tuling123.com/openapi/api?key=797597c9e9c2d17334465623c8dec413&info=" + 
        URLEncoder.encode(content, "utf-8");
      GetMethod getMethod = new GetMethod(tuling123Url);
      HttpClient httpClient = new HttpClient();
      httpClient.executeMethod(getMethod);
      String response = getMethod.getResponseBodyAsString();

      JSONObject obj = new JSONObject(response);
      String text = (String)obj.get("text");
      return text;
    } catch (Exception ex) {
      if (ex.getMessage() == null) return "something wrong"; 
      return ex.getMessage();
    }
  }
}