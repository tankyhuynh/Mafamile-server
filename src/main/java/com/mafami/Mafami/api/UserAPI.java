package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Convert.UserConvert;
import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Service.LogService;
import com.mafami.Mafami.Service.UserService;
import com.mafami.Mafami.Utils.FileUtils;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private UserConvert userConvert;
	
	@Autowired
	private FileUtils fileUtils;
	
	@GetMapping
	public List<UserEntity> getAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public UserEntity getOneById(@PathVariable String id) {
		return userService.findOneById(id);
	}
	
	
	@PostMapping
	public UserEntity saveOne(@RequestBody UserEntity userEntity) throws Exception {
		String password =  userEntity.getPassword();
		String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
		userEntity.setPassword(hashedPass);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã thêm nhân viên " + userEntity.getFullname() + " lúc " + ( df.parse(sf.format(Calendar.getInstance().getTime())) ) ;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return userService.save(userEntity);
	}
	
	@PutMapping("/{id}")
	public UserEntity saveOneById(@PathVariable String id, @RequestBody UserEntity userEntity) throws Exception {
		UserEntity newEntity = userService.findOneById(id);
		newEntity = userConvert.Entity_To_Entity(userEntity);
		newEntity.setId(id);
		
		String password =  userEntity.getPassword();
		String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
		userEntity.setPassword(hashedPass);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã sửa thông tin nhân viên " + userEntity.getFullname() + " lúc " +( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) ;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return userService.save(newEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id, @RequestBody(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		UserEntity user = userService.findOneById(id);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa nhân viên " + user.getFullname() + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		userService.delete(id);
	}
	
	
	@PutMapping("/role/{id}")
	public UserEntity saveOneById(@PathVariable String id, @RequestBody String role) throws Exception {
		UserEntity  userEntity = userService.findOneById(id);
		userEntity.setId(id);
		userEntity.setRole(role);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã sửa vai trò của nhân viên " + userEntity.getFullname() + " lúc " +( df.parse(sf.format(( Calendar.getInstance().getTime())) ) + " thành " + userEntity.getRole() ) ;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return userService.save(userEntity);
	}
	
}
