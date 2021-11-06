var pageEditor;
$(function () {
    initPageTable();
    // 初始化editor
	ClassicEditor
		.create(document.querySelector( '#postContent' ), {
				
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
						'|',
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
				
				
			} )
		.then( editor => {
			pageEditor = editor;
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
function initPageTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        pagination:true,
        pageSize:10,
        url: '/system/cms/page/list',
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
                title: '评论数',
                field: 'commentCount'
            },
            {
                title: '创建时间',
                field: 'createTime'
            },
	        {
	            field: 'operate',
	            title: '操作',
	            align: 'center',
	            events : {
	                'click .post-delete': function (e, value, row, index) {
	                    delPage(row.id);
	                },
	                'click .post-edit': function (e, value, row, index) {
	                    updatePage(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('pageTable', setting);
}

function refresh() {
    $Jtk.refreshTable("pageTable");
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a type="button" class="post-edit btn btn-xs btn-default m-r-5" title="修改" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>',
        '<a type="button" class="post-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delPage(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/cms/page/delete', {"pageIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deletePages() {
    var ids = $("#postsTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的页面！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中页面？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/cms/page/delete', {"pageIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

