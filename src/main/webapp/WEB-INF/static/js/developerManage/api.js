/**
 * Created by xiaoming on 21/11/2016.
 */
$(function(){
    var urlResources = {
        query: function(param){
            $.get("")
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
