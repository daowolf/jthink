var $userAddForm = $("#userAddForm");
var $rolesSelect = $("#rolesSelect");
var $formValidator;
var $permsTree;
var $font_element
$(function () {
    // 初始化验证器
	initValidate();
	initRole();
    $("#userAddBtn").click(function () {
        var name = $(this).attr("name");
        $formValidator = $userAddForm.validate();
        var flag = $formValidator.form();
        if (flag) {
            if (name === "save") {
                $.post("system/user/add", $userAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("system/user/update", $userAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
    });

    $("#addUserModal .btn-close").click(function () {
        closeModal();
    });
})

function closeModal() {
    $("#userAddBtn").attr("name", "save");
    $formValidator.resetForm();
    $userAddForm.find(".password-row").show();
    $userAddForm.find("input[name='username']").removeAttr("readonly");
    $Jtk.closeAndRestModal("addUserModal");
}
function initRole() {
    $.get("system/role/list", {pageSize:20,pageNum:1}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>"
        }
        $rolesSelect.html("").append(option);
        $rolesSelect.selectpicker();
    });
}
function initValidate(){
   $formValidator= $('#userAddForm').validate({
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