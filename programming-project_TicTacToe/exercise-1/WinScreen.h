#ifndef WINSCREEN_H
#define WINSCREEN_H

#include "Rectangle.h"
#include "Color.h"
#include "Button.h"

class WinScreen{
private:
    Rectangle* endScreen;
    Button* winner;
    Button* restart;
    bool exitClicked;
    bool restartClicked;

public:
    WinScreen(char winningPlayer){
        endScreen = new Rectangle(-1.0, -1.0, 2.0, 2.0, Color(0.0, 0.0, 0.0));
        
        std::string winMessage;

        if(winningPlayer == 'D'){
            winMessage = "DRAW: Click to exit";
        }
        else{
            winMessage = "Player" + std::string(1, winningPlayer) + " Won! Click to Exit";
        }

        winner = new Button(-0.6, 0.0, winMessage); 
        restart = new Button(-0.6, -0.4,"RESTART");
        exitClicked = false;
        restartClicked = false;

    }

    void draw(){
        endScreen->draw();
        winner->draw();
        restart->draw();
    }

    void handleMouseClick(float mx, float my){
        if(winner->contains(mx, my)){
            winner->setPressed();
            exitClicked = true;
        }
        else if(restart->contains(mx, my)){
            restart->setPressed();
            restartClicked = true;
        }
    }

    bool isExitClicked(){
        return exitClicked;
    }
    bool isRestartClicked(){
        return restartClicked;
    }


    ~WinScreen(){
        delete endScreen;
        delete winner;
        delete restart;
    }
};
#endif