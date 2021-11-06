$(function () {
    var $couponTableForm = $(".coupon-table-form");
    var settings = {
        url: "/system/shop/coupon/list",
        pageSize: 10,
        toolbar:"#toolbar",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1
            };
        },
        columns: [{
            checkbox: true
        }, {
        	title: 'ID',
            field: 'id',
            visible: true
        },{
            field: 'title',
            title: '优惠券类型名称'
        }, {
        	field:'amount',
        	title:'金额'
        }, {
            field: 'withAmount',
            title: '起用门槛'
        }, {
        	field:'quota',
        	title:'配发数量'
        },{
        	field:'takeCount',
        	title:'已领取数量'
        },{
        	field:'usedCount',
        	title:'已使用数量'
        },{
            field: 'createTime',
            title: '创建时间'
        }, 
        {
            title: '类型',
            field: 'type',
            formatter: function (value, row, index) {
                if (value === 1) return '<font class="text-success">满减券 </font>';
                if (value === 2) return '<font class="text-success">叠加满减券</font>';
                if (value === 3) return '<font class="text-success">无门槛券</font>';
            }
        },
        {
            field: 'productsType',
            title: '限制类型',
            formatter: function (value, row, index) {
                if (value === 0) return '<font class="text-success">全品类</font>';
                if (value === 1) return '<font class="text-danger">分类限制</font>';
                if (value === 2) return '<font class="text-danger">商品限制</font>';
            }
        },
        {
            field: 'couStatus',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === 1) return '<font class="text-success">正常</font>';
                if (value === 0) return '<font class="text-danger">锁定</font>';
            }
        }, {
            field: 'id',
            title: '操作',
            formatter: function (value, row, index) {
            	return '<div class="btn-group"><a class="btn btn-xs btn-default" onclick="viewUsers('+value+')" title="领取详情">领取详情</a></div>';
            }
        }

        ]
    };

    $Jtk.initTable('couponTable', settings);
});

function search() {
    $Jtk.refreshTable('couponTable');
}

function refresh() {
    $(".coupon-table-form")[0].reset();
    $Jtk.refreshTable('couponTable');
}
function viewUsers(couponId){
	loadUrlToMain("/system/shop/coupon/couponUser?couponId="+couponId);
}
function deleteTopics() {
    var selected = $("#couponTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $Jtk.n_warning('请勾选需要删除的优惠券类型！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $Jtk.confirm({
        text: "确定删除选中优惠券类型？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post('/system/shop/coupon/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}
    // 时间日期选择器
    jQuery("[data-provide = 'datetimepicker']").each(function() {
        var options = {
            locale: moment.locale(),
        }
  
        options = $.extend( options, getDataOptions( $(this) ));
  
        if ( $(this).prop("tagName") != 'INPUT' ) {
            options.inputs = [$(this).find('input:first'), $(this).find('input:last')];
        }

        $(this).datetimepicker(options);
    });