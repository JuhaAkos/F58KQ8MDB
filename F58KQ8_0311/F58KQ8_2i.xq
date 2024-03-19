xquery version "3.1";

for $papirminoseg in fn:doc("beadando.xml")//tasakrendeles[mennyiseg>80000]/papirmin
return $papirminoseg



