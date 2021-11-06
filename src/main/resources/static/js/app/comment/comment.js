$(function () {
    initCommentTable();
});
function initCommentTable() {
    var setting = {
	    idField: 'id',
        uniqueId: 'id',
        toolbar:'#toolbar',
	    sidePagination: 'server',
	    showColumns: true,
        pagination:true,
        pageSize:10,
        url: '/system/cms/comments/list?postId='+postId,
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
                title: '评论人',
                field: 'commentAuthor'
            },
            {
                title: 'IP地址',
                field: 'commentAuthorIp'
            },
            {
                title: '评论内容',
                field: 'commentContent'
            },
            {
                title: '审核状态',
                field: 'commentApproved',
                formatter: approveFormatter
            },
            {
                title: '创建时间',
                field: 'createTime'
            },
            {
                title: '审核时间',
                field: 'updateTime'
            },
	        {
	            field: 'operate',
	            title: '操作',
	            align: 'center',
	            events : {
	                'click .comment-delete': function (e, value, row, index) {
	                    delComment(row.id);
	                },
	                'click .comment-edit': function (e, value, row, index) {
	                    updateComment(row.id);
	                }
	            },
	            formatter: operateFormatter
	        }
        ]

    };

    $Jtk.initTable('commentTable', setting);
}

function refresh() {
    $Jtk.refreshTable("commentTable");
}
// 操作按钮
function operateFormatter(value, row, index) {
    return [
        '<a  class="comment-edit btn btn-xs btn-default m-r-5" title="修改" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>',
        '<a  class="comment-delete btn btn-xs btn-default" title="删除" data-toggle="tooltip"><i class="mdi mdi-delete"></i></a>'
    ].join('');
}
function approveFormatter(value, row, index){
	if(value === "approved"){
		return "<span class='text-success'>审核通过</span>";
	}else if(value === "auditing"){
		return "<span class='text-warning'>审核中</span>";
	}else if(value === "refuesd"){
		return "<span class='text-danger'>已拒绝</span>";
	}else{
		return value;
	}
}
function delComment(id){
	$Jtk.confirm({
		text:" 确定要删除?",
		confirmButtonText:"确定"
	},function(){
        $.post('/system/cms/comments/delete', {"commentIds": id}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
	})
}
function deleteComments() {
    var ids = $("#commentsTable").bootstrapTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $Jtk.n_warning("请勾选需要删除的评论！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $Jtk.confirm({
        text: "确定删除选中评论？",
        confirmButtonText: "确定删除"
    }, function () {
        $.get('/system/cms/comments/delete', {"commentIds": ids_arr}, function (r) {
            if (r.code === 200) {
                $Jtk.n_success(r.msg);
                refresh();
            } else {
                $Jtk.n_danger(r.msg);
            }
        });
    });
}

