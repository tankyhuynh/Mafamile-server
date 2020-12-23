/**
 * 
 */
package com.mafami.Mafami.Utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.mafami.Mafami.Constant.MailConstant;
import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Service.UserService;

@Component
public class MailUtils {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailConstant mailConstant;

	@Autowired
	private UserService userService;
	

	public void sendUser_addTicket(String userId,BillEntity billEntity, String emailTitle, String emailBody, String emailFooter) {

		String email = userService.findOneById(userId).getEmail();
	
		String content = 	"<br>Bill ID: " + billEntity.getId()  
							+ "<br><a href='https://mafamile.herokuapp.com/'>Chọn vào đây để xác nhận</a>";

		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		try {
			helper.setText(emailBody
					+ "<br> " + content
					+ "<br>" + emailFooter, true);
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
