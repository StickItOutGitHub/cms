package org.bread.worm.cms.web.controller;

import java.util.List;

import org.apache.commons.lang.enums.EnumUtils;
import org.bread.worm.cms.bean.Role;
import org.bread.worm.cms.bean.RoleType;
import org.bread.worm.cms.service.RoleService;
import org.bread.worm.cms.service.UserService;
import org.bread.worm.cms.utils.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/***
	 * 角色列表
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String list(Model model){
		model.addAttribute("roles", roleService.listRole());
		return "role/list";
	}
	
	/**
	 * 导航至角色添加页
	 * @param model
	 * @return
	 */
	@GetMapping("add")
	public String add(Model model){
		model.addAttribute("role", new Role());
		model.addAttribute("roletypes", EnumUtil.enum2ListName(RoleType.class));
		return "role/add";
		
	}
	
	/***
	 * 添加角色对象
	 * @param role
	 * @return
	 */
	@PostMapping("add")
	public String add(Role role){
		roleService.add(role);
		return "redirect:/admin/role/list";
	}
	
	/**
	 * 删除指定角色对象
	 * @param id
	 * @return
	 */
	@GetMapping("delete/{id}")
	public String delete(@PathVariable int id){
		roleService.delete(id);
		return "redirect:/admin/role/list";
	}
	
	/**
	 * 显示角色详情 包含该角色下的所有用户
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}")
	public String show(@PathVariable int id,Model model){
		model.addAttribute("role", roleService.load(id));
		model.addAttribute("users", userService.listRoleUsers(id));
		return "role/show";
	}
	
	/**
	 * 清除指定角色ID中的所有用户对象
	 * @param id
	 * @return
	 */
	@GetMapping("clearRoleUser/{id}")
	public String clearRoleUser(@PathVariable int id){
		roleService.deleteRoleUsers(id);
		return "redirect:/admin/role/list";
	}
	
	
	
	@GetMapping("update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("role", roleService.load(id));
		model.addAttribute("users", userService.listRoleUsers(id));
		model.addAttribute("roletypes", EnumUtil.enum2ListName(RoleType.class));
		return "role/update";
	}


	@PostMapping("update/{id}")
	public String update(@PathVariable int id,Role role){
		Role erole = roleService.load(id);
		erole.setName(role.getName());
		erole.setRoleType(role.getRoleType());
		roleService.update(erole);
		return "redirect:/admin/role/list";
	}
	
}
