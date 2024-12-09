<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Nuevo Categoria</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="CSS/estilos.css"/> 
    </head>
    <body>
        
        <!-- Sidebar -->
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

        
        <div class="container">
            <div class="form-container">
                <h2>Registrar Nueva Categoria</h2>
                <form action="CategoriaController?accion=nuevo" method="post">
                    <div class="mb-3">
                        <label for="nombrecat" class="form-label">Nombre Categoria</label>
                        <input type="text" class="form-control" id="nombrecat" name="nombrecat" required>
                    </div>
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripcion</label>
                        <input type="text" class="form-control" id="descripcion" name="descripcion" required>
                    </div>
                    
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Registrar</button>
                        <a href="ProductoController?accion=listar" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>