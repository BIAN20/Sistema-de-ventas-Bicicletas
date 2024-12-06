<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Nuevo Cliente</title>
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
            <a href="/SistemaDolmarBike/CategoriaController?accion=nueva">Nueva Categoría</a>
            <a href="/SistemaDolmarBike/TrabajadorController?accion=nuevo">Nuevo Trabajador</a>
            <a href="/SistemaDolmarBike/TrabajadorController?accion=listar">Listar Trabajadores</a>
            <a href="#">Nueva Venta</a>
        </div>

        
        <div class="container">
            <div class="form-container">
                <h2>Registrar Nuevo Cliente</h2>
                <form action="ClienteController?accion=nuevo" method="post">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="apellidos" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" id="apellidos" name="apellidos" required>
                    </div>
                    <div class="mb-3">
                        <label for="nroIdentificacion" class="form-label">DNI</label>
                        <input type="text" class="form-control" id="nroIdentificacion" name="nroIdentificacion" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">E-mail</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label for="direccion" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="direccion" name="direccion" required>
                    </div>
                    <div class="mb-3">
                        <label for="telefono" class="form-label">Teléfono</label>
                        <input type="text" class="form-control" id="telefono" name="telefono" required>
                    </div>
                    <div class="mb-3">
                        <label for="tipoCliente" class="form-label">Tipo de Cliente</label>
                        <select name="tipoCliente" id="tipoCliente" class="form-control" onchange="mostrarCampoRuc()">
                            <option value="natural">Natural</option>
                            <option value="juridico">Jurídico</option>
                        </select>
                    </div>
                    <div class="mb-3" id="rucField" style="display: none;">
                        <label for="ruc" class="form-label">RUC</label>
                        <input type="text" class="form-control" id="ruc" name="ruc">
                    </div>
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Registrar</button>
                        <a href="ClienteController?accion=listar" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>

        <script>
            function mostrarCampoRuc() {
                var tipoCliente = document.getElementById("tipoCliente").value;
                var rucField = document.getElementById("rucField");
                rucField.style.display = tipoCliente === "juridico" ? "block" : "none";
            }

            window.onload = function () {
                mostrarCampoRuc();
            };
        </script>
    </body>
</html>