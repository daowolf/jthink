var $skuAddForm = $("#skuAddForm");
var $formValidator;
var $font_element
$(function () {
    // 初始化验证器
	initValidate();
		initSpuList();
    $("#skuAddBtn").click(function () {
        var name = $(this).attr("name");
        $formValidator = $skuAddForm.validate();
        var flag = $formValidator.form();
        if (flag) {
	           
                var idArrs=[];
                var strArrs=[];
                $(".spuItem").each(function(index,item){
	                var $sel=$(item).find("option:selected");
	                idArrs.push($sel.val());
                    var label = $(item).siblings("label").text();
                    strArrs.push(label+":"+$sel.text());
                })
                // ID要进行组件排序，否则同一套ID可能会有N多种组合，可能不太科学
	            idArrs.sort(function(value1, value2) {
					return parseInt(value1) - parseInt(value2);
				});
                var spuvalIds = idArrs.join(";");
                var spuvalStr = strArrs.join(",");
                var formItems = $skuAddForm.serialize();
                formItems +="&spuvalIds="+spuvalIds;
                formItems +="&spuvalStr="+spuvalStr;      
                formItems +="&productId="+productId;  
                $.post("/system/shop/product/sku/add",formItems, function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
        }
    });

    $("#addSkuModal .btn-close").click(function () {
        closeModal();
    });
})


function closeModal() {
    $("#skuAddBtn").attr("name", "save");
    $formValidator.resetForm();
     $('#sku-value').importTags("");
    $Jtk.closeAndRestModal("addSkuModal");
}
function initSpuList(){
	$.getJSON("/system/shop/productspu/selectList?productId="+productId,function(res){
		if(res.code === 200){
			var items = res.msg;
		    $('#skuList').tmpl(items).insertBefore("#row-stock")
		}
	})
}
function initValidate(){
   $formValidator= $('#skuAddForm').validate({
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