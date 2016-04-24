<?php

/*
 * All database connection variables
 */

define('DB_USER', "admin"); // db user
define('DB_PASSWORD', "pass4050"); // db password (mention your db password here)
define('DB_DATABASE', "farm2home"); // database name
define('DB_SERVER', "farm2homedb.crzxx3ekbzrf.us-west-2.rds.amazonaws.com:3306"); // db server

$con=mysqli_connect("farm2homedb.crzxx3ekbzrf.us-west-2.rds.amazonaws.com","admin","pass4050","farm2home") or die('Unable to connect');
?>
