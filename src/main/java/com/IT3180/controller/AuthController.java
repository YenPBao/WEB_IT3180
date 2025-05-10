package com.IT3180.controller;

import jakarta.validation.Valid;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.IT3180.model.Notifications;
import com.IT3180.model.User;
import com.IT3180.services.ApartmentService;
import com.IT3180.services.BillService;
import com.IT3180.services.EmailService;
import com.IT3180.services.NotificationsServices;
import com.IT3180.services.ResidentService;
import com.IT3180.services.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private ResidentService residentService;
	@Autowired
	private BillService billService;
	@Autowired
    private EmailService emailService;
	@Autowired
	private NotificationsServices notificationsServices;
	@Autowired
	private UserService userService;
	
	@GetMapping ("/login")
	public String loginForm ()
	{
		return "auth/login";
	}
	 @GetMapping("/admin")
	    public String adminPage(Model model)
	 {
	    	List<User> users = userService.getAllUsers();
	    	model.addAttribute("users", users);
	    	Long numApartment = apartmentService.getTotalApartments();
		 	model.addAttribute("numApartment",numApartment);
		 	Long numResident = residentService.getTotalResidents();
		 	model.addAttribute("numResident", numResident);
		 	Long numUser = userService.getTotalUser();
		 	model.addAttribute("numUser", numUser);
		 	Long numFee = billService.getAllBillType();
		 	model.addAttribute("numFee", numFee);
	        return "admin/admin_dashboard";
	 }
	 @GetMapping("/user")
	 public String userPage(Model model,Principal principal)
	 {
		 // principal.getName() sẽ trả về username (thường là email hoặc tên tài khoản)
		    String username = principal.getName();

		    // Tùy cấu trúc, tìm user theo username
		    User user = userService.findUserByName(username);

		    model.addAttribute("user", user);
		 List<Notifications> notifications = notificationsServices.getAllNotifications();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        for (Notifications notification : notifications) {
	            // Chuyển đổi LocalDateTime thành String đã định dạng
	            notification.setFormatDate(notification.getCreatedAt().format(formatter));
	        }
	    	model.addAttribute("notifications", notifications);
		 return "user/user_dashboard"; 
	 }		
}
