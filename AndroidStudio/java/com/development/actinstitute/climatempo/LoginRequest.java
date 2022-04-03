package com.development.actinstitute.climatempo;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pyrdna85 on 17/03/2022.
 */

public class LoginRequest extends StringRequest{
    private static final String rota = "https://hledificacoes.com.br/appMobile/login.php";
    private Map<String, String> parametros;
    public LoginRequest (String email, String pass, Response.Listener<String> listener){
        super(Request.Method.POST, rota, listener, null);
        parametros = new HashMap<>();
        parametros.put("email",email+"");
        parametros.put("pass",pass+"");
    }

    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }
}
