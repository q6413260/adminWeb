/**
 * Created by xiaoming on 17/11/2016.
 */
$(function(){
    var urlResource = {
        query : function (param) {
            $.get("/developers", param, function (data) {
                showTable(data);
            });
        },
        queryApi: function(developerId){
            var url = "/developers/" + developerId + "/apis"
            $.get(url, function (data) {
                $("#apiModal").modal('show');
                showApiTable(data);
            });
        },
        insert : function (param) {
            $.ajax({
                url: '/developers',
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
        },
        insertApi : function (param) {
            $.ajax({
                url: "/developers/" +param[developerId] + "/apis",
                method: 'post',
                contentType: 'application/json', // 这句不加出现415错误:Unsupported Media Type
                data: JSON.stringify(param), // 以json字符串方式传递
                success: function(data) {
                    var developerId = $("#developerId").val();
                    urlResource.queryApi(developerId);
                },
                error: function(data) {
                    alert(data);
                }
            });
        }
    };
    $("#reset").click(function () {
        $("#queryForm input[type='text']").val("");
        initTable();
    });

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

    $("#table").on('click', '.showApi', function(){
        var developerId = $(this).attr("data-id");
        $("#developerId").val(developerId);
        urlResource.queryApi(developerId);
    });

    $("#addApi").click(function(){
        var param = {};
        param['apiId'] = $("#apiId").val();
        param['developerId'] = $("#developerId").val();
        urlResource.insertApi(param);
    });

    function showTable(data) {
        $('#table').DataTable( {
            "destroy": true,
            "ordering": true,
            "data": data,
            "columns": [
                { "data": "name", "title": "开发者",
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<a class='showApi' data-id='" + oData.id + "'><b><i>"+ sData + "</i></b></a>");
                    }
                },
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

    function showApiTable(data) {
        if(data.length == 0){
            return;
        }
        $('#apiTable').DataTable( {
            "destroy": true,
            "paging": false,
            "ordering": false,
            "info": false,
            "searching": false,
            "data": data,
            "columns": [
                { "data": "apiServiceName", "title": "API名称", "width": "200px" },
                { "data": "apiDescription", "title": "API描述", "width": "200px" },
                { "data": "id", "title": "操作" ,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("123");
                    }
                },
                { "data": "gmtCreated", "title": "授权时间", "width": "200px" }
            ]
        } );
        $('#table').attr("style", "");
    };

    function initTable() {
        var param = $("#queryForm").serialize();
        urlResource.query(param);
    };

    $(".select2_single").select2({
        ajax: {
            url: "/apis",
            dataType: 'json',
            delay: 250,
            data: function (param) {
                return {
                    serviceName: param['term']
                };
            },
            processResults: function (apis) {
                return {
                    results: $.map(apis, function (item) {
                        return {
                            text: item.serviceName + "(" + item.description + ")",
                            apiId: item.id,
                            id: item.id
                        }
                    })
                }
            },
            cache: true
        }
    });

    $(".select2_single").on("select2:select", function (event) {
        var apiId = event.params.data.apiId;
        $("#apiId").val(apiId);
    });


    initTable();
});
