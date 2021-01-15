
<h1 id="weather-app">Weather App</h1>
<p>L’applicazione presente nella repository è una REST API in grado di fornire informazioni meteorologiche precedentemente salvate in un dataset che contiene: dati storici, attuali e previsioni future, tutte relative ad un periodo prestabilito.</p>

### Dataset
Il dataset è costruito con due differenti modalità :
 - dati-attuali & dati-storici vengono già parsati come oggetti JSONArray tramite la rotta “/save” dell’app SpringBoot
 - previsioni-future sono raccolte dalla rispettiva  API di OpenWeather così come sono, mediante una chiamata al client Postman ( e successivamente parsate tramite l’app SpringBoot )
 
### Previsioni dal 01/01/2021 al 10/01/2021
 - dati storici :	  01/01 - 05/01
 - dati attuali :	  06/01 - 10/01
 - previsioni future : 06/01 - 10/01

<h2 id="formato-dei-dati-restituiti">Formato dei dati restituiti</h2>
Metadati ralat
<pre><code>{
 "date": 1609502400,
 "visibility": 10000,
 "cityname": "Trieste",
 "wind": {
    "speed": 0.06
    }
 }
</code></pre>

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTI5ODcwNjQ4MCwtMTU1NzQ0MTcyNSwyMj
MzMjI0MiwxMzE0Nzk0MDAxXX0=
-->