var postEditor;
var _productCategoryId;
$(function () {
    initPostTable();
    // 初始化editor
	ClassicEditor
		.create(document.querySelector( '#postContent' ),{
				
				toolbar: {
					items: [
						'heading',
						'|',
						'bold',
						'italic',
						'link',
						'bulletedList',
						'numberedList',
						'|',
						'outdent',
						'indent',
						'imageInsert',
						'blockQuote',
						'insertTable',
						'mediaEmbed',
						'undo',
						'redo',
						'alignment',
						'code',
						'codeBlock',
						'fontSize',
						'fontColor',
						'fontFamily',
						'removeFormat',
						'underline'
					]
				},
				ckfinder:{
				    uploadUrl:"/upload/image"
			     },				
				language: 'zh-cn',
				image: {
					toolbar: [
						'imageTextAlternative',
						'imageStyle:full',
						'imageStyle:side',
						'linkImage'
					]
				},
				table: {
					contentToolbar: [
						'tableColumn',
						'tableRow',
						'mergeTableCells'
					]
				},
				licenseKey: '',
				
				
			})
		.then( editor => {
			postEditor = editor;
			editor.model.document.on('change:data', function () {
                $("#postContent").val(editor.getData());
            });
            editor.model.document.on('mouseup',function(){
	            console.log("mouseup....");
            })
         // 解决jquery validate校验失败问题
		} )
		.catch( err => {
			console.error( err.stack );
		} );

});
function initPostTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        pagination:true,
        pageSize:10,
        url: '/system/shop/product/list',
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                categoryId:_productCategoryId,
            };
        },
        columns: [
            {
                field: 'selectItem',
                checkbox: true
            },
            {
                title: '编号',
                field: 'id',
                width: '50px'
            },
            {
                title: '标题名',
                field: 'postTitle'
            },
            {
                title: '销量',
                field: 'saleCounts'
            },
            {
                title: '创建时间',
                field: 'createTime'
            },
	        {
	            field: 'operate',
	            title: '操作',
	            align: 'right',
	            events : {
	                'click .post-delete': function (e, value, row, index) {
	                    delProduct(row.id);
	                },
	                'click .post-commentmng': function (e, value, row, index) {
	                    commentsMng(row.id);
	                },
                    'click .post-spu': function (e, value, row, index) {
	                    productSpuMng(row.id);
	                },
                    'click .post-sku': function (e, value, row, index) {
	                    productSkuMng(row.id);
	                },
	                'click .post-edit': function (e, value, row, index) {
	                    updatePost(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('productsTable', setting);
}

function refresh() {
    $Jtk.refreshTable("productsTable");
}
function commentsMng(productId){
	loadUrlToMain("/system/shop/comments/index?postId="+productId);
}
function productSpuMng(productId){
	loadUrlToMain("/system/shop/productspu/index?productId="+productId);
}
function productSkuMng(productId){
	loadUrlToMain("/system/shop/product/sku/index?productId="+productId);
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a type="button" class="post-edit btn btn-xs btn-default m-r-5" title="编辑" data-toggle="tooltip">编辑</a>',
        '<a type="button" class="post-commentmng btn btn-xs btn-default m-r-5" title="评论" data-toggle="tooltip">评论</a>',
        '<a type="button" class="post-spu btn btn-xs btn-default m-r-5" title="规格" data-toggle="tooltip">规格</a>',
        '<a type="button" class="post-sku btn btn-xs btn-default m-r-5" title="库存" data-toggle="tooltip">库存</a>',
        '<a type="button" class="post-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip">删除</a>'
    ].join('');
}
function delProduct(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/shop/product/delete', {"postIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deleteProducts() {
    var ids = $("#productsTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的商品！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中商品？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/shop/product/delete', {"postIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

