var $roleAddForm = $("#roleAddForm");
var $formValidator;
var $rolePermsTree;
var $font_element
$(function () {
		// 初始化树菜单select
	createRolePermsTree();
    // 初始化验证器
	initValidate();
    $("#roleAddBtn").click(function () {
	   var selPerms = $rolePermsTree.getSelectedID();
       // 选中父菜单
       $roleAddForm.find("input[name='permissionIds']").val(selPerms)
        var name = $(this).attr("name");
        $formValidator = $roleAddForm.validate();
        var flag = $formValidator.form();
        if (flag) {
            if (name === "save") {
                $.post("system/role/add", $roleAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("system/role/update", $roleAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
    });

    $("#addRoleModal .btn-close").click(function () {
        closeModal();
    });
})
/**
 * 下拉列表树初始化
 **/
function createRolePermsTree(){
	
	$.getJSON("system/permission/treeList",function(obj){
	var data = [obj.msg];
	 $rolePermsTree = $('#roleTree').lyearDropdownTree({
	    data : data,
	    multiSelect : true,
	    jsonStr : ',',
	    selectedData : [],
	    relationParent : false,
	    relationChildren : true,
		checkHandler: function(el) {
			//console.log("checked ", el);
		},
	});
})
}

function refreshTree(){
	$rolePermsTree.refreshTree();
}
function closeModal() {
    $("#roleAddBtn").attr("name", "save");
    $formValidator.resetForm();
    $Jtk.closeAndRestModal("addRoleModal");
    refreshTree();
}

function initValidate(){
   $formValidator= $('#roleAddForm').validate({
        ignore: ".ignore",    // 插件默认不验证隐藏元素,这里可以自定义一个不验证的class,即验证隐藏元素,不验证class为.ignore的元素
        focusInvalid: false,  // 禁用无效元素的聚焦
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 10
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