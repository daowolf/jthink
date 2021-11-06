$(function () {
    initOrderTable();
});
function initOrderTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        pagination:true,
        pageSize:10,
        url: '/system/shop/order/list',
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
                field: 'orderTitle'
            },
            {
	            title: '顾客',
                field: 'buyerNickname'
            },
            {
                title: '订单号',
                field: 'ns'
            },
            {
                title: '状态',
                field: 'tradeStatus',
                formatter:tradeFormatter
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
	                    delOrder(row.id);
	                },
	                'click .order-info': function (e, value, row, index) {
	                    viewOrder(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('orderTable', setting);
}

function refresh() {
    $Jtk.refreshTable("orderTable");
}
function viewOrder(orderId){
	loadUrlToMain("/system/shop/order/viewOrder?orderId="+orderId);
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="order-info btn btn-xs btn-default m-r-5" title="订单详情" data-toggle="tooltip"><i class="mdi mdi-comment-text-outline"></i></a>',
        '<a  class="post-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
// 交易状态
function tradeFormatter(value,row,index){
	if(value == 1){
		return "交易中";
	}else if(value ==2){
		return "交易完成";
	}else if(value ==3){
		return "取消交易";
	}else if(value == 4){
		return "申请退款";
	}else if(value ==5){
		return "拒绝退款";
	}else if(value ==6){
		return "退款中";
	}else if(value == 7){
		return "退款完成";
	}else if(value == 9){
		return "交易结束";
	}
}
function delOrder(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/shop/order/delete', {"orderIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deleteOrders() {
    var ids = $("#orderTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的订单！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中订单？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/shop/order/delete', {"orderIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

