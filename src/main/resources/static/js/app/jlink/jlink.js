$(function () {
    initJlinkTable();
});
function initJlinkTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        pagination:true,
        pageSize:10,
        url: '/system/appearance/jlink/list?termId='+termId,
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
                field: 'linkName'
            },
            {
                title: '链接地址',
                field: 'linkUrl'
            },
            {
                title: '打开方式',
                field: 'linkTarget'
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
	                'click .jlink-delete': function (e, value, row, index) {
	                    delJlink(row.id);
	                },
	                'click .jlink-edit': function (e, value, row, index) {
	                    updatePost(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('jlinkTable', setting);
}

function refresh() {
    $Jtk.refreshTable("jlinkTable");
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="jlink-edit btn btn-xs btn-default m-r-5" title="修改" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>',
        '<a  class="jlink-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delJlink(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/appearance/jlink/delete', {"linkIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deleteJlinks() {
    var ids = $("#jlinksTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的链接！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中链接？",
        confirmButtonText: "确定删除"
    }, function () {
        $.get('/system/appearance/jlink/delete', {"linkIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

