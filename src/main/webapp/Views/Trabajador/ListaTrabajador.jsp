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
        <title>Lista de Trabajadores</title>
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
                        <a href="/SistemaDolmarBike/CategoriaController?accion=listar"><i class="fa fa-th-large"></i><span>Categorias</span></a>
                        <a href="/SistemaDolmarBike/ProductoController?accion=listar"><i class="fa fa-box"></i><span>Productos</span></a>
                        <a href="/SistemaDolmarBike/ClienteController?accion=listar"><i class="fa fa-users"></i><span>Clientes</span></a>
                        <a href="/SistemaDolmarBike/TrabajadorController?accion=listar"><i class="fa fa-user-tie"></i><span>Trabajadores</span></a>
                        <a href="login.jsp"><i class="fa fa-sign-out-alt"></i><span>Cerrar Sesion</span></a>
                    </nav>
                    <footer class="footer">
                        <p>DolmarBike<small>©</small></p>
                        <div>DolmarBikes ©<br />Algunos derechos reservados</div>
                    </footer>
                </div>
                <div class="app-body-main-content">
                    <section class="service-section">
                        <h2>Lista de Trabajadores</h2>
                        <div class="service-section-header">  
                            <a href="TrabajadorController?accion=nuevo"  class="flat-button">
                                <i class="fa fa-user-tie"> </i>
                            </a>
                            <div class="search-field">
                                <form action="TrabajadorController?accion=buscar" method="get">
                                    <i class="fa fa-search"></i>
                                    <input type="hidden" name="accion" value="buscar">
                                    <input type="text" name="criterio" placeholder=" Ingrese nombre de Trabajador ..." aria-label="Buscar trabajador">
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
                                    <th>Dirección</th>
                                    <th>Teléfono</th>
                                    <th>Email</th>
                                    <th>Cargo</th> <!-- Columna para el tipo de cliente -->
                                    <th>Sueldo</th> 
                                    <th>Estado</th> 
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Verifica si no hay clientes -->
                                <c:if test="${empty listaTrabajador}">
                                    <tr>
                                        <td colspan="9" class="text-center">No hay trabajadores registrados</td>
                                    </tr>
                                </c:if>

                                <!-- Itera sobre los clientes -->
                                <c:forEach var="tra" items="${listaTrabajador}">
                                    <tr>
                                        <td>${tra.idTrabajador}</td>
                                        <td>${tra.nombre}</td>
                                        <td>${tra.apellidos}</td>
                                        <td>${tra.nroIdentificacion}</td>
                                        <td>${tra.direccion}</td>
                                        <td>${tra.telefono}</td>
                                        <td>${tra.email}</td>
                                        <td>${tra.cargo}</td>
                                        <td>${tra.sueldo}</td>
                                        <td>${tra.estado}</td>
                                        <td>
                                            <!-- Botón para editar el cliente -->
                                            <a href="TrabajadorController?accion=actualizar&idTrabajador=${tra.idTrabajador}" class="btn btn-warning btn-sm">
                                                <i class="fa fa-edit"></i>
                                            </a>

                                            <form id="formEliminar_${tra.idTrabajador}" action="TrabajadorController" method="post" style="display:inline;">
                                                <input type="hidden" name="accion" value="eliminar">
                                                <input type="hidden" name="idTrabajador" value="${tra.idTrabajador}">
                                                <button type="button" class="btn btn-danger btn-sm" onclick="confirmarEliminacion(${tra.idTrabajador})">
                                                    <i class="fa fa-trash"></i>
                                                </button>
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