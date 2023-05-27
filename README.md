# Prova Finale di Ingegneria del Software - AA 2022-2023

![alt text](src/main/resources/it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/Publisher_material/Box%20noshadow%20280x280.png)

Implementazione del gioco da tavolo [My Shelfie](http://www.craniocreations.it/prodotto/my-shelfie/).

Il progetto consiste nell’implementazione di un sistema composto da un singolo server in grado di gestire più partite contemporaneamente e da 2 a 4 client per partita (uno per giocatore) che possono
partecipare ad una sola partita alla volta utilizzando il pattern MVC (Model-View-Controller). Il gameplay si può svolgere tramite linea di comando (CLI) o interfaccia grafica (GUI).

## Documentazione

### UML

Di seguito alcuni diagrammi uml descrittivi del progetto

- [Model](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/model.jpg)
- [Controller](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/controller.jpg)
- [Network](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/network.jpg)
- [Virtual Model](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/virtual_model.jpg)
- [Virtual View](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/virtual_view.jpg)
- [CLI](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/cli.jpg)
- [GUI](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/gui.jpg)
- [Enums](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/enum.jpg)
- [JSON readers](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/json_reader.jpg)

### JavaDoc

La seguente documentazione include una descrizione per la maggior parte delle classi e dei metodi utilizzati, segue le tecniche di documentazione di Java e può essere consultata al seguente
indirizzo: [Javadoc](https://sigcatta.github.io/prog-ingsw-The_Compiler_Coalition/)

### Coverage report

Al seguente link è possibile consultare il report della coverage dei test effettuati con Junit: [TODO](https://...)

### Librerie e Plugins

| Libreria/Plugin | Descrizione                                              |
|-----------------|----------------------------------------------------------|
| __Maven__       | Strumento di automazione della compilazione.             |
| __JavaFx__      | Libreria grafica per realizzare interfacce utente.       |
| __JUnit__       | Framework di unit testing.                               |
| __PlantUML__    | Strumento per la generazione di diagrammi UML.           |
| __Docker__      | Strumento di creazione ed esecuzione di container linux. |

## Funzionalità

### Funzionalità Sviluppate

- Regole Complete
- CLI
- GUI
- Socket
- 2 FA (Funzionalità Avanzate):
    - __Chat:__ Client e server devono offrire la possibilità ai giocatori coinvolti in una partita di chattare tra di loro, inviando messaggi (testuali) indirizzati a tutti i giocatori della partita
      o a un singolo giocatore.
    - __Partite Multiple:__ Realizzare il server in modo che possa gestire più partite contemporaneamente.

## Client

Di seguito è fornito il [jar precompilato](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/my-shelfie-client.jar) necessario per utilizzare il client. <br />
Per compilare il jar autonomamente, posizionarsi nella root del progetto e lanciare il comando:

```
mvn clean install assembly:single
```

Il jar compilato verrà posizionati all'interno della cartella ```target/``` con il nome ```my-shelfie-client-jar-with-dependencies.jar```. <br />
Si noti che questo progetto richiede una versione di Java 11 o superiore per essere eseguito correttamente.

#### CLI

Per lanciare My Shelfie Client CLI digitare da terminale il comando:

```
java -jar my-shelfie-client.jar --cli
```

oppure

```
java -jar my-shelfie-client.jar -c
```

#### GUI

Per poter lanciare la modalità GUI:
digitare da terminale il comando: ```java -jar my-shelfie-client.jar``` <br />
oppure
effettuare doppio click sull'eseguibile ```my-shelfie-client.jar```

### Server

Il server è stato realizzato con docker, è quindi necessario scaricare l'immagine dalla repository di [DockerHub](https://hub.docker.com/repository/docker/mrcatta/my-shelfie-server/general), per fare
ciò è necessario eseguire il comando:

```
docker pull mrcatta/my-shelfie-server:latest
```

successivamente basterà far partire il container:

```
docker run -p 28888:28888 mrcatta/my-shelfie-server 
```

É importante notare che il container utilizza la porta 28888 e i client sono impostati per inviare messaggi alla porta 28888. (il parametro -p 28888:28888 mappa la porta 28888 del computer utilizzato
alla 28888 del container)

## Componenti del gruppo
