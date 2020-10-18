package com.mafami.Mafami.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
	

	@GetMapping("/")
	@ResponseBody
    public String login_user(Model model) {
		 
		
        return "home";
    }
	
	@RequestMapping("/welcome")
	@ResponseBody
	public String welcom() {
		
		return "Welcom"; 
	}
	
	@RequestMapping("/admin")
	@ResponseBody
	public String test_Admin() {
		
		return "admin Authorize";
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public String test_User() {
		
		return "user Authorize";
	}
	
	
	
	
	
		
}
