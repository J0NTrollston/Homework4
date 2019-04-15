import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;



public class MazeWindow extends Application{
    private File result;
    private Label title;
    private Maze maze;
    private File file;
    private Robot currentRobot;
    private double HEIGHT = 20;
    private double WIDTH = 20;
    private MazePane pane;

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage)throws Exception{

        //set the title of the application
        title = new Label("Homework 4: GUI Robot Maze");
        pane = new MazePane();




        //Create the menu and add all necessary components to this application.
        MenuBar menuBar = new MenuBar();

        //Create File menu
        Menu fileMenu = new Menu("File");
        MenuItem solveItem  = new MenuItem("Solve");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(solveItem, new SeparatorMenuItem(), exitItem);

        //Create the Maze menu
        Menu mazeMenu = new Menu("Maze");
        MenuItem loadItem = new MenuItem("Load File");
        mazeMenu.getItems().add(loadItem);

        //Create the Robot menu
        Menu robotMenu = new Menu("Robot");
        MenuItem leftHandRobotItem = new MenuItem("Left Hand Robot");
        MenuItem rightHandRobotItem = new MenuItem("Right Hand Robot");
        MenuItem randomRobotItem = new MenuItem("Random Robot");
        MenuItem lookAheadRobotItem = new MenuItem("Look Ahead Robot");
        robotMenu.getItems().addAll(leftHandRobotItem,rightHandRobotItem,randomRobotItem,lookAheadRobotItem);

        //Add all the menus into the menu bar
        menuBar.getMenus().addAll(fileMenu,mazeMenu,robotMenu);


        //Next we will add events to these menu items
        solveItem.setOnAction(event -> {
            solveMaze();
        });
        exitItem.setOnAction(event -> System.exit(0));

        loadItem.setOnAction(event -> {
            file = getFile();
            try {
                maze = new Maze(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pane.setMaze(maze);
            pane.paint();

        });

        leftHandRobotItem.setOnAction(event -> {
            currentRobot = new LeftHandRobot(maze);
            pane.paint();
        });
        rightHandRobotItem.setOnAction(event -> {
            currentRobot = new RightHandRobot(maze);
            pane.paint();
        });
        randomRobotItem.setOnAction(event -> {
            currentRobot = new RandomRobot(maze);
            pane.paint();
        });
        lookAheadRobotItem.setOnAction(event ->{
            currentRobot = new LookAheadRobot(maze);
            pane.paint();
        });

        VBox mainVBox = new VBox(menuBar,pane);
        Scene scene = new Scene(mainVBox);

        //Set the scene to the stage and display it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void resetMaze(){

    }

    /**
     * Once the maze has been solved, we will ask the user if they would like to choose a new robot.
     */
    public void askUserNewRobot(){
        showInfoDialog("Maze is complete, pick a new robot and go again");
    }

    public File getFile(){
        JFileChooser chooser;
        try {
            //Get the file
            chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status != JFileChooser.APPROVE_OPTION)
            {
                showInfoDialog("No File Chosen\n Exiting Program");
                System.exit(0);
            }
            return chooser.getSelectedFile();
        }catch(Exception e){
            showInfoDialog("Exception: " + e.getMessage());
        }
        return null;
    }

    public void solveMaze(){
        for(int i = 0; i < 1000000 && !currentRobot.solved(); i++){
            int direction = currentRobot.chooseMoveDirection();
            if(direction >=0){
                currentRobot.move(direction);
            }
            currentRobot.solved();
            pane.paint();
            //System.out.println(maze);
            //System.out.println("\n");
        }
        maze.setCell(maze.getExitRow(),maze.getExitCol(),'X');
    }

    /**
     * Pops up a window that has a message for the user
     * @param message string to display to user
     */
    private void showInfoDialog(String message){
        Alert a = (new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK));
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // stretch box to show all of message
        a.show();
    }
}

