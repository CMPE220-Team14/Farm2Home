<?php

 $EventName = $_GET['EventName '];
 $FarmName = $_GET['FarmName'];
 $Category = $_GET['Category '];
 $Time= $_GET['Time '];
 $NoofPart = $_GET['NoofPart'];
 
if($EventName == '' || $ FarmName == '' || $ Category == '' || $ Time == '' ||  $ NoofPart == ''){
echo 'please fill all values';
}else
{
require_once('db_config.php');
$sql = "SELECT  *  FROM Event_Farm WHERE FarmName='$Farmname' AND EventName=$'EventName'";
$result = mysql_query($query,$db_link) or die('cannot get results!');

while($row = mysql_fetch_assoc($result)) {
	$events[$row['event_date']][] = $row;
}

 mysqli_close($con);
 }

?>
