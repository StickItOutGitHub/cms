package org.bread.worm.cms.web.controller;

import org.bread.worm.cms.bean.Group;
import org.bread.worm.cms.service.GroupService;
import org.bread.worm.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/group")
public class GroupController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;
	
	@GetMapping("list")
	public String list(Model model){
		model.addAttribute("list", groupService.findGroup());
		return "group/list";
	}
	
	
	@GetMapping("add")
	public String add(Model model){
		model.addAttribute("group", new Group());
		return "group/add";
	}
	
	@PostMapping("add")
	public String add(@Validated Group group,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "group/add";
		}
		groupService.add(group);
		return "redirect:/admin/group/list";
	}
	
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable int id){
		groupService.delete(id);
		return "redirect:/admin/group/list";
	}
	
	@GetMapping("update/{id}")
	public String update(@PathVariable int id,Model model){
		model.addAttribute("group", groupService.load(id));
		return "group/update";
	}
	
	@PostMapping("update/{id}")
	public String update(@PathVariable int id,@Validated Group group,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "group/update";
		}
		Group g = groupService.load(id);
		g.setName(group.getName());
		g.setDescr(group.getDescr());
		groupService.update(g);
		return "redirect:/admin/group/list";
	}
	
	
	@GetMapping("clearUsers/{id}")
	public String clearUsers(@PathVariable int id){
		userService.removeGroupUser(id);
		return "redirect:/admin/group/list";
	}
	
	
	@GetMapping("/{id}")
	public String show(@PathVariable int id,Model model){
		model.addAttribute("group", groupService.load(id));
		
		model.addAttribute("groupUser", userService.listGroupUsers(id));
		return "group/show";
	}
	
	
	

}
