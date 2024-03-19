xquery version "3.1";


for $nyomtatogepek in fn:doc("beadando.xml")/tasakgyartas/nyomtatogep
return $nyomtatogepek
