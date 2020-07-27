# java-school

El consumo del API de la solución es como está planteado en el ejercicio:

http -f POST localhost:8080 url=www.google.maps.com 

HTTP/1.1 200 OK
Connection: keep-alive
Content-Type: application/json
Date: Mon, 27 Jul 2020 01:44:33 GMT
Transfer-Encoding: chunked

{
    "alias": "faQTT"
}

http -f GET localhost:8080/faQTT  

HTTP/1.1 301 Moved Permanently
Connection: keep-alive
Content-Length: 0
Date: Mon, 27 Jul 2020 01:44:54 GMT
Location: www.google.maps.com

