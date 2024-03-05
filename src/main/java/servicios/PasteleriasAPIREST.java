package servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import static spark.Spark.*;
import dao.AsociacionesDAOInterface;
import dao.PasteleriaDAOInterface;
import dao.ProveedorDAO;
import dao.ProveedorDAOInterface;
import dto.PasteleriaDTO;
import entidades.Pasteleria;
import entidades.Proveedor;
import spark.Spark;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PasteleriasAPIREST {
    private PasteleriaDAOInterface dao_Pasteleria;
    private ProveedorDAOInterface dao_prov;

    private AsociacionesDAOInterface dao_asoc;
    private Gson gson = new GsonBuilder()
                       .excludeFieldsWithoutExposeAnnotation()
                       .create();

    public PasteleriasAPIREST(PasteleriaDAOInterface implementacion_Pasteleria,ProveedorDAOInterface implementacion_prov,AsociacionesDAOInterface implementacion_asoc) {
        Spark.port(8080);
        dao_Pasteleria = implementacion_Pasteleria;
        dao_prov = implementacion_prov;
        dao_asoc = implementacion_asoc;

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.type("application/json");
        });

        Spark.exception(Exception.class, (e, req, res) -> {
            e.printStackTrace(); // Imprime la excepción en la consola
            res.status(500); // Establece el código de estado HTTP 500
            res.body("Excepcion en tu codigo"); // Mensaje de error para el cliente
        });

        Spark.post("/Pastelerias/proveedor/:idprov/:idmu", (request, response) -> {
            Long idprov = Long.parseLong(request.params(":idprov"));
            Long idmu = Long.parseLong(request.params(":idmu"));
            Pasteleria m= dao_Pasteleria.buscarPorId(idmu);
            Proveedor p= dao_prov.buscarPorID(idprov);
            response.type("application/json");
            
            return gson.toJson(dao_asoc.asignarProveedor(m,p));
        });


        //...
        Spark.get("/Pastelerias/proveedor/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            Pasteleria m= dao_Pasteleria.buscarPorId(id);
            Proveedor p = dao_asoc.obtenerProvedorPasteleria(m);
            response.type("application/json");
            return gson.toJson(p);
        });



        // Endpoint para obtener todos los Pastelerias
        Spark.get("/Pastelerias", (request, response) -> {
            List<Pasteleria> Pastelerias = dao_Pasteleria.devolverTodos();
            System.out.println(Pastelerias);
            response.type("application/json");
            return gson.toJson(Pastelerias);
        });

        //En caso de intentar un endpoint incorrecto
        Spark.notFound((request, response) -> {
            response.type("application/json");
            return "{\"error\": \"Ruta no encontrada\",\"hint1\": \"/Pastelerias\"," +
                    "\"hint2\": \"/Pastelerias/paginado/:pagina/:tam_pagina\",\"hint3\": \"/Pastelerias/id/:id\"}";
        });


        Spark.get("/Pastelerias/paginado/:pagina/:tam_pagina", (request, response) -> {

            Integer pagina = Integer.parseInt(request.params(":pagina"));
            Integer tamaño_pagina = Integer.parseInt(request.params(":tam_pagina"));

            List<Pasteleria> Pastelerias = dao_Pasteleria.devolverTodos(pagina, tamaño_pagina);

            Long totalElementos = dao_Pasteleria.totalPastelerias(); // Obtener el total de Pastelerias


            RespuestaPaginacion paginaResultado = new RespuestaPaginacion<>(Pastelerias, totalElementos, pagina, tamaño_pagina);


            return gson.toJson(paginaResultado);

        });

        // Endpoint para obtener Pastelerias más caros
        Spark.get("/Pastelerias/mascaros", (request, response) -> {
            List<Pasteleria> masCaros = dao_Pasteleria.devolverMasCaros();
            System.out.println(masCaros);
            return gson.toJson(masCaros);
        });

        // Endpoint para obtener todas las imágenes de Pastelerias
        Spark.get("/Pastelerias/imagenes", (request, response) -> {
            List<String> imagenes = dao_Pasteleria.devolverTodasImages();
            return gson.toJson(imagenes);
        });

        // Endpoint para obtener un resumen con solo el nombre el precio y la URL
        Spark.get("/Pastelerias/resumenobjetos", (request, response) -> {
            //List<Map> resumen = dao.devolverNombreImagenes();
            List<PasteleriaDTO> resumen = dao_Pasteleria.devolverNombreImagenes();
            return gson.toJson(resumen);
        });


        // Endpoint para obtener un Pasteleria por su ID
        Spark.get("/Pastelerias/id/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            Pasteleria Pasteleria = dao_Pasteleria.buscarPorId(id);
            if (Pasteleria != null) {
                return gson.toJson(Pasteleria);
            } else {
                response.status(404);
                return "Pasteleria no encontrado";
            }
        });


        // Endpoint para obtener el total de Pastelerias
        Spark.get("/Pastelerias/total", (request, response) -> {
            Long total = dao_Pasteleria.totalPastelerias();
            return total.toString();
        });

        // Endpoint para calcular la media de precios de los Pastelerias
        Spark.get("/Pastelerias/mediaprecios", (request, response) -> {
            Double media = dao_Pasteleria.mediaPrecios();
            return media.toString();
        });

        // Endpoint para calcular la media de precios de los Pastelerias de una marca
        Spark.get("/Pastelerias/mediaprecios/:marca", (request, response) -> {
            String marca = request.params(":marca");
            Double media = dao_Pasteleria.mediaPreciosMarca(marca);
            return media.toString();
        });

        // Endpoint para buscar Pastelerias por nombre (similar a LIKE)
        Spark.get("/Pastelerias/buscar/:nombre", (request, response) -> {
            String nombre = request.params(":nombre");
            List<Pasteleria> Pastelerias = dao_Pasteleria.buscarPorNombreLike(nombre);
            return gson.toJson(Pastelerias);
        });

        // Endpoint para buscar Pastelerias entre precios mínimos y máximos
        Spark.get("/Pastelerias/buscar/:min/:max", (request, response) -> {
            Double min = Double.parseDouble(request.params(":min"));
            Double max = Double.parseDouble(request.params(":max"));
            List<Pasteleria> Pastelerias = dao_Pasteleria.buscarEntrePrecios(min, max);
            return gson.toJson(Pastelerias);
        });

        // Endpoint para buscar Pastelerias entre precios mínimos y máximos de una marca
        Spark.get("/Pastelerias/buscar/:min/:max/:marca", (request, response) -> {
            Double min = Double.parseDouble(request.params(":min"));
            Double max = Double.parseDouble(request.params(":max"));
            String marca = request.params(":marca");
            List<Pasteleria> Pastelerias = dao_Pasteleria.buscarEntrePreciosMarca(min, max, marca);
            return gson.toJson(Pastelerias);
        });

        // Endpoint para buscar Pastelerias entre precios mínimos y máximos de varias marcas
        Spark.get("/Pastelerias/buscarvarias/:min/:max/:listamarcas", (request, response) -> {
            Double min = Double.parseDouble(request.params(":min"));
            Double max = Double.parseDouble(request.params(":max"));
            String marcasParam = request.params(":listamarcas");

            List<String> marcas = Arrays.asList(marcasParam.split(","));
            System.out.println(marcas);

            List<Pasteleria> Pastelerias = dao_Pasteleria.buscarEntrePreciosMarcas(min, max, marcas);
            return gson.toJson(Pastelerias);
        });

        //CREAR UN Pasteleria CON TODOS LOS DATOS
        Spark.post("/Pastelerias/crear", (request, response) -> {
            String body = request.body();
            Pasteleria nuevoPasteleria = gson.fromJson(body, Pasteleria.class);

            Pasteleria creado = dao_Pasteleria.create(nuevoPasteleria);
            return gson.toJson(creado);
        });

        // Endpoint para actualizar un Pasteleria por su ID
        Spark.put("/Pastelerias/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            String body = request.body();
            Pasteleria PasteleriaActualizado = gson.fromJson(body, Pasteleria.class);
            PasteleriaActualizado.setId(id);
            Pasteleria actualizado = dao_Pasteleria.update(PasteleriaActualizado);
            if (actualizado != null) {
                return gson.toJson(actualizado);
            } else {
                response.status(404);
                return "Pasteleria no encontrado";
            }
        });

        // Endpoint para eliminar un Pasteleria por su ID
        Spark.delete("/Pastelerias/:id", (request, response) -> {
            Long id = Long.parseLong(request.params(":id"));
            boolean eliminado = dao_Pasteleria.deleteById(id);
            if (eliminado) {
                return "Pasteleria eliminado correctamente";
            } else {
                response.status(404);
                return "Pasteleria no encontrado";
            }
        });



        //----------------------------END POINT DE RELACIONES------------------------------------------
    }
}
