<?php

 $farmname = $_GET['farmname'];
 $password = $_GET['password'];
 $email = $_GET['email'];
 $phoneno = $_GET['phoneno'];
 $address = $_GET['address'];
 
 if($farmname == '' || $password == '' || $email == '' || $phoneno == '' ||  $address == ''){
 echo 'please fill all values';
 }else{
 require_once('db_config.php');
 $sql = "SELECT * FROM Farm_Login WHERE FarmName='$farmname' OR Email='$email'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 if(isset($check)){
 echo 'username or email already exist';
 }else{ 
 $sql = "INSERT INTO Farm_Login (FarmName,Email,password,Phone_number,FarmAddress) VALUES('$farmname','$email','$password','$phoneno','$address')";
 if(mysqli_query($con,$sql)){
 echo 'successfully registered';
 }else{
 echo 'oops! Please try again!';
 }
 }
 mysqli_close($con);
 }

?>
