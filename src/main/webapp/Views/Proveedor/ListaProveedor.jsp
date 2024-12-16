<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Lista de Proveedores</title>
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
                <a href="/SistemaDolmarBike/ProveedorController?accion=nuevo">Nuevo Proveedor</a>
                <a href="/SistemaDolmarBike/ProveedorController?accion=listar">Listar Proveedores</a>
                <a href="#">Nueva Venta</a>
            </div>
            <div class="card">
                <div class="card-body">
                    <h2>Lista de Proveedores</h2>   
                    <a href="ProveedorController?accion=nuevo" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle"> </i> Nuevo Proveedor
                    </a>

                    <!-- Formulario de búsqueda -->
                    <form action="ProveedorController?accion=buscar" method="get" class="d-flex align-items-center mt-3">
                        <input type="hidden" name="accion" value="buscar">
                        <input type="text" name="criterio" class="form-control me-2" placeholder="Buscar proveedor..." aria-label="Buscar proveedor">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search"></i> Buscar
                        </button>
                    </form>

                    <!-- Tabla de proveedores -->
                    <table class="table table-bordered table-striped mt-3">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>DNI</th>
                                <th>Dirección</th>
                                <th>Teléfono</th>
                                <th>Email</th>
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty listaProveedor}">
                                <tr>
                                    <td colspan="9" class="text-center">No hay proveedores registrados</td>
                                </tr>
                            </c:if>

                            <!-- Itera sobre los proveedores -->
                            <c:forEach var="prov" items="${listaProveedor}">
                                <tr>
                                    <td>${prov.idProveedor}</td>
                                    <td>${prov.nombre}</td>
                                    <td>${prov.apellidos}</td>
                                    <td>${prov.nroIdentificacion}</td>
                                    <td>${prov.direccion}</td>
                                    <td>${prov.telefono}</td>
                                    <td>${prov.email}</td>
                                    <td>
                                        <!-- Botón para editar el cliente -->
                                        <a href="ProveedorController?accion=actualizar&idProveedor=${prov.idProveedor}" class="btn btn-warning btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>

                                        <!-- Formulario para eliminar cliente -->
                                        <form id="formEliminar_${prov.idProveedor}" action="ProveedorController" method="post" style="display:inline;">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="id" value="${prov.idProveedor}">
                                            <button type="button" class="btn btn-danger btn-sm" onclick="confirmarEliminacion(${prov.idProveedor})">
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