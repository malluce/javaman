# javaman

This repository contains the source code of a bomberman game written entirely in Java using Java Swing. It was created as a side project to practice design patterns and (Java) programming in general.

## Getting Started

These instructions will get you a copy of the project for playing or extending the game.

### Prerequisites

* You need an installation of [maven](https://maven.apache.org/) (standalone or for example as Eclipse Add-On) to play or extend the game.

### Install instructions for playing or extending the game

* Get the project on your machine by cloning the repository.

```
git clone https://github.com/malluce/javaman
```

#### Play the game

* Use maven to create an executable .jar file. 

```
cd javaman
mvn package
```

* Start the .jar file which will be located in target/

* Let the UI guide you. And have fun!

* Default controls 1: WASD for movement, SPACE to plant a bomb, ESCAPE to pause/unpause

* Default controls 2: ←↑→↓ for movement, NUMPAD_0 to plant a bomb, ENTER to pause/unpause

#### Extend the game
 * Tiles: You can create your own custom tiles! Custom tiles can be created by extending the abstract class `AbstractTile`.
 * Arenas: The game can be extended by adding your own arenas. Those can also contain your own tiles created before! To create your own arena, implement the `ArenaI` interface. Note that you have to add the annotation `@MetaInfServices(ArenaI.class)`to your arena class and call `mvn package` again to add the arena to the game. If done accordingly, the arena can be selected in the game configuration frame.
