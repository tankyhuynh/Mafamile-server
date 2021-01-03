/**
 * 
 */
package com.mafami.Mafami.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.mafami.Mafami.Constant.MailConstant;
import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Service.UserService;
import com.mafami.Mafami.model.FoodInformationModel;

@Component
public class MailUtils {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailConstant mailConstant;

	@Autowired
	private UserService userService;
	

	@Async
	public void sendAddBill_Admin(BillEntity billEntity, String emailTitle, String emailBody, String emailFooter) {

		String email = userService.findByUsername("admin").getEmail();
		
		String link = "<a href='http://api.thisisatestingdomain.site/api/bill/verifyBill/?id=" + billEntity.getId()  +" '> Chọn vào đây để xác nhận </a>";
	
		String content = 	"<br>Bill ID: " + billEntity.getId()  
							+ "<br>"
							+ link;
		System.out.println("Verify " + billEntity.getId());

		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		try {
			helper.setText(emailBody
					+ "<br> " + content
					+ "<br><br>" + emailFooter, true);
			helper.setTo(email);
			helper.setSubject(emailTitle);
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		javaMailSender.send(mimeMessage);	
		
	}
	
	
	@Async
	public void sendAddBill_Customer(String email,BillEntity billEntity, String emailTitle, String emailBody, String emailFooter) throws Exception {
	
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));

		SimpleDateFormat sf_log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_log.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		String listFood = "";
		for (FoodInformationModel food : billEntity.getFoodInformation()) {
			if( food != billEntity.getFoodInformation().get(0)  ) {
				listFood += "<li>" + food.getFood().getName() + " - " + food.getQuantity() + "</li>" + "<br>";
			}else 	listFood += food.getFood().getName() + " - " + food.getQuantity()  + "<br>";
		}
		
//		String content = 	"<br>Bill_ID: " + billEntity.getId()  + "<br>"
//															+ "Bao gồm:" + "<br>"
//															+ "<li>" +listFood + "</li>"
// 															+ "Thời gian đặt: " + (df.parse(sf_log.format( billEntity.getCreatedDate() ))) + "<br>"
//															+  "Thời gian nhận: " + billEntity.getOrderDate()  + "<br>"
//															+ "<b>" + "Thành tiền: " + billEntity.getTotal() + " VND" + "</b>";

		String content = "<h1>Cảm ơn bạn đã đặt tiệc.</h1>\r\n"
				+ "<h3>Đây là thông tin của bạn</h3>\r\n"
				+ "<hr>\r\n"
				+ "<p>Thời gian tạo đơn: " +(df.parse(sf_log.format( billEntity.getCreatedDate() )))  + "</p>\r\n"
				+ "<p>Thời gian đặt: " + (df.parse(sf_log.format( billEntity.getOrderDate() )))+ "</p>\r\n"
				+ "<p>Thông tin thêm: " + billEntity.getAdditionInformation()  + "</p>\r\n"
				+ "<p>Trạng thái: Chưa xác nhận</p>\r\n"
				+ "<hr>\r\n"
				+ "<h3>Hoá đơn</h3>\r\n"
				+ "<table cellspacing=\"0\" cellpadding=\"0\" border=\"1\">\r\n"
				+ "  <thead>\r\n"
				+ "    <tr>\r\n"
				+ "      <th style=\"padding: 3px 5px;\" width=\"150\">Tên món</th>\r\n"
				+ "      <th style=\"padding: 3px 5px;\" width=\"150\">Số lượng</th>\r\n"
				+ "      <th style=\"padding: 3px 5px;\" width=\"150\">Đơn giá</th>\r\n"
				+ "      <th style=\"padding: 3px 5px;\" width=\"150\">Thành tiền</th </tr>\r\n"
				+ "  </thead>\r\n"
				+ "  <tbody>\r\n";
	
			
		
