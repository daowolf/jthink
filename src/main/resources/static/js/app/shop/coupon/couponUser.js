$(function () {
    var $couponUserTableForm = $(".couponUser-table-form");
    var settings = {
        url: "/system/shop/coupon/couponUser/list",
        pageSize: 10,
        toolbar:'#toolbar',
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                couponId:couponId
            };
        },
        columns: [{
            checkbox: true
        }, {
        	title: 'ID',
            field: 'id',
            visible: true
        },{
            field: 'userName',
            title: '用户名'
        },{
            field: 'usedOrderId',
            title: '订单ID'
        },{
            field: 'userPaymentId',
            title: '支付记录ID'
        }, {
            field: 'useTime',
            title: '使用时间'
        },{
            field: 'createTime',
            title: '领取时间'
        }, {
            field: 'couStatus',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === 1) return '<font class="text-success">未使用</font>';
                if (value === 2) return '<font class="text-success">已经使用</font>';
                if (value === 3) return '<font class="text-success">已过期</font>';
                if (value === 9) return '<font class="text-danger">不可用</font>';
            }
        }

        ]
    };

    $Jtk.initTable('couponUserTable', settings);
});

function search() {
    $Jtk.refreshTable('couponUserTable');
}

function refresh() {
    $(".couponUser-table-form")[0].reset();
    $Jtk.refreshTable('couponUserTable');
}

function deleteCouponUsers() {
    var selected = $("#couponUserTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $Jtk.n_warning('请勾选需要删除的优惠券！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
    	if(selected[i].couStatus === 2){
   		 $Jtk.n_warning('已使用的优惠券不能删除！');
   		return;
   	}
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中优惠券？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/shop/coupon/couponUser/delete', {"ids": ids}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}
