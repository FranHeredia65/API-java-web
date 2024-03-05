// Función para obtener todos los pasteles
  function obtenerPastelerias() {
    var xhrImagenes = new XMLHttpRequest();
    xhrImagenes.open("GET", "http://localhost:8080/Pastelerias/imagenes", true);

    xhrImagenes.onreadystatechange = function() {
      if (xhrImagenes.readyState === 4 && xhrImagenes.status === 200) {
        var imagenes = JSON.parse(xhrImagenes.responseText);
        obtenerDetallesPastelerias(imagenes);
      }
    };

    xhrImagenes.send();
  }

  // Función para obtener los detalles de los pasteles
  function obtenerDetallesPastelerias(imagenes) {
    var xhrPasteles = new XMLHttpRequest();
    xhrPasteles.open("GET", "http://localhost:8080/Pastelerias", true);

    xhrPasteles.onreadystatechange = function() {
      if (xhrPasteles.readyState === 4 && xhrPasteles.status === 200) {
        var pasteles = JSON.parse(xhrPasteles.responseText);
        mostrarPastelerias(pasteles, imagenes);
      }
    };

    xhrPasteles.send();
  }

  // Función para mostrar los pasteles en la página
  function mostrarPastelerias(pasteles, imagenes) {
    var pasteleriasList = document.getElementById("pastelerias-list");
    pasteleriasList.innerHTML = "";

    pasteles.forEach(function(pastel, index) {
      var li = document.createElement("li");
      li.classList.add("pastel");

      var imagen = document.createElement("img");
      imagen.src = imagenes[index];
      imagen.alt = pastel.nombre;
      li.appendChild(imagen);

      var detalles = document.createElement("div");
      detalles.classList.add("pastel-details");

      var nombre = document.createElement("div");
      nombre.classList.add("pastel-name");
      nombre.textContent = pastel.nombre;
      detalles.appendChild(nombre);

      var precio = document.createElement("div");
      precio.classList.add("pastel-price");
      precio.textContent = "Precio: " + pastel.precio + " euros";
      detalles.appendChild(precio);

      var boton = document.createElement("button");
      boton.classList.add("buy-button");
      boton.textContent = "Comprar";
      detalles.appendChild(boton);

      li.appendChild(detalles);
      pasteleriasList.appendChild(li);
    });
  }

  // Función para buscar pasteles por nombre
  function buscarPasteles() {
    var searchInput = document.getElementById("search-input");
    var nombre = searchInput.value.trim();

    var xhrBusqueda = new XMLHttpRequest();
    xhrBusqueda.open("GET", "http://localhost:8080/Pastelerias/buscar/" + nombre, true);

    xhrBusqueda.onreadystatechange = function() {
      if (xhrBusqueda.readyState === 4 && xhrBusqueda.status === 200) {
        var pastelesEncontrados = JSON.parse(xhrBusqueda.responseText);
        mostrarPasteleriasEncontradas(pastelesEncontrados);
      }
    };

    xhrBusqueda.send();
  }

  // Función para mostrar los pasteles encontrados por la búsqueda
  function mostrarPasteleriasEncontradas(pasteles) {
    var pasteleriasList = document.getElementById("pastelerias-list");
    pasteleriasList.innerHTML = "";

    pasteles.forEach(function(pastel) {
      var li = document.createElement("li");
      li.classList.add("pastel");

      var detalles = document.createElement("div");
      detalles.classList.add("pastel-details");

      var nombre = document.createElement("div");
      nombre.classList.add("pastel-name");
      nombre.textContent = pastel.nombre;
      detalles.appendChild(nombre);

      var precio = document.createElement("div");
      precio.classList.add("pastel-price");
      precio.textContent = "Precio: " + pastel.precio + " euros";
      detalles.appendChild(precio);

      var boton = document.createElement("button");
      boton.classList.add("buy-button");
      boton.textContent = "Comprar";
      detalles.appendChild(boton);

      li.appendChild(detalles);
      pasteleriasList.appendChild(li);
    });
  }