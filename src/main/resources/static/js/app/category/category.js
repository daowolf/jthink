$(function () {
    initTreeTable();
});
function initTreeTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        url: '/system/cms/category/list',
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
                title: '名称',
                field: 'name'
            },

            {
                title: '排序',
                field: 'orderNum'
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
	                    delTerm(row.id);
	                },
	                'click .term-post': function (e, value, row, index) {
	                    selPost(row.id);
	                },
	                'click .term-edit': function (e, value, row, index) {
	                    updateTerm(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ],
	    treeShowField: 'name',
	    parentIdField: 'parentId',
	    onResetView: function() {
	        $("#termsTable").treegrid({
	            initialState: 'collapsed', // 所有节点都折叠
                // 第二列做为树形节点展示列(columns中配置的列顺序索引)
	            treeColumn: 2,
	          //  expanderExpandedClass: 'mdi mdi-folder-open',  // 可自定义图标样式
	           // expanderCollapsedClass: 'mdi mdi-folder',
	        });
	        // 只展开树形的第一集节点
            // $("#menuTable").treegrid('getRootNodes').treegrid('expandAll');
            // 展开所有节点
	         $("#termsTable").treegrid('expandAll');
	    }

    };

    $Jtk.initTreeTable('termsTable', setting);
}

function refresh() {
    $Jtk.refreshTable("termsTable");
   // termsAdd.js中
    refreshTree();
}
function selPost(termId){
	loadUrlToMain("/system/cms/post/selpost?postType=posts&termId="+termId);
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="term-edit btn btn-xs btn-default m-r-5" title="修改" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>',
        '<a  class="term-post btn btn-xs btn-default m-r-5" title="分类文章" data-toggle="tooltip"><i class="mdi mdi-comment-text-outline"></i></a>',
        '<a  class="term-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delTerm(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/cms/category/delete', {"termIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deleteTerms() {
    var ids = $("#termsTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的分类！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中分类？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/cms/category/delete', {"termIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

