<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Krub:wght@200;700&display=swap"
            rel="stylesheet"
            />
        <title>Lista de Clientes</title>
        <link rel="stylesheet" href="CSS/listasStyles.css"/>
        <script src="JS/App.js"></script>           
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
                                <c:if test="${sessionScope.rol == 'Administrador'}">
                            <a href="/SistemaDolmarBike/CategoriaController?accion=listar"
                               ><i class="fa fa-th-large"></i><span>Categorias</span></a
                            >
                            <a href="/SistemaDolmarBike/TrabajadorController?accion=listar">
                                <i class="fa fa-user-tie"></i><span>Trabajadores</span>
                            </a>
                        </c:if>
                        <a href="/SistemaDolmarBike/ProductoController?accion=listar"
                           ><i class="fa fa-box"></i><span>Productos</span></a
                        >
                        <a href="/SistemaDolmarBike/ClienteController?accion=listar"
                           ><i class="fa fa-users"></i><span>Clientes</span></a
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
                        <h2>Lista de Clientes</h2>
                        <div class="service-section-header">  
                            <a href="ClienteController?accion=nuevo"  class="flat-button">
                                <i class="fa fa-user-plus"> </i>   
                            </a>
                            <div class="search-field">
                                <form action="ClienteController?accion=buscar" method="get">
                                    <i class="fa fa-search"></i>
                                    <input type="hidden" name="accion" value="buscar">
                                    <input type="text" name="criterio" placeholder=" Ingrese nombre de cliente ..." aria-label="Buscar cliente">
                                    <button type="submit" class="flat-button">
                                        Buscar
                                    </button>
                                </form>
                            </div>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombres</th>
                                    <th>Apellidos</th>
                                    <th>DNI</th>                                                                
                                    <th>Telefono</th>
                                    <th>E-mail</th>
                                    <th>Direccion</th>
                                    <th>Tipo Cliente</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty listaClientes}">
                                    <tr>
                                        <td colspan="10" class="text-center">No hay clientes registrados</td>
                                    </tr>
                                </c:if>

                                <!-- Itera sobre los clientes -->
                                <c:forEach var="cli" items="${listaClientes}">
                                    <tr>
                                        <td>${cli.idCliente}</td>
                                        <td>${cli.nombre}</td>
                                        <td>${cli.apellidos}</td>
                                        <td>${cli.nroIdentificacion}</td>
                                        <td>${cli.direccion}</td>
                                        <td>${cli.telefono}</td>
                                        <td>${cli.email}</td>
                                        <td>${cli.tipoCliente.nombreTipo}</td>
                                        <td>
                                            <!-- Botón para editar el cliente -->
                                            <a href="ClienteController?accion=actualizar&idCliente=${cli.idCliente}" class="btn btn-warning btn-sm">
                                                <i class="fa fa-edit"></i>
                                            </a>

                                            <!-- Formulario para eliminar cliente -->
                                            <form id="formEliminar_${cli.idCliente}" action="ClienteController" method="post" style="display:inline;">
                                                <input type="hidden" name="accion" value="eliminar">
                                                <input type="hidden" name="id" value="${cli.idCliente}">
                                                <button type="button" class="btn btn-danger btn-sm"onclick="confirmarEliminacion(${cli.idCliente})">
                                                    <i class="fa fa-trash"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </section>
                </div>
            </div>
        </div>

    </body>
</html>