# Dynamic Programming Hopscotch
## Guidelines and Rules
    * Squares are label from 0 to n, and player starts on square i = n
    * Goal is to reach i = 0 with the lowest score possible
## Rules
    1. On any square, the player can move forward by one square; from square i to i-1.  
        - Adds 1 to the score
    2. If the number of the square is a prime greater than 10, you may move forward by a number equal to the  
    digit of the number. 
        - Adds 3 to the score
    3. If the square is a multiple of 11, the player may move forward by the sum of the digits contained in that
    square. This adds 4 to score
        - Adds 4 to the score
        - CurrentSquare = 22, then CurrentSquare = 22 - (2+2) = 18
    4. If the square is a multiple of 7, the player may move forward by 4 spaces
        - Add 2 to the score.


