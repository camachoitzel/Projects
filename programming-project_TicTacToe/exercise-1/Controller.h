#ifndef CONTROLLER_H
#define CONTROLLER_H

#include <cstddef>
#include <iostream>
#include <GL/freeglut.h>
#include "AppController.h"
#include "StartScreen.h"
#include "GameBoard.h"
#include "WinScreen.h"

class Controller : public AppController {

    StartScreen* startScreen;
    GameBoard* gameBoard;
    WinScreen* winScreen;
    bool startGame;
    bool wonGame;
    bool endGame;
    bool aiPlayer;


public:
    Controller(){
        // Initialize your state variables
        startScreen = new StartScreen();
        gameBoard = nullptr;
        winScreen = nullptr;
        startGame = false;
        wonGame = false;
        endGame = false;
        aiPlayer = false;
    }

    void render() {
        // Render some graphics
        if(!startGame){
            startScreen->draw();
        }
        else if(!wonGame && gameBoard != nullptr){
            gameBoard->draw();
        }
        else if(wonGame && winScreen != nullptr){
            winScreen->draw();
        }
        
        
    }

    void leftMouseDown(float mx, float my) {
        if(!startGame){
            startScreen->handleMouseClick(mx, my);
            if(startScreen->isAIPlayerSelected()){
                aiPlayer = true;
            }
            if(startScreen->isStartSelected()){
                initializeGame();
            }
        }
        else if(!wonGame && gameBoard != nullptr){
            gameBoard->handleMouseClick(mx, my);
            if(gameBoard->checkWin()){
                wonGame = true;
                char winner = gameBoard->getCurrentPlayer();
                winScreen = new WinScreen(winner);
            }
            else if(gameBoard->isDraw()){
                wonGame = true;
                winScreen = new WinScreen('D');

            }
            else{
                if(gameBoard->getCurrentPlayer() == 'X'){
                    gameBoard->setCurrentPlayer('O');
                }
                else{
                    gameBoard->setCurrentPlayer('X');
                }

                if(aiPlayer){
                    gameBoard->aiTurn();

                    if(gameBoard->checkWin()){
                    wonGame = true;
                    char aiWon = 'O';
                    winScreen = new WinScreen(aiWon);
                    }
                    else{
                    gameBoard->setCurrentPlayer('X');
                    }
                }   
            }
        }
        else if(wonGame && winScreen != nullptr){
            winScreen->handleMouseClick(mx, my);
            if(winScreen->isExitClicked()){
                endGame = true;
                delete winScreen;
                winScreen = nullptr;
                exit(0);
            }
            else if(winScreen->isRestartClicked()){
                restartGame();
            }
        }
    
    }
    
    void restartGame(){
        delete gameBoard;
        delete winScreen;
        gameBoard = nullptr;
        winScreen = nullptr;
        wonGame = false;
        startGame = false;

        startScreen = new StartScreen();

    }

    void initializeGame(){
        int boardSize = 3;
        
        if(startScreen->isFourGridSelected()){
            boardSize = 4;
        }
        else if(startScreen->isFiveGridSelected()){
            boardSize = 5;
        }

        gameBoard = new GameBoard(boardSize);
        startGame = true;
        delete startScreen;
        startScreen = nullptr;

        gameBoard->setCurrentPlayer('X');
    }

    ~Controller(){
        // Release memory
        delete startScreen;
        delete gameBoard;
        delete winScreen;
    }
};

#endif