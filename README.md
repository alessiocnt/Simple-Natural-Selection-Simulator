# Simple Natural Selection Simulator #

Il gruppo si pone come obiettivo quello di realizzare un simulatore che visualizzi l'evoluzione di una popolazione di individui nel tempo a seguito di adattamenti generazionali che possono colpire ogni individuo nel momento della nascita in maniera casuale.

Riteniamo che il progetto possa risultare utile per visualizzare come una data popolazione, che possiede determinate caratteristiche e proprietà iniziali si evolva nel tempo per meglio adattarsi all'ambiente in cui vive. 

L'idea prende spunto da un video che tratta di selezione naturale il cui link di seguito: 

https://www.youtube.com/watch?v=0ZGbIKd0XrM&feature=emb_logo 
 
Gli individui dovranno muoversi alla ricerca di cibo il quale si troverà disposto sull'ambiente.
Muovendosi essi perdono energie in base alle loro proprietà. 
Cibandosi essi acquistano una quantità di energia.
Le entità possono procreare.
L'entità figlia avrà le stesse caratteristiche del padre più una eventuale mutazione (casuale). 
La simulazione procede fino a quando tutte le creature si estinguono oppure infinitamente se le condizioni della popolazione e dell'ambiente lo permettono.
<br>
Doc and full relation available [here](relazioneSNSS.pdf)
<br>
#### Mutazioni degli individui OBBLIGATORIE: ####

* Aumento/diminuzione della dimensione (un individuo di dimensione maggiore potrà immagazzinare una quantità di cibo maggiore, ma sarà più lento)
* Aumento/diminuzione della velocità (un individuo più veloce consumerà più energie)

#### Funzionalità dell'ambiente OBBLIGATORIE: ####

* Possibilità di modificare in modo costante la quantità del cibo creato ogni mattina di giorno in giorno


#### Funzionalità minimali ritenute obbligatorie: ####

* Movimento degli oggetti all'interno dell'ambiente
* Gestione velocità, direzione e consumo delle energie di ogni individuo
* Mutazioni dell'individuo obbligatorie
* Funzionalità dell'ambiente obbligatorie
* Possibilità di stoppare la simulazione preventivamente


#### Funzionalità opzionali: ####

* Gestione delle collisioni tra individui
* Mutazione che permette ad un individuo di avere più figli contemporaneamente
* Temperatura, favorisce la sopravvivenza degli individui più adatti (un individuo più grande resisterà meglio al freddo ma soffrirà il caldo)
* Sensibilità vista, permette di andare nella direzione più efficiente per trovare maggior cibo
* Mutazione che consente di aumentare o diminuire il raggio in cui l'individuo è in grado di reperire e mangiare il cibo attorno a se
* Grafici sull'andamento della popolazione
* Gestione risoluzioni schermo


#### "Challenge" principali: ####

* Gestione dello stato e dell'aggiornamento della simulazione
* Gestire le mutazioni
* Gestione raccolta cibo durante il percorso di vita
* Progettare il simulatore in modo tale da renderlo facilmente espandibile con altre mutazioni o con altri elementi dell'ambiente.


#### Suddivisione del lavoro: ####

* Alessio Conti: Gestione delle entità di base,  gestione movimenti, aggiornamento simulazione. 
* Andrea Giulianelli: Gestione delle mutazioni dell'individuo, menu di avvio della simulazione.
* Simone Ceredi: Gestione dell'ambiente, gestione ciclo giornata, aggiornamento simulazione.

#### Contatti ####
Andrea: andrea.giulianelli4@studio.unibo.it <br>
Simone: simone.ceredi@studio.unibo.it <br>
Alessio: alessio.conti3@studio.unibo.it
