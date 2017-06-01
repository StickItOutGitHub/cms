package org.bread.worm.cms.channel.web.controller;

import java.util.List;


import org.bread.worm.cms.channel.bean.Channel;
import org.bread.worm.cms.channel.bean.ChannelTree;
import org.bread.worm.cms.channel.bean.ChannelType;
import org.bread.worm.cms.channel.service.ChannelService;
import org.bread.worm.cms.utils.EnumUtil;
import org.bread.worm.cms.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/admin/channel")
@Controller
public class ChannelController {

	@Autowired
	private ChannelService channelService;
	
	@GetMapping("list")
	public String list(Model model){
		model.addAttribute("zNodes", JsonUtil.getInstance().obj2json(channelService.generateTree()));
		return "channel/list";
	}
	
	
	@PostMapping("getTrees")
	public @ResponseBody List<ChannelTree> getTrees(){
		
		return channelService.generateTree();
	}
	
	
	@GetMapping("getChildent/{id}")
	public String getChildent(@PathVariable Integer id,Model model){
		model.addAttribute("current", channelService.loadChannel(id));
		model.addAttribute("channels", channelService.listByParentId(id));
		return "channel/list_child";
	}
	
	
	/*@PostMapping("getChildentAsync/{id}")
	public @ResponseBody String getChildentAsync(@PathVariable Integer id,Model model){
		List<TreeDto> tds = new ArrayList<TreeDto>();
		model.addAttribute("current", channelService.loadChannel(id));

		List<Channel> cs = channelService.listByParentId(id);
		TreeDto treeDto = null;
		for (Channel channel : cs) {
			treeDto = new TreeDto(channel.getId(),channel.getName(),channel.getParent().getId());
			tds.add(treeDto);
		}
		// TODO 
		model.addAttribute("channels", tds);
		return "channel/list_child";
	}*/
	
	
	
	@GetMapping("add/{id}")
	public String toAddUI(@PathVariable Integer id,Model model){
		model.addAttribute("channel", new Channel());
		Channel parentChannel = null;
		if (id == 0) {
			parentChannel = new Channel();
			parentChannel.setId(0);
			parentChannel.setName("系统栏目管理");
		}else {
			parentChannel = channelService.loadChannel(id);
		}
		model.addAttribute("current", parentChannel);
		model.addAttribute("types", EnumUtil.enumProp2NameMap(ChannelType.class, "chineseName"));
		return "channel/add";
	}
	
	@PostMapping("add/{id}")
	public String add(@PathVariable Integer id,Channel channel,BindingResult bindingResult,Model model){
		if (bindingResult.hasErrors()) {
			model.addAttribute("channel", new Channel());
			Channel parentChannel = null;
			if (id == 0) {
				parentChannel = new Channel();
				parentChannel.setId(0);
				parentChannel.setName("系统栏目管理");
			}else {
				parentChannel = channelService.loadChannel(id);
			}
			
			model.addAttribute("current", parentChannel);
			model.addAttribute("types", EnumUtil.enumProp2NameMap(ChannelType.class, "chineseName"));
			return "channel/add";
		}
		channelService.add(channel, id);
		
		return "redirect:/admin/channel/getChildent/"+id;
	}
	
	
	
	@GetMapping("delete/{pid}/{id}")
	public String delete(@PathVariable Integer pid,@PathVariable Integer id,Model model){
		channelService.removeChannel(id);
		return "redirect:/admin/channel/getChildent/"+pid;
	}
	
	@GetMapping("update/{id}")
	public String update(@PathVariable Integer id,Model model){
		Channel currentChannel = channelService.loadChannel(id);
		Channel pc = null;
		if (currentChannel.getParent() == null) {
			pc = new Channel();
			pc.setId(0);
			pc.setName("系统栏目管理");
		}else {
			pc = currentChannel.getParent();
		}
		model.addAttribute("types", EnumUtil.enumProp2NameMap(ChannelType.class, "chineseName"));
		model.addAttribute("channel", currentChannel);
		model.addAttribute("current",pc);
		return "channel/update";
	}
	
	
	@PostMapping("update/{id}")
	public String update(@PathVariable Integer id,Channel channel,BindingResult bindingResult,Model model){
		if (bindingResult.hasErrors()) {
			model.addAttribute("types", EnumUtil.enumProp2NameMap(ChannelType.class, "chineseName"));
			return "channel/update";
		}
		Channel tc = channelService.loadChannel(id);
		int pid = 0;
		if(tc.getParent()!=null) pid = tc.getParent().getId();
		tc.setIsCustomLink(channel.getIsCustomLink());
		tc.setCustomLinkUrl(channel.getCustomLinkUrl());
		tc.setIsIndex(channel.getIsIndex());
		tc.setIsTopNav(channel.getIsTopNav());
		tc.setName(channel.getName());
		tc.setIsRecommend(channel.getIsRecommend());
		tc.setStatus(channel.getStatus());
		tc.setType(channel.getType());
		tc.setParent(channelService.loadChannel(pid));
		channelService.update(tc);
		return "redirect:/admin/channel/getChildent/"+pid;
	}
}
