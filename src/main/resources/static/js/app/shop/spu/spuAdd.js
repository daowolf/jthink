var $spuAddForm = $("#spuAddForm");
var $formValidator;
var $font_element
$(function () {
    // 初始化验证器
	initValidate();
		    // 标签
	$('.js-tags-input').each(function() {
        var $this = $(this);
        $this.tagsInput({
			height: $this.data('height') ? $this.data('height') : '36px',
			width: '100%',
			minInputWidth:'100%',
			defaultText: $this.attr("placeholder"),
			removeWithBackspace: true,
			delimiter: [',']
		});
    });
    $("#spuAddBtn").click(function () {
        var name = $(this).attr("name");
        $formValidator = $spuAddForm.validate();
        var flag = $formValidator.form();
        if (flag) {
            if (name === "save") {
                $.post("/system/shop/spu/add", $spuAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("system/shop/spu/update", $spuAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
    });

    $("#addSpuModal .btn-close").click(function () {
        closeModal();
    });
})


function closeModal() {
    $("#spuAddBtn").attr("name", "save");
    $formValidator.resetForm();
     $('#spu-value').importTags("");
    $Jtk.closeAndRestModal("addSpuModal");
}

function initValidate(){
   $formValidator= $('#spuAddForm').validate({
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