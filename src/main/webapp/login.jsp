<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <!-- Importar Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <title>Login</title>
        <link rel="stylesheet" href="CSS/styleslogin.css">

    </head>
    <body>
        <h2>Bienvenido a Dolmar Bike</h2>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="#">
                    <h1>Crear Cuenta</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>usa un email para registrate</span>
                    <input type="text" placeholder="Nombres" />
                    <input type="email" placeholder="Email" />
                    <input type="password" placeholder="Contraseña" />
                    <button>Iniciar</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="LoginServlet" method="post">
                    <h1>Iniciar Sesión</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <input type="text" name="usuario"  id="usuario" placeholder="Usuario" required />
                    <input type="password" name="contraseña"  id="contraseña" placeholder="Contraseña" required />
                    <a href="#">¿olvidate tu contraseña?</a>
                    <button>Iniciar Sesión</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>¡Bienvenido de nuevo!</h1>
                        <p>Para mantenerte conectado, por favor inicia sesión</p>
                        <button class="ghost" id="signIn">Iniciar Sesión</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Bienvenido!</h1>
                        <p>Ingresa tus datos y comienza a inteactuar con nuestro sistema</p>
                        <button class="ghost" id="signUp">Registrate</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="JS/appLogin.js"></script>  

    </body>

</html>