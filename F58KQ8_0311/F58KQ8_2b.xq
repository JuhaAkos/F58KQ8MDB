xquery version "3.1";

for $rendelesek in fn:doc("beadando.xml")//tasakrendeles[@tasakID="2"]
return $rendelesek

