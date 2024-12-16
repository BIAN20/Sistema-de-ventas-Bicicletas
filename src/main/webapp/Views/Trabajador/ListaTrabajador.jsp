<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Lista de Trabajadores</title>
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
                <a href="#">Nueva Venta</a>
            </div>
            <div class="card">
                <div class="card-body">
                    <h2>Lista de Trabajadores</h2>   
                    <a href="TrabajadorController?accion=nuevo" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle"> </i> Nuevo Trabajador
                    </a>

                    <!-- Formulario de búsqueda -->
                    <form action="TrabajadorController?accion=buscar" method="get" class="d-flex align-items-center mt-3">
                        <input type="hidden" name="accion" value="buscar">
                        <input type="text" name="criterio" class="form-control me-2" placeholder="Buscar Trabajador..." aria-label="Buscar trabajador">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search"></i> Buscar
                        </button>
                    </form>

                    <!-- Tabla de clientes -->
                    <table class="table table-bordered table-striped mt-3">
                        <thead>
                            <tr>
                                <th>ID-Trabajador</th>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>DNI</th>
                                <th>Dirección</th>
                                <th>Teléfono</th>
                                <th>Email</th>
                                <th>Cargo</th> <!-- Columna para el tipo de cliente -->
                                <th>Sueldo</th> 
                                <th>Estado</th> 
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Verifica si no hay clientes -->
                            <c:if test="${empty listaTrabajador}">
                                <tr>
                                    <td colspan="9" class="text-center">No hay trabajadores registrados</td>
                                </tr>
                            </c:if>

                            <!-- Itera sobre los clientes -->
                            <c:forEach var="tra" items="${listaTrabajador}">
                                <tr>
                                    <td>${tra.idTrabajador}</td>
                                    <td>${tra.nombre}</td>
                                    <td>${tra.apellidos}</td>
                                    <td>${tra.nroIdentificacion}</td>
                                    <td>${tra.direccion}</td>
                                    <td>${tra.telefono}</td>
                                    <td>${tra.email}</td>
                                    <td>${tra.cargo}</td>
                                    <td>${tra.sueldo}</td>
                                    <td>${tra.estado}</td>

                                    <td>
                                        <!-- Botón para editar el cliente -->
                                        <a href="TrabajadorController?accion=actualizar&idTrabajador=${tra.idTrabajador}" class="btn btn-warning btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>

                                        <form id="formEliminar_${tra.idTrabajador}" action="TrabajadorController" method="post" style="display:inline;">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="idTrabajador" value="${tra.idTrabajador}">
                                            <button type="submit" class="btn btn-danger btn-sm">
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