$(document).ready(function() {
     
    $('.input-stat').change(function() {
        var statName = this.id;
        var statNumber = $('#'+statName).val();
        $('#'+statName+'-number').text(statNumber);
        tryToUpdateBar(statName, statNumber);
    });

});

function updateBar(statName, bar) {
    $('#'+statName+'-bar').text("");
    $('#'+statName+'-bar').append(bar);
}

function tryToUpdateBar(statName, statNumber) {
    $.ajax({
       type: "GET",
       url: "HexColorGenerator?statNumber="+statNumber,
       success: function(response) {
           updateBar(statName, response);
       }
    });    
}
