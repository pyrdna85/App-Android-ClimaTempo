<%-- 
    Document   : index
    Created on : 21 de mar. de 2022, 21:06:35
    Author     : pyrdna85
    Professor  : 
--%>

<%@page import="br.uninove.climatempo.api.HTTP"%>
<%@page import="br.uninove.model.Clima"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    Clima clima = null;
    String cidade ="";
    if(request.getParameter("cidade") != null){
        cidade = request.getParameter("cidade");        
        clima = HTTP.getClima(cidade);
    
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" type="image/x-icon" href="./img/favicon.ico">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <style>
            .clima-card{
                padding: 100px 0 0 0;
                width: 400px;
                margin: 0 auto;
                
            }
        </style>
    </head>
    <body>
        
        <nav class="navbar navbar-light bg-light">
            <a class="navbar-brand" href="#">
              <img src="img/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">              
                Clima Tempo
              </a>
            
            
            <form class="form-inline" method="post">
              <input class="form-control mr-sm-2" type="search" name="cidade" placeholder="Pesquisar" aria-label="Pesquisar" required>
              <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Pesquisar</button>
            </form>
          </nav>
          <!-- card para o clima -->
          <% if(clima != null){%>
          <div class="container">
              <div class="row">
                  <div class="clima-card">
                      <div class="card">
                          <div class="card-body">
                              <div class="text-center">
                                <img src="http://openweathermap.org/img/wn/<%=clima.getWeather().get(0).getIcon()%>@2x.png">
                                <h3>Cidade: <%= clima.getName() + "," + clima.getSys().getCountry()%></h3>
                              </div>
                          
                          <hr>
                          <div class="text-capitalize">
                                <p><b>Agora: </b><%=clima.getWeather().get(0).getDescription()%></p>
                          </div>
                      </div>
              </div>
          </div>
          <% } %>
          
    </body>
</html>
