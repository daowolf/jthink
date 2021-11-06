var $couponAddForm = $("#coupon-add-form");
$(function () {
	initUpload();
    $couponAddForm.find("input[name='template']").val("couponProductList.html");
    $("#coupon-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var form = $couponAddForm[0];
        var flag = form.checkValidity();
        form.classList.add('was-validated');
        if (flag) {
            if (name === "save") {
                $.post("/system/shop/coupon/add", $couponAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        closeModal();
                        $Jtk.n_success(r.msg);
                        $Jtk.refreshTable("couponTable");
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("/system/shop/coupon/update", $couponAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        closeModal();
                        $Jtk.n_success(r.msg);
                        $Jtk.refreshTable("couponTable");
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
    });

    $("#coupon-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#coupon-add-button").attr("name", "save");
    $couponAddForm.find("input[name='template']").val("couponProductList.html");
    $couponAddForm.find("input[name='recommend']").prop("checked", true);
    $("#coupon-add-modal-title").html('新增优惠券类型');
    $Jtk.closeAndRestModal("coupon-add");

}
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