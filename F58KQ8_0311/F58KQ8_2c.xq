xquery version "3.1";

for $vevo in fn:doc("beadando.xml")/tasakgyartas/vevo[2]
return $vevo