				for (FoodInformationModel food : billEntity.getFoodInformation()) {
					double price = food.getFood().getPrice().get(food.getFood().getPrice().size() -1).getPrice();
					int quantity = food.getQuantity();
					content = content+  "    <tr>\r\n"
							+ "      <td style=\"padding: 3px 5px;\">" + food.getFood().getName() + "</td>\r\n"
							+ "      <td style=\"padding: 3px 5px;\">" +  quantity + "</td>\r\n"
							+ "      <td style=\"padding: 3px 5px;\">"+  price + " VND "+ "</td>\r\n"
							+ "       <td style=\"padding: 3px 5px;\">" + price *  quantity + " VND" + "</td>\r\n"
							+ "    </tr>\r\n";
				}
				
				
				content = content + "  </tbody>\r\n"
				+ "</table>\r\n"
				+ "<h3>Tổng: " + billEntity.getTotal() + " VND" +"</h3>\r\n"
				+ "<p>Bạn hãy chuyển khoản ít nhất 50% giá trị đơn trước thời gian đặt ... để chúng tôi có thể xác nhận thực hiện. Cảm ơn. </p>\r\n"
				+ "<hr>\r\n"
				+ "<h3>THÔNG TIN CHUYỂN KHOẢN</h3>\r\n"
				+ "<ul>\r\n"
				+ "  <li>Chủ tài khoản: TA DUC KIM </li>\r\n"
				+ "  <li>Số tài khoản: 1015419465 </li>\r\n"
				+ "  <li>Ngân hàng: VCB </li>\r\n"
				+ "</ul>";
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		try {
			helper.setText(emailBody
					+ "<br> " + content
					+ "<br><br>" + emailFooter, true);
			helper.setTo(email);
			helper.setSubject(emailTitle);
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		javaMailSender.send(mimeMessage);	
		
	}
	
	
	@Async
	public void sendUpdateBill_Admin(BillEntity billEntity, String emailTitle, String emailBody, String emailFooter) {

		String email = userService.findByUsername("admin").getEmail();
		
		String listFood = "";
		for (FoodInformationModel food : billEntity.getFoodInformation()) {
			listFood += food.getFood().getName() + " - " + food.getQuantity() + "<br>";
		}
	
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		try {
			helper.setText(emailBody
					+ "<br><br>" + emailFooter, true);
			helper.setTo(email);
			helper.setSubject(emailTitle);
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		javaMailSender.send(mimeMessage);	
		
	}
	
	@Async
	public void sendUpdateBill_Customer(String email,BillEntity billEntity, String emailTitle, String emailBody, String emailFooter) {
		
		String listFood = "";
		for (FoodInformationModel food : billEntity.getFoodInformation()) {
			listFood += food.getFood().getName() + " - " + food.getQuantity() + "<br>";
		}
	
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		try {
			helper.setText(emailBody
					+ "<br><br>" + emailFooter, true);
			helper.setTo(email);
			helper.setSubject(emailTitle);
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		javaMailSender.send(mimeMessage);	
		
	}
	
	
//	public void sendUser_addTicket(String userId,TicketEntity ticketEntity, String emailTitle, String emailBody, String emailFooter) {
//
//		String email = userService.findOne(userId).getEmail();
//		
//		String	content = 	"<br>TicketID: " + ticketEntity.getId() 
//							+ "<br> Time: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE)).format(ticketEntity.getStartDate())
//							+ "<br> Title: " + ticketEntity.getTitle() 
//							+ "<br> Description: " + ticketEntity.getDescription()
//							+ "<br> Place: " + ticketEntity.getPlace() 
//							+ "<br> Images: " + ticketEntity.getImages() + "<br>";
//
//		
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//		
//		try {
//			helper.setText(emailBody
//					+ "<br> " + content
//					+ "<br>" + emailFooter, true);
//			helper.setTo(email);
//			helper.setSubject(emailTitle);
//			
//			mailSender.send(mimeMessage);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		mailSender.send(mimeMessage);	
//		
//	}
//	
//	
//	
//	public void sendUser_statusChange(String userId, TicketEntity ticketEntity) {
//
//		String email = userService.findOne(userId).getEmail();
//
//		String	content = 	"<br>TicketID: " + ticketEntity.getId() 
//							+ "<br> Time: " + ticketEntity.getStartDate().toString() 
//							+ "<br> Title: " + ticketEntity.getTitle() 
//							+ "<br> Description: " + ticketEntity.getDescription()
//							+ "<br> Status: " + ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).getName().getEn() + " at " + ticketEntity.getStatus().get(ticketEntity.getStatus().size()-1).getTime()
//							+ "<br> Technician: " + ticketEntity.getTechnicianName();
//					
//		
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//
//		try {
//			helper.setTo(email);
//			helper.setSubject(mailConstant.mail_title_status_change_user);
//			
//			helper.setText(mailConstant.mail_body_status_change_user
//					+ "<br> " + content
//					+ "<br>" + mailConstant.mail_footer_status_change_user, true); 
//
//			mailSender.send(mimeMessage);
//			
//		} catch (MessagingException e) {
//
//			e.printStackTrace();
//		} 
//		
//		mailSender.send(mimeMessage);	
//
//	}
//	
//	
//	
//	
//	public void sendTechinician_statusChange(String userId,TicketEntity ticketEntity) {
//
//		String email = userService.findOne(userId).getEmail();
//		
//		String	content = 	"<br>TicketID: " + ticketEntity.getId() 
//							+ "<br> Time: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE)).format(ticketEntity.getStartDate())
//							+ "<br> Title: " + ticketEntity.getTitle() 
//							+ "<br> Description: " + ticketEntity.getDescription()
//							+ "<br> Place: " + ticketEntity.getPlace() 
//							+ "<br> Images: " + ticketEntity.getImages() + "<br>";
//
//		
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//		
//		try {
//			helper.setTo(email);
//			helper.setSubject(mailConstant.mail_title_status_change_user);
//			
//			helper.setText(mailConstant.mail_body_status_change_technician
//					+ "<br> " + content
//					+ "<br>" + mailConstant.mail_footer_status_change_technician, true); 
//
//			mailSender.send(mimeMessage);
//		} catch (MessagingException e) {
//
//			e.printStackTrace();
//		} 
//		
//		mailSender.send(mimeMessage);	
//		
//	}
//	
//	
//	
//	public void sendAdmin_dropTicket(TicketEntity ticketEntity) {
//
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//		
//		String email = userService.findOne("5f6ff6a57cbfb744d95344c8").getEmail();
//
//		String	content = 	"<br>TicketID: " + ticketEntity.getId() 
//							+ "<br> Time: " + ticketEntity.getStartDate().toString() 
//							+ "<br> Title: " + ticketEntity.getTitle() 
//							+ "<br> Description: " + ticketEntity.getDescription()
//							+ "<br> Place: " + ticketEntity.getPlace() 
//							+ "<br> Images: " + ticketEntity.getImages();
//		
//		
//
//		try {
//			helper.setText(mailConstant.mail_body_drop_ticket_admin
//					+ "<br> " + content
//					+ "<br>" + mailConstant.mail_footer_drop_ticket_admin, true);
//			helper.setTo(email);
//			helper.setSubject(mailConstant.mail_title_drop_ticket_admin);
//			mailSender.send(mimeMessage);
//			
//		} catch (MessagingException e) {
//
//			e.printStackTrace();
//		} 
//		
//		mailSender.send(mimeMessage);	
//
//	}
//	
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
