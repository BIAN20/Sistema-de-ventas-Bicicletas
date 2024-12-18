<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Proveedor</title>
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
        <a href="/SistemaDolmarBike/TrabajadorController?accion=nuevo"><i class="fa fa-user-tie"></i> Nuevo Trabajador</a>
        <a href="/SistemaDolmarBike/TrabajadorController?accion=listar"><i class="fa fa-users-cog"></i> Listar Trabajadores</a>
        <a href="/SistemaDolmarBike/ProveedorController?accion=nuevo"><i class="fa fa-handshake"></i> Nuevo Proveedor</a>
        <a href="/SistemaDolmarBike/ProveedorController?accion=listar"><i class="fa fa-address-book"></i> Listar Proveedores</a>
        <a href="#"><i class="fa fa-shopping-cart"></i> Nueva Venta</a>
    </div>

    <!-- Main Content -->
    <div class="container">
        <div class="card">
            <h2>Actualizar Proveedor</h2>
            <form action="ProveedorController?accion=actualizar" method="POST">
                <input type="hidden" name="idProveedor" value="${proveedor.idProveedor}" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" name="nombre" value="${proveedor.nombre}" required />
                </div>

                <div class="mb-3">
                    <label for="apellidos" class="form-label">Apellidos:</label>
                    <input type="text" class="form-control" name="apellidos" value="${proveedor.apellidos}" required />
                </div>

                <div class="mb-3">
                    <label for="nroIdentificacion" class="form-label">Número de Identificación:</label>
                    <input type="text" class="form-control" name="nroIdentificacion" value="${proveedor.nroIdentificacion}" required />
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Correo Electrónico:</label>
                    <input type="email" class="form-control" name="email" value="${proveedor.email}" required />
                </div>

                <div class="mb-3">
                    <label for="direccion" class="form-label">Dirección:</label>
                    <input type="text" class="form-control" name="direccion" value="${proveedor.direccion}" required />
                </div>

                <div class="mb-3">
                    <label for="telefono" class="form-label">Teléfono:</label>
                    <input type="text" class="form-control" name="telefono" value="${proveedor.telefono}" required />
                </div>
                
                <div class="mb-3">
                    <label for="nombreEmpresa" class="form-label">Nombre Empresa</label>
                    <input type="text" class="form-control" name="nombreEmpresa" value="${proveedor.nombreEmpresa}" required />
                </div>

                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary me-2">Actualizar</button>
                    <a href="ProveedorController?accion=listar" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>