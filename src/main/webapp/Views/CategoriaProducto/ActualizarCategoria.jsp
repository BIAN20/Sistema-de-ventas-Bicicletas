<%@page import="Model.CategoriaProducto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Actualizar Categoria</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
        <link rel="stylesheet" href="CSS/estilos.css"/>
    </head>
    <body>

        <!-- Sidebar -->
        <div class="sidebar">
            <h3>Dolmar Bikes</h3>
            <a href="/SistemaDolmarBike/ClienteController?accion=nuevo"><i class="fa fa-user-plus"></i> Agregar Cliente</a>
            <a href="/SistemaDolmarBike/ClienteController?accion=listar"><i class="fa fa-users"></i> Listar Clientes</a>
            <a href="/SistemaDolmarBike/ProductoController?accion=nuevo"><i class="fa fa-box"></i> Nuevo Producto</a>
            <a href="/SistemaDolmarBike/ProductoController?accion=listar"><i class="fa fa-list"></i> Listar Productos</a>
            <a href="/SistemaDolmarBike/CategoriaController?accion=nueva"><i class="fa fa-tags"></i> Nueva Categoría</a>
            <a href="#"><i class="fa fa-shopping-cart"></i> Nueva Venta</a>
        </div>

        <!-- Main Content -->
        <div class="container">
            <div class="card">
                <h2>Actualizar Categoria</h2>
                <form action="CategoriaController?accion=actualizar" method="POST">
                    <input type="hidden" name="idCategoria" value="${categoria.idCategoria}" />

                    <div class="mb-3">
                        <label for="nombrecat" class="form-label">Nombre Categoria:</label>
                        <input type="text" class="form-control" name="nombrecat" value="${categoria.nombreCat}" required />
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción:</label>
                        <textarea class="form-control" name="descripcion" required>${categoria.descripcion}</textarea>
                    </div>

                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary me-2">Actualizar</button>
                        <a href="CategoriaController?accion=listar" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
