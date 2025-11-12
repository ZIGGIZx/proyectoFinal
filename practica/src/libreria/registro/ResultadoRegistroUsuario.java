package libreria.registro;

import libreria.modelo.Usuario;

public class ResultadoRegistroUsuario {
    public Usuario[] arregloUsuarios;
    public int usuariosCargados;

    public ResultadoRegistroUsuario(Usuario[] arregloUsuarios, int usuariosCargados) {
        this.arregloUsuarios = arregloUsuarios;
        this.usuariosCargados = usuariosCargados;
    }
}

