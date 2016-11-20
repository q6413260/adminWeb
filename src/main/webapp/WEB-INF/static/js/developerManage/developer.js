/**
 * Created by xiaoming on 17/11/2016.
 */
$(function(){
    var urlResource = {
        query : function (param) {
            $.get("/developer/query", param, function (data) {
                if(!data){
                    location.href = "/login.vm";
                }
                showTable(data);
            });
        },
        insert : function (param) {
            $.ajax({
                url: '/developer/create',
                method: 'post',
                contentType: 'application/json', // 这句不加出现415错误:Unsupported Media Type
                data: JSON.stringify(param), // 以json字符串方式传递
                success: function(data) {
                    $("#createModal").modal('hide');
                },
                error: function(data) {
                    console.log("error...");
                }
            });
        }
    };
    $("#create").click(function () {
        $("#createModal").modal('show');
    });

    $("#save").click(function () {
        var param = {};
        param['name'] = $('#name').val();
        param['appKey'] = $('#appKey').val();
        param['publicKey'] = $('#publicKey').val();
        urlResource.insert(param);
    });

    $("#query").click(function(){
        initTable();
    });

    function showTable(data) {
        //$('#table').remove();
        $('#table').DataTable( {
            "ordering": true,
            "data": data,
            "columns": [
                { "data": "name", "title": "开发者" },
                { "data": "appKey", "title": "appKey" },
                { "data": "publicKey", "title": "开发者公钥" },
                { "data": "gmtCreated", "title": "创建时间" ,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("123");
                    }
                }
            ]
        } );
        $('#table').attr("style", "");
    };

    function initTable() {
        var param = $("#queryForm").serialize();
        urlResource.query(param);
    };

    initTable();
});
