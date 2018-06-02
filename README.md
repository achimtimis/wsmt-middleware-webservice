Problema
Fisiere: Crearea unei DB cu informatii despre fisierele aflatee sub o radacina:
* Cautarea de fisiere dupa parti din nume
* Cautarea de fisiere dupa parti din continut text sau binar (furnizat prin valori hexa ale succesiunilor de octeti).
* Cautarea de fisiere duble (cu acelasi continut de exemplu prin MD5, SHA1 etc.)


Technologies:
1. Spring Boot Java backend service communicating with Postgres DB
2. PHP script consumer running under XAMPP


Java backend service
====================

Features:
1. Indexing all files&folders under a root context
2. Implemenating the searching and filtering of the files information from the DB




PHP consumer
============


