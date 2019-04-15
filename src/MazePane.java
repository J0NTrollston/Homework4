import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * This will be to paint and display the pane
 */
public class MazePane extends Pane{
    private Maze maze;
    private Robot currentRobot;

    public void setCurrentRobot(Robot currentRobot){this.currentRobot = currentRobot;}

    public Robot getCurrentRobot(){return currentRobot;}

    public void setMaze(Maze maze){this.maze = maze;}

    public Maze getMaze(){return maze;}

    public void paint(){
        final int WIDTH = 20;
        final int HEIGHT = 20;

    Rectangle rectangles;
    getChildren().clear();
        for(int row = 0; row < HEIGHT*maze.getRows(); row = row + 20){
            for(int col = 0; col < WIDTH*maze.getCols(); col = col + 20){
                rectangles = new Rectangle(col,row,WIDTH,HEIGHT);
                getChildren().add(rectangles);
                if(maze.getCell(row/20,col/20) == '*'){
                    //black box
                    rectangles.setFill(Color.BLACK);
                }else if(maze.getCell(row/20,col/20) == ' ' || maze.getCell(row/20,col/20) == 'X' ||maze.getCell(row/20,col/20) == 'S'){
                    //white box
                    rectangles.setFill(Color.WHITE);
                }else{
                    //paint white background then robot circle
                    rectangles.setFill(Color.WHITE);
                    Circle circle = new Circle(HEIGHT/2);
                    circle.setFill(Color.GRAY);
                    circle.relocate(col,row);
                    getChildren().add(circle);
                }

            }
        }
    }
}
