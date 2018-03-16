<?php
$servername = "localhost";
$username = "kennedy";
$password = "kennedy";
$dbname = "csv_db";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT * FROM `tblname` where `Link State` = 'UNKNOWN' and `Network Platform` = 'WIMAX'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "id: " . $row["SNID"]. " - Service Code: " . $row["Circuit ID"]. " - " . $row["Network Platform"]. "\n";
        $newSC = strtolower($row["Circuit ID"]);
        $newSC = trim($newSC);
        $id = $row["SNID"];

        $sqll = "UPDATE `tblname` SET `Circuit ID` = '$newSC' WHERE `SNID` = $id";

        if ($conn->query($sqll) === TRUE) {
        } else {
            echo "Error updating record: " . $conn->error."\n";
        }
    }
} else {
    echo "0 results";
}

$conn->close();
?>