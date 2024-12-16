<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Trabajador</title>
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
        <a href="#"><i class="fa fa-shopping-cart"></i> Nueva Venta</a>
    </div>

    <!-- Main Content -->
    <div class="container">
        <div class="card">
            <h2>Actualizar Trabajador</h2>
            <form action="TrabajadorController?accion=actualizar" method="POST">
                <input type="hidden" name="idTrabajador" value="${trabajador.idTrabajador}" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" name="nombre" value="${trabajador.nombre}" required />
                </div>

                <div class="mb-3">
                    <label for="apellidos" class="form-label">Apellidos:</label>
                    <input type="text" class="form-control" name="apellidos" value="${trabajador.apellidos}" required />
                </div>

                <div class="mb-3">
                    <label for="nroIdentificacion" class="form-label">Número de Identificación:</label>
                    <input type="text" class="form-control" name="nroIdentificacion" value="${trabajador.nroIdentificacion}" required />
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Correo Electrónico:</label>
                    <input type="email" class="form-control" name="email" value="${trabajador.email}" required />
                </div>

                <div class="mb-3">
                    <label for="direccion" class="form-label">Dirección:</label>
                    <input type="text" class="form-control" name="direccion" value="${trabajador.direccion}" required />
                </div>

                <div class="mb-3">
                    <label for="telefono" class="form-label">Teléfono:</label>
                    <input type="text" class="form-control" name="telefono" value="${trabajador.telefono}" required />
                </div>

                <div class="mb-3">
                        <label for="cargo" class="form-label">Cargo</label>
                        <select class="form-control" id="cargo" name="cargo" required>
                            <option value="" disabled selected>Seleccione un cargo</option>
                            <option value="Vendedor">Vendedor</option>
                            <option value="Cajero">Cajero</option>
                        </select>
                    </div>

                <div class="mb-3">
                    <label for="sueldo" class="form-label">Sueldo:</label>
                    <input type="number" step="0.01" class="form-control" name="sueldo" value="${trabajador.sueldo}" required />
                </div>
                
                <div class="mb-3">
                        <label for="estado" class="form-label">Estado</label>
                        <select class="form-control" id="estado" name="estado" required>
                            <option value="" disabled selected>Seleccione un cargo</option>
                            <option value="Activo">Activo</option>
                            <option value="Inactivo">Inactivo</option>
                            <option value="Suspendido">Suspendido</option>
                        </select>
                    </div>

                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary me-2">Actualizar</button>
                    <a href="TrabajadorController?accion=listar" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
