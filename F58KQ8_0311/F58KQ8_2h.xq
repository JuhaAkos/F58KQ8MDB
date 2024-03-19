xquery version "3.1";

for $vevo in fn:doc("beadando.xml")//vevo[@*]
return $vevo

