$(document).ready(function() {
    $("form").submit( function () {
        var obj = $("#password");
        var val = obj.val();
        var hash = md5(val);
        obj.val(hash);
    } );
} );
