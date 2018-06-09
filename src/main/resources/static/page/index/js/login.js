(function ($) {
    $("form").submit( function () {
        var obj = $("#password");
        var val = obj.val();
        var hash = md5("value")
        obj.val(hash);
    } );
})(jQuery)