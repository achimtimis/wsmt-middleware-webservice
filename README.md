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




Middleware
==========
1. The model file must implement serializable and be identical between server and client
2. Since java 9, java.corba is not in the default modules. Must add --add-modules java.corba to the program arguments
3. ?? add also -Djava.rmi.server.useCodebaseOnly=false.
4. run start rmiregistry before all