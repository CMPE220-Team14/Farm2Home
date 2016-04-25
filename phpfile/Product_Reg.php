<?php

 $Productname = $_GET['Productname'];
 $Category = $_GET['Category'];
 $PricePerLb = $_GET['PricePerLb'];
 $Quantity_in_Lb = $_GET['Quantity_in_Lb'];
 $ FarmName  = $_GET[' FarmName '];
 
 if($Productname == '' ||  $Category == '' ||   $PricePerLb== '' ||  $Quantity_in_Lb== '' ||  FarmName== ''){
 echo 'please fill all values';
 }else{
 require_once('db_config.php');
 $sql = "SELECT * FROM Product_Reg WHERE FarmName='$farmname' OR Productname='$Productname'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
// if(isset($check)){
// echo 'username or email already exist';
 //}else{ 
 $sql = "INSERT INTO Product_Reg(Productname  ,Category,PricePerLb,Quantity_in_Lb,FarmName) VALUES('$Productname','$PricePerLb','$Quantity_in_Lb','$phoneno','$FarmName')";
 if(mysqli_query($con,$sql)){
 echo 'successfully added product';
 }else{
 echo 'oops! Please try again!';
 }
 }
 mysqli_close($con);
 }

?>
