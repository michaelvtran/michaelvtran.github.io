$(document).ready(function() {
    initializePage();
    $('.input-stat').change(updateIndividualStat);
    $('#getPokemon').change(getPokemonStats);
    $(document).on("click", ".saved-pokemon", retrieveStats);
});

function initializePage() {
    reset();
    // load recent/saved?
}

function updateIndividualStat() {
    var statName = this.id.substring(6);
    var statNumber = $('#input-'+statName).val();
    verifyStatAndUpdate(statName, statNumber); 
}

// limits stats to [1,255]
function verifyStatAndUpdate(statName, statNumber) {
    if (statNumber < 1 || statNumber > 255) {
        statNumber = statNumber < 1 ? 1 : 255;
        $('#input-' + statName).val(statNumber);
    }
    $('#'+statName+'-number').text(statNumber);
    updateBST();
    updateBar(statName, statNumber);
}

function getPokemonStats() {
    var pokemon = $('#getPokemon').val();
    $('#pokeapiError').text("");
    if (pokemon.localeCompare("") !== 0)
        showStatsOf(pokemon);
}

function showStatsOf(pokemon) {
    $('#pokeapiMessage').text("Getting data...");
    $('#input-name').val(pokemon);
    $.getJSON("http://pokeapi.co/api/v2/pokemon/"+pokemon.toLowerCase())
        .done(function(json) {
            var hp  = json.stats[5].base_stat, 
                att = json.stats[4].base_stat,
                def = json.stats[3].base_stat,
                sat = json.stats[2].base_stat,
                sdf = json.stats[1].base_stat,
                spe = json.stats[0].base_stat;
                
            updatePage(hp, att, def, sat, sdf, spe);
            storePokemonStats("recent", pokemon, hp, att, def, sat, sdf, spe);
    })
        .fail(function() {
            $('#pokeapiError').text('Results for "'+pokemon+'" not found.');
    })
        .always(function () {
       $('#pokeapiMessage').text("");     
    });
    ;
}

function reset() {
    updatePage(50, 50, 50, 50, 50, 50);
    $('#input-name').val("");
}

function updatePage(hp, att, def, sat, sdf, spe) {
    updateInputFields(hp, att, def, sat, sdf, spe);
    updateStatNumbers(hp, att, def, sat, sdf, spe);
    updateStatBars(hp, att, def, sat, sdf, spe);
    updateBST();    
}

function updateInputFields(hp, att, def, sat, sdf, spe) {
    $('#input-hp').val(hp);
    $('#input-att').val(att);
    $('#input-def').val(def);
    $('#input-sat').val(sat);
    $('#input-sdf').val(sdf);
    $('#input-spe').val(spe);    
}

function updateStatNumbers(hp, att, def, sat, sdf, spe) {
    $('#hp-number').text(hp);
    $('#att-number').text(att);
    $('#def-number').text(def);
    $('#sat-number').text(sat);
    $('#sdf-number').text(sdf);
    $('#spe-number').text(spe);    
}

function updateStatBars(hp, att, def, sat, sdf, spe) {
    updateBar("hp", hp);
    updateBar("att", att);
    updateBar("def", def);
    updateBar("sat", sat);
    updateBar("sdf", sdf);
    updateBar("spe", spe);
}

function updateBST() {
    var bst = parseInt($('#hp-number').text()) +
            parseInt($('#att-number').text()) +
            parseInt($('#def-number').text()) +
            parseInt($('#sat-number').text()) +
            parseInt($('#sdf-number').text()) +
            parseInt($('#spe-number').text());
    $('#bst').text(bst);
}

function updateBar(statName, statNumber) {
    $.ajax({
       type: "GET",
       url: "StatBarGenerator?statNumber="+statNumber,
       success: function(response) {
            $('#'+statName+'-bar').text("");
            $('#'+statName+'-bar').append(response);
       }
    });    
}

function save() {
    var name = $('#input-name').val();
        hp   = $('#hp-number').text(),
        att  = $('#att-number').text(),
        def  = $('#def-number').text(),
        sat  = $('#sat-number').text(),
        sdf  = $('#sdf-number').text(),
        spe  = $('#spe-number').text();
    storePokemonStats("saved", name, hp, att, def, sat, sdf, spe);
}

// TODO: depending on how sessions work, maybe pass another data parameter to distinguish official pokemon from user created ones
function storePokemonStats(whichList, name, hp, att, def, sat, sdf, spe) {
    $.ajax({
       type: "POST",
       url: "SavePokemon",
       data: {
           name: name,
           hp: hp,
           att: att,
           def: def,
           sat: sat,
           sdf: sdf,
           spe: spe
       },
       success: function(response) {
           // get true/false from response. probably add it in SavePokemon.java
            if (whichList === "saved")
                addToSaved(response, true);
            else if (whichList === "recent")
                addToRecents(name);
       }
    });   
}

// TODO: check for "None"
function addToSaved(pokemon, alreadyExists) {
    // if pokemon already exists, replace it
    if (alreadyExists)
        
    $('#saved').append(pokemon);
}

// TODO: check for "None"
function addToRecents(pokemon) {
    pokemon = properName(pokemon);
    $('#recent').append(pokemon+"<br>");
}

function retrieveStats() {
    $.ajax({
       type: "GET",
       url: "RetrievePokemonStats?name="+this.id,
       success: function(response) {
           var arr = response.split(",");
           var hp  = arr[1].substr(1), 
               att = arr[2].substr(1),
               def = arr[3].substr(1),
               sat = arr[4].substr(1),
               sdf = arr[5].substr(1),
               spe = $.trim(arr[6]).substr(0,$.trim(arr[6]).length-1);
           updatePage(hp, att, def, sat, sdf, spe);
           $('#input-name').val(arr[0]);
       }
    });
}

function properName(pokemon) {
    return pokemon.substr(0,1).toUpperCase() + pokemon.substr(1);
}
