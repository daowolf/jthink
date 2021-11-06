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
        url: '/system/permission/list',
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
                title: '图标',
                field: 'icon',
                formatter: function (value, item, index) {
                    return '<i class="zmdi ' + item.icon + '"></i>';
                }

            },
            {
                title: '类型',
                field: 'type',
                formatter: function (value, item, index) {
                    if (item.type === '0') return '<font class="text-pink">目录</font>';
                    if (item.type === '1') return '<font class="text-pink">菜单</font>';
                    if (item.type === '2') return '<font class="text-info">按钮</font>';
                }

            },
            {
                title: '是否显示',
                field: 'isShow',
                formatter: function (value, item, index) {
                    if (item.isShow === 0) return '<font class="text-pink">否</font>';
                    if (item.isShow === 1) return '<font class="text-info">是</font>';
                }

            },
            {
                title: '地址',
                field: 'url',
                formatter: function (value, item, index) {
                    return item.url === 'null' ? '' : item.url;
                }
            },
            {
                title: '权限标识',
                field: 'perms',
                formatter: function (value, item, index) {
                    return item.perms === 'null' ? '' : item.perms;
                }
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
	                'click .role-delete': function (e, value, row, index) {
	                    delPermission(row.id);
	                },
	                'click .role-edit': function (e, value, row, index) {
	                    updatePermission(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ],
	    treeShowField: 'name',
	    parentIdField: 'pid',
	    onResetView: function() {
	        $("#permissionTable").treegrid({
	            initialState: 'collapsed', // 所有节点都折叠
                // 第二列做为树形节点展示列(columns中配置的列顺序索引)
	            treeColumn: 2,
	         //   expanderExpandedClass: 'mdi mdi-folder-open',  // 可自定义图标样式
	          //  expanderCollapsedClass: 'mdi mdi-folder',
	        });
	        // 只展开树形的第一集节点
            // $("#menuTable").treegrid('getRootNodes').treegrid('expandAll');
            // 展开所有节点
              if($("#permissionTable").length>0 && $("#permissionTable").treegrid('expandAll')!=null){
	             $("#permissionTable").treegrid('expandAll');
              }
	        
	    }

    };

    $Jtk.initTreeTable('permissionTable', setting);
}

function refresh() {
    $Jtk.refreshTable("permissionTable");
   // permissionAdd.js中
    refreshTree();
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="role-edit btn btn-xs btn-default m-r-5" title="修改" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>',
        '<a  class="role-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delPermission(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('system/permission/delete', {"permIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deletePerms() {
    var ids = $("#permissionTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的菜单或按钮！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中菜单或按钮？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('system/permission/delete', {"permIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

