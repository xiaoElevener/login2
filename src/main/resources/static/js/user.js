$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_user').bootstrapTable({
            url: '/admin/users',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            // pagination: true,                   //是否显示分页（*）
            // sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "userId",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: true,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'userId',
                title: '用户id'
            },{
                field: 'userName',
                title: '用户名'
            }, {
                field: 'userEmail',
                title: '邮箱'
            },{
                field: 'userPassword',
                title: '用户密码'
            }, {
                field: 'createTime',
                title: '创建时间',
                formatter: function (value) {
                    return getMyDate(value);
                }
            }, {
                field: 'userStatus',
                title: '用户状态'
            }, ],

            onExpandRow: function(index, row, $detail) {

                oTableInit.InitSubTable(index, row, $detail);

            }
        });
    };


    oTableInit.InitSubTable = function(index, row, $detail) {

        var tb_role = $detail.html('<table id="tb_role"></table>').find('table');
        var userId=row.userId;
        $(tb_role).bootstrapTable({
            url:'/admin/roles/'+userId,
            method:'get',
            clickToSelect:true,
            detailView:false,//父子表
            uniqueId: "roleId",
            pageSize: 10,
            pageList: [10, 25],
            columns: [{
                field:'roleId',
                title:'角色id'
            }, {
                field:'roleName',
                title:'角色名'
            },{
                field: 'operate',
                title: '操作',
                align: 'center',
                width : 100,
                formatter: function (value, row, index) {
                    var roleId=row.roleId;
                    var e = '<button id="deleteRole" type="button" class="btn btn-default" onclick="removeRole('+userId+','+roleId+')">' +
                        '<span class="glyphicon glyphicon-remove" aria-hidden="true">删除</span>' +
                        '</button> ';
                    return e;
                }//初始化按钮
            },
            ],
        });

    };
    return oTableInit;
};

function removeRole(userId,roleId) {
    $.ajax({
        type : 'delete',
        url : '/admin/user_role/'+userId+'/'+roleId,
        async : true,
        success : function() {
            $('#tb_role').bootstrapTable('refresh');
        }
    });
}

function addRole() {
    var userId=$('#addRole_userId').val();
    var roleId=$('#addRole_roleId').val();
    $.ajax({
        type : 'post',
        url : '/admin/user_role/'+userId+'/'+roleId,
        async : true,
        success : function() {
            $('#tb_role').bootstrapTable('refresh');
        }
    });
}



var ButtonInit = function () {
    var oInit = new Object();

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};

//将毫秒数转换为具体日期
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};

//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}



