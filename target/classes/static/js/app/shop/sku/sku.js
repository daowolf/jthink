$(function () {
    initSkuTable();
});
function initSkuTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        url: '/system/shop/product/sku/list?productId='+productId,
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
                title: '规格ID',
                field: 'spuvalStr'
            },
            {
                title: '价格',
                field: 'price'
            },
            {
                title: '原价',
                field: 'originPrice'
            },
            {
                title: '库存',
                field: 'stock'
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
	                'click .sku-delete': function (e, value, row, index) {
	                    delSku(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('skuTable', setting);
}

function refresh() {
    $Jtk.refreshTable("skuTable");
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="sku-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function delSku(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/shop/product/sku/delete', {"idStr": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deleteSkus() {
    var ids = $("#skuTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的库存！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中库存？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/shop/product/sku/delete', {"idStr": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

