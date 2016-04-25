<?php

 $OrderNo = $_GET['OrderNo '];
 $Productname = $_GET['Productname'];
 $FarmName = $_GET['FarmName '];
 $price= $_GET['price '];
 $Quantity = $_GET['Quantity'];
 
if($OrderNo == '' || $ Productname == '' || $ FarmName == '' || $ price == '' ||  $ Quantity == ''){
echo 'please fill all values';
}else
{
require_once('db_config.php');
$sql = "SELECT  *  FROM Inventory WHERE OrderNo='$OrderNo' AND Productname=$'Productname'";
$result = mysql_query($query,$db_link) or die('cannot get results!');

if(isset($check)){
			//displaying success 
			echo "success";
		}else{
			//displaying failure
			echo "failure";
		}
		mysqli_close($con);
	}
	
	?>