/**
 * 菜单
 * @param data 菜单JSON数据
 *        id 菜单唯一ID
 *        name 菜单名称
 *        url 菜单链接地址
 *        icon 图标
 *        pid  父级ID
 *        is_out 是否外链0否|1是,外链a标签没有class='multitabs'
 *        is_home 是否首页
 */
var setSidebar = function(data){
    if (data.length == 0) return false;
    var arrData = (typeof data == 'object') ? data : JSON.parse(data);
   // var treeObj = getTrees(arrData, 0, 'id', 'parentId', 'children');
    html = createMenu(arrData, true);
    $('#sidebar-main').append(html);
}

var createMenu = function (data, is_frist) {
    var menu_body = is_frist ? '<ul class="nav-drawer">' : '<ul class="nav nav-subnav">';
    
    for(var i = 0; i < data.length; i++){
	    var isOut=data[i].is_out;
        var linkClass=isOut?"link":"navlink";
        icon_div     = data[i].parentId == 0 ? '<i class="' + data[i].icon + '"></i>' : '';
        selected     = (data[i].parentId == 0) && (data[i].is_home == 1) ? 'active' : '';
        menuName     = data[i].parentId == 0 ? '<span>' + data[i].title + '</span>' : data[i].title;
        if (data[i].children && data[i].children.length > 0) {
            menu_body += '<li class="nav-item nav-item-has-subnav"><a href="javascript:void(0)">' + icon_div + menuName + '</a>';
            menu_body += createMenu(data[i].children);
        } else {
            menu_body += '<li class="nav-item ' + selected + '"><a href="' + data[i].url + '" class="'+linkClass+'">' + icon_div + menuName + '</a>';
        }
        menu_body += '</li>';
    }
    
    menu_body += '</ul>';
    
    return menu_body;
};

/**
 * 树状的算法
 * @params list     代转化数组
 * @params parentId 起始节点
 * @params idName 主键ID名
 * @params parentIdName 父级ID名称
 * @params childrenName 子级名称
 * @author CSDN博主「伤包子」
 */
var getTrees = function (list, parentId, idName, parentIdName, childrenName) {
    let items= {};
    // 获取每个节点的直属子节点，*记住是直属，不是所有子节点
    for (let i = 0; i < list.length; i++) {
         let key = list[i][parentIdName];
         if (items[key]) {
             items[key].push(list[i]);
         } else {
             items[key] = [];
             items[key].push(list[i]);
         }
     }
     return formatTree(items, parentId, idName, childrenName);
}

/**
 * 利用递归格式化每个节点
 */
var formatTree = function (items, parentId, idName, childrenName) {
    let result = [];
    if (!items[parentId]) {
        return result;
    }
    for (let t in items[parentId]) {
        items[parentId][t][childrenName] = formatTree(items, items[parentId][t][idName], idName, childrenName)
        result.push(items[parentId][t]);
    }
    return result;
}

// 使用
var menu_list = [{
    id: '1',
    title: '后台首页',
    url: '/system',
    parentId: 0,
    icon: 'mdi mdi-home',
    is_out:1,
    is_home: 1,
}];

function renderLeftMenu(){
	$.get("system/permission/userTreeList",function(result){
		if(result.code == 200){
			menu_list = menu_list.concat(result.msg.children);
			var json_str = JSON.stringify(menu_list);
            setSidebar(json_str);
		}
	})
}
renderLeftMenu();
var $main_content = $("#content_wrapper");
function loadUrlToMain(url){
	if(url==""||url==undefined){
		return;
	}
	    // 加载内容
		$.ajax({
			    	type : "get",
			    	url : url,
			    	dataType : "html",
			    	success : function(r) {
				        if (r.code === 401) {
				            $Jtk.n_danger("登录已失效，您的账号已被踢出或已在别的地方登录，请重新登录。如果密码遭到泄露，请立即修改密码！");
				            setTimeout(function () {
				                location.href = "/index"
				            }, 4000);
				            return;
				        } else if (r.code === 500) {
				            $Jtk.n_danger(r.msg);
				            return;
				        }
				        $main_content.html(r);
                        $("body").removeClass("modal-open");
                        $(".modal-backdrop").remove();
             			}
			    });
}
$('#sidebar-main').delegate(".navlink","click",function(evt){
	evt.preventDefault();
	$(this).parent().addClass("active").siblings().removeClass("active");
	let url = $(this).attr("href");
    loadUrlToMain(url);
})
$('#topInfoList').delegate(".navlink","click",function(evt){
	evt.preventDefault();
	let url = $(this).attr("href");
    loadUrlToMain(url);
})

