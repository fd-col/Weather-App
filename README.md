---


---

<h1 id="weather-app">Weather App</h1>
<p>L’applicazione presente nella repository è una REST API in grado di fornire informazioni meteorologiche precedentemente salvate in un dataset che contiene: dati storici, attuali e previsioni future, tutte relative ad un periodo prestabilito.</p>
<h3 id="dataset">Dataset</h3>
<p>Il dataset è costruito con due differenti modalità :</p>
<ul>
<li>dati-attuali &amp; dati-storici vengono già parsati come oggetti JSONArray tramite la rotta “/save” dell’app SpringBoot</li>
<li>previsioni-future sono raccolte dalla rispettiva  API di OpenWeather così come sono, mediante una chiamata al client Postman ( e successivamente parsate tramite l’app SpringBoot )</li>
</ul>
<h3 id="previsioni-dal-01012021-al-10012021">Previsioni dal 01/01/2021 al 10/01/2021</h3>
<ul>
<li>dati storici :	  01/01 - 05/01</li>
<li>dati attuali :	  06/01 - 10/01</li>
<li>previsioni future : 06/01 - 10/01</li>
</ul>
<h2 id="formato-dei-dati-restituiti">Formato dei dati restituiti</h2>
Metadati relativi ai dati del dataset :
<pre><code>{
 "date": 1609502400,
 "visibility": 10000,
 "cityname": "Trieste",
 "wind": {
    "speed": 0.06
    }
 }
</code></pre>

