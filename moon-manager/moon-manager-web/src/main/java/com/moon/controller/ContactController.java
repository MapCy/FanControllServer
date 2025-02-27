package com.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moon.common.pojo.MoonResult;
import com.moon.pojo.TbContact;
import com.moon.service.ContactService;

/*
 * 联系人管理Controller
 */
@Controller
public class ContactController {

	@Autowired 
	private ContactService contactService;
	
	@RequestMapping("/contact")
	@ResponseBody
	public MoonResult getAllContact(){
		return contactService.getAllContact();
	}
	
	@RequestMapping(value="/contact/addContact",method=RequestMethod.POST)
	@ResponseBody
	public MoonResult addContact(TbContact contact){
		contactService.addContact(contact);
		return MoonResult.ok("ADD_OK");
	}
	
	@RequestMapping("/contact/delContact")
	@ResponseBody
	public MoonResult delContact(String number){
		if (number == null) {
			return MoonResult.ok("ERROR");
		}
		contactService.delContact(number);
		return MoonResult.ok("SUCCESS");
	}
	
	@RequestMapping("/contact/updateContact")
	@ResponseBody
	public MoonResult updateContact(TbContact contact){
		if (contact == null) {
			return MoonResult.ok("ERROR");
		}
		contactService.updateContact(contact);
		return MoonResult.ok("SUCCESS");
	}
	
	@RequestMapping("/contact/lookContact/{number}")
	@ResponseBody
	public MoonResult lookContact(@PathVariable String number){
		if (number == null) {
			return MoonResult.ok("ERROR");
		}
		TbContact dbContact = contactService.selectByNumber(number);
		return MoonResult.ok(dbContact);
	}
}
