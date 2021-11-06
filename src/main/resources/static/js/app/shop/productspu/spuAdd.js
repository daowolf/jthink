$(function () {
    $("#spuAddBtn").click(function () {
    var ids = $("#selSpuTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要使用的规格！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定使用选中规格？",
        confirmButtonText: "确定"
    }, function () {
        $.post('/system/shop/productspu/savespu', {"productId":productId,"ids_str": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
    });

    $("#addSpuModal .btn-close").click(function () {
        closeModal();
    });
})

function closeModal() {
    $Jtk.closeModal("addSpuModal");
}