<?php

 $username = $_GET['username'];
 $password = $_GET['password'];
 $email = $_GET['email'];
 $phoneno = $_GET['phoneno'];
 $address = $_GET['address'];
 
 if($username == '' || $password == '' || $email == '' || $phoneno == '' ||  $address == ''){
 echo 'please fill all values';
 }else{
 require_once('db_config.php');
 $sql = "SELECT * FROM User_Login WHERE UserName='$username' OR Email='$email'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 if(isset($check)){
 echo 'username or email already exist';
 }else{ 
 $sql = "INSERT INTO User_Login (Username,Email,password,Phone_number,Address) VALUES('$username','$email','$password','$phoneno','$address')";
 if(mysqli_query($con,$sql)){
 echo 'successfully registered';
 }else{
 echo 'oops! Please try again!';
 }
 }
 mysqli_close($con);
 }

?>
