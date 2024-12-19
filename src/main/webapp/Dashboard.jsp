<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Validar sesión
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://unpkg.com/phosphor-icons" rel="stylesheet">
        <title>Dashboard Dolmar Bikes</title>
        <link rel="stylesheet" href="CSS/listasStyles.css"/>
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
                        <a href="Dashboard.jsp" class="active">Resumen</a>
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

                        <!-- Opciones visibles solo para Administrador -->
                        <c:if test="${sessionScope.rol == 1}">
                            <a href="/SistemaDolmarBike/CategoriaController?accion=listar">
                                <i class="fa fa-th-large"></i><span>Categorías</span>
                            </a>
                            <a href="/SistemaDolmarBike/TrabajadorController?accion=listar">
                                <i class="fa fa-user-tie"></i><span>Trabajadores</span>
                            </a>
                        </c:if>

                        <!-- Opciones visibles para todos los roles -->
                        <a href="/SistemaDolmarBike/ProductoController?accion=listar">
                            <i class="fa fa-box"></i><span>Productos</span>
                        </a>
                        <a href="/SistemaDolmarBike/ClienteController?accion=listar">
                            <i class="fa fa-users"></i><span>Clientes</span>
                        </a>
                        <a href="login.jsp"><i class="fa fa-sign-out-alt"></i><span>Cerrar Sesión</span></a>
                    </nav>
                    <footer class="footer">
                        <p>DolmarBike<small>©</small></p>
                        <div>DolmarBikes ©<br />Algunos derechos reservados</div>
                    </footer>
                </div>
                <div class="app-body-main-content">
                    <section class="service-section">
                        <h2>Servicios</h2>
                        <div class="tiles">

                            <!-- Categorías: Visible solo para Administradores -->
                            <c:if test="${sessionScope.rol == 1}">
                                <article class="tile">
                                    <div class="tile-header">
                                        <i class="fa fa-th-large"></i>
                                        <h3>
                                            <span>Categorías</span>
                                            <span>Cree nuevas categorías para sus productos.</span>
                                        </h3>
                                    </div>
                                    <a href="/SistemaDolmarBike/CategoriaController?accion=listar">
                                        <span>Ir a la lista</span>
                                        <span class="icon-button"><i class="fa fa-arrow-right"></i></span>
                                    </a>
                                </article>
                            </c:if>

                            <!-- Productos: Visible para todos los roles -->
                            <article class="tile">
                                <div class="tile-header">
                                    <i class="fa fa-box"></i>
                                    <h3>
                                        <span>Productos</span>
                                        <span>Agregue nuevos productos y listelos</span>
                                    </h3>
                                </div>
                                <a href="/SistemaDolmarBike/ProductoController?accion=listar">
                                    <span>Ir a la lista</span>
                                    <span class="icon-button"><i class="fa fa-arrow-right"></i></span>
                                </a>
                            </article>

                            <!-- Proveedores: Visible solo para Administradores -->
                            <c:if test="${sessionScope.rol == 1}">
                                <article class="tile">
                                    <div class="tile-header">
                                        <i class="fa fa-building"></i>
                                        <h3>
                                            <span>Proveedores</span>
                                            <span>Lista de todos sus proveedores</span>
                                        </h3>
                                    </div>
                                    <a href="/SistemaDolmarBike/ProveedorController?accion=listar">
                                        <span>Ir a la lista</span>
                                        <span class="icon-button"><i class="fa fa-arrow-right"></i></span>
                                    </a>
                                </article>
                            </c:if>

                            <!-- Trabajadores: Visible solo para Administradores -->
                            <c:if test="${sessionScope.rol == 1}">
                                <article class="tile">
                                    <div class="tile-header">
                                        <i class="fa fa-user-tie"></i>
                                        <h3>
                                            <span>Trabajadores</span>
                                            <span>Agregue nuevos empleados a la empresa</span>
                                        </h3>
                                    </div>
                                    <a href="/SistemaDolmarBike/TrabajadorController?accion=listar">
                                        <span>Ir a la lista</span>
                                        <span class="icon-button"><i class="fa fa-arrow-right"></i></span>
                                    </a>
                                </article>
                            </c:if>

                            <!-- Clientes: Visible para todos los roles -->
                            <article class="tile">
                                <div class="tile-header">
                                    <i class="fa fa-users"></i>
                                    <h3>
                                        <span>Clientes</span>
                                        <span>Agregue, Liste y elimine Clientes</span>
                                    </h3>
                                </div>
                                <a href="/SistemaDolmarBike/ClienteController?accion=listar">
                                    <span>Ir a la lista</span>
                                    <span class="icon-button"><i class="fa fa-arrow-right"></i></span>
                                </a>
                            </article>

                        </div>

                        <div class="service-section-footer">
                            <p>Estamos trabajando para el módulo de ventas 🙂 ....</p>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>
