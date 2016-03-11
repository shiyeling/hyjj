package com.zqykj.hyjj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author yshi
 *
 */
@Controller
@RequestMapping({"/","/home","/index"})
public class BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){
		return "index";
	}
}
