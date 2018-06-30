
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
                formatMenu(navUl, msg);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    // title: '操作出错!',
                    content: '操作出错！',
                    icon: 'fa fa-frown-o',
                    theme: 'modern',
                    closeIcon: true,
                    animation: 'scale',
                    type: 'red'
                });
            }
        });
    }


    function formatMenu(obj, sysMenuMaps) {
        for (var i = 0; i < sysMenuMaps.length; i++){

        }

    }
});