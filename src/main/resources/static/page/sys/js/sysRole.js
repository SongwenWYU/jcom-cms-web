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

    var menuDiv = $('#menu');
    var model = $('#menuModel').modal({
        keyboard: false,
        show: false,
        backdrop: 'static',
        boolean: false

    });
    $("#roleTable").on('click', '.role-operate',function() {
        // 添加点击事件
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseUrl + "/au/menu/getAll",
            success: function (msg) {
                var html = formatMenu(msg);
                menuDiv.empty();
                menuDiv.html(html);
                model.modal('show');
                model.modal('handleUpdate');
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    content: '刷新出错！',
                    icon: 'fa fa-frown-o',
                    theme: 'modern',
                    closeIcon: true,
                    animation: 'scale',
                    type: 'red',
                    buttons: {
                        ok: {
                            text: "确定",
                            btnClass: "btn btn-danger",
                            keys: ['enter']
                        }
                    }
                });
            }
        });

    });
    $("#query").click(function () {
        table.ajax.reload(null, false);
    });

    function formatMenu(sysMenuMaps) {
        var menuHtml = "";
        var i = 1;
        for (var sysMenuMap in sysMenuMaps) {
            var menu = sysMenuMaps[sysMenuMap];
            var item = "";
            if (!menu.parent) {
                item = "<li class=\"nav-item\" data-id='" + menu.sysMenu.id + "'>" +
                    "    <a href=\"#\" class=\"nav-link\">" +
                    "        <i class=\"nav-icon fa " + menu.sysMenu.cssIcon + " \"></i>" +
                    "        <p>" + menu.sysMenu.manuName +
                    "            <div class=\"float-right\">" +
                    "               <i data-type='up' class=\"fa fa-arrow-up ttt\"></i>" +
                    "               <i data-type='down' class=\"fa fa-arrow-down ttt\"></i>" +
                    "               <i data-type='edit' class=\"fa fa-pencil-square-o ttt\"></i>" +
                    "               <i data-type='remove' class=\"fa fa-times ttt\"></i>" +
                    "            </div>" +
                    "        </p>" +
                    "    </a>" +
                    "</li>";
            } else {
                var itemChild = formatMenu(menu.sysMenuMaps);
                item = "<li class=\"nav-item has-treeview\" data-id='" + menu.sysMenu.id + "'>" +
                    "    <a href=\"#\" class=\"nav-link\">" +
                    "        <i class=\"nav-icon fa " + menu.sysMenu.cssIcon + "\"></i>" +
                    "        <p>" +
                    "            <span>" + menu.sysMenu.manuName + "</span>" +
                    "            <div class=\"float-right\">" +
                    "               <i data-type='up' class=\"fa fa-arrow-up ttt\"></i>" +
                    "               <i data-type='down' class=\"fa fa-arrow-down ttt\"></i>" +
                    "               <i data-type='edit' class=\"fa fa-pencil-square-o ttt\"></i>" +
                    "               <i data-type='remove' class=\"fa fa-times ttt\"></i>" +
                    "               <i class=\"right fa fa-angle-left\"></i>" +
                    "            </div>" +
                    "        </p>" +
                    "    </a>" +
                    "    <ul class=\"nav nav-treeview\">" +
                    itemChild +
                    "    </ul>" +
                    "</li>";

            }
            menuHtml += item;
            i++;
        }
        return menuHtml;
    }

});