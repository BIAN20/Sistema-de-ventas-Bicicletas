<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Lista de Categorías</title>
        <link rel="stylesheet" href="CSS/estilos.css"/>
        <script src="JS/App.js"></script>           
    </head>
    <body>

        <div class="container mt-3">
            <div class="sidebar">
                <h3>Dolmar Bikes</h3>
                <a href="/SistemaDolmarBike/ClienteController?accion=nuevo">Agregar Cliente</a>
                <a href="/SistemaDolmarBike/ClienteController?accion=listar">Listar Clientes</a>
                <a href="/SistemaDolmarBike/ProductoController?accion=nuevo">Nuevo Producto</a>
                <a href="/SistemaDolmarBike/ProductoController?accion=listar">Listar Productos</a>
                <a href="/SistemaDolmarBike/CategoriaController?accion=nuevo">Nueva Categoría</a>
                <a href="/SistemaDolmarBike/TrabajadorController?accion=nuevo">Nuevo Trabajador</a>
                <a href="/SistemaDolmarBike/TrabajadorController?accion=listar">Listar Trabajadores</a>
                <a href="/SistemaDolmarBike/CategoriaController?accion=listar">Listar Categorías</a>
                <a href="#">Nueva Venta</a>
            </div>
            <div class="card">
                <div class="card-body">
                    <h2>Lista de Categorías</h2>   
                    <a href="CategoriaController?accion=nuevo" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle"> </i> Nueva Categoría
                    </a>
                    <!-- Formulario de búsqueda -->
                    <form action="CategoriaController" method="GET" class="d-flex align-items-center mt-3">
                        <input type="text" name="criterio" class="form-control me-2" placeholder="Buscar categoría..." aria-label="Buscar categoría" value="${criterio}">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search"></i> Buscar
                        </button>
                        <input type="hidden" name="accion" value="buscar" />
                    </form>


                    <!-- Tabla de categorías -->
                    <table class="table table-bordered table-striped mt-3">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Descripción</th>                                                                
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty categorias}">
                                <tr>
                                    <td colspan="4" class="text-center">No hay categorías registradas</td>
                                </tr>
                            </c:if>
                            <c:forEach var="cat" items="${categorias}">
                                <tr>
                                    <td>${cat.idCategoria}</td>
                                    <td>${cat.nombreCat}</td>
                                    <td>${cat.descripcion}</td>
                                    <td>
                                        <!-- Botón para editar la categoría -->
                                        <a href="CategoriaController?accion=actualizar&idCategoria=${cat.idCategoria}" class="btn btn-warning btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>

                                        <!-- Formulario para eliminar categoría -->
                                        <form id="formEliminar_${cat.idCategoria}" action="CategoriaController" method="post" style="display:inline;">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="idCategoria" value="${cat.idCategoria}">
                                            <button type="button" class="btn btn-danger btn-sm" onclick="confirmarEliminacion(${cat.idCategoria})">
                                                <i class="fa fa-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
