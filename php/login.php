<?php

    //servidor, usuario, senha e bando de dados
    $con = mysqli_connect("localhost","hledific_pyrdna85","hgy^yKVA5Ov30%nx9LA","hledific_appMobile");

    $email  = $_POST["email"];
    $pass   = $_POST["pass"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE email = '$email' AND password = '$pass'");
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["email"] = $email;
        $response["pass"] = $pass;        
    }

    echo json_encode($response);
?>