<html>
<head>
<title>Webservice PHP client</title>
<style type="text/css">
    table {
        margin: 8px;
    }
    #maindiv {
    margin: auto;
    width: 50%;
    /*border: 3px solid green;*/
    padding: 10px;
	/*border: 1px solid black;*/
	
    }
    #div2 {
    margin: auto;
    width: 50%;
    /*border: 3px solid green;*/
    padding: 10px;
	border: 1px solid black;
	
    }
    table, th, td {
    border: 1px solid black;
}
</style>
<div id="maindiv">
<form>
  Root VALUE:<br>
  <input type="text" name="rootvalue" value=""><br>
  <input type="submit" value="Submit">
</form>

<?php
$path_value=null;
if (isset($_GET['rootvalue'])) {
	$path_value =  $_GET['rootvalue'];
	


}
if ($path_value){
	echo "<p>The selected root value is:</p>";
	Print $path_value;
	$url_string = "http://localhost:8080/webservice?path=" . $path_value;
	echo "<p>The constructed url is:</p>";
	print $url_string;
	echo "<p>INDEX the root value here:</p>";

	// MAKE POST CALL: D:/github/wsmt-middleware/root
	// $postData = array("path" => "D:/github/wsmt-middleware/root");
	// $postParam = http_build_query($postData);
	// $headers = ['Content-Type: application/json; charset=utf-8'];
	// // $postData['path'] = "D:/github/wsmt-middleware/root";
	// $ch = curl_init();
	// curl_setopt($ch, CURLOPT_URL, $url_string);
	// curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
	// curl_setopt($ch, CURLOPT_POST, 1);
	// curl_setopt($ch, CURLOPT_POSTFIELDS, $postParam);
	// curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
	// $result = curl_exec($ch);
	// print $result;
	// curl_close($ch);
	// END OF POST CALL

/////////////////////////////////////////////////////////

	echo "<p>The result of the HTTP GET info is:</p>";

	$json = file_get_contents("http://localhost:8080/webservice/info");
	// Print $json;
	echo "<h3>File Table under root<hr>";
	$array = json_decode($json, true); // decode json
	// print_r($array[0]["path"]);
	echo '<table style="width:100%">
	  <tr>
	    <th>path</th>
	    <th>name</th>
	    <th>extension</th>
	    <th>size</th>
	    <th>md5</th>
	    <th>sha1</th>
	  </tr>';
	for($idx = 0; $idx < count($array); $idx++){
		$obj = (Array)$array[$idx];	
		echo '<tr>';
		echo '<td>', $obj["path"] ,'</td>';
		 echo '<td>', $obj["name"],'</td> ';
		 echo '<td>', $obj["fileExtension"],'</td>';
 		 echo '<td>', $obj["fileSize"],'</td>';
		 echo '<td>', $obj["md5_hash"],'</td>';
		 echo '<td>', $obj["sha1_hash"],'</td>';

	  echo '</tr>';
/////////////////////////////////////////////////////////
}
echo '</table>';

echo '
<p> Filtering </p>
<form>
  File name:<br>
  <input type="text" name="filename"><br>
  MD5 hash:<br>
  <input type="text" name="md5hash"><br>
  SHA1 hash:<br>
  <input type="text" name="sha1hash"><br>
  Bytes:<br> 
  <input type="text" name="bytes"><br>
  <input type="submit" value="Submit">
</form>';

} elseif (isset($_GET['filename']) || isset($_GET['md5hash']) || isset($_GET['sha1hash']) || isset($_GET['bytes'])) {
	$filenameFilterValue = $_GET['filename'];
	$md5FilterValue = $_GET['md5hash'];
	$sha1FilterValue = $_GET['sha1hash'];
	$bytesFilterValue = $_GET['bytes'];

	echo "<p>The result of the HTTP GET info is:</p>";

	$url = "http://localhost:8080/webservice/filter?name=" . $filenameFilterValue . "&md5=" .
			$md5FilterValue . "&sha1=" . $sha1FilterValue . "&bytes" . $bytesFilterValue;

	$json = file_get_contents($url);
	// Print $json;
	echo "<h3>File Table under root<hr>";
	$array = json_decode($json, true); // decode json
	// print_r($array[0]["path"]);
	echo '<table style="width:100%">
	  <tr>
	    <th>path</th>
	    <th>name</th>
	    <th>extension</th>
	    <th>size</th>
	    <th>md5</th>
	    <th>sha1</th>
	  </tr>';
	for($idx = 0; $idx < count($array); $idx++){
		$obj = (Array)$array[$idx];	
		echo '<tr>';
		echo '<td>', $obj["path"] ,'</td>';
		 echo '<td>', $obj["name"],'</td> ';
		 echo '<td>', $obj["fileExtension"],'</td>';
 		 echo '<td>', $obj["fileSize"],'</td>';
		 echo '<td>', $obj["md5_hash"],'</td>';
		 echo '<td>', $obj["sha1_hash"],'</td>';

	  echo '</tr>';
/////////////////////////////////////////////////////////
}
echo '</table>';

echo '
<p> Filtering </p>
<form>
  File name:<br>
  <input type="text" name="filename"><br>
  MD5 hash:<br>
  <input type="text" name="md5hash"><br>
  SHA1 hash:<br>
  <input type="text" name="sha1hash"><br>
  Bytes:<br> 
  <input type="text" name="bytes"><br>
  <input type="submit" value="Submit">
</form>';	
}
?>



</div>
<div id="div2">
</div>

</head>
</html>

