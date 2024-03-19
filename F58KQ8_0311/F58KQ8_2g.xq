xquery version "3.1";

for $szerzodesek in fn:doc("beadando.xml")//szerzodes[position()<3]
return $szerzodesek

