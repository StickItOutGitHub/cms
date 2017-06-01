(function($) {
	
	//栏目树
	$.fn.mytree = function(opts) {
		var setting = $.extend({
			data:{
				simpleData:{
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: -1
				}
			},
			view: {
				dblClickExpand: false,
				selectedMulti: false
			},
			async: {
				enable: true,
				url: opts?(opts.url||"getTrees"):"getTrees"
				
			},
			mine: {
				listChild:1,
				srcElement:"#iframe"
			},
			callback:{
				onAsyncSuccess:function(){
					if(opts.mine.expandAll)
						t.expandAll(true);
				}
			}
		},opts||{});
		var _mine = setting.mine;
		var t = $.fn.zTree.init($(this), setting);
		t.getCheckParentNodes = getCheckParentNodes;
		t.getCheckChildNodes = getCheckChildNodes;
		if(_mine.listChild) {
			t.setting.callback.onClick = listChild;
		}
		
		function listChild(event,treeId,treeNode) {
			$(_mine.srcElement).attr("src","getChildent/"+treeNode.id);
		}
		
		function getCheckParentNodes(treeNode,checked) {
			var ps = new Array();
			var pn;
			while((pn=treeNode.getParentNode())) {
				if(pn.checked==checked) ps.push(pn);
				treeNode = pn;
			}
			return ps;
		}
		//第三个参数存储所有子节点
		function getCheckChildNodes(treeNode,checked,cs) {
			var ccs;
			if((ccs=treeNode.children)) {
				for(var i=0;i<ccs.length;i++) {
					var c = ccs[i];
					if(c.checked==checked) {
						cs.push(c);
					}
					getCheckChildNodes(c,checked,cs);
				}
			}
		}
		return t;
	}
	
	
	// 自定义JQuery插件  手风琴   参数：要渲染的div（其他）元素
	$.fn.myaccordion = function(opts){
		var settings = $.extend({
			selectedClazz:"navSelected",
			titleTagName:"h3"
		},opts||{});
		//所有的标题元素
		var titleNode = $(this).find("ul>"+settings.titleTagName);
		//获取选中的标题元素
		var selectedNode = $(this).find("ul."+settings.selectedClazz+">"+settings.titleTagName);
		//给标题元素添加手型样式
		titleNode.css("cursor","pointer");
		//隐藏所有标题下的同级元素
		titleNode.nextAll().css("display","none");
		//显示标题选中下的所有同级元素
		selectedNode.nextAll().css("display","block");
		
		//给每个标题元素添加单击事件
		titleNode.click(function(){
			//获取该标题元素的父元素是否存在被选中的样式，即：该标题元素是否选中
			var checked = $(this).parent().hasClass(settings.selectedClazz);
			//如果该标题被选中，则移除父元素被选中的样式，并且收起同级元素,反之。。。。。
			if (checked) {
				$(this).parent().removeClass(settings.selectedClazz);
				$(this).nextAll().slideUp();
			}else {
				$(this).parent().addClass(settings.selectedClazz);
				$(this).nextAll().slideDown();
			}
		});
	};
	
	//列表行变色
	$.fn.trChangeColor = function(opts) {
		var settings = $.extend({
			overClazz:"trMouseover",
			evenClazz:"trEvenColor"
		},opts||{});
		
		$(this).find("tbody tr:odd").addClass(settings.evenClazz);
		$(this).find("tbody tr").on("mouseenter mouseleave",function(){
			$(this).toggleClass(settings.overClazz);
		});
	}
	
	//删除
	$.fn.confirmOperat = function(opts) {
		var settings = $.extend({
			msg:"该操作不可逆，是否确认操作？",
			eventName:"click"
		},opts||{});
		$(this).on(settings.eventName,function(){
			if(!confirm(settings.msg)){
				event.preventDefault();
			}
		})
	}
	
})(jQuery);