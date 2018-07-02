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
                    operation($(this));
                });
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
                item = "<li class=\"nav-item has-treeview\">" +
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

    var stopMoveConfirm = $.confirm({
        content: '已经无法移动了喔！',
        icon: 'fa fa-hand-paper-o',
        theme: 'modern',
        closeIcon: true,
        animation: 'scale',
        type: 'green',
        lazyOpen: true,
        buttons: {
            ok: {
                text: "确定",
                btnClass: "btn btn-success",
                keys: ['enter', 'esc']
            }
        }
    });

    function operation(obj) {
        var operatObj = $(obj);
        var type = operatObj.attr('data-type');
        var url = "";
        var id = "";
        var oId = "";
        var parentsObj = $(obj).parents("li.nav-item");
        var prevObj = parentsObj.prev();
        var nextObj = parentsObj.next();


        if (type === 'up') {
            if(prevObj.length <= 0){
                stopMoveConfirm.open();
                return;
            }
        } else if (type === 'down') {
            if(nextObj.length <= 0){
                stopMoveConfirm.open();
                return;
            }
        } else if (type === 'edit') {

        } else if (type === 'remove') {

        } else {
            return;
        }
    }

    refreshMenu();

});