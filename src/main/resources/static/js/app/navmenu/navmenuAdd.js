var $postAddForm = $("#postAddForm");
var $formValidator;
var $termTree;
var $productTree;
var $navTree;
function initValidate(){
   $formValidator= $('#postAddForm').validate({
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
/**
 * 下拉列表树初始化
 **/
function createNavMenuTree(){
	$.getJSON("/system/appearance/navmenu/treeList?termId="+termId,function(obj){
	var data = obj.msg;
	 $navTree = $('#lyear-dropdown-tree').lyearDropdownTree({
	    data : data,
        reloadUrl:"/system/appearance/navmenu/treeList?termId="+termId,
        reloadKey:"msg",
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
/**
 * 分类下拉列表树初始化
 **/
function createCategoryTree(){
	$.getJSON("/system/cms/category/treeList",function(obj){
	    var nodes = obj.msg;
		var data =[];
		if(nodes[0]){
			 data = nodes[0].children;
		}	

	 $termTree = $('#lyear-dropdown-tree-category').lyearDropdownTree({
	    data : data,
	    multiSelect : false,
	    jsonStr : ',',
	    selectedData : [],
	    relationParent : false,
	    relationChildren : true,
		checkHandler: function(el) {
		//	console.log("checked ", el);
	        var selName = $termTree.getSelectedText();
            $("#postTitle").val(selName);

		}
	});
})
}
function createProductCategoryTree(){
	$.getJSON("/system/shop/category/treeList",function(obj){
	    var nodes = obj.msg;
		var data =[];
		if(nodes[0]){
			 data = nodes[0].children;
		}	

	 $productTree = $('#lyear-dropdown-tree-product').lyearDropdownTree({
	    data : data,
	    multiSelect : false,
	    jsonStr : ',',
	    selectedData : [],
	    relationParent : false,
	    relationChildren : true,
		checkHandler: function(el) {
		//	console.log("checked ", el);
	        var selName = $productTree.getSelectedText();
            $("#postTitle").val(selName);

		}
	});
})
}
/** 
 * page列表
 */
function createPageList(){
	$.getJSON("/system/cms/page/list?pageSize=100&pageNum=1",function(res){
		var rows = res.rows;
		var optStr="";
		for(var i=0;i<rows.length;i++) {
			var page = rows[i];
			optStr += ' <option value="'+page.id+'">'+page.postTitle+'</option>';
		}
		$("#navPage").html(optStr);
        $('.selectpicker').selectpicker();
	})
}
function refreshTree(){
	$termTree.refreshTree();
	$productTree.refreshTree();
	$navTree.reload();
}
function closeModal() {
    $("#postAddBtn").attr("name", "save");
    $formValidator.resetForm();
    $Jtk.closeAndRestModal("addPostModal");
    $(".selectpicker").selectpicker('refresh');
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
$(function(){
    // 初始化验证器
	initValidate();
	// 初始化树菜单select
	createNavMenuTree();
	createCategoryTree();
	createProductCategoryTree();
	createPageList();
	initUpload();
	    $postAddForm.find("input[name='type']").change(function () {
        var $value = $postAddForm.find("input[name='type']:checked").val();
        if($value === "category") {
	      $("#categoryRow").show();
          $("#productRow").hide();
	      $("#pageRow").hide();
          $("#userLinkAddr").hide();
         }else if ($value === "page") {
	      $("#categoryRow").hide();
          $("#productRow").hide();
	      $("#pageRow").show();
          $("#userLinkAddr").hide();
        }else if($value === "link_category"){
	      $("#categoryRow").hide();
          $("#productRow").hide();
	      $("#pageRow").hide();
          $("#userLinkAddr").show();
        }else if($value === "product_category"){
	      $("#categoryRow").hide();
          $("#productRow").show();
	      $("#pageRow").hide();
          $("#userLinkAddr").hide();
        } 
         }	
       )
    $("#addPostModal .btn-close").click(function () {
        closeModal();
    });	
	$("#postAddBtn").click(function(){
	   var selPid = $termTree.getSelectedID();
       var postParent = $navTree.getSelectedID();
       $("#postParent").val(postParent);
       var $value = $postAddForm.find("input[name='type']:checked").val();
       // termId 为navmenu.html全局变量
       $("#termId").val(termId);
       if($value=="category"){
	      var selCategoryId = $termTree.getSelectedID();
	      $("#postContent").val(selCategoryId);
       }else if($value=="page"){
	      var selPage = $("#navPage").val();
	     $("#postContent").val(selPage);
       }else if($value=="link_category"){
	      var navUrl = $("#navUrl").val();
	     $("#postContent").val(navUrl);
      }else if($value=="product_category"){
		   var selCategoryId = $productTree.getSelectedID();
	      $("#postContent").val(selCategoryId);
       }
		var name = $(this).attr("name");
        $formValidator = $postAddForm.validate();
        var flag = $formValidator.form();
        if (flag) {
            if (name === "save") {
                $.post("system/appearance/navmenu/add", $postAddForm.serialize(), function (r) {
                    if (r.code === 200) {
                        refresh();
                        closeModal();
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post("system/appearance/navmenu/update", $postAddForm.serialize(), function (r) {
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