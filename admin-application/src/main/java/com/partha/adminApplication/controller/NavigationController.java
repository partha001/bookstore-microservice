package com.partha.adminApplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {

	public static final Logger logger= LoggerFactory.getLogger(NavigationController.class);


	/*@GetMapping("/")
	public String indexRedirect(){
		return "redirect:/index";
	}*/

	@GetMapping("/index")
	public String index(){
		return "index";
	}
	
	
	@GetMapping("/login-error")
	public String loginFailed(Model model){
		//User user = new User();
		//model.addAttribute("user", user);
		model.addAttribute("message", "username or password incorrect");
		return "index";
	}

//	@PostMapping("/login")
//	public String login(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model){
//		logger.info("inside HomeController.login"+ user.getUsername());
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("user",user);
//
//			System.out.println("BINDING RESULT ERROR");
//			ObjectError objectError = bindingResult.getAllErrors().get(0);
//			model.addAttribute("message",objectError.getDefaultMessage());
//
//			return "index";
//		} else {
//			model.addAttribute("module", "home");
//			return "home";
//		}
//	}

	
	@GetMapping(value="/home")
	public ModelAndView getHome(Model model){
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("module", "home");
		return mv;
	}

	
//	@ResponseBody
//	@GetMapping(value="/test1")
//	public ResponseEntity<String> test1(){
//		JSONObject obj = new JSONObject();
//		try {
//			obj.put("key1", "value1");
//			obj.put("key2", "value2");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
//	}


}
