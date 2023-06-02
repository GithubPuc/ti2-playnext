package service;

import dao.UsuarioDAO;
import spark.Request;
import spark.Response;

public class LoginService extends Service<UsuarioDAO> {

    public LoginService() {
        super(new UsuarioDAO(), "indexLogin.html");
    }

    public Object getPaginaLogin(Request requestm, Response response) {
        return construirPagina();
    }

    public Object postLogarUsuario(Request request, Response response) {
        return null;
    }
}
