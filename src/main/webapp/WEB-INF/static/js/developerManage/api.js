/**
 * Created by xiaoming on 21/11/2016.
 */
$(function(){
    var urlResource = {
        query: function(param){
            $.get("/apis", param, function (data) {
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
                url: '/apis',
                method: 'post',
                contentType: 'application/json', // 这句不加出现415错误:Unsupported Media Type
                data: JSON.stringify(param), // 以json字符串方式传递
                success: function(data) {
                    if(data.resultCode =="00"){
                        $("#createModal").modal('hide');
                        initTable();
                    }else {
                        alert(data.resultMsg);
                    }
                },
                error: function(data) {
                    console.log("error...");
                }
            });
        },
        addAttr: function (param) {
            $.ajax({
                url: '/api/attribute/add',
                method: 'post',
                contentType: 'application/json', // 这句不加出现415错误:Unsupported Media Type
                data: JSON.stringify(param), // 以json字符串方式传递
                success: function(data) {
                    if(data.resultCode =="00"){
                        var apiId = $("#apiId").val();
                        urlResource.showApiAttrModal(apiId);
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
            "destroy": true,
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
                        $(nTd).html("<a class='modify' data-apiId='" + sData + "'data-serviceName='" + oData.serviceName + "'><b><i>编辑API入参</i></b></a>");
                    }
                }
            ]
        } );
        $('#table').attr("style", "");
    };

    function showApiAttrTable(data) {
        if(data.length == 0){
            return;
        }
        $('#apiAttrTable').DataTable( {
            "destroy": true,
            "data": data,
            "paging": false,
            "ordering": false,
            "info": false,
            "searching": false,
            "columns": [
                { "data": "orderNo", "title": "序号"},
                { "data": "isDTO", "title": "是否DTO"},
                { "data": "dtoClassName", "title": "DTO类名"},
                { "data": "argumentType", "title": "参数类型"},
                { "data": "argumentType", "title": "参数描述"}
            ]
        } );
        $('#table').attr("style", "");
    };

    $("#create").click(function () {
        $("#createModal").modal('show');
    });

    $("#reset").click(function () {
        $("#queryForm input[type='text']").val("");
        initTable();
    });

    $("#save").click(function () {
        var param = {};
        param['serviceName'] = $('#serviceName').val();
        param['interfaceName'] = $('#interfaceName').val();
        param['methodName'] = $('#methodName').val();
        param['description'] = $('#description').val();
        urlResource.insert(param);
    });

    $("#addAttr").click(function(){
        var param = {};
        param['apiId'] = $("#apiId").val();
        param['orderNo'] = $("input[name='orderNo']").val();
        param['isDTO'] = $("input[type='radio']:checked").val();
        param['dtoClassName'] = $("input[name='dtoClassName']").val();
        param['argumentType'] = $("input[name='argumentType']").val();
        param['argumentDesc'] = $("input[name='argumentDesc']").val();
        urlResource.addAttr(param);
    });

    $("#query").click(function(){
        initTable();
    });

    $('#table').on('click','.modify',function(){
        var apiId = $(this).attr("data-apiId");
        $("#apiId").val(apiId);
        var text = "编辑方法(" + $(this).attr("data-serviceName") + ")入参";
        $("#myApiAttrModalLabel").html(text);
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
