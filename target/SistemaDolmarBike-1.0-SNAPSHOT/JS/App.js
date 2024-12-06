function confirmarEliminacion(idCliente) {
    var confirmacion = confirm("¿Estás seguro de que deseas eliminar este cliente?");

    if (confirmacion) {
        document.getElementById("formEliminar_" + idCliente).submit();
    }
}
