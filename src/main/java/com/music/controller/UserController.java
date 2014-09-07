package com.music.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/{userId}", method = RequestMethod.GET)
	public String getSong(@PathVariable String userId, ModelMap model) {

		model.addAttribute("music", " for user " + userId + " : " + MusicRecommendation.getInstance().userRecommend(Long.valueOf(userId)));
		return "user";

	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultSong(ModelMap model) {
 
		model.addAttribute("music", "Enter User Id");
		return "user";
 
	}
	
	
//	@PostConstruct
//	public static void init() {
//	     System.out.println("This is during startup");
//	}
}