$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar-user',
	    sidePagination: 'server',
	    showColumns: true,
        pagination:true,
        pageSize:10,
        url: '/system/member/list',
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
                title: '会员名',
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
            }
        ]

    };

    $Jtk.initTable('userTable', settings);
});


function searchSel() {
    $Jtk.refreshTable('userTable');
}

function sendCoupon(){
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $Jtk.n_warning('请勾选需要发放的的用户！');
        return;
    }
    $Jtk.confirm({
        text: "确定给这些用户发优惠券？",
        confirmButtonText: "确定加发放"
    }, function () {
        var ids = "";
        for (var i = 0; i < selected_length; i++) {
            ids += selected[i].id;
            if (i !== (selected_length - 1)) ids += ",";
        }
        // couponId 为全局变量，已经在父页面coupunUser.html中声明
        $.post('/system/shop/coupon/couponUser/send', {"couponId":couponId,"ids": ids}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                $Jtk.refreshTable("couponUserTable");
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}