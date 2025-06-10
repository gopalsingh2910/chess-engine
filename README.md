# Chess-engine
A Java-based chess engine featuring a graphical user interface.

## *Initial Game State:*
R Kn B K Q B Kn R<br/>
P &nbsp; P &nbsp; P P P P P &nbsp; P<br/>
. .  . . . . . . . . . . . . . . . <br/>
. .  . . . . . . . . . . . . . . . <br/>
. .  . . . . . . . . . . . . . . . <br/>
. .  . . . . . . . . . . . . . . . <br/>
P &nbsp; P &nbsp; P P P P P &nbsp; P<br/>
R Kn B K Q B Kn R<br/>


## *Features*
Graphical User Interface: A simple and intuitive GUI for user interaction.
Board Initialization: The chessboard initializes with pieces in the standard starting positions.
Piece Movement: Players can move pieces by clicking on the board's squares.
Players can choose to decide their opponent in the beginning whether it should be Human or Computer.
Once the game starts, players take turn one by one. On invalid moves, pop up message shows up displaying error while playing move.
Player can also set flip mode on to flip the board at each turn

## *Prerequisites*
 - Java Runtime Environment (JRE) or Java Development Kit (JDK);
 - An IDE like eclipse or IntelliJ(recommended) to work smoothly;

## *Algorithm*
The algorithm implemented in this chess code is [minimax algorithm](https://www.chessprogramming.org/Minimax) where the computer thinks all posiible game moves it can play and in turn generates moves which opponent can play in response to each move and like this the entire game tree is generated. The tree is generated to maximum depth of 4, which can be adjusted by deciding level of game. Diffcult the level, higher is game tree depth. At so called leaf node, the computer looks at game state and assigns some score to it by some heuristics.

As an example consider -

                                    ◯                
                                  /    \
                                ◯      ◯             - max
                                /        \
                              ◯          ◯           - min
                            /    \      /    \
                           ◯     ◯   ◯     ◯       - max
                          /  \   /  \    \   /  \
                        10   -6 28  4    -3 -1   27

                            
Now the computer at aternate levels takes maximum and minimum values from the children of some particular node and puts it in the node.
This continues till we have value at root node and one we have the value, we can trace which child node of root node gave that value and corresponding move will be played.
To optimise the search and reduce computation, an optimization techinque called [alpha beta pruning](https://www.chessprogramming.org/Alpha-Beta) is also used. In this technique, once we have value say 10 from one child at some level say level 5, and assume its parent will take maximum among all its children, so parent at level 4 will take value 10 or greater as one of its child have value 10. Suppose other child(say child 2) of this parent got value 5 from one of its child(level 6), now this child(child 2) will take value 5 or lesser, and since it knows that its parent already has some better value, this will not explore its other children. This way pruning the search tree.

                                /
                              ◯    level - 4
                            /   \
            child - 1 ->  10     ◯    <- child - 2 level - 5 
                                /   \
                  child - 3 -> 5     ◯
                                   /    \
                                  ◯     ◯
                                 /  \


## *Heuristics*
To evaluate game state, the numbe rof pieces which are left are used and the score asscoiated with each piece is added to overall score.
 - King - 100000 (Infinite)
 - Queen - 500
 - Bishop - 250
 - Knight - 200
 - Rook - 350
 - Pawn - 80
 - Check - 50
 - Checkmate - 100000 (Infinite)

score = (check score) * (# checks given) + Σ (# piece of one type) * (score of this piece type)

## *Running*
The main method is contained in src/gui/JChess.java.<br/>
The code begin from this main method.

## *Snippets*

![alt_img](https://github.com/gopalsingh2910/chess-engine/blob/main/images/Screenshot%202025-06-10%20212552.png)
![alt_img](https://github.com/gopalsingh2910/chess-engine/blob/main/images/Screenshot%202025-06-10%20212620.png)
![alt_img](https://github.com/gopalsingh2910/chess-engine/blob/main/images/Screenshot%202025-06-10%20213142.png)
![alt_img](https://github.com/gopalsingh2910/chess-engine/blob/main/images/Screenshot%202025-06-10%20213251.png)

