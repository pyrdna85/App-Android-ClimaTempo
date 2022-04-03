//**
Adaptado por Rodrigo Andrade em 29/03/2022
*/
<?php
    //servidor, usuario, senha e bando de dados
    $con = mysqli_connect("localhost","hledific_pyrdna85","hgy^yKVA5Ov30%nx9LA","hledific_appMobile");

    $email  = $_POST["email"];
    $pass   = $_POST["pass"];
    
    //Verificando se o dado digitado já existe no banco de dados.
    $verifica = mysqli_query($con, "SELECT email FROM user WHERE email = '$email'");
    
    
    
    //condição a partir de contagem de colunas
    if(mysqli_num_rows($verifica) > 0){
        while($data = mysqli_fetch_assoc($verifica)){
            
            $response["success"] = false;
            
        }
    // caso não exista nenhum usuário igual ao digitado, o SQL concluí o cadastro.     
    }else{
        if($statement = mysqli_prepare($con, "INSERT INTO user (email, password) VALUES ('$email', '$pass')"));
            mysqli_execute($statement);
            $response = array();
            $response["success"] = true;
            
    }
    echo json_encode($response);
?>