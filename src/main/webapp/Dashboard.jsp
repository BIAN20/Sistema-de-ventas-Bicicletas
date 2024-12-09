<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Dolmar Bikes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
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

        <!-- Contenido Principal -->
        <div class="main-content">
            <div class="container">
                <h1 class="mb-4">Bienvenido al Dashboard</h1>
                <div class="row">
                    <!-- Tarjetas para las acciones principales -->
                    <div class="col-md-4">
                        <div class="card text-center shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Agregar Cliente</h5>
                                <p class="card-text">Registra un nuevo cliente en el sistema.</p>
                                <a href="/SistemaDolmarBike/ClienteController?accion=nuevo" class="btn btn-primary">Ir</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-center shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Listar Clientes</h5>
                                <p class="card-text">Consulta los clientes registrados.</p>
                                <a href="/SistemaDolmarBike/ClienteController?accion=listar" class="btn btn-primary">Ir</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-center shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Nuevo Producto</h5>
                                <p class="card-text">Añade productos al inventario.</p>
                                <a href="/SistemaDolmarBike/ProductoController?accion=nuevo" class="btn btn-primary">Ir</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mt-4">
                        <div class="card text-center shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Listar Productos</h5>
                                <p class="card-text">Visualiza todos los productos disponibles.</p>
                                <a href="/SistemaDolmarBike/ProductoController?accion=listar" class="btn btn-primary">Ir</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mt-4">
                        <div class="card text-center shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Nueva Categoría</h5>
                                <p class="card-text">Crea categorías para los productos.</p>
                                <a href="/SistemaDolmarBike/CategoriaController?accion=nuevo" class="btn btn-primary">Ir</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mt-4">
                        <div class="card text-center shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Nuevo Trabajador</h5>
                                <p class="card-text">Registra trabajadores en el sistema.</p>
                                <a href="/SistemaDolmarBike/TrabajadorController?accion=nuevo" class="btn btn-primary">Ir</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mt-4">
                        <div class="card text-center shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Listar Trabajadores</h5>
                                <p class="card-text">Consulta los trabajadores registrados.</p>
                                <a href="/SistemaDolmarBike/TrabajadorController?accion=listar" class="btn btn-primary">Ir</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mt-4">
                        <div class="card text-center shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Nueva Venta</h5>
                                <p class="card-text">Registra una nueva venta.</p>
                                <a href="#" class="btn btn-primary">Ir</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
