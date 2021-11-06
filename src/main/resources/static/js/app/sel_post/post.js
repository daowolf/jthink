$(function () {
    initPostTable();
    initSelTable();
    $("#selPostModel .btn-close").click(function () {
        closeModal();
    });	
	$("#selSaveBtn").click(function(){
	    var ids = $("#selTable").bootstrapTable("getSelections");
	    var ids_arr = "";
	    if (!ids.length) {
	        $Jtk.n_warning("请勾选需要关联的关联关系！");
	        return;
	    }
	    for (var i = 0; i < ids.length; i++) {
	        ids_arr += ids[i].id;
	        if (i !== (ids.length - 1)) ids_arr += ",";
	    }
	    $Jtk.confirm({
	        text: "确定选中关联？",
	        confirmButtonText: "确定"
	    }, function () {
	        $.post('/system/cms/post/saveRelation', {"postIds": ids_arr,"termId":termId}, function (r) {
	            if (r.code === 200) {
	                $Jtk.n_success(r.msg);
	                refresh();
	            } else {
	                $Jtk.n_danger(r.msg);
	            }
	        });
	    });		
	})
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
        url: '/system/cms/post/sellist?postType='+postType+'&termId='+termId,
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
                title: '标题',
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
	                    delPost(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('postTable', setting);
}
function initSelTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
	    sidePagination: 'server',
	    showColumns: true,
        pagination:true,
        pageSize:10,
        url: '/system/cms/post/sellist?postType='+postType,
        columns: [
            {
                field: 'selSelectItem',
                checkbox: true
            },
            {
                title: '编号',
                field: 'id',
                width: '50px'
            },
            {
                title: '标题',
                field: 'postTitle'
            },
            {
                title: '评论数',
                field: 'commentCount'
            },
            {
                title: '创建时间',
                field: 'createTime'
            }
        ]

    };

    $Jtk.initTable('selTable', setting);
}
function refresh() {
    $Jtk.refreshTable("postTable");
}
function closeModal() {
   var $modal = $("#selPostModel");
       $modal.find("button.btn-hide").attr("data-dismiss", "modal").trigger('click');
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a type="button" class="post-delete btn btn-xs btn-default" title="取消" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delPost(id){
	$Jtk.confirm({
		text:" 确定要删除关联?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/cms/post/delRelation', {"postIds": id,"termId":termId}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function cancelRelation() {
    var ids = $("#postTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的关联关系！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中关联？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/cms/post/delRelation', {"postIds": ids_arr,"termId":termId}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

