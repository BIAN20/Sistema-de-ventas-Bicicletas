<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Lista de Clientes</title>
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
                <a href="/SistemaDolmarBike/CategoriaController?accion=nueva">Nueva Categoría</a>
                <a href="/SistemaDolmarBike/TrabajadorController?accion=nuevo">Nuevo Trabajador</a>
                <a href="/SistemaDolmarBike/TrabajadorController?accion=listar">Listar Trabajadores</a>
                <a href="#">Nueva Venta</a>
            </div>
            <div class="card">
                <div class="card-body">
                    <h2>Lista de Clientes</h2>   
                    <a href="ClienteController?accion=nuevo" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle"> </i> Nuevo Cliente
                    </a>

                    <!-- Formulario de búsqueda -->
                    <form action="ClienteController?accion=buscar" method="get" class="d-flex align-items-center mt-3">
                        <input type="hidden" name="accion" value="buscar">
                        <input type="text" name="criterio" class="form-control me-2" placeholder="Buscar cliente..." aria-label="Buscar cliente">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search"></i> Buscar
                        </button>
                    </form>

                    <!-- Tabla de clientes -->
                    <table class="table table-bordered table-striped mt-3">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>DNI</th>                                                                
                                <th>Telefono</th>
                                <th>E-mail</th>
                                <th>Direccion</th>
                                <th>Tipo Cliente</th> 
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty listaClientes}">
                                <tr>
                                    <td colspan="9" class="text-center">No hay clientes registrados</td>
                                </tr>
                            </c:if>

                            <!-- Itera sobre los clientes -->
                            <c:forEach var="cli" items="${listaClientes}">
                                <tr>
                                    <td>${cli.idCliente}</td>
                                    <td>${cli.nombre}</td>
                                    <td>${cli.apellidos}</td>
                                    <td>${cli.nroIdentificacion}</td>
                                    <td>${cli.direccion}</td>
                                    <td>${cli.telefono}</td>
                                    <td>${cli.email}</td>
                                    <td>${cli.tipoCliente.nombreTipo}</td> <!-- Muestra el tipo de cliente -->
                                    <td>
                                        <!-- Botón para editar el cliente -->
                                        <a href="ClienteController?accion=actualizar&idCliente=${cli.idCliente}" class="btn btn-warning btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>

                                        <!-- Formulario para eliminar cliente -->
                                        <form id="formEliminar_${cli.idCliente}" action="ClienteController" method="post" style="display:inline;">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="id" value="${cli.idCliente}">
                                            <button type="button" class="btn btn-danger btn-sm" onclick="confirmarEliminacion(${cli.idCliente})">
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