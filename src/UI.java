/**
 * Created by xianfeng on 10/20/17.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UI extends Application {
    private OOXX game;
    private Label label;
    private Button a1;
    private Button a2;
    private Button a3;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button c1;
    private Button c2;
    private Button c3;
    private boolean start = true;

    private void makeMove(Button b, int r, int c){

//        if(start){
//            start = false;
//            Random random = new Random();
//            int first = random.nextInt(2);
//            if(first == 0){
//                label.setText("You go first");
//            }
//        }else {
            // make move
            if (game.isValid(r, c)) {
                if (game.getMove() == 1) {
                    b.setText("O");
                } else if (game.getMove() == 2) {
                    b.setText("X");
                }
                game.makeMove(r, c);
            }

            if (game.hasWon()) {
                this.label.setText("Game Over");
                //label.setFont(new Font("Courier New", 15));
            }

            //Dony move
            List<Integer> coor = game.makeAImove();
            System.out.println("AI "+coor);
            r = coor.get(0);
            c = coor.get(1);
            b = getButton(r, c);



            if (game.getMove() == 1) {
                b.setText("X");
            } else if (game.getMove() == 2) {
                b.setText("O");
            }

            if (game.hasWon()) {
                this.label.setText("Game Over");
                //label.setFont(new Font("Courier New", 15));
            }
//        }

    }

    private Button getButton(int row, int col){
        Button b = new Button();
        if (row == 0){
            if(col==0){
                b = a1;
            }
            if(col == 1){
                b=a2;
            }
            if(col == 2){
                b=a3;
            }
        }
        if(row == 1){
            if(col==0){
                b = b1;
            }
            if(col == 1){
                b=b2;
            }
            if(col == 2){
                b=b3;
            }
        }
        if(row == 2){
            if(col==0){
                b = c1;
            }
            if(col == 1){
                b=c2;
            }
            if(col == 2){
                b=c3;
            }
        }
        return b;
    }
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.game = new OOXX();

        primaryStage.setTitle("OOXX");
        this.label = new Label("Welcome");
        label.setFont(new Font("Courier New", 12));
        label.setAlignment(Pos.CENTER_RIGHT);
        //label.setMinWidth(500);
        label.setMinHeight(50);


        BorderPane mainBoard = new BorderPane();

        HBox h1 = new HBox();
        a1 = new Button("");
        a1.setMinSize(50,50);
        a2 = new Button("");
        a2.setMinSize(50,50);
        a3 = new Button("");
        a3.setMinSize(50,50);
        h1.getChildren().addAll(a1,a2,a3);

        HBox h2 = new HBox();
        b1 = new Button("");
        b1.setMinSize(50,50);
        b2 = new Button("");
        b2.setMinSize(50,50);
        b3 = new Button("");
        b3.setMinSize(50,50);
        h2.getChildren().addAll(b1,b2,b3);

        HBox h3 = new HBox();
        c1 = new Button("");
        c1.setMinSize(50,50);
        c2 = new Button("");
        c2.setMinSize(50,50);
        c3 = new Button("");
        c3.setMinSize(50,50);
        h3.getChildren().addAll(c1,c2,c3);


        a1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(a1,0,0);
            }
        });



        a2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(a2,0,1);
            }
        });

        a3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(a3,0,2);
            }
        });

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(b1,1,0);
            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(b2,1,1);
            }
        });

        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(b3,1,2);
            }
        });

        c1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(c1,2,0);
            }
        });

        c2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(c2,2,1);
            }
        });

        c3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove(c3,2,2);
            }
        });

        VBox gameBoard = new VBox();
        gameBoard.getChildren().addAll(h1,h2,h3);
        mainBoard.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);
        mainBoard.setCenter(gameBoard);

        Scene scene = new Scene(mainBoard);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
