var state = [];
state["STATE_ACCOUNTEXPIRED"] = " 账号过期";
state["STATE_LOCK"] = "账号锁定";
state["STATE_TOKENEXPIRED"] = "TOKEN过期";
state["STATE_NORMAL"] = "账号正常";

$(document).ready(function() {
    var table = $('#userTable').DataTable( {
        "language": dataTableLanguage,
        "dom":'<"top"l>t<"bottom"ip><"clear">',
        "info": true,
        "deferRender": true,
        "processing" : true,
        "serverSide": true,
        // "pageLength": 10,
        "ajax": {
            "url": baseUrl + "/au/user/getAll",
            "type":"post",
            "data" : function ( d ) {
                var obj = {};
                obj.username = $('#username').val();
                obj.nickname = $('#nickname').val();
                obj.start = d.start;
                obj.length = d.length;
                return obj;
            }
        },
        "searching": false,
        "ordering": false,
        "columns": [
            // { "data": "id", "title":"ID" },
            { "data": "username", "title": "用户名" },
            { "data": "nickname", "title": "昵称" },
            { "data": "state", "title": "账号状态", "render": function ( data, type, row ) {
                    return state[data];
                }},
            { "data": "email", "title": "邮箱" },
            { "data": "gmtCreate", "title": "创建时间" },
            { "data": "gmtModified", "title": "修改时间" },
            { "data": "username", "title": "操作", "render": function ( data, type, row ) {
                var buttonGroup = "<div class=\"btn-group\" role=\"group\" aria-label=\"操作\">\n" +
                    "  <button type=\"button\" class=\"btn btn-warning state\">锁定</button>\n" +
                    "  <button type=\"button\" class=\"btn btn-danger delete\">删除</button>\n" +
                    "</div>";
                    return buttonGroup;
                }}
        ]
    } );
    $("#query").click(function () {
        table.ajax.reload( null, false );
    });
} );