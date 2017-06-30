package com.project.matam.tetris.gamecore;

import com.project.matam.tetris.bricks.Brick;

import java.util.Random;

/**
 * Created by matam on 30/06/2017.
 * Les fonctions de cette classe se trouvaient dans le main,
 * pour utiliser add et remove dans les classes bricks j'ai
 * refactor mon code. ça rend le main un peu plus propre et ça m'évite de trop
 * repeter mon code.
 */

public class BrickManager {

    private int[][] gameMatrix;

    public BrickManager(int[][] gameMatrix){
        this.gameMatrix = gameMatrix;
    }

    public boolean addBrick(Brick brick)
    {
        int [][] gameMatrix = this.gameMatrix;
        boolean success = false;
        int brickX = -1;
        int brickY = -1;
        int maxY = brick.getY()+ brick.getHeight();
        int maxX = brick.getX()+ brick.getWitdh();

        for(int y = brick.getY(); y < maxY; y++ )
        {
            brickY++;
            for(int x = brick.getX(); x < maxX; x++)
            {
                brickX++;
                //**************************************************
                //La matrice de la piece montre une piece solide (1)
                //et la matrice de jeu est vide
                if(brick.getMatrix()[brickY][brickX] == 1 && gameMatrix[y][x] == 0)
                {
                    success = true;
                    gameMatrix[y][x] = brick.getMatrix()[brickY][brickX];
                }
                else if(brick.getMatrix()[brickY][brickX] == 1 && gameMatrix[y][x] == 1)
                {
                    success = false;
                }
                else{
                    success = true;
                }
                if(!success)
                {
                    break;
                }
            }//end for
            brickX = -1;
            if(!success){
                break;
            }
        }//end for
        if(success)
        {
            this.gameMatrix = gameMatrix;
        }
        return success;
    }

    public void removeBrick(Brick brick)
    {
        int brickX = -1;
        int brickY = -1;
        int maxY = brick.getY()+ brick.getHeight();
        int maxX = brick.getX()+ brick.getWitdh();
        for(int y = brick.getY(); y < maxY; y++ ){
            brickY++;
            for(int x = brick.getX(); x < maxX; x++)
            {
                brickX++;
                if(brick.getMatrix()[brickY][brickX] == 1)
                {
                    this.gameMatrix[y][x] = 0;
                }
            }
            brickX = -1;
        }
    }

    int lineToErase;
    public boolean checkLine(int row, int col)
    {
        boolean fullLine = false;

        for(int y = row-1; y >= 0 ; y--)
        {
            int cpt = 0;
            for(int x = 0; x < col; x++){
                if(this.gameMatrix[y][x] == 1)//if(this.gameMatrix[y][x] == 1)
                {
                    cpt++;
                }
                if(cpt == col && x < col)
                {
                    lineToErase = y;
                    fullLine = true;
                    break;
                }
            }
            if(fullLine)
            {
                break;
            }
        }

        return fullLine;
    }

    public void downAll(int col){
        for (int y = lineToErase; y>0; y--){
            for(int x = 0; x<col; x++){
                this.gameMatrix[y][x] = this.gameMatrix[y-1][x];
                if(y==1)
                {
                    this.gameMatrix[y-1][x] = 0;
                }
            }
        }
    }

    public int[][] getGameMatrix() {
        return gameMatrix;
    }

    public void setGameMatrix(int[][] gameMatrix) {
        this.gameMatrix = gameMatrix;
    }
}
