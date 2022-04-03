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
        <title>Clima Tempo</title>
        <link rel="icon" type="image/x-icon" href="./img/favicon.ico">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <style>
            .campo{
             width: 280px;
             height: 40px;
             border: 3px solid #0bb2ff;
            }
            .espaco{
                line-height: 20px ;
                
            }

        </style>
    </head>
    <body>       
        <nav class="navbar navbar-light bg-light">
            <a class="navbar-brand mb-2" href="#">
              <img src="img/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">              
                Clima Tempo
            </a>           
            <div class="d-flex justify-content-between mb-2" >
              <form class="form-inline" method="post">
                <input class="form-control mr-sm-2" type="search" name="cidade" placeholder="Local/País" aria-label="Pesquisar" required>                    
                <button class="btn btn-outline-info my-2 my-sm-2 espaco" type="submit">Pesquisar</button>
              </form>
            </div>
            
        </nav>
        
          <!-- card para o clima -->
          <% if(clima != null){%>
          
          
          <div class="container mt-5">
            
            <div class="bg-white border rounded mt-2">
                <div class="px-3 mt-3 d-flex flex-row justify-content-center"> 
                    <span><p align="center">
                    <img src="http://openweathermap.org/img/wn/<%=clima.getWeather().get(0).getIcon()%>@2x.png"></p>
                    <h5>Cidade: <%= clima.getName() + "," + clima.getSys().getCountry()%></h5></span>
                    
                </div>
                <hr>
                <div class="d-flex flex-row justify-content-center">
                    <div class="mr-4">
                        <div class="d-flex flex-row"> <img src="https://i.imgur.com/fExp9fE.png" width="50">
                            <div class="ml-2"> <span class="d-block font-weight-bold">Latitude</span> <span><%=clima.getCoord().getLat()%></span> </div>
                        </div>                        
                    </div>
                    <div class="mr-4">
                        <div class="d-flex flex-row"> <img src="https://i.imgur.com/fExp9fE.png" width="50">
                            <div class="ml-2"> <span class="d-block font-weight-bold">Longitude</span> <span><%=clima.getCoord().getLon()%></span> </div>
                        </div>  
                        
                    </div>
                    
                </div><br>
                
            </div>
            <div class="row mb-5">
                <div class="col-md-6 mt-3">
                    <div class="bg-white p-3 rounded border mb-2"><br>
                        <h6><p><img src="icons\1x\temperatura.png">&nbsp; <b> Temperatura:</b>                            
                            <b><font color="#0f76ff">Min:</font></b> <%=clima.getMain().getTempMin()%> <b> <font color="#ff7231">Max:</font></b> <%=clima.getMain().getTempMax()%></p></h6>                       
                        
                     </div>
                </div>
                <div class="col-md-6 mt-3">
                    <div class="bg-white p-3 rounded border"><br>
                        <h6><p><img src="icons\1x\ar.png">&nbsp; <b> Humidade do ar: </b><%=clima.getMain().getHumidity()%>%</p></h6>
                        
                    </div>
                </div>
                <div class="col-md-6 mt-3">
                    <div class="bg-white p-3 rounded border"><br>
                        <h6><p><img src="icons\1x\velocidade.png">&nbsp; <b> Velocidade do vento: </b><%=clima.getWind().getSpeed()%> m/s</p></h6>
                        
                    </div>
                </div>
                <div class="col-md-6 mt-3">
                    <div class="bg-white p-3 rounded border"><br>
                        <h6><p><img src="icons\1x\nuvens.png">&nbsp; <b> Nuvens: </b><%=clima.getClouds().getAll()%></p></h6>
                        
                    </div>
                </div>
                <div class="col-md-6 mt-3">
                    <div class="bg-white p-3 rounded border"><br>
                        <h6><p><img src="icons\1x\pressao.png">&nbsp; <b> Pressão: </b><%=clima.getMain().getPressure()%></p></h6>
                        
                    </div>
                </div>
                <div class="col-md-6 mt-3">
                    <div class="bg-white p-3 rounded border"><br>
                        <h6><p><img src="icons\1x\sensacao.png">&nbsp; <b> Sensação: </b><%=clima.getMain().getFeelsLike()%></p></h6>
                        
                    </div>
                </div>
                
            </div>
        </div>
    </body>
    <% } %>
</html>
