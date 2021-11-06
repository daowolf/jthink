var $termAddForm = $("#termAddForm");
var $formValidator;
function initValidate(){
   $formValidator= $('#termAddForm').validate({
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

function closeModal() {
    $("#termAddBtn").attr("name", "save");
    $formValidator.resetForm();
    $Jtk.closeAndRestModal("addTermModal");
}
$(function(){
    // 初始化验证器
	initValidate();
    $("#addTermModal .btn-close").click(function () {
        closeModal();
    });	
	$("#termAddBtn").click(function(){
		var name = $(this).attr("name");
        $formValidator = $termAddForm.validate();
        var flag = $formValidator.form();
        if (flag) {
            if (name === "save") {
                $.post("/system/appearance/navcategory/add", $termAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("/system/appearance/navcategory/update", $termAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
	})
})