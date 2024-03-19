xquery version "3.1";

for $logos in fn:doc("beadando.xml")//elkeszites | //tervezo
return $logos

