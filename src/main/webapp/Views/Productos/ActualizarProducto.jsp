<%@page import="Model.CategoriaProducto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Actualizar Producto</title>
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
                <h2>Actualizar Producto</h2>
                <form action="ProductoController?accion=actualizar" method="POST">
                    <input type="hidden" name="idProducto" value="${producto.idProducto}" />

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre del Producto:</label>
                        <input type="text" class="form-control" name="nombre" value="${producto.nombreProducto}" required />
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción:</label>
                        <textarea class="form-control" name="descripcion" required>${producto.descripcion}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio:</label>
                        <input type="number" step="0.01" class="form-control" name="precio" value="${producto.precio}" required />
                    </div>

                    <div class="mb-3">
                        <label for="stock" class="form-label">Stock:</label>
                        <input type="number" class="form-control" name="stock" value="${producto.stock}" required />
                    </div>

                    <div class="mb-3">
                    <label for="categoria" class="form-label">Categoría</label>
                    <select class="form-select" id="categoria" name="categoria" required>
                        <option value="">Seleccione una categoría</option>
                        <%
                            List<CategoriaProducto> categorias = (List<CategoriaProducto>) request.getAttribute("categorias");
                            if (categorias != null && !categorias.isEmpty()) {
                                for (CategoriaProducto categoria : categorias) {
                        %>
                        <option value="<%= categoria.getNombreCat()%>"><%= categoria.getNombreCat()%></option>
                        <%
                            }
                        } else {
                        %>
                        <option value="" disabled>No hay categorías disponibles</option>
                        <%
                            }
                        %>
                    </select>
                </div>


                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary me-2">Actualizar</button>
                        <a href="ProductoController?accion=listar" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
