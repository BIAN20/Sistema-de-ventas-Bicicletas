<%@page import="Model.CategoriaProducto"%>
<%@page import="java.util.List"%>
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
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Actualizar Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Krub:wght@200;700&display=swap" rel="stylesheet" />
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
                        <a href="Dashboard.jsp" class="active">Resumen</a>
                    </div>
                </div>
                <div class="app-header-actions">
                    <button class="user-profile">
                        <span>
                            <c:choose>
                                <c:when test="${sessionScope.rol == 1}">
                                    Administrador
                                </c:when>
                                <c:when test="${sessionScope.rol == 2}">
                                    Trabajador
                                </c:when>
                                <c:otherwise>
                                    Invitado
                                </c:otherwise>
                            </c:choose>
                        </span>
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
                        <h2>Actualizar Producto</h2>
                        <form class="form" action="ProductoController?accion=actualizar" method="post">
                            <input type="hidden" name="idProducto" value="${producto.idProducto}" />

                            <label>
                                <input
                                    required
                                    placeholder=""
                                    type="text"
                                    class="input"
                                    value="${producto.nombreProducto}"
                                    name="nombre"
                                    />
                                <span>Nombre Producto</span>
                            </label>

                            <div class="flex">
                                <label>
                                    <input
                                        required
                                        placeholder=""
                                        step="0.01"
                                        type="number"
                                        class="input"
                                        value="${producto.precio}"
                                        name="precio"
                                        />
                                    <span>Precio</span>
                                </label>

                                <label>
                                    <input
                                        required
                                        placeholder=""
                                        type="number"
                                        class="input"
                                        value="${producto.stock}"
                                        name="stock"
                                        />
                                    <span>Stock</span>
                                </label>
                            </div>

                            <label>
                                <input
                                    required
                                    placeholder=""
                                    type="text"
                                    class="input"
                                    value="${producto.descripcion}"
                                    name="descripcion"
                                    />
                                <span>Descripción</span>
                            </label>

                            <label for="categoria">
                                <select class="input" id="categoria" name="categoria" required>
                                    <option value="" disabled selected>Seleccione una categoría</option>
                                    <c:forEach var="categoria" items="${categorias}">
                                        <option value="${categoria.nombreCat}">${categoria.nombreCat}</option>
                                    </c:forEach>
                                </select>
                                <span>Categoría</span>
                            </label>

                            <button class="submit">Actualizar</button>
                        </form>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>