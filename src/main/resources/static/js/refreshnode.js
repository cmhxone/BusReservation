$(document).ready(
    setInterval(
        function () {
            $.ajax({
                url: $(location).attr('href') + '/refresh',
                data: 'info',
                method: 'GET'})
                .done(function (fragment) {
                    $('#arrRoutes').replaceWith(fragment)
                })
        }, 60000)
)