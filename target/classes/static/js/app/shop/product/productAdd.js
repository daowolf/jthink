var $productAddForm = $("#productAddForm");
var $formValidator;
var $termTree;
var $font_element;
$(function () {
	// 预览图上传
	initUpload();
    // 初始化验证器
	initValidate();
	// 初始化树菜单select
	createTermsTree();
	$("#template").val("product");
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
    $("#productAddBtn").click(function () {
        var name = $(this).attr("name");
        // 同步ckeditor内容
        var contents = postEditor.getData();
        $("#postContent").val(contents);
        $formValidator = $productAddForm.validate();
        var txIds = $termTree.getSelectedID();
        $("#termIds").val(txIds);
        var flag = $formValidator.form();
        if (flag) {
	        var formDatas = $productAddForm.serialize();
           //  var metas=[];
          //   metas.push({"metaKey":"price","metaValue":$("#price").val()});
          //   metas.push({"metaKey":"oriPrice","metaValue":$("#oriPrice").val()});
             // 扩展参数
        //     formDatas+="&extarsStr="+JSON.stringify(metas);
            if (name === "save") {
                $.post("/system/shop/product/add", formDatas, function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("/system/shop/product/update", formDatas, function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
        }
    });

    $("#addProductModal .btn-close").click(function () {
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
    $("#productAddBtn").attr("name", "save");
    $formValidator.resetForm();
    postEditor.setData("");
    $termTree.setSelectedItem([]);
    $Jtk.closeAndRestModal("addProductModal");
    $('#tags').importTags("");
    $("#template").val("product");
}
$(".btn-close").click(function(){
	$termTree.refreshTree();
})
/**
 * 下拉列表树初始化
 **/
function createTermsTree(){
	$.getJSON("/system/shop/category/treeList",function(obj){
	var data = obj.msg;
	 $termTree = $('#lyear-dropdown-tree').lyearDropdownTree({
	    data : data[0].children,
	    multiSelect : true,
	    jsonStr : ',',
	    selectedData : [],
	    relationParent : false,
	    relationChildren : false,
		checkHandler: function(el) {
			// console.log($termTree.getSelectedID());
		}
	});
	// 创建分类过滤树
	createFilterTree(data);
})
}
function createFilterTree(data){
    $('#productCat-tree').jstree({
        'core' : {
            'themes' : {
                'responsive': true
            },
            'data' : data
        },
        'plugins' : ['types']
    });
    // 节点击过滤
	$('#productCat-tree').bind("activate_node.jstree", function (obj, e) {
	    // 处理代码
	    // 获取当前节点
	    var currentNode = e.node;
         _productCategoryId=currentNode.id;
         if(_productCategoryId=='0'){
              _productCategoryId=undefined;
         }
         refresh();
	});	
}
function initValidate(){
   $formValidator= $('#productAddForm').validate({
        ignore: ".ignore",    // 插件默认不验证隐藏元素,这里可以自定义一个不验证的class,即验证隐藏元素,不验证class为.ignore的元素
        focusInvalid: false,  // 禁用无效元素的聚焦
        //用覆盖onfocusout 解决ckeditor5无法校验的问题
        onfocusout: function(ele){
	        // console.log("element.....失去校验");
        },
        rules: {
            postTitle: {
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