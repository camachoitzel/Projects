#ifndef GAMEBOARD_H
#define GAMEBOARD_H

#include "Rectangle.h"
#include "Color.h"
#include "Texture.h"
#include <GL/gl.h>

class GameBoard{

private:
    Rectangle** gameBoard;
    Texture* xSymbol;
    Texture* oSymbol;
    int size;
    char currentPlayer;
    char** board;

    bool hasWon;

    void drawSymbol(Texture* symbol, float x, float y){
        symbol->setX(x);
        symbol->setY(y);
        symbol->draw();
    }

    bool checkRow(int row) const{
        for(int col = 1; col < size; col++){
            if(board [row][col] != board [row][0] || board [row][0] == ' '){
                return false;
            }
            
        }
        return true;
    }

    bool checkColumn(int col) const{
        for(int row = 1; row < size; row++){
            if(board [row][col] != board [0][col] || board [0][col] == ' '){
                return false;
            }
            
        }
        return true;
    }

    bool checkDiag() const{
        bool diagonal = true;
        bool antiDiagonal = true;

        for(int i = 1; i < size; i++){
            if(board [i][i] != board[0][0] || board [0][0] == ' '){
                diagonal = false;
            }
            if(board [i][size -1 - i] != board [0][size - 1] || board[0][size - 1] == ' '){
                antiDiagonal = false;
            }
        }

        return diagonal || antiDiagonal;
    }

public:

    GameBoard(int boardSize){

        size = boardSize;
        currentPlayer = 'X';

        gameBoard = new Rectangle*[size];
        board = new char* [size];

        for(int i = 0; i < size; i++){
            gameBoard[i] = new Rectangle[size];
            board[i] = new char [size];
            for(int j = 0; j < size; j++){
                float x = -1.0 + j * (2.0 / size);
                float y = 1.0 - i * (2.0 / size);
                gameBoard [i][j] = Rectangle(x, y, 2.0/size, 2.0/size, Color(0.0,0.0,1.0));

                board [i][j] = ' ';
            }
        }
        xSymbol = new Texture(0.0, 0.0, 2.0/size, 2.0/size, "assets/TicTacToe_X.png");
        oSymbol = new Texture(0.0, 0.0, 2.0/size, 2.0/size, "assets/TicTacToe_O.png");
        hasWon = false; 
        
    }

    void draw(){

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                gameBoard [i][j].draw();
                if(board [i][j] == 'X'){
                    drawSymbol(xSymbol, gameBoard[i][j].getX(), gameBoard[i][j].getY());
                }
                else if(board [i][j] == 'O'){
                    drawSymbol(oSymbol, gameBoard[i][j].getX(), gameBoard[i][j].getY());
                }
            }
        }
        
    }

    
    void handleMouseClick(float mx, float my){
     for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(gameBoard [i][j].contains(mx, my) && board[i][j] == ' '){
                    gameBoard [i][j].select();
                    board [i][j] = currentPlayer;

                    //check is someone won
                    if(checkWin()){
                        return;
                    }
                    
                    return;
                }
            }
        }   
    }

    bool checkWin(){
        if(hasWon){
            return true;
        }

        for(int i = 0; i < size; i++){
            if(checkRow(i) || checkColumn(i)){
                hasWon = true;
                return true;
            }
        }
        
        if(checkDiag()){
            hasWon = true;
            return true;
        }

        return false;
    }

    char getCurrentPlayer(){
        return currentPlayer;
    }

    void setCurrentPlayer(char currentPlayer){
        this->currentPlayer = currentPlayer;
    }

    bool aiTurn(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board [i][j] == ' '){
                    board[i][j] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    bool isDraw(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board [i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    ~ GameBoard(){
        for(int i = 0; i < size; i++){
            delete[] gameBoard[i];
            delete [] board[i];
        }
        delete[] gameBoard;
        delete[] board;
        delete  xSymbol;
        delete oSymbol;
}


};





#endif