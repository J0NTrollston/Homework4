
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;


/**
 * @author Brandon Ramos
 * Resources: Learning Commons tutor Alex S. and Trenton
 *
 * This program simulates a robot getting through a maze.
 *	Unlike tic-tac-toe, where the user is the player, the robot moves
 autonomously through the maze.
 * 	We intentionally share the memory location of the maze with the robot.
 Otherwise the robot would not know how to plan and make its moves.

 The maze is stored in a text file that will be entered at runtime.
 The layout of each maze file will contain:
 �	In the first line:  two integers (the number of rows and columns, respectively,  in the maze)
 �	In the second line:  two integers (the row and column locations, respectively, of the Start cell
 �	In the third line:  two integers (the row and column locations, respectively, of the Exit cell
 �	Each line thereafter will contain characters appearing in one row of the maze.

 * Created by Sherri Harms
 * built on top of Eddy's UW-Parkside solution
 *
 * Multiple Choice 1-10
 * 1.) D
 * 2.) A
 * 3.) A
 * 4.) C
 * 5.) B
 * 6.) A
 * 7.) A
 * 8.) 10
 * 9.) The base case was the input being 0 and moving up til it returns 10
 * 10.) direct
 * 11.)
 *          public class FindTheError{
 *              publicstatic void main(String[] args){
 *                  myMethod(0);
 *              }
*           public static void myMethod(int num) {
 *           if(num<10){
 *              System.out.print(num+ “ ”);
 *             myMethod(num+1);
 *           }
 *           return num
 *
 *           }
 *           }
 */

public class MazeDriver {

    public static void main(String[] args) throws Exception {
        MazeWindow.main(args);
        File inputFile = getFile();  //sample: testmaze.txt
        Maze maze = new Maze(inputFile);
        System.out.println(maze);
        Robot bot = new RightHandRobot(maze);//this ties the robot to the maze it is in
        Robot randomBot = new RandomRobot(maze);
        Robot leftBot = new LeftHandRobot(maze);
        FacingRobot lookAheadRobot = new LookAheadRobot(maze);

        Scanner scan = new Scanner(System.in);
        System.out.println("Press 1 for RightHandRobot\nPress 2 for RandomBot\nPress 3 for LeftHandRobot\nPress 4 for LookAheadRobot");
        int choice = scan.nextInt();

        switch(choice) {
            case 1:
            for (int k = 0; k < 1000000 && !bot.solved(); k++)
            //this limits the robot's moves, in case it takes too long to find the exit.
            {
                int direction = bot.chooseMoveDirection();
                if (direction >= 0)  //invalid direction is -1
                    bot.move(direction);
                bot.solved();
                System.out.println(maze);
                System.out.println("\n");
            }
            break;
            case 2:
            for (int k = 0; k < 1000000 && !randomBot.solved(); k++)
            //this limits the robot's moves, in case it takes too long to find the exit.
            {
                int direction = randomBot.chooseMoveDirection();
                if (direction >= 0)  //invalid direction is -1
                    randomBot.move(direction);
                randomBot.solved();
                System.out.println(maze);
                System.out.println("\n");
            }
            break;
            case 3:
            for (int k = 0; k < 1000000 && !leftBot.solved(); k++)
            //this limits the robot's moves, in case it takes too long to find the exit.
            {
                int direction = leftBot.chooseMoveDirection();
                if (direction >= 0)  //invalid direction is -1
                    leftBot.move(direction);
                leftBot.solved();
                System.out.println(maze);
                System.out.println("\n");
            }
            break;
            case 4:
                for (int k = 0; k < 10000000 && !lookAheadRobot.solved(); k++)
                //this limits the robot's moves, in case it takes too long to find the exit.
                {
                    int direction = lookAheadRobot.chooseMoveDirection();
                    if (direction >= 0)  //invalid direction is -1
                        lookAheadRobot.move(direction);
                    lookAheadRobot.solved();
                    System.out.println(maze);
                    System.out.println("\n");
                }
                break;
            default:
                System.out.println("You did not pick 1, 2 or 3\nExiting System...");
        }
    }

    /**
     * Get the file that has the maze specifications.
     * @return File chosen by user.
     */
    public static File getFile()
    {
        JFileChooser chooser;
        try{

            // Get the filename.
            chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status != JFileChooser.APPROVE_OPTION)
            {
                System.out.println("No File Chosen");
                System.exit(0);
            }
            return chooser.getSelectedFile();
        } catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
            System.exit(0);

        }
        return null; //should never get here, but makes compiler happy
    }

}
