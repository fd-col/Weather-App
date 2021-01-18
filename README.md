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
"date": "2021-01-06 12:00:00",
"visibility": 7000.0,
"cityname": "Venice",
"timeUNIX": 1609934400,
"wind": {
		"speed": 2.9
	}
 }
</code></pre>

Metadati relativi ai dati delle statistiche:
<pre><code>{
"visibilityStats": {
	"visibilityMax": 10000.0,
	"visibilityMin": 300.0,
	"visibilityAverage": 9575.0,
	"visibilityVariance": 2.226203125E7
	},
"speedStats": {
	"speedMax": 1.77,
	"speedMin": 0.82,
	"speedAverage": 1.8199999999999998,
	"speedVariance": 0.32124999999999987
	}
}
</code></pre>
In particolare:

 - "**date**" indica la data del giorno a cui fanno riferimento i dati riportati;
 - "**visibility**" indica il valore numerico che esprime la visibilità relativa a quella città in quel giorno;
 - "**cityname**" indica la città;
 - "**timeUnix**" indica la data espressa in secondi a partire dal 1° gennaio 1970;
 - "**speed**" indica il valore numerico che esprime la velocità del vento relativa a quella città in quel giorno;
 
 Considerando invece i metadati relativi alle statistiche:
 
 - "**visibilityMin**", "**visibilityMax**", "**visibilityAverage**" e "**visibilityVariance**" indicano rispettivamente il valore minimo,  massimo, la media e la varianza delle visibilità dei giorni presi in considerazione.
 - "**speedMin**", "**speedMax**", "**speedAverage**" e "**speedVariance**" indicano rispettivamente il valore minimo, massimo, la media e la varianza delle velocità del vento dei giorni presi in considerazione.
 
## Statistiche

 Le statistiche che la REST API restituisce si distinguono in:
 

 - statistiche relative ai dati storici (attraverso un file JSON vengono illustrati i valori descritti in precedenza, ovvero media, varianza, valori minimi e massimi). La città e il periodo da valutare vengono scelti dall'utente.

 -  statistiche relative ai dati futuri (attraverso un file JSON viene mostrato all'utente se le previsioni, in base ai dati raccolti, sono state attendibili oppure no). L'utente inoltre sceglierà la soglia di errore attraverso la quale si valuterà l'attendibilità.
  
## Rotte dell'applicazione

| Tipo di chiamata| Rotta | 
|--|--| 
| 1) **GET** |/metadata  | 
|2) **GET** | /save|
|3) **POST** |/weather/current|
|4) **POST**| /weather/historical|
|5) **POST**| /weather/forecast|
|6) **POST**| /stats/historical|
|7) **POST**| /stats/forecast|

 

 1. Restituisce all'utente i metadati
 2. E' servita per salvare i dati nel dataset
 3. 

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTgzMTI5MTQwNiw4OTI3NDAyNTcsLTEyOT
UwNDQ4NzBdfQ==
-->