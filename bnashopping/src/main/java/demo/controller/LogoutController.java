package demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping(value = "/logoutpage")
	  public String login(HttpSession httpSession,ModelMap model) {
		httpSession.removeAttribute("himember");
		return "login";
	  }
}
