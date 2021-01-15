
<h1 id="weather-app">Weather App</h1>
<p>L’applicazione presente nella repository è una REST API in grado di fornire informazioni meteorologiche precedentemente salvate in un dataset che contiene: dati storici, attuali e previsioni future, tutte relative ad un periodo prestabilito.</p>

## Dataset
<![endif]-->

Il dataset è costruito con due differenti modalità:

<![if !supportLists]>- <![endif]>dati-attuali & dati-storici vengono già parsati come degli oggetti JSONArray tramite la rotta “/save” dell’app SpringBoot

<![if !supportLists]>- <![endif]>previsioni-future sono ritornate così come sono, attraverso una chiamata al client Postman, e successivamente parsate tramite l’app SpringBoot


<h3 id="formato-dei-dati-restituiti">Formato dei dati restituiti</h3>
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
eyJoaXN0b3J5IjpbLTE2OTg5NDg1NjUsLTE1NTc0NDE3MjUsMj
IzMzIyNDIsMTMxNDc5NDAwMV19
-->