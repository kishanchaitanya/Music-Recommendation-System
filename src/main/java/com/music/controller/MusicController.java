package com.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/music")
public class MusicController {

	@RequestMapping(value = "/{songId}", method = RequestMethod.GET)
	public String getSong(@PathVariable String songId, ModelMap model) {

		model.addAttribute("music", " for song " + songId + " : " + MusicRecommendation.getInstance().itemrecommend(Long.valueOf(songId)));
		return "item";
	}
	
	@RequestMapping(value = "/music", method = RequestMethod.GET)
	public ModelAndView song(ModelMap model) {
		DataInput input = new DataInput();
		model.addAttribute("input",input);
		return new ModelAndView("Home", "command", input);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultSong(ModelMap model) {

		model.addAttribute("music", "Enter Song Id");
		return "item";

	}
	
//	@RequestMapping(value = "/display", method = RequestMethod.POST)
//	public String displaySong(@ModelAttribute("input") DataInput input, ModelMap model) {
//
//		long songId = Long.valueOf(input.getSongId());
//		model.addAttribute("music", " for song " + songId + " : " + MusicRecommendation.getInstance().itemrecommend(songId));
//		return "item";
//
//	}

	
	

	// @RequestMapping(value = "/itembased", method = RequestMethod.POST)
	// public String getSong(@ModelAttribute("SpringWeb")String songId,
	// ModelMap model) {
	// model.addAttribute("music",
	// MusicRecommendation.getInstance().recommend(Long.valueOf(songId)));
	// return "list";
	// }



	// @PostConstruct
	// public static void init() {
	// System.out.println("This is during startup");
	// }
}