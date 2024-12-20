function confirmarEliminacion(idCliente) {
    var confirmacion = confirm("¿Estás seguro de que deseas eliminar?");

    if (confirmacion) {
        document.getElementById("formEliminar_" + idCliente).submit();
    }
}

function confirmarEliminacion(idProducto) {
    var confirmacion = confirm("¿Estás seguro de que deseas eliminar?");

    if (confirmacion) {
        document.getElementById("formEliminar_" + idProducto).submit();
    }
}


function confirmarEliminacion(idProveedor) {
    var confirmacion = confirm("¿Estás seguro de que deseas eliminar?");

    if (confirmacion) {
        document.getElementById("formEliminar_" + idProveedor).submit();
    }
}

function confirmarEliminacion(idTrabajador) {
    var confirmacion = confirm("¿Estás seguro de que deseas eliminar?");

    if (confirmacion) {
        document.getElementById("formEliminar_" + idTrabajador).submit();
    }
}

function confirmarEliminacion(idCategoria) {
    var confirmacion = confirm("¿Estás seguro de que deseas eliminar?");

    if (confirmacion) {
        document.getElementById("formEliminar_" + idCategoria).submit();
    }
}