function asignarProveedor() {
    var idProveedor = document.getElementById("idProveedor").value.trim();
    var idPasteleria = document.getElementById("idPasteleria").value.trim();

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/Pastelerias/proveedor/" + idProveedor + "/" + idPasteleria, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                document.getElementById("resultado").innerText = JSON.stringify(response, null, 2);
                document.getElementById("resultado").innerText = "Se ha actualizado el proveedor en la pastelería.";
            } else {
                document.getElementById("resultado").innerText = "Error al asignar proveedor";
            }
        }
    };

    xhr.send();
}

function obtenerProveedor() {
    var idPasteleriaObtener = document.getElementById("idPasteleriaObtener").value.trim();

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/Pastelerias/proveedor/" + idPasteleriaObtener, true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                document.getElementById("resultado").innerText = JSON.stringify(response, null, 2);
            } else {
                document.getElementById("resultado").innerText = "Error al obtener proveedor";
            }
        }
    };

    xhr.send();
}