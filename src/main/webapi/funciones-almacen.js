function obtenerPasteleriasPaginado(pagina, tam_pagina) {
    var xhrPasteles = new XMLHttpRequest();
    xhrPasteles.open("GET", "http://localhost:8080/Pastelerias/paginado/" + pagina + "/" + tam_pagina, true);

    xhrPasteles.onreadystatechange = function() {
      if (xhrPasteles.readyState === 4 && xhrPasteles.status === 200) {
        var respuesta = JSON.parse(xhrPasteles.responseText);
        mostrarPastelerias(respuesta.datos);
      }
    };

    xhrPasteles.send();
  }

  function mostrarPastelerias(pasteles) {
    var pastelesContainer = document.getElementById("pasteles-container");
    pastelesContainer.innerHTML = "";

    pasteles.forEach(function(pastel) {
      var div = document.createElement("div");
      div.innerHTML = "<strong>Nombre:</strong> " + pastel.nombre + ", <strong>Precio:</strong> " + pastel.precio + " euros";
      pastelesContainer.appendChild(div);
    });
  }