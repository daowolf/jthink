$(function () {
    initUserTable();
});
function initUserTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        pagination:true,
        pageSize:10,
        url: 'system/user/list',
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
                title: '用户名',
                field: 'username'
            },
            {
                title: '昵称',
                field: 'nickname'
            },
            {
                title: '邮箱',
                field: 'email'
            },
            {
                title: '电话',
                field: 'phone'
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
	                'click .user-delete': function (e, value, row, index) {
	                    delUser(row.id);
	                },
	                'click .user-edit': function (e, value, row, index) {
	                    updateUser(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('userTable', setting);
}

function refresh() {
    $Jtk.refreshTable("userTable");
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="user-edit btn btn-xs btn-default m-r-5" title="修改" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>',
        '<a  class="user-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delUser(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('system/user/delete', {"userIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deleteUsers() {
    var ids = $("#userTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的用户！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/user/delete', {"userIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

