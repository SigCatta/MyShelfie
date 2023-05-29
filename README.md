# Prova Finale di Ingegneria del Software - AA 2022-2023

![alt text](src/main/resources/it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/Publisher_material/Box%20noshadow%20280x280.png)

Implementation of the board game [My Shelfie](http://www.craniocreations.it/prodotto/my-shelfie/).
The project consists in the implementation of a single-server system capable of simultaneously managing multiple games made up of 2 to 4 clients (one for each player). Each player can choose to play using either the command line interface (later referred to as CLI) or the graphical user interface (later referred to as GUI). The project fully implements the MVC (Model-View-Controller) design pattern, further information about the realization of the network can be found [here](deliveries/NetworkDocumentation/NetworkDocumentation.md).

## Documentation

### UML

Below some of the more significant UML diagrams:

- [Model](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/model.jpg)
- [Controller](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/controller.jpg)
- [Network](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/network.jpg)
- [Virtual Model](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/virtual_model.jpg)
- [Virtual View](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/virtual_view.jpg)
- [CLI](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/cli.jpg)
- [GUI](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/UML/gui.jpg)

### JavaDoc

The following documentation includes a description of most of the classes and methods: [Javadoc](https://sigcatta.github.io/prog-ingsw-The_Compiler_Coalition/)

### Line coverage report

The unit test present in the [test](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/tree/main/src/test/java/it/polimi/ingsw) directory provide a model line coverage of 92% and 39% overall line coverage

### Used tools

| Libreria/Plugin | Descrizione                                      |
|-----------------|--------------------------------------------------|
| __Maven__       | Build automation tool.                           |
| __JavaFx__      | Set of packages used to develop the GUI.         |
| __JUnit__       | Testing framework.                               |
| __PlantUML__    | UML generation tool.                             |
| __Docker__      | Platform used to build and run linux containers. |

## Features

### Developed Features

- Complete Rules
- CLI
- GUI
- Socket
- Docker
- 2 FA (Advanced Features):
    - __Chat:__ Client and server offer the provide the players with the ability to exchange messages to either all players or a single player.
    - __Multiple Games:__ The server must be able to simultaneously manage multiple games.

## Client

The [precompiled jar](https://github.com/SigCatta/prog-ingsw-The_Compiler_Coalition/blob/main/deliveries/my-shelfie-client.jar) can be used to run the client application. <br />
To indipendently create a client application jar, execute the following command in the project root:

```
mvn clean install assembly:single
```

The compiled jar will be found in the ```target/``` under the name ```my-shelfie-client-jar-with-dependencies.jar```. <br />

#### CLI

To run the client's CLI for My Shelfie, open the terminal and run either ```java -jar my-shelfie-client.jar --cli``` or ```java -jar my-shelfie-client.jar -c```.

#### GUI

To run the client's GUI for My Shelfie either run the following command ```java -jar my-shelfie-client.jar``` or double click on  ```my-shelfie-client.jar```.

### Server

The server application can be run with docker, the image can be found at the following [DockerHub repository](https://hub.docker.com/repository/docker/mrcatta/my-shelfie-server/general). <br />
to run the server, open the terminal and pull the image from the repository using:

```
docker pull mrcatta/my-shelfie-server:latest
```

the start the container:

```
docker run -p 28888:28888 mrcatta/my-shelfie-server 
```

Note that the container uses port 28888 and the client application is the to send messages to port 28888 (-p 28888:28888 maps your machine's 28888 port to the container's 28888 port)

## Group members

- [__Luca Cattani__](https://github.com/SigCatta)
- [__Simone Lucca__](https://github.com/SimoneLucca2)
- [__Arianna Zerbini__](https://github.com/azerbini01)
