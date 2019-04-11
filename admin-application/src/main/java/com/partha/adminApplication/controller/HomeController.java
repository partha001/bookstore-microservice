package com.partha.adminApplication.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.partha.adminApplication.model.User;

@Controller
public class HomeController {
	
	public static final Logger logger= LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String indexRedirect(){
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		User user = new User();
		model.addAttribute("user", user);
		return "index";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model){
		logger.info("inside HomeController.login"+ user.getUsername());
		if (bindingResult.hasErrors()) {
			model.addAttribute("user",user);
			
			System.out.println("BINDING RESULT ERROR");
			ObjectError objectError = bindingResult.getAllErrors().get(0);
			model.addAttribute("message",objectError.getDefaultMessage());

			return "index";
		} else {
			model.addAttribute("module", "home");
			return "home";
		}
	}
	
	@GetMapping(value="/home")
	public ModelAndView getHome(Model model){
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("module", "home");
		return mv;
	}
	
		
	@GetMapping(value="/addBook")
	public ModelAndView getAddBook(Model model){
		ModelAndView mv = new ModelAndView("addBook");
		mv.addObject("module", "addBook");
		return mv;
	}
	
	@GetMapping(value="/editBook")
	public ModelAndView getEditBook(Model model){
		ModelAndView mv = new ModelAndView("editBook");
		mv.addObject("module", "editBook");
		return mv;
	}
	
	@GetMapping(value="/report")
	public ModelAndView report(Model model){
		ModelAndView mv = new ModelAndView("report");
		mv.addObject("module", "report");
		return mv;
	}
	
	@ResponseBody
	@GetMapping(value="/test1")
	public ResponseEntity<String> test1(){
		JSONObject obj = new JSONObject();
		try {
			obj.put("key1", "value1");
			obj.put("key2", "value2");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
		
	}
	
	
}
