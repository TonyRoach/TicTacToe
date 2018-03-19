import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xianfeng on 10/20/17.
 */
public class OOXX {

    private int[][] gameBoard;

    public int move = 1;

    public int idAI;

    public int idHuman;

    private int step = 0;

    public void setMove(){
        if(this.move == 1){
            this.move = 2;
        }else {
            this.move = 1;
        }
    }
    public void AIFirst(){
        this.idAI = 1;
        this.idHuman = 2;
    }

    public void HumanFirst(){
        this.idHuman = 1;
        this.idAI = 2;
    }

    public OOXX(){
        this.gameBoard = new int[3][3];
        for (int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                gameBoard[i][j] = 0;
            }
        }
    }

    public boolean hasWon(){
        // 00 01 02 (row 1)
        if(this.gameBoard[0][0] == this.gameBoard[0][1] ){
            if(this.gameBoard[0][1] == this.gameBoard[0][2]){
                if(this.gameBoard[0][2] != 0) {
                    return true;
                }
            }
        }

        // 10 11 12 (row 2)
        if(this.gameBoard[1][0] == this.gameBoard[1][1]){
            if(this.gameBoard[1][1] == this.gameBoard[1][2]){
                if(this.gameBoard[1][2] != 0) {
                    return true;
                }
            }
        }

        // 20 21 22 (row 3)
        if(this.gameBoard[2][0] == this.gameBoard[2][1]){
            if(this.gameBoard[2][1] == this.gameBoard[2][2]){
                if(this.gameBoard[2][2] != 0){
                    return true;
                }
            }
        }

        // 00 10 20 (col 1)
        if(this.gameBoard[0][0] == this.gameBoard[1][0]){
            if(this.gameBoard[1][0] == this.gameBoard[2][0]){
                if(this.gameBoard[2][0] != 0) {
                    return true;
                }
            }
        }

        // 01 11 21 (col 2)
        if(this.gameBoard[0][1] == this.gameBoard[1][1]){
            if(this.gameBoard[1][1] == this.gameBoard[2][1]){
                if(this.gameBoard[2][1] != 0) {
                    return true;
                }
            }
        }

        // 02 12 22 (col 3)
        if(this.gameBoard[0][2] == this.gameBoard[1][2]){
            if(this.gameBoard[1][2] == this.gameBoard[2][2]){
                if(this.gameBoard[2][2] != 0) {
                    return true;
                }
            }
        }

        // 00 11 22
        if(this.gameBoard[0][0] == this.gameBoard[1][1]){
            if(this.gameBoard[1][1] == this.gameBoard[2][2]){
                if(this.gameBoard[2][2] != 0) {
                    return true;
                }
            }
        }

        // 02 11 20
        if(this.gameBoard[0][2] == this.gameBoard[1][1]){
            if(this.gameBoard[1][1] == this.gameBoard[2][0]){
                if(this.gameBoard[2][0] != 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getMove(){
        return this.move;
    }

    public boolean isValid(int r, int c){
        return this.gameBoard[r][c] == 0;
    }

    public void makeMove(int row, int col){

        if(isValid(row,col)) {
            if (this.move == 1) {
                this.gameBoard[row][col] = 1;
                this.move = 2;
            } else if (this.move == 2) {

                this.gameBoard[row][col] = 2;
                this.move = 1;
            }

            this.step += 1;
        }


    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                sb.append(this.gameBoard[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /***************************
     * Tony: AI Dony
     ***************************/

    public List<Integer> makeAImove(){
        List<Integer> coor = AImove();
        int r = coor.get(0);
        int c = coor.get(1);
        makeMove(r,c);
        return coor;
    }

    public List<Integer> AImove(){
        List<Integer> xy = new ArrayList<>();

        // step 0
        if(this.step == 0){

            Random rand = new Random();
            int x = rand.nextInt(2);
            int y = rand.nextInt(2);

            if(x == 0){
                xy.add(0);
            }else {
                xy.add(2);
            }

            if(y == 0){
                xy.add(0);
            }else {
                xy.add(2);
            }

            return xy;
        }
        xy = stopToWin();

        if (!xy.isEmpty()){
            return xy;
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(this.gameBoard[i][j] == 0){
                    xy.add(i);
                    xy.add(j);
                    return xy;
                }
            }
        }
        return xy;
    }
    public void deepCopy(OOXX temp){
        this.gameBoard = new int[3][3];

        int [][] tempBoard = temp.gameBoard;
        this.move = temp.move;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                this.gameBoard[i][j] = tempBoard[i][j];
            }
        }
    }


    public List<Integer> stopToWin(){
        List<Integer> xy = new ArrayList<>();


        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                OOXX copy = new OOXX();
                copy.deepCopy(this);
                copy.setMove();
                copy.makeMove(i,j);
                System.out.println("coor" +i+j);
                System.out.println(copy);
                if(copy.hasWon()){
                    xy.add(i);
                    xy.add(j);
                    return xy;
                }
            }
        }

        return xy;
    }

    public static void main(String[] args) {
        OOXX game = new OOXX();

        game.makeMove(0,0);

        game.makeMove(0,1);
//
        game.makeMove(2,0);

        game.makeMove(2,1);
//
//        game.makeMove(2,1);
        System.out.println(game.hasWon());
        List<Integer> coor = game.stopToWin();
        System.out.println(coor);
        System.out.println(game);

        Random r = new Random();
        int i = r.nextInt(2);
        System.out.println(i);
    }
}
