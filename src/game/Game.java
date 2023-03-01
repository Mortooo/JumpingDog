package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.SHIFT;
import static javafx.scene.input.MouseButton.PRIMARY;
import static javafx.scene.input.MouseButton.SECONDARY;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

    Position lastPosition;
    Position currentPosition;

    Group root = new Group();

    boolean isFirstPlayerTurn = true;//blue
    boolean dontMove = false;
    boolean vsComputer = false;// computer play with green 

    ProgressBar blueProgressBar = new ProgressBar();
    ProgressBar greenProgressBar = new ProgressBar();

    Text firstPlayerName = new Text("Blue Player");
    Text secondPlayerName = new Text("Green Player");

    Rectangle eaten = null;

    public static void main(String[] args) {

        launch(args);
    }
//
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = createMainBoard();
        stage.setScene(scene);
        stage.resizableProperty().set(false);// prevent user from resizing screen 
        stage.show();
    }

    /**
     * this method create the main board and return it as Scene
     *
     * @return Scene
     */
    public Scene createMainBoard() {

        Group root = new Group();
        Scene scene = new Scene(root, 400, 550);
        scene.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());// add the css file 

        //create elements 
        ImageView backgroun_img = new ImageView(new Image(getClass().getResourceAsStream("background.gif")));
        VBox vBox = new VBox();
        Button singlePlayer = new Button("Single Player");
        Button MultiPlayer = new Button("Multi Player");
        Button setting = new Button("settings");
        Button about = new Button("about");
        Button exit = new Button("exit");

        //Determine properties
        vBox.setSpacing(30);

        //Determine the size
        singlePlayer.setPrefSize(220, 50);
        MultiPlayer.setPrefSize(220, 50);
        setting.setPrefSize(220, 50);
        about.setPrefSize(220, 50);
        exit.setPrefSize(220, 50);

        //Determine the Location on the pane
        vBox.setLayoutX(90);
        vBox.setLayoutY(60);

        //Add to vbox
        vBox.getChildren().add(singlePlayer);
        vBox.getChildren().add(MultiPlayer);
        vBox.getChildren().add(setting);
        vBox.getChildren().add(about);
        vBox.getChildren().add(exit);

        //Add to the pane       
        root.getChildren().add(backgroun_img);
        root.getChildren().add(vBox);

        //Add event Handler
        MultiPlayer.setOnAction((t) -> {

            //get players names
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("First Player Name");
            dialog.showAndWait();
            firstPlayerName.setText(dialog.getResult());
            dialog.getEditor().setText("");
            dialog.setHeaderText("second player name");
            dialog.showAndWait();
            secondPlayerName.setText(dialog.getResult());

            //show game board
            Stage stage = (Stage) MultiPlayer.getScene().getWindow();
            stage.setScene(createGameBoard());
            stage.resizableProperty().set(false);// prevent change size of window

        });

        singlePlayer.setOnAction((t) -> {

            //get players names
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Player Name");
            dialog.showAndWait();
            firstPlayerName.setText(dialog.getResult());
            secondPlayerName.setText("Computer");
            vsComputer = true;

            //show game board
            Stage stage = (Stage) MultiPlayer.getScene().getWindow();
            stage.setScene(createGameBoard());
            stage.resizableProperty().set(false);// prevent change size of window

        });

        exit.setOnAction((t) -> {
            System.exit(0);
        });// exit

        return scene;

    }

    public Scene createGameBoard() {

        Scene scene = new Scene(root, 500, 650);
        scene.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());

        //create elements 
        ImageView backgroun_img = new ImageView(new Image(getClass().getResourceAsStream("background.gif")));
        Circle circle = new Circle();// this circle is used to refer to the role of the palyer "Blue or Green"
        Button shift_role = new Button();
        Button back = new Button("Back");
        Button newGame = new Button("New Game");

        //Determine properties
        circle.setRadius(16);
        circle.setFill(Color.BLUE);
        circle.setStroke(Color.WHITESMOKE);
        shift_role.setGraphic(circle);
        blueProgressBar.setProgress(1);
        greenProgressBar.setProgress(1);
        firstPlayerName.setFill(Color.WHITE);
        firstPlayerName.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));
        secondPlayerName.setFill(Color.WHITE);
        secondPlayerName.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));

        //Determine the size
        shift_role.setPrefSize(16, 16);
        back.setPrefSize(150, 30);
        newGame.setPrefSize(150, 30);
        blueProgressBar.setPrefWidth(200);
        blueProgressBar.setPrefHeight(30);
        greenProgressBar.setPrefWidth(200);
        greenProgressBar.setPrefHeight(30);
        greenProgressBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        greenProgressBar.setStyle("-fx-accent:green;");

        //Determine the Location on the pane
        shift_role.setLayoutX(225);
        shift_role.setLayoutY(30);
        back.setLayoutX(0);
        back.setLayoutY(605);
        newGame.setLayoutX(350);
        newGame.setLayoutY(605);
        blueProgressBar.setLayoutY(40);
        greenProgressBar.setLayoutY(40);
        greenProgressBar.setLayoutX(300);
        firstPlayerName.setX(0);
        firstPlayerName.setY(30);
        secondPlayerName.setX(300);
        secondPlayerName.setY(30);

        //Add to the pane       
        root.getChildren().add(backgroun_img);
        root.getChildren().add(secondPlayerName);
        root.getChildren().add(firstPlayerName);
        root.getChildren().add(blueProgressBar);
        root.getChildren().add(greenProgressBar);
        root.getChildren().add(shift_role);
        root.getChildren().add(back);
        root.getChildren().add(newGame);

        // add event handeler
        root.getScene().setOnMouseClicked((event) -> {//shift role when press right click
            switch (event.getButton()) {
                case SECONDARY:
                    shift_role.fire();
                    break;

            }
        });

        root.getScene().setOnKeyPressed((event) -> {//shift role when press SHIFT key on the keyboard

            switch (event.getCode()) {
                case SHIFT:
                    shift_role.fire();
                    break;

            }
        });

        shift_role.setOnAction((t) -> {//shifting role

            if (isFirstPlayerTurn) {

                if (vsComputer) {// if callange Computer

                    computerRole();
                    circle.setFill(Color.GREEN);
                    isFirstPlayerTurn = false;

                } else {
                    circle.setFill(Color.GREEN);
                    isFirstPlayerTurn = false;
                }

            } else {
                isFirstPlayerTurn = true;
                circle.setFill(Color.BLUE);

            }

        });

        // draw board 
        drawBoard();

        return scene;

    }

    /**
     * this method responsible of drawing the shapes that form the game
     */
    public void drawBoard() {

        /*create 25 rectangle 12 blue , 12 green and 1 white
          using the dimensions x=500 and y=600  
         */
        for (int y = 100; y < 600; y += 100) {
            for (int x = 0; x < 500; x += 100) {

                Rectangle rectangle = new Rectangle();

                rectangle.setStrokeWidth(5);
                rectangle.setArcHeight(100);
                rectangle.setArcWidth(100);

                rectangle.setWidth(100);
                rectangle.setHeight(100);

                rectangle.setX(x);
                rectangle.setY(y);

                if (x == 200 && y == 300) {// (200,300) empty position with color white 
                    rectangle.setFill(Color.WHITE);

                } else if (x == 300 && y == 300 || x == 400 && y == 300) {// (300,300) -(400,300) green
                    rectangle.setFill(Color.GREEN);

                } else if (y < 400) {

                    rectangle.setFill(Color.BLUE);//from (-,100) to (-,300) blue 

                } else {
                    rectangle.setFill(Color.GREEN);//from (-,400) to (-,500) green

                }

                rectangle.setOnMouseClicked((event) -> {

                    switch (event.getButton()) {// the selection must be with primary key because the secondery for shifting role 
                        case PRIMARY:
                            selectRectengle(((Position) rectangle.getUserData()));
                            break;
                    }

                });

                // the effect when hovering over regtangle 
                rectangle.setOnMouseEntered((t) -> {
                    rectangle.setStroke(Color.YELLOW);
                    rectangle.setStrokeWidth(5);
                });
                rectangle.setOnMouseExited((t) -> {
                    rectangle.setStroke(null);
                    rectangle.setStrokeWidth(5);
                });

                //save the postition in UserData of the rectangle 
                rectangle.setUserData(new Position(x, y));

                // add to the pane 
                root.getChildren().add(rectangle);
            }
        }
    }

    /**
     * clear Positions
     */
    public void restPositions() {
        lastPosition = null;
        currentPosition = null;
    }

    public void selectRectengle(Position position) {

        // if the player want to move his rectangle - the nex
        if (!getitem(position).getFill().equals(Color.WHITE)) {

            if (isFirstPlayerTurn) {//first player have the blue color

                if (!getitem(position).getFill().equals(Color.BLUE)) {//if the player select wronge color

                    System.out.println("wrong move!");

                    dontMove = true;

                } else {
                    dontMove = false;
                }

            } else {//second player green color

                if (!getitem(position).getFill().equals(Color.GREEN)) {//if the player select wronge color

                    dontMove = true;

                } else {
                    dontMove = false;
                }
            }

        }

        if (currentPosition == null) {/*at the start of the game or shifting role */

            currentPosition = position;

        } else if (currentPosition == position) {/* press the same item twise*/


        } else {

            lastPosition = currentPosition;
            currentPosition = position;

            if (dontMove) {
                restPositions();

            } else {
                move();

            }

        }
    }

    public void move() {

        Rectangle lastRectangle = getitem(lastPosition);
        Rectangle cuRectangle = getitem(currentPosition);

        if (cuRectangle.getFill().equals(Color.WHITE)) {

            if (isNear()) {//normal move

                System.out.println("is near");

                cuRectangle.setFill(lastRectangle.getFill());
                lastRectangle.setFill(Color.WHITE);
                restPositions();

            } else if (isEatable()) {

                eat();

                System.out.println("is eatable");
                cuRectangle.setFill(lastRectangle.getFill());
                lastRectangle.setFill(Color.WHITE);
                restPositions();

            } else {
                System.out.println("wrong move ");
                restPositions();

            }

        } else {
            restPositions();

        }

    }

    public Rectangle getitem(Position position) {

        Rectangle rectangle = new Rectangle();

        for (Node node : root.getChildren()) {

            if (node instanceof Rectangle) {

                Rectangle item = (Rectangle) node;
                Position p = (Position) item.getUserData();
                if (p.getX() == position.getX() & p.getY() == position.getY()) {
                    rectangle = item;
                }
            }

        }

        return rectangle;

    }

    public boolean isEatable() {

        boolean result = true;// the final result if there is good move 

        boolean jumb = true;// the eat move require jump
        boolean eat = true;// the next rectangle is enamy rectangel 

        String direction = "";

        Rectangle lastRectangle = getitem(lastPosition);

        int C_X = currentPosition.getX();
        int C_Y = currentPosition.getY();
        int L_X = lastPosition.getX();
        int L_Y = lastPosition.getY();

        if (C_X == L_X && C_Y == L_Y + 200) {//down

            eaten = getitem(new Position(L_X, L_Y + 100));

        } else if (C_X == L_X && C_Y == L_Y - 200) {//up

            eaten = getitem(new Position(L_X, L_Y - 100));

        } else if (C_X == L_X - 200 && C_Y == L_Y) {//left

            eaten = getitem(new Position(L_X - 100, L_Y));

        } else if (C_X == L_X + 200 && C_Y == L_Y) {//right

            eaten = getitem(new Position(L_X + 100, L_Y));

        } else {

            jumb = false;
        }

        if (jumb) {

            if (!lastRectangle.getFill().equals(eaten.getFill()) || !eaten.getFill().equals(Color.WHITE)) {

            } else {
                result = false;
            }

        } else {

            result = false;
        }

        return result;
    }

    public boolean isNear() {

        boolean result = true;

        int C_X = currentPosition.getX();
        int C_Y = currentPosition.getY();
        int L_X = lastPosition.getX();
        int L_Y = lastPosition.getY();

        if (C_X == L_X && C_Y == L_Y + 100) {//down

        } else if (C_X == L_X && C_Y == L_Y - 100) {//up

        } else if (C_X == L_X - 100 && C_Y == L_Y) {//left

        } else if (C_X == L_X + 100 && C_Y == L_Y) {//right

        } else {
            result = false;
        }

        return result;
    }

    public void eat() {
        // make effect while eating
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(eaten);
        scaleTransition.setDuration(Duration.millis(500));
        scaleTransition.setCycleCount(2);
        scaleTransition.setByX(-1);
        scaleTransition.setByY(-1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

        if (eaten.getFill().equals(Color.BLUE)) {
            blueProgressBar.setProgress(blueProgressBar.getProgress() - 0.083333333);
        } else {//green
            greenProgressBar.setProgress(greenProgressBar.getProgress() - 0.083333333);

        }

        eaten.setFill(Color.WHITE);

        isGameEnd();

    }

    public void isGameEnd() {
        if (blueProgressBar.getProgress() == 3.99999991462785E-9 || greenProgressBar.getProgress() == 3.99999991462785E-9) {

            new Alert(Alert.AlertType.INFORMATION, "game Finish!").showAndWait();

        } else if (blueProgressBar.getProgress() == 0.08333333699999991 && greenProgressBar.getProgress() == 0.08333333699999991) {
            new Alert(Alert.AlertType.INFORMATION, "Draw!").showAndWait();

        }
    }

    public void computerRole() {// how computer will play "Green"

        List<Rectangle> OwnedRectangles = new ArrayList<>();

        for (Node node : root.getChildren()) {// filter regtangles 

            if (node instanceof Rectangle) {

                Rectangle rectangle = (Rectangle) node;

                if (rectangle.getFill().equals(Color.GREEN)) {// if the regtangle is green it belong to computer

                    OwnedRectangles.add(rectangle);
                }

            }
        }

        // check if the computer can eat 
        List<Rectangle> eatRectangles = new ArrayList<>();

        for (Rectangle OwnedRectangle : OwnedRectangles) {
            currentPosition = (Position) OwnedRectangle.getUserData();

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {

                }
            }
//            lastPosition=
        }
        
        restPositions();

    }

}
