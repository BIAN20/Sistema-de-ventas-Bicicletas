<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Actualizar Trabajador</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Krub:wght@200;700&display=swap"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="CSS/forms.css"/>
    </head>
    <body>
        <div class="app">
            <header class="app-header">
                <div class="app-header-logo">
                    <div class="logo">
                        <span class="logo-icon">
                            <img src="Images/logoDolmarBike.jpg" />
                        </span>
                        <h1 class="logo-title">
                            <span>Dolmar Bikes</span>
                            <span>Venta de Bicicletas</span>
                        </h1>
                    </div>
                </div>
                <div class="app-header-navigation">
                    <div class="tabs">
                        <a
                            href="Dashboard.jsp"
                            class="active"
                            >Resumen</a
                        >
                    </div>
                </div>
                <div class="app-header-actions">
                    <button class="user-profile">
                        <span>Administrador</span>
                        <span>
                            <img src="https://assets.codepen.io/285131/almeria-avatar.jpeg" />
                        </span>
                    </button>
                </div>
            </header>
            <div class="app-body">
                <div class="app-body-navigation">
                    <nav class="navigation">
                        <a href="Dashboard.jsp"><i class="fa fa-tachometer-alt"></i><span>Dashboard</span></a>
                        <a href="/SistemaDolmarBike/CategoriaController?accion=listar"
                           ><i class="fa fa-th-large"></i><span>Categorias</span></a
                        >
                        <a href="/SistemaDolmarBike/ProductoController?accion=listar"
                           ><i class="fa fa-box"></i><span>Productos</span></a
                        >
                        <a href="/SistemaDolmarBike/ClienteController?accion=listar"
                           ><i class="fa fa-users"></i><span>Clientes</span></a
                        >
                        <a href="/SistemaDolmarBike/TrabajadorController?accion=listar"
                           ><i class="fa fa-user-tie"></i><span>Trabajadores</span></a
                        >
                        <a href="login.jsp"><i class="fa fa-sign-out-alt"></i><span>Cerrar Sesion</span></a>
                    </nav>
                    <footer class="footer">
                        <p>DolmarBike<small>©</small></p>
                        <div>DolmarBikes ©<br />Algunos derechos reservados</div>
                    </footer>
                </div>
                <div class="app-body-main-content">
                    <section class="service-section">
                        <h2>Actualizar Trabajador</h2>
                        <form
                            class="form"
                            action="TrabajadorController?accion=actualizar"
                            method="post"
                            >
                            <input
                                type="hidden"
                                name="idTrabajador"
                                value="${trabajador.idTrabajador}"
                                />
                            <div class="flex">
                                <label>
                                    <input
                                        required
                                        placeholder=""
                                        type="text"
                                        class="input"
                                        value="${trabajador.nombre}"
                                        name="nombre"
                                        />
                                    <span>Nombres</span>
                                </label>

                                <label>
                                    <input
                                        required
                                        placeholder=""
                                        type="text"
                                        class="input"
                                        value="${trabajador.apellidos}"
                                        name="apellidos"
                                        />
                                    <span>Apellidos</span>
                                </label>
                            </div>

                            <div class="flex">
                                <label>
                                    <input
                                        required
                                        placeholder=""
                                        type="text"
                                        class="input"
                                        value="${trabajador.nroIdentificacion}"
                                        name="nroIdentificacion"
                                        />
                                    <span>DNI</span>
                                </label>

                                <label>
                                    <input
                                        required
                                        placeholder=""
                                        type="text"
                                        class="input"
                                        value="${trabajador.telefono}"
                                        name="telefono"
                                        />
                                    <span>Telefono</span>
                                </label>
                            </div>

                            <label>
                                <input
                                    required
                                    placeholder=""
                                    type="email"
                                    class="input"
                                    value="${trabajador.email}"
                                    name="email"
                                    />
                                <span>Email</span>
                            </label>

                            <label>
                                <input
                                    required
                                    placeholder=""
                                    type="text"
                                    class="input"
                                    value="${trabajador.direccion}"
                                    name="direccion"
                                    />
                                <span>Direccion</span>
                            </label>

                            <div class="flex">
                                <label>
                                    <select
                                        name="cargo"
                                        id="cargo"
                                        class="input"
                                        required
                                        >
                                        <option
                                            value=""
                                            disabled
                                            selected
                                            ></option>
                                        <option value="Vendedor">Vendedor</option>
                                        <option value="Cajero">Cajero</option>
                                        <option value="Vendedor">Gerente de Tienda</option>
                                        <option value="Cajero">Técnico de Reparación</option>
                                        <option value="Vendedor">Encargado de Inventario</option>
                                    </select>
                                    <span>Cargo</span>
                                </label>

                                <label>
                                    <input
                                        required
                                        placeholder=""
                                        type="number"
                                        class="input"
                                        value="${trabajador.sueldo}"
                                        name="sueldo"
                                        step="0.01"
                                        min="0"
                                        />
                                    <span>Sueldo</span>
                                </label>

                                <label>
                                    <select
                                        name="estado"
                                        id="estado"
                                        class="input"
                                        required
                                        >
                                        <option
                                            value=""
                                            disabled
                                            selected
                                            ></option>
                                        <option value="Activo">Activo</option>
                                        <option value="Inactivo">Inactivo</option>
                                        <option value="Suspendido">Suspendido</option>
                                    </select>
                                    <span>Estado</span>
                                </label>
                            </div>
                            <button class="submit">Registrar</button>
                        </form>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>
