var $jlinkAddForm = $("#jlinkAddForm");
var $targetSelect = $("#linkTarget");
var $formValidator;
var $font_element;
$(function () {
	// 预览图上传
	initUpload();
    // 初始化验证器
	initValidate();
	$targetSelect.selectpicker();
    $("#jlinkAddBtn").click(function () {
        var name = $(this).attr("name");
        $("#input-TermId").val(termId);
        // 同步ckeditor内容
        $formValidator = $jlinkAddForm.validate();
        var flag = $formValidator.form();
        if (flag) {
            if (name === "save") {
                $.post("/system/appearance/jlink/add", $jlinkAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("/system/appearance/jlink/update", $jlinkAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
    });

    $("#addJlinkModal .btn-close").click(function () {
        closeModal();
    });
})
function initUpload(){
	$(".file-browser").click(function(){
        var $browser = $(this);
        var file = $browser.closest('.file-group').find('[type="file"]');
        file.on( 'click', function(e) {
            e.stopPropagation();
        });
        file.trigger('click');
	})
	$('.file-group [type="file"]').change(function(){
		        var $this    = $(this);
        var $input   = $(this)[0];
        var $len     = $input.files.length;
        var formFile = new FormData();
        
        if ($len == 0) {
            return false;
        } else {
            var fileAccaccept = $this.attr('accaccept');
            var fileType      = $input.files[0].type;
            var type          = (fileType.substr(fileType.lastIndexOf("/") + 1)).toLowerCase();
            
            if (!type || fileAccaccept.indexOf(type) == -1) {
                lightyear.notify('您上传图片的类型不符合(.jpg|.jpeg|.gif|.png|.bmp)', 'danger', 1000);
                return false;
            }
            formFile.append("upload", $input.files[0]);
        }
        
        var data = formFile;
        var l = $('body').lyearloading({
            opacity: 0.2,
            spinnerSize: 'nm'
        });
        $.ajax({
            url: '/upload/image',
            data: data,
            type: "POST",
            dataType: "json",
            //上传文件无需缓存
            cache: false,
            //用于对data参数进行序列化处理 这里必须false
             processData: false,
            //必须
            contentType: false, 
            success: function (res) {
                l.destroy();
                if (res.uploaded === true) {
                    $this.closest('.file-group').find('.file-value').val(res.url);
                    $("#previewImg").attr("src",res.url);
                } else {
                    jQuery.notify({
                        icon: '',
                        message: res.info
                    },
                    {
                        element: 'body',
                        type: 'danger',
                        allow_dismiss: true,
                        newest_on_top: true,
                        showProgressbar: false,
                        placement: {
                            from: 'top',
                            align: 'center'
                        },
                        offset: 20,
                        spacing: 10,
                        z_index: 10800,
                        delay: 3000,
                        animate: {
                            enter: 'animated shake',
                            exit: 'animated fadeOutDown'
                        }
                    });
                }
            },
        });
	})
}
function closeModal() {
    $("#jlinkAddBtn").attr("name", "save");
    $formValidator.resetForm();
    $Jtk.closeAndRestModal("addJlinkModal");
    $("#previewRow").hide();
    $("#previewImg").attr("src","/images/defaultPreview.jpeg");
}
function initValidate(){
   $formValidator= $('#jlinkAddForm').validate({
        ignore: ".ignore",    // 插件默认不验证隐藏元素,这里可以自定义一个不验证的class,即验证隐藏元素,不验证class为.ignore的元素
        focusInvalid: false,  // 禁用无效元素的聚焦
        //用覆盖onfocusout 解决ckeditor5无法校验的问题
        onfocusout: function(ele){
	        // console.log("element.....失去校验");
        },
        rules: {
            jlinkTitle: {
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