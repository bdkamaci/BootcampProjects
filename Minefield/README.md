# MineSweeper Game

## Overview
This is a simple implementation of the classic MineSweeper game in Java. The game is played in the console/terminal and allows players to uncover cells on a grid while avoiding hidden mines.

## Features
- Dynamic grid size based on user input
- Random placement of mines
- User-friendly interface with input validation
- Win and loss conditions detection
- Option to play again after winning or losing

## Usage
1. Open the Main.java file.
2. Update the `rowNumber` and `colNumber` parameters in the main method according to the size you want to play the game.
3. Compile the code:
   ```bash
   javac Main.java
4. Run the game:
   ```bash
   java Main
5. Follow the prompts to enter row and column numbers for the game grid.

## Classes
1. MineSweeper
- Contains the core logic and functionalities of the MineSweeper game.
- Handles game initialization, board management, mine placement, user input, game state tracking, and more.
2. Main
- Contains the main method to start the game.
- Creates an instance of the MineSweeper class and starts the game.

## How to Play?
1. Run the game as instructed above. 
2. Enter the row and column numbers to uncover cells on the grid. Avoid using caracters, only use numbers. 
3. Avoid uncovering cells with mines. 
4. Use revealed cell numbers to deduce the locations of mines. 
5. Uncover all non-mine cells to win the game.

## Licence
This project is created by Burcu Doga KAMACI and is licensed under the MIT License. You are free to modify and distribute the code for personal or commercial use. See the LICENSE file for more details.