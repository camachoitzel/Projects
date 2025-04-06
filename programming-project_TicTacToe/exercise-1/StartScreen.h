#ifndef STARTSCREEN_H
#define STARTSCREEN_H

#include "Rectangle.h"
#include "Button.h"
#include "Texture.h"
#include <cstddef>

class StartScreen{

private:
    Rectangle* startScreen;
    Button* startButton;
    Button* threeGridButton;
    Button* fourGridButton;
    Button* fiveGridButton;
    Button* twoPlayerButton;
    Button* aiPlayerButton;
    Texture* title;
    Texture* instructions;

    void drawButtons(){
        startButton->draw();
        threeGridButton->draw();
        fourGridButton->draw();
        fiveGridButton->draw();

        twoPlayerButton->draw();
        aiPlayerButton->draw();

    }

    void deleteButtons(){
        delete startButton;
        delete threeGridButton;
        delete fourGridButton;
        delete fiveGridButton;

        delete twoPlayerButton;
        delete aiPlayerButton;
    }

    void deleteAll(){
        delete startScreen;
        delete title;
        delete instructions;
        deleteButtons();
    }

public:

    StartScreen(){
        startScreen = new Rectangle(-1.0f, 1.0f, 2.0f, 2.0f, Color(1.0f, 1.0f, 1.0f));
        title = new Texture(-0.5, 1.0, 1.0, 0.3, "assets/TicTacToe_title.png");
        instructions = new Texture(-0.5, 0.6, 0.8, 0.5,"assets/instructions.png");

        //buttons for selecting grid size and starting game
        startButton = new Button(0.0, 0.0, "START");
        threeGridButton = new Button(0.1, -0.3, "3 x 3");
        fourGridButton = new Button(0.1, -0.55, "4 x 4");
        fiveGridButton = new Button(0.1, -0.8, "5 x 5");

        twoPlayerButton = new Button(-0.85, -0.3, "Player1 vs Player2");
        aiPlayerButton = new Button(-0.85, -0.55, "Player1 vs AI");

    }

    void handleMouseClick(float mx, float my){
        if(startButton->contains(mx, my)){
            startButton->setPressed();
        }
        else if (threeGridButton->contains(mx, my)) {
            threeGridButton->setPressed();
            fourGridButton->setNotPressed();
            fiveGridButton->setNotPressed();
        }
        else if (fourGridButton->contains(mx, my)) {
            fourGridButton->setPressed();
            threeGridButton->setNotPressed();
            fiveGridButton->setNotPressed();
        }
        else if (fiveGridButton->contains(mx, my)) {
            fiveGridButton->setPressed();
            threeGridButton->setNotPressed();;
            fourGridButton->setNotPressed();
        }
        else if(twoPlayerButton->contains(mx, my)){
            twoPlayerButton->setPressed();
            aiPlayerButton->setNotPressed();
        }
        else if(aiPlayerButton->contains(mx, my)){
            aiPlayerButton->setPressed();
            twoPlayerButton->setNotPressed();
        }
    }
    

    void draw(){
        startScreen->draw();
        title->draw();
        instructions->draw();
        drawButtons();

    }
    
    bool isStartSelected() const{
        return startButton->isPressed();
    }

    bool isThreeGridSelected() const{
        return threeGridButton->isPressed();
    }
    
    bool isFourGridSelected() const{
        return fourGridButton->isPressed();
    }

    bool isFiveGridSelected() const{
        return fiveGridButton->isPressed();
    }

    bool isTwoPlayerSelected() const{
        return twoPlayerButton->isPressed();
    }

    bool isAIPlayerSelected() const{
        return aiPlayerButton->isPressed();
    }

    ~StartScreen(){
        deleteAll();
    }

};
#endif