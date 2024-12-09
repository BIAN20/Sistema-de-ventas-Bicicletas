<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="CSS/estilos.css"/>
        <title>Lista de Productos</title>
        <script src="JS/App.js"></script>        
    </head>
    <body>
        
        <!-- Sidebar -->
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

        <div class="container mt-3">
            <div class="card">
                <div class="card-body">
                    <h2>Lista de Productos</h2>   
                    <a href="ProductoController?accion=nuevo" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle"> </i> Nuevo Producto
                    </a>

                    <form action="ProductoController?accion=buscar" method="get" class="d-flex align-items-center mt-3">
                        <input type="hidden" name="accion" value="buscar">
                        <input type="text" name="criterio" class="form-control me-2" placeholder="Buscar producto..." aria-label="Buscar Producto">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search"></i> Buscar
                        </button>
                    </form>

                    <table class="table table-bordered table-striped table-sm mt-3">
                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Categoria</th>
                                <th>Producto</th>
                                <th>Precio</th>
                                <th>Stock</th>
                                <th>Descripcion</th>
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                          
                            <c:if test="${empty producto}">
                                <tr>
                                    <td colspan="7" class="text-center">No hay Productos registrados</td>
                                </tr>
                            </c:if>

                            <!-- Itera sobre los productos -->
                            <c:forEach var="pro" items="${producto}">
                                <tr>
                                    <td>${pro.idProducto}</td>
                                    <td>${pro.nombreCat}</td>
                                    <td>${pro.nombreProducto}</td>
                                    <td>${pro.precio}</td>
                                    <td>${pro.stock}</td>
                                    <td>${pro.descripcion}</td>
                                    <td>
                                        <!-- Botón para editar el producto -->
                                        <a href="ProductoController?accion=actualizar&idProducto=${pro.idProducto}" class="btn btn-warning btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        
                                        <form id="formEliminar_${pro.idProducto}" action="ProductoController" method="post" style="display:inline;">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="id" value="${pro.idProducto}">
                                            <button type="button" class="btn btn-danger btn-sm" onclick="confirmarEliminacion(${pro.idProducto})">
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
