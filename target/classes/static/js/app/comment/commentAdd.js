var $commentAddForm = $("#commentAddForm");
var $commentApproved = $("#commentApproved");
var $formValidator;
var $font_element;
$(function () {
    // 初始化验证器
	initValidate();
	$commentApproved.selectpicker();
    $("#commentAddBtn").click(function () {
        var name = $(this).attr("name");
        var flag = $formValidator.form();
        if (flag) {
            if (name === "save") {
                $.post("/system/cms/comments/add", $commentAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("/system/cms/comments/update", $commentAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
    });

    $("#addCommentModal .btn-close").click(function () {
        closeModal();
    });
})

function closeModal() {
    $("#commentAddBtn").attr("name", "save");
    $formValidator.resetForm();
    $Jtk.closeAndRestModal("addCommentModal");
}
function initValidate(){
   $formValidator= $('#commentAddForm').validate({
        ignore: ".ignore",    // 插件默认不验证隐藏元素,这里可以自定义一个不验证的class,即验证隐藏元素,不验证class为.ignore的元素
        focusInvalid: false,  // 禁用无效元素的聚焦
        //用覆盖onfocusout 解决ckeditor5无法校验的问题
        onfocusout: function(ele){
	        // console.log("element.....失去校验");
        },
        rules: {
            commentContent: {
                required: true,
                minlength: 2,
                maxlength: 50
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