/**
 * Created by xiaoming on 21/11/2016.
 */
$(function(){
    var urlResource = {
        query: function(param){
            $.get("/api/query", param, function (data) {
                showTable(data);
            });
        },
        showApiAttrModal: function(apiId){
            $.get("/api/attribute/query?apiId=" + apiId, function (data) {
                $("#apiAttrModal").modal('show');
                showApiAttrTable(data);
            });
        },
        insert : function (param) {
            $.ajax({
                url: '/api/create',
                method: 'post',
                contentType: 'application/json', // 这句不加出现415错误:Unsupported Media Type
                data: JSON.stringify(param), // 以json字符串方式传递
                success: function(data) {
                    if(data.resultCode =="00"){
                        $("#createModal").modal('hide');
                    }else {
                        alert(data.resultMsg);
                    }
                },
                error: function(data) {
                    console.log("error...");
                }
            });
        }
    };

    function showTable(data) {
        $('#table').DataTable( {
            "ordering": true,
            "data": data,
            "columns": [
                { "data": "serviceName", "title": "外部服务名称" },
                { "data": "interfaceName", "title": "接口全类名" },
                { "data": "methodName", "title": "方法名" },
                { "data": "description", "title": "API描述" },
                { "data": "gmtCreated", "title": "创建时间" ,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("123");
                    }
                },
                { "data": "id", "title": "操作" ,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<a class='modify' data-apiId='" + sData + "'><b><i>编辑API入参</i></b>" + "</a>");
                    }
                }
            ]
        } );
        $('#table').attr("style", "");
    };

    function showApiAttrTable(data) {
        $('#apiAttrTable').DataTable( {
            "data": data,
            "paging": false,
            "info": false,
            "searching": false,
            "columns": [
                { "data": "isDTO", "title": "是否DTO" },
                { "data": "dtoClassName", "title": "DTO类名" },
                { "data": "argumentType", "title": "参数类型" },
                { "data": "argumentType", "title": "参数描述" },
                { "data": "order", "title": "参数序号" }
            ]
        } );
        $('#table').attr("style", "");
    };

    $("#create").click(function () {
        $("#createModal").modal('show');
    });

    $("#save").click(function () {
        var param = {};
        param['serviceName'] = $('#serviceName').val();
        param['interfaceName'] = $('#interfaceName').val();
        param['methodName'] = $('#methodName').val();
        param['description'] = $('#description').val();
        urlResource.insert(param);
    });

    $('#table').on('click','.modify',function(){
        var apiId = $(this).attr("data-apiId");
        urlResource.showApiAttrModal(apiId);
    });

    $("input[type='radio']").change(function(){
        var isDTO = $("input[type='radio']:checked").val();
        if("Yes" == isDTO){
            $('.isDTO').show();
            $('.notDTO').hide();
            $('.notDTO').val("");
        }else{
            $('.notDTO').show();
            $('.isDTO').hide();
            $('.isDTO').val("");
        }
    });

    function initTable() {
        var param = $("#queryForm").serialize();
        urlResource.query(param);
    };

    initTable();
});
