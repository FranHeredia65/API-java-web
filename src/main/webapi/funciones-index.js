function buscarPastel() {
    var pastelId = document.getElementById("pastelId").value.trim();

    var xhrBusqueda = new XMLHttpRequest();
    xhrBusqueda.open("GET", "http://localhost:8080/Pastelerias/id/" + pastelId, true);

    xhrBusqueda.onreadystatechange = function() {
        if (xhrBusqueda.readyState === 4) {
            if (xhrBusqueda.status === 200) {
                var pasteleriaEncontrada = JSON.parse(xhrBusqueda.responseText);
                mostrarPasteleriaEncontrada(pasteleriaEncontrada);
            } else {
                console.error('Ese pastel no existe, intenta con otro ID:', xhrBusqueda.status);
                mostrarError();
            }
        }
    };

    xhrBusqueda.send();
}

function mostrarPasteleriaEncontrada(pasteleria) {
    var resultadoDiv = document.getElementById("resultado");
    resultadoDiv.innerHTML = '<pre>' + JSON.stringify(pasteleria, null, 2) + '</pre>';
}

function mostrarError() {
    var resultadoDiv = document.getElementById("resultado");
    resultadoDiv.innerHTML = '<p style="color:red;">Ese pastel no existe, intenta con otro ID</p>';
}