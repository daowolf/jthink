$(function () {
    initDataTable();
});
function initDataTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        url: '/system/appearance/linkcategory/list',
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
                title: '简称',
                field: 'slug'
            },
            {
                title: '排序',
                field: 'termOrder'
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
	                'click .term-linkmng': function (e, value, row, index) {
	                    linkmng(row.id);
	                },
	                'click .term-edit': function (e, value, row, index) {
	                    updateTerm(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('termsTable', setting);
}

function refresh() {
    $Jtk.refreshTable("termsTable");
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="term-edit btn btn-xs btn-default m-r-5" title="修改" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>',
        '<a  class="term-linkmng btn btn-xs btn-default m-r-5" title="内容管理" data-toggle="tooltip"><i class="mdi mdi-comment-text-outline"></i></a>',
        '<a  class="term-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function linkmng(termId){
	loadUrlToMain("/system/appearance/jlink/index?termId="+termId);
}
function delTerm(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/appearance/linkcategory/delete', {"termIds": id}, function (r) {
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
        $.post('/system/appearance/linkcategory/delete', {"termIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

