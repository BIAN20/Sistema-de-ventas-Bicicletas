
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="CSS/estilos.css"/>
        <title>Nuevo Producto</title>
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


        <div class="container mt-5">
            <h2 class="text-center">Registro de Producto</h2>
            <form>
                <!-- Campo ID Producto -->
                <div class="mb-3">
                    <label for="idProducto" class="form-label">ID Producto</label>
                    <input type="text" class="form-control" id="idProducto" name="idProducto" value="12345" readonly>
                </div>

                <!-- Campo Nombre Producto -->
                <div class="mb-3">
                    <label for="nombreProducto" class="form-label">Nombre del Producto</label>
                    <input type="text" class="form-control" id="nombreProducto" name="nombreProducto" placeholder="Ingrese el nombre del producto" required>
                </div>

                <!-- Campo Descripción -->
                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" id="descripcion" name="descripcion" rows="3" placeholder="Ingrese una descripción del producto" required></textarea>
                </div>

                <!-- Campo Precio -->
                <div class="mb-3">
                    <label for="precio" class="form-label">Precio</label>
                    <input type="number" class="form-control" id="precio" name="precio" step="0.01" placeholder="Ingrese el precio del producto" required>
                </div>

                <!-- Campo Stock -->
                <div class="mb-3">
                    <label for="stock" class="form-label">Stock</label>
                    <input type="number" class="form-control" id="stock" name="stock" placeholder="Ingrese el stock disponible" required>
                </div>

                <!-- Select Categorías -->
                <div class="mb-3">
                    <label for="categoria" class="form-label">Categoría</label>
                    <select class="form-select" id="categoria" name="categoria" required>
                        <option value="">Seleccione una categoría</option>
                        <option value="1">Electrónica</option>
                        <option value="2">Hogar</option>
                        <option value="3">Ropa</option>
                        <option value="4">Alimentos</option>
                    </select>
                </div>

                <!-- Botón de Enviar -->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Registrar Producto</button>
                </div>
            </form>
        </div>

    </body>
</html>
