$(document).ready(function () {
    var navUl = $('#navUl');
    $('#refresh').click(function () {
        refreshMenu();
    });
    $('#add').click(function () {

    });

    function refreshMenu() {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseUrl + "/au/menu/getAll",
            success: function (msg) {
                var html = formatMenu(msg);
                navUl.empty();
                navUl.html(html);
                $('.ttt').click(function () {
                    alert(1)
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    // title: '操作出错!',
                    content: '操作出错！',
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
    }


    function formatMenu(sysMenuMaps) {
        var menuHtml = "";
        var i = 1;
        for (var sysMenuMap in sysMenuMaps) {
            var menu = sysMenuMaps[sysMenuMap];
            var item = "";
            if (!menu.parent) {
                item = "<li class=\"nav-item\">" +
                    "    <a href=\"#\" class=\"nav-link\">" +
                    "        <i class=\"nav-icon fa " + menu.sysMenu.cssIcon + " \"></i>" +
                    "        <p>" + menu.sysMenu.manuName +
                    "            <div class=\"float-right\">" +
                    "               <i data=type='up' class=\"fa fa-arrow-up ttt\"></i>" +
                    "               <i data=type='down' class=\"fa fa-arrow-down ttt\"></i>" +
                    "               <i data=type='edit' class=\"fa fa-pencil-square-o ttt\"></i>" +
                    "               <i data=type='remove' class=\"fa fa-times ttt\"></i>" +
                    "            </div>" +
                    "        </p>" +
                    "    </a>" +
                    "</li>";
            } else {
                var itemChild = formatMenu(menu.sysMenuMaps);
                item = "<li class=\"nav-item has-treeview\">" +
                    "    <a href=\"#\" class=\"nav-link\">" +
                    "        <i class=\"nav-icon fa " + menu.sysMenu.cssIcon + "\"></i>" +
                    "        <p>" +
                    "            <span>" + menu.sysMenu.manuName + "</span>" +
                    "            <div class=\"float-right\">" +
                    "               <i data=type='up' class=\"fa fa-arrow-up ttt\"></i>" +
                    "               <i data=type='down' class=\"fa fa-arrow-down ttt\"></i>" +
                    "               <i data=type='edit' class=\"fa fa-pencil-square-o ttt\"></i>" +
                    "               <i data=type='remove' class=\"fa fa-times ttt\"></i>" +
                    "               <i class=\"right fa fa-angle-left\"></i>" +
                    "            </div>" +
                    "        </p>" +
                    "    </a>" +
                    "    <ul class=\"nav nav-treeview\">" +
                    itemChild+
                    "    </ul>" +
                    "</li>";

            }
            menuHtml += item;
            i ++;
        }
        return menuHtml;
    }

    refreshMenu();

});