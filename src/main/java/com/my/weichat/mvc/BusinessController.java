package com.my.weichat.mvc;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.my.weichat.domain.Beautician;
import com.my.weichat.domain.BeauticianWorks;
import com.my.weichat.domain.Comment;
import com.my.weichat.domain.Customer;
import com.my.weichat.domain.Order;
import com.my.weichat.pub.PubUtil;
import com.my.weichat.pub.WebUtils;
import com.my.weichat.repo.BeauticianDao;
import com.my.weichat.repo.CustomerDao;
import com.my.weichat.repo.OrderDao;
import com.my.weichat.repo.SequenceDao;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

@Controller
public class BusinessController {
	@Autowired
	JdbcTemplate jdbcTemplate = null;
	@Autowired
	private JavaMailSender javaMailSender = null;
	@Autowired
	private SequenceDao sequenceDao = null;
	@Autowired
	private OrderDao orderDao = null;
	@Autowired
	private BeauticianDao beauticianDao;
	@Autowired
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao){
		this.customerDao = customerDao;
	}
	
	public void setOrderDao(OrderDao orderDao){
		this.orderDao = orderDao;
	}
	public void setSequence(SequenceDao sequenceDao){
		this.sequenceDao = sequenceDao;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	public void setBeauticianDao(BeauticianDao beauticianDao){
		this.beauticianDao = beauticianDao;
	}
	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public HttpServletRequest getRequest(){
		HttpServletRequest request =  
				((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	@RequestMapping ("/beauticianReservation" )
	public ModelAndView beauticianReservation() throws Exception{
		List<Beautician> beauticianList = beauticianDao.queryAll();
		ModelAndView mv = new ModelAndView("BeauticianReservation","beauticianList",beauticianList);
		return mv;
	}
	@RequestMapping ("/index" )
	public ModelAndView index(){
		HttpServletRequest request = this.getRequest();
		System.out.println(request.getHeader("User-Agent"));
		String wid = request.getParameter("wid");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("wid",wid);
		return mv;
	}
	@RequestMapping ("/beauticianDetail" )
	public ModelAndView BeauticianDetail() throws Exception{
		HttpServletRequest request = this.getRequest();
		String beauticianID = request.getParameter("beauticianID");
		Beautician beautician = beauticianDao.findByPrimaryKey(Integer.parseInt(beauticianID));
		ModelAndView mv = new ModelAndView("BeauticianDetail","beautician",beautician);
		return mv;
	}
	@RequestMapping ("/moreWorks" )
	public ModelAndView MoreWorks(){
		HttpServletRequest request = this.getRequest();
		String beauticianID = request.getParameter("beauticianID");
		List<BeauticianWorks> beauticianWorksList = 
				this.jdbcTemplate.query(
						"select * from t_beauticianworks where beautician_ID=?", 
						new Object[]{beauticianID}, 
						new ResultSetExtractor<List<BeauticianWorks>>(){
			public List<BeauticianWorks> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<BeauticianWorks> list = new ArrayList<BeauticianWorks>();
				while(rs.next()){
					BeauticianWorks w = new BeauticianWorks();
					w.setBeauticianID(rs.getInt("Beautician_ID"));
					w.setWorksDuration(rs.getString("works_duration"));
					w.setWorksID(rs.getInt("Works_id"));
					w.setWorksImg(rs.getString("works_img"));
					w.setWorksRemark(rs.getString("works_remark"));
					w.setWorksSpendTime(rs.getString("works_spendtime"));
					list.add(w);
				}
				return list;
			}
		});
		if(beauticianWorksList==null) beauticianWorksList = new ArrayList<BeauticianWorks>();
		ModelAndView mv = new ModelAndView("MoreWorks","beauticianWorksList",beauticianWorksList);
		return mv;
	}
	@RequestMapping ("/worksDetail" )
	public ModelAndView worksDetail(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WorksDetail");
		return mv;
	}
	@RequestMapping ("/comment" )
	public ModelAndView comment(){
		HttpServletRequest request = this.getRequest();
		String beauticianID = request.getParameter("beauticianID");
		List<Comment> commentList = this.jdbcTemplate.query(
				"select * from t_comment where beautician_id=? " +
				" order by comment_id desc limit 0,6",
				new Object[]{beauticianID},
				new ResultSetExtractor<List<Comment>>(){
					public List<Comment> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<Comment> list = new ArrayList<Comment>();
						while(rs.next()){
							Comment c = new Comment();
							c.setCommentID(rs.getInt("comment_id"));
							c.setBeauticianID(rs.getInt("beautician_id"));
							c.setCommentTime(rs.getDate("comment_time"));
							c.setCommentDetail(rs.getString("comment_detail"));
							c.setOrderCode(rs.getString("order_code"));
							list.add(c);
						}
						return list;
					}});
		for(Comment c : commentList) c.setOrderCode(PubUtil.mosaicString(c.getOrderCode(), 5));
		ModelAndView mv = new ModelAndView("comment","commentList",commentList);
		return mv;
	}
	@RequestMapping ("/dateDashboard" )
	public ModelAndView dateDashboard(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("DateDashboard");
		return mv;
	}
	@RequestMapping ("/reservePage" )
	public ModelAndView reservePage() throws Exception{
		HttpServletRequest request = this.getRequest();
		String beauticianID = request.getParameter("beauticianID");
		String customerID = new SessionHelper().getCustomerID();
		Beautician beautician = beauticianDao.findByPrimaryKey(Integer.parseInt(beauticianID));
		Customer customer = customerDao.findByPrimaryKey(customerID);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ReservePage");
		mv.addObject("beautician",beautician);
		mv.addObject("currentDate",dateFormat.format(new Date()));
		mv.addObject("customer",customer);
		return mv;
	}
	@RequestMapping ("/showMyOrder" )
	public ModelAndView showMyOrder() throws Exception{
		String customerID = new SessionHelper().getCustomerID();
		
		String sql = "select * from t_order where customer_id = ? ";
		System.out.println("customerID="+customerID);
		List<Order> orderList = orderDao.queryBySql(sql, new Object[]{customerID});
		if(orderList==null) orderList = new ArrayList<Order>();
		ModelAndView mv = new ModelAndView("MyOrder","orderList",orderList);
		return mv;
	}
	@RequestMapping ("/createOrder" )
	public ModelAndView createOrder() throws Exception{
		HttpServletRequest request = getRequest();
		Order order = WebUtils.generateObjectFromRequest(request, Order.class);
		order.setOrderDate(new Date());
		String orderCode = "00000000000"+order.getOrderID();
		order.setOrderCode("P"+orderCode.substring(orderCode.length()-9));
		this.orderDao.insert(order);
		Customer customer = customerDao.findByPrimaryKey(order.getCustomerID());
		if(customer!=null){
			customer.setCustomerAddr(order.getAddr());
			customer.setCustomerDoor(order.getDoor());
			customer.setCustomerRemark(order.getRemark());
			customerDao.update(customer);
		}
		class MailThread implements Runnable{
			JavaMailSender javaMailSender = null;
			Order order = null;
			public MailThread(JavaMailSender javaMailSender,Order order){
				this.javaMailSender = javaMailSender;
				this.order = order;
			}
			@Override
			public void run() {
				try {
					MimeMessage mailMessage = this.javaMailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
					messageHelper.setTo("yglah126@126.com");
//					messageHelper.addTo("reskid@qq.com");
					messageHelper.setFrom("yglah126@126.com");
					Configuration config = new Configuration();
					System.out.println(new File(this.getClass().getResource("/META-INF/ftl/").getFile()).getAbsolutePath());
					config.setDirectoryForTemplateLoading(new File(this.getClass().getResource("/META-INF/ftl/").getFile()));
					config.setObjectWrapper(new DefaultObjectWrapper());
					Template template = config.getTemplate("mail.ftl");
					Map root = new HashMap();
					root.put("order", order);
					Writer out = new StringWriter();
					template.process(root, out);
					messageHelper.setSubject(String.format("∂©µ•Ã·–—°æID=%s°ø",order.getOrderID()));
					System.out.println("email msg:"+out.toString());
					messageHelper.setText(out.toString(),true); 
					this.javaMailSender.send(mailMessage);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		Thread mailThread = new Thread(new MailThread(javaMailSender,order));
		mailThread.start();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("OrderConfirm");
		return mv;
	}
	@RequestMapping ("/Payment" )
	public ModelAndView Payment(){
		HttpServletRequest request = this.getRequest();
		String orderID = request.getParameter("orderID");
		ModelAndView mv = new ModelAndView("Payment","orderID",orderID);
		return mv;
	}
	@RequestMapping ("/InputComment" )
	public ModelAndView InputComment() throws NumberFormatException, Exception{
		HttpServletRequest request = this.getRequest();
		String orderID = request.getParameter("orderID");
		Order order = orderDao.findByPrimaryKey(Integer.parseInt(orderID));
		System.out.println(order.getBeauticianID());
		Beautician beautician = beauticianDao.findByPrimaryKey(order.getBeauticianID());
		ModelAndView mv = new ModelAndView("InputComment");
		mv.addObject("order",order);
		mv.addObject("beautician",beautician);
		return mv;
	}
	@RequestMapping("/doOfflinePaid")
	public ModelAndView doOfflinePaid() throws Exception{
		HttpServletRequest request = this.getRequest();
		String orderID = request.getParameter("orderID");
		this.jdbcTemplate.update(
				"update t_order set order_status = '1' where order_id=?",
				new Object[]{orderID});
		return showMyOrder();
	}
	@RequestMapping("/addComment")
	public ModelAndView addComment(){
		HttpServletRequest request = this.getRequest();
		String beauticianID = request.getParameter("beauticianID");
		String orderCode = request.getParameter("orderCode");
		String commentDetail = request.getParameter("commentDetail");
		String customerID = request.getParameter("customerID");
		this.jdbcTemplate.update(
				"insert into t_comment(beautician_id,order_code,comment_time,comment_detail,customer_id)" +
				"values(?,?,now(),?,?)",
				new Object[]{beauticianID,orderCode,commentDetail,customerID});
		return new ModelAndView("redirect:/comment.do?beauticianID="+beauticianID);
	}
	@RequestMapping("/bindUserInfo")
	public ModelAndView bindUserInfo(){
		ModelAndView mv = new ModelAndView("BindUserInfo");
		return mv;
	}
}
