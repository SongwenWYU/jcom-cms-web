$(document).ready(function () {
    var table = $('#roleTable').DataTable({
        "language": dataTableLanguage,
        "dom": '<"top"l>t<"bottom"ip><"clear">',
        "info": true,
        "deferRender": true,
        "processing": true,
        "serverSide": true,
        // "pageLength": 10,
        "ajax": {
            "url": baseUrl + "/au/role/getAll",
            "type": "post",
            "data": function (d) {
                var obj = {};
                obj.start = d.start;
                obj.length = d.length;
                return obj;
            }
        },
        "searching": false,
        "ordering": false,
        "columns": [
            // { "data": "id", "title":"ID" },
            {"data": "role", "title": "角色"},
            {"data": "roleName", "title": "名称"},
            {
                "data": "id", "title": "操作", "render": function (data, type, row) {
                    var buttonGroup = "<div class=\"btn-group\" role=\"group\" aria-label=\"操作\">\n";

                    buttonGroup += "  <button type=\"button\" class=\"btn btn-info btn-sm role-operate\" _type=\"menu\" " +
                        "_data='"+data+"' data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"页面授权\"><i class=\"fa fa-bars\" aria-hidden=\"true\"></i></button>\n" +
                        "</div>";
                    return buttonGroup;
                }
            }
        ]
    });

    $('#roleTable').on( 'draw.dt', function () {
        $('[data-toggle="tooltip"]').tooltip();
    } );

    $("#roleTable").on('click', '.user-operate',function() {
        // 添加点击事件
        
    });

});