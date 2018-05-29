<?php
$db = new mysqli('matt-smith-v4.ics.uci.edu', 'inf124db057', 'wRd8MJP2XGWa', 'inf124db057');

if($db->connect_errno > 0){
    die('Unable to connect to database [' . $db->connect_error . ']');
}

$sql = "SELECT * FROM `Orders` WHERE oid = " . htmlspecialchars($_GET["oid"]);

if(!$result = $db->query($sql)){
    die('There was an error running the query [' . $db->error . ']');
}
$pid = 0;
while($row = $result->fetch_assoc()){
    echo '    <p>First Name: ' . $row['firstName'] . '</p>';
    echo '    <p>Last Name: ' . $row['lastName'] . '</p>';
    echo '    <p>Address: ' . $row['addr'] . '</p>';
    echo '    <p>City: ' . $row['city'] . '</p>';
    echo '    <p>Zip Code: ' . $row['zip'] . '</p>';
    echo '    <p>Quantity: ' . $row['quantity'] . '</p>';
    $pid = $row['pid'];

}
mysqli_close($db);

$db = new mysqli('matt-smith-v4.ics.uci.edu', 'inf124db057', 'wRd8MJP2XGWa', 'inf124db057');

if($db->connect_errno > 0){
    die('Unable to connect to database [' . $db->connect_error . ']');
}

$sql = "SELECT * FROM `Products` WHERE pid = " . $pid;

if(!$result = $db->query($sql)){
    die('There was an error running the query [' . $db->error . ']');
}

while($row = $result->fetch_assoc()){
    echo '    <p>Product: ' . $row['name'] . '</p>';
    echo '    <p>Price: $' . $row['price'] . '</p>';
    echo '    <p>Description:' . $row['description'] . '</p>';


}
mysqli_close($db);

?>
