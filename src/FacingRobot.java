public abstract class FacingRobot extends Robot {
    private direction currentFace;

    public FacingRobot(Maze maze){
        super(maze);
        currentFace = direction.SOUTH;
    }

    enum direction {NORTH, SOUTH, EAST, WEST }

    public direction getDirection(){
        return currentFace;
    }

    public void setDirection(direction face){
        currentFace = face;
    }


}
