# java-school

### El consumo del API de la solución es como está planteado en el ejercicio:

http -f POST localhost:8080 url=www.google.maps.com 

HTTP/1.1 200 OK
Connection: keep-alive
Content-Type: application/json
Date: Mon, 27 Jul 2020 01:44:33 GMT
Transfer-Encoding: chunked
```json
{
    "alias": "faQTT"
}
```

http -f GET localhost:8080/faQTT  

HTTP/1.1 301 Moved Permanently
Connection: keep-alive
Content-Length: 0
Date: Mon, 27 Jul 2020 01:44:54 GMT
Location: www.google.maps.com

En cuanto a la estructuración de la solución, se pretende mantener un orden en cuanto a la 
funcionalidad de cada clase. 

El paquete 'controllers' contiene las clases que están de cara a lo que recibe el cliente final,
controlador para los servicios web REST y una clase para indicar como manejar la excepción específica
creada en la solución para contemplar posibles errores considerados y no mostrar largos stacktrace en
la salida estándar.

El paquete 'data' contiene la abstracción de repositorio y una implementación sencilla para este caso.
Soporta la funcionalidad de almacenar el mapeo de shortUrl => Url. El contenedor final es un ConcurrentHashMap,
con la intención de garantizar un alto nivel de concurrencia.

El paquete 'shorteners' contiene las implementaciones específicas de cada uno de los posibles algoritmos
a emplear en el acortamiento de las Urls así como algunos utilitarios y validaciones asociadas a los mismos.
Los algoritmos en si fueron implementados considerando una clase base junto a la cual implementan una
cadena de responsabilidades(excluyente), de esa forma se abstrae el uso directo de los algoritmos en
el servicio que los consuma, siendo fácil la modificación de los mismos o la inclusión de otros. Los
algoritmos empleados para la creación de los acortadores están implementados de manera determinista, de
no existir problema de concurrencia al momento de crear los shortLinks, los valores de los shortLinks 
a partir de un mismo URL original, serán los mismos siempre. La base de la contrucción de los shortLinks
es el algoritmo MD5. Las validaciones fueron consideradas para cuando se pretende recuperara links 
originales, tomando en cuenta que deben cumplir las condiciones de al menos un tipo de shortLink generado
por esta solución.

El paquete 'error' contiene la clase excepción incluida para el manejo de posibles errores identificados.
 
El paquete 'services' contiene la 