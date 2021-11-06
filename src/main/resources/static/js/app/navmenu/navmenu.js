$(function () {
    initTreeTable();
});
function initTreeTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
        pagination:false,
	    showColumns: true,
        url: '/system/appearance/navmenu/list?termId='+termId,
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
                title: '类型',
                field: 'postMimeType'
            },
            {
                title: '排序',
                field: 'postOrder'
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
	                'click .term-delete': function (e, value, row, index) {
	                    delPost(row.id);
	                },
	                'click .term-edit': function (e, value, row, index) {
	                    updateMenuPost(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ],
	    treeShowField: 'postTitle',
	    parentIdField: 'postParent',
	    onResetView: function() {
	        $("#navmenuTable").treegrid({
	            initialState: 'collapsed', // 所有节点都折叠
                // 第二列做为树形节点展示列(columns中配置的列顺序索引)
	            treeColumn: 2,
	          //  expanderExpandedClass: 'mdi mdi-folder-open',  // 可自定义图标样式
	           // expanderCollapsedClass: 'mdi mdi-folder',
	        });
	        // 只展开树形的第一集节点
            // $("#navmenuTable").treegrid('getRootNodes').treegrid('expandAll');
            // 展开所有节点

	         $("#navmenuTable").treegrid('expandAll');
	    }

    };

    $Jtk.initTreeTable('navmenuTable', setting);
}

function refresh() {
    $Jtk.refreshTable("navmenuTable");
   // termsAdd.js中
    refreshTree();
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="term-edit btn btn-xs btn-default m-r-5" title="修改" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>',
        '<a  class="term-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delPost(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/appearance/navmenu/delete', {"postIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deletePosts() {
    var ids = $("#termsTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的导航！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中导航？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/appearance/navmenu/delete', {"postIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

