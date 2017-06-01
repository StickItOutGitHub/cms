package org.bread.worm.cms.web.controller;

import javax.validation.Valid;

import org.bread.worm.cms.basic.bean.Pager;
import org.bread.worm.cms.bean.User;
import org.bread.worm.cms.service.GroupService;
import org.bread.worm.cms.service.RoleService;
import org.bread.worm.cms.service.UserService;
import org.bread.worm.cms.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private GroupService groupService;

	@GetMapping("list")
	public String list(Model model) {
		Pager<User> datas = userService.findUser();
		model.addAttribute("datas", datas);
		return "user/list";
	}

	/**
	 * 导航至添加页
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("add")
	public String add(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute(userDto);
		model.addAttribute("roles", roleService.listRole());
		model.addAttribute("groups", groupService.listGroup());
		return "user/add";
	}

	/***
	 * 用户名唯一性验证
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("/valid_username")
	public @ResponseBody String valid_username(String username) {
		return String.valueOf(userService.existsByUsername(username));
	}

	/**
	 * 添加
	 * 
	 * @param userDto
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("add")
	public String save(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("roles", roleService.listRole());
			model.addAttribute("groups", groupService.listGroup());
			return "redirect:user/add";
		}
		userService.add(userDto.getUser(), userDto.getRoleIds(), userDto.getGroupIds());
		return "redirect:/admin/user/list";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/admin/user/list";

	}
	
	
	@GetMapping("updateStatus/{id}")
	public String updateStatus(@PathVariable int id){
		userService.updateStatus(id);
		return "redirect:/admin/user/list";
	}
	
	
	@GetMapping("update/{id}")
	public String update(@PathVariable int id,Model model){
		User user = userService.load(id);
		model.addAttribute("roles", roleService.listRole());
		model.addAttribute("groups", groupService.listGroup());
		model.addAttribute("userDto", new UserDto(user, 
				userService.listUserRoleIds(id), userService.listUserGroupIds(id)));
		return "user/update";
	}
	
	
	@PostMapping("update/{id}")
	public String update(@PathVariable int id,@Valid UserDto userDto, BindingResult bindingResult, Model model) {
		User user = userService.load(id);
		if (bindingResult.hasErrors()) {
			model.addAttribute("roles", roleService.listRole());
			model.addAttribute("groups", groupService.listGroup());
			return "user/update";
		}
		user.setNickname(userDto.getNickname());
		user.setEmail(userDto.getEmail());
		user.setStatus(userDto.getStatus());
		user.setPhone(userDto.getPhone());
		userService.update(user, userDto.getRoleIds(), userDto.getGroupIds());
		return "redirect:/admin/user/list";
	}
	
	
	
	@GetMapping("{id}")
	public String show(@PathVariable int id,Model model){
		User user = userService.load(id);
		model.addAttribute("user", user);
		model.addAttribute("roles", userService.listUserRoles(id));
		model.addAttribute("groups", userService.listUserGroups(id));
		return "user/show";
	}
	
	
	

}
