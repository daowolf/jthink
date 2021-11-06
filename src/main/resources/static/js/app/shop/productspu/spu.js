$(function () {
    initSpuTable();
 initSelSpuTable();
});
function initSpuTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        url: '/system/shop/productspu/list?productId='+productId,
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
                field: 'spu'
            },
            {
                title: '描述',
                field: 'value'
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
	                'click .spu-delete': function (e, value, row, index) {
	                    delSpu(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('spuTable', setting);
}
function initSelSpuTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#seltoolbar',
	    sidePagination: 'server',
	    showColumns: true,
        url: '/system/shop/spu/list',
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
                field: 'spu'
            },
            {
                title: '描述',
                field: 'value'
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
	                'click .spu-delete': function (e, value, row, index) {
	                    delSpu(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('selSpuTable', setting);
}

function refresh() {
    $Jtk.refreshTable("spuTable");
    $Jtk.refreshTable("selSpuTable");
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="spu-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delSpu(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/shop/productspu/delete', {"idStr": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deleteSpus() {
    var ids = $("#spuTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的规格！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中规格？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/shop/productspu/delete', {"idStr": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

