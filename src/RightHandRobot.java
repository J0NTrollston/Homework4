

/**
 * This extends the robot class, here we have the right handed robot.
 */
public class RightHandRobot extends FacingRobot{
    private Maze currentMaze;

    /**
     * Constructor
     * @param maze maze from text file
     */
    public RightHandRobot(Maze maze){
        super(maze);
        currentMaze = maze;
    }

    /**
     * Sets the current row for robot
     * @param row given row
     */
    public void setCurrentRow(int row){
        super.setCurrentRow(row);
    }

    /**
     * Sets the current column for robot
     * @param col given column
     */
    public void setCurrentCol(int col){
        super.setCurrentCol(col);
    }

    /**
     * Return the current row
     * @return row
     */
    public int getCurrentRow(){ return super.getCurrentRow();}

    /**
     * Return the current col
     * @return col
     */
    public int getCurrentCol(){ return super.getCurrentCol();}

    /**
     * Choose the move direction
     * @return int direction
     */
    public int chooseMoveDirection(){
        if(getDirection()==direction.SOUTH){
            if(currentMaze.openCell(getCurrentRow(), getCurrentCol()-1)) {
                setDirection(direction.WEST);
                return 3;
            }else if(currentMaze.openCell(getCurrentRow()+1,getCurrentCol())){
                setDirection(direction.SOUTH);
                return 2;
            }else if(currentMaze.openCell(getCurrentRow(),getCurrentCol()+1)){
                setDirection(direction.EAST);
                return 1;
            }else{
                setDirection(direction.NORTH);
                return 0;
            }
        }else if(getDirection()==direction.WEST){
            if(currentMaze.openCell(getCurrentRow()-1, getCurrentCol())) {
                setDirection(direction.NORTH);
                return 0;
            }else if(currentMaze.openCell(getCurrentRow(),getCurrentCol()-1)){
                setDirection(direction.WEST);
                return 3;
            }else if(currentMaze.openCell(getCurrentRow()+1,getCurrentCol())){
                setDirection(direction.SOUTH);
                return 2;
            }else {
                setDirection(direction.EAST);
                return 1;
            }
        } else if (getDirection()==direction.NORTH) {
            if(currentMaze.openCell(getCurrentRow(), getCurrentCol()+1)) {
                setDirection(direction.EAST);
                return 1;
            }else if(currentMaze.openCell(getCurrentRow()-1,getCurrentCol())){
                setDirection(direction.NORTH);
                return 0;
            }else if(currentMaze.openCell(getCurrentRow(),getCurrentCol()-1)){
                setDirection(direction.WEST);
                return 3;
            }else {
                setDirection(direction.SOUTH);
                return 2;
            }
        }else if (getDirection()==direction.EAST){
            if(currentMaze.openCell(getCurrentRow()+1, getCurrentCol())) {
                setDirection(direction.SOUTH);
                return 2;
            }else if(currentMaze.openCell(getCurrentRow(),getCurrentCol()+1)){
                setDirection(direction.EAST);
                return 1;
            }else if(currentMaze.openCell(getCurrentRow()-1,getCurrentCol())){
                setDirection(direction.NORTH);
                return 0;
            }else {
                setDirection(direction.WEST);
                return 3;
            }
        }
        return Integer.parseInt(null);
    }

    /**
     * Move the robot
     * @param direction int direction to move
     * @return true if worked
     */
    public boolean move(int direction){
        //use the 2D array to move
        boolean valid = false;
        switch(direction){
            case 0:

                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol(), ' ');
                currentMaze.setCell(super.getCurrentRow()-1, super.getCurrentCol(),'R');
                setCurrentCol(super.getCurrentCol());
                setCurrentRow(super.getCurrentRow()-1);
                valid = true;
                break;
            case 1:
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol(), ' ');
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol()+1,'R');
                setCurrentCol(super.getCurrentCol()+1);
                setCurrentRow(super.getCurrentRow());
                valid = true;
                break;
            case 2:
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol(), ' ');
                currentMaze.setCell(super.getCurrentRow()+1, super.getCurrentCol(),'R');
                setCurrentCol(super.getCurrentCol());
                setCurrentRow(super.getCurrentRow()+1);
                valid = true;
                break;
            case 3:
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol(), ' ');
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol()-1,'R');
                setCurrentCol(super.getCurrentCol()-1);
                setCurrentRow(super.getCurrentRow());
                valid = true;
                break;
        }

        return valid;
    }

    /**
     * Find out if the maze is solved
     * @return true if solved
     */
    public boolean solved(){
        if(getCurrentCol() == currentMaze.getExitCol() && getCurrentRow() == currentMaze.getExitRow()){
            return true;
        }else{
            return false;
        }

    }

}
