
function closeModal(id) {
	$Jtk.closeAndRestModal(id);
}
function closeDeliveryModal() {
	$("#saveDelivery").attr("name", "save");
	$Jtk.closeAndRestModal("deliveryModal");
}
function addOrderRemark() {
	var remarks = $("#orderRemarks").val();
	var orderId = $("#orderId").val();
	var params = {
		orderRemarks : remarks,
		orderId : orderId
	};
	$.post("/system/shop/order/updateRemark", params, function(r) {
		if (r.code === 200) {
			closeModal("orderRemarkModal");
			$Jtk.n_success(r.msg);
			$("#orderRemarkTxt").text(remarks);
		} else
			$Jtk.n_danger(r.msg);
	});
}
$("#deliverTable").delegate(".edit-deliver","click",function(evt){
	var alink = evt.currentTarget;
	var $td = $(alink).closest("td");
	var deliveryId = $td.find("input[name='deliveryId']").val();
    $.post("/system/shop/order/editDelivery", {"deliveryId": deliveryId}, function (r) {
        if (r.code === 200) {
        	var $form = $("#deliveryModal");
        	$form.modal();
            var orderDelivery = r.msg;
            $("#delivery-add-modal-title").html('修改配送方式');
            $form.find("input[name=id]").val(orderDelivery.id);
            $form.find("select[name=deliveryId]").val(orderDelivery.deliveryId);
            $form.find("input[name=deliveryNumber]").val(orderDelivery.deliveryNumber);
            $form.find("textarea[name=remarks]").val(orderDelivery.remarks);
            $form.find("select[name=deliveryStatus]").val(orderDelivery.deliveryStatus);
            $("#saveDelivery").attr("name", "update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
})

function deliverGoods(){
	var $deliverForm=$("#deliverForm");
	var orderId = $("#orderId").val();
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $deliverForm.validate({
        rules: {
        	deliveryNumber: {
            	required: true
            }
        },
        messages: {
        	deliveryNumber: icon + "请输入运单号",
        }
    });
    var name=$("#saveDelivery").attr("name");
    var orderDelivers = {};
    var $company=$deliverForm.find("select[name=deliveryId]");
    var deliveryNumber=$deliverForm.find("input[name=deliveryNumber]").val();
    var remarks=$deliverForm.find("textarea[name=remarks]").val();
    var deliveryId=$company.val();
    var companyName = $company.find("option:selected").text();
    var deliveryStatus=$deliverForm.find("select[name=deliveryStatus]").val();
    orderDelivers["orderId"]=orderId;
    orderDelivers["company"]=companyName;
    orderDelivers["addrUsername"]=deliveryAddrUsername;
    orderDelivers["addrMobile"]=deliveryAddrMobile;
    orderDelivers["deliveryId"]=deliveryId;
    orderDelivers["deliveryStatus"]=deliveryStatus;
    orderDelivers["remarks"]=remarks;
    orderDelivers["deliveryNumber"]=deliveryNumber;
    orderDelivers["addrProvince"]=deliveryAddrProvince;
    orderDelivers["addrCity"]=deliveryAddrProvince;
    orderDelivers["addrDistrict"]=deliveryAddrDistrict;
    orderDelivers["addrDetail"]=deliveryAddrDetail;
    orderDelivers["addrZipcode"]=deliveryAddrZipcode;
    if(name=="save"){
    	$.post("/system/shop/order/updateDeliver", orderDelivers, function(r) {
    		if (r.code === 200) {
    			$Jtk.n_success(r.msg);
    			loadUrlToMain("/system/shop/order/viewOrder?orderId="+orderId);
    		} else
    			$Jtk.n_danger(r.msg);
    	});
    }else{
    	orderDelivers["id"]=$deliverForm.find("input[name=id]").val();
    	$.post("/system/shop/order/updateDeliveryById", orderDelivers, function(r) {
    		if (r.code === 200) {
    			$Jtk.n_success(r.msg);
    			loadUrlToMain("/system/shop/order/viewOrder?orderId="+orderId);
    		} else
    			$Jtk.n_danger(r.msg);
    	});
    }

	
}
$("#deliverTable").delegate(".delete-deliver","click",function(evt){
	var item = evt.currentTarget;
	var $td = $(item).closest("td");
	var $tr = $td.parent();
	var deliverId=$td.find("input[name=deliveryId]").val();
	var params={};
	params["deliverId"]=deliverId
    $Jtk.confirm({
        text: "确定删除？",
        confirmButtonText: "确定删除"
    }, function () {
    	$.post("/system/shop/order/deleteDeliver",params, function(r) {
    		if (r.code === 200) {
    			closeModal("orderRemarkModal");
    			$Jtk.n_success(r.msg);
    			$tr.remove();
    		} else
    			$Jtk.n_danger(r.msg);
    	});
    });

})
// 提交备注
$("#submitRemarks").click(function() {
	addOrderRemark();
});
// 发货
$("#saveDelivery").click(function(){
	deliverGoods();
});
// 更新订单状态
function updateOrderTradeStatus(){
	var $tradeStatusForm=$("#tradeStatusForm");
	var orderId = $("#orderId").val();
	var tradeStatus = $tradeStatusForm.find("select[name=tradeStatusStatus]").val();
	var orderSummary = $tradeStatusForm.find("textarea[name=orderSummary]").val();
	var order={id:orderId,tradeStatus:tradeStatus,orderSummary:orderSummary};
	//closeModal("tradeStatusModal");
	$.post("/system/shop/order/updateOrderTradeStatus",order, function(r) {
		if (r.code === 200) {
			$Jtk.n_success(r.msg);
            loadUrlToMain("/system/shop/order/viewOrder?orderId="+orderId);
		} else
			$Jtk.n_danger(r.msg);
	});
	
}
$("#savetradeStatus").click(function(){
	var selectText = $("#tradeStatusStatus").find("option:selected").text();
    $Jtk.confirm({
        text: "确定更新订单状态为:"+selectText+"？",
        confirmButtonText: "确定更新"
    }, function () {
    	updateOrderTradeStatus();
    });
	
});