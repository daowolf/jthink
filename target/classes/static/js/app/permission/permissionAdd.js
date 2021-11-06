var $permissionAddForm = $("#permissionAddForm");
var $permName = $permissionAddForm.find("input[name='name']");
var $permNameLabel = $permissionAddForm.find("#permNameLabel");
var $menuUrlListRow = $permissionAddForm.find(".menu-url-list-row");
var $menuIconListRow = $permissionAddForm.find(".menu-icon-list-row");
var $menuPermsListRow = $permissionAddForm.find(".menu-perms-list-row");
var $menuShowListrow =  $permissionAddForm.find(".menu-show-list-row");
var $formValidator;
var $menuTree;
var $font_element
$(function () {
		// 初始化树菜单select
		createPermsTree();
		// 初始化验证器
		initValidate();
		// 初始化图标选择
		initIconSelect();
	    $permissionAddForm.find("input[name='type']").change(function () {
        var $value = $permissionAddForm.find("input[name='type']:checked").val();
         console.log(typeof $value);
        if ($value === "0") {
            $permNameLabel.text("目录名称");
            $menuUrlListRow.hide();
            $menuIconListRow.show();
            $menuShowListrow.show();
        }else if($value ==="1"){
            $permNameLabel.text("菜单名称");
            $menuUrlListRow.show();
            $menuIconListRow.show();
            $menuShowListrow.show();
       }
       else {
            $permNameLabel.text("按钮名称");
            $menuUrlListRow.hide();
            $menuIconListRow.hide();
            $menuShowListrow.hide();
        }
    });

	
	
    $("#permissionAddBtn").click(function () {
	   var selPid = $menuTree.getSelectedID();
       // 选中父菜单
       $permissionAddForm.find("input[name='pid']").val(selPid)
        var name = $(this).attr("name");
        $formValidator = $permissionAddForm.validate();
        var flag = $formValidator.form();
        if (flag) {
            if (name === "save") {
                $.post("system/permission/add", $permissionAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("system/permission/update", $permissionAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
    });

    $("#addPermissionModal .btn-close").click(function () {
        $("input:radio[value='0']").trigger("click");
        closeModal();
    });
})
/**
 * 下拉列表树初始化
 **/
function createPermsTree(){
	$.getJSON("system/permission/treeList",function(obj){
	var data = [obj.msg];
	 $menuTree = $('#lyear-dropdown-tree').lyearDropdownTree({
	    data : data,
	    multiSelect : false,
	    jsonStr : ',',
	    selectedData : [],
	    relationParent : false,
	    relationChildren : true,
		checkHandler: function(el) {
			//console.log("checked ", el);
		}
	});
})
}

function refreshTree(){
	$menuTree.refreshTree();
}
function closeModal() {
    $("#permissionAddBtn").attr("name", "save");
    $("#show-mdi").text("");
    $font_element.setIcon("");
    $menuUrlListRow.show();
    $menuIconListRow.show();
    $formValidator.resetForm();
    $Jtk.closeAndRestModal("addPermissionModal");
    refreshTree();
}
function initIconSelect(){
	 $font_element = $('#menu-icon').fontIconPicker({
        theme: 'fip-bootstrap'
    });
    
    $.ajax({
        url: './js/fontIconPicker/fontjson/materialdesignicons.json',
        type: 'GET',
        dataType: 'json'
    }).done(function(response) {
        var fontello_json_icons = [];
        $.each(response.glyphs, function(i, v) {
            fontello_json_icons.push( v.css );
        });
 
        $font_element.setIcons(fontello_json_icons);
    }).fail(function() {
        console.error('字体图标配置加载失败');
    });
    
    $(document).on('change', '#menu-icon', function(){
        $('#show-mdi').html($(this).val());
    });
}
function initValidate(){
   $formValidator= $('#permissionAddForm').validate({
        ignore: ".ignore",    // 插件默认不验证隐藏元素,这里可以自定义一个不验证的class,即验证隐藏元素,不验证class为.ignore的元素
        focusInvalid: false,  // 禁用无效元素的聚焦
        rules: {
	        name:{
		        required:true,
                minlength:2,
                maxlength:50
	        },
            perms: {
                required: false,
                minlength: 2,
                maxlength: 50,
                remote: {
                    url: "system/permission/checkMenuCode",
                    type: "get",
                    dataType: "json",
                    data: {
                        permCode: function () {
                            return $("input[name='perms']").val().trim();
                        },
                        opType: function () {
	                        var opType =  $("#permissionAddBtn").attr("name");
                            return opType;
                        },
                        menuId:function(){
	                       var menuId = $("input[name='id']").val();
                           return menuId;
                       }
                    }
                }
            }
         },
       errorPlacement: function errorPlacement(error, element) {
            var $parent = $(element).parents('.form-group');
            if ($parent.find('.invalid-feedback').length) {
                return;
            }
            $(element).addClass('is-invalid');
            $parent.append(error.addClass('invalid-feedback'));
        },
        unhighlight: function(element) {
            $(element).removeClass('is-invalid');
        },
    })	
	
}