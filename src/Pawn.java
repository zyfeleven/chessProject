public class Pawn implements Piece{
    private int[] position;
    private boolean isCaptured;
    private char user;
    private char name;
    private boolean firstStep;

    Pawn(int[] position, char user){
        this.position = position;
        this.user = user;
        this.isCaptured = false;
        if(user=='b'){
            this.name = 'P';
        }
        else{
            this.name = 'p';
        }
        this.firstStep = true;
    }
    ////Move rule for Pawn: only move forward one tile or two tiles(only for the first step)
    public boolean canMove(int[] position, Piece[][] board){
        if(this.user=='w'){
            if(position[1]!=this.position[1]){
                return false;
            }
            if(this.position[0] - position[0]!=1 && this.position[0] - position[0]!=2){
                return false;
            }
            if(this.position[0] - position[0] == 2 && !firstStep){
                return false;
            }
        }
        else{
            if(position[1]!=this.position[1]){
                return false;
            }
            if(position[0] - this.position[0]!=1 && position[0] - this.position[0]!=2){
                return false;
            }
            if(position[0] - this.position[0] == 2 && !firstStep){
                return false;
            }
        }

        //if the destination has friendly piece then return false
        if(board[position[0]][position[1]].getUser() == this.user){
            System.out.println("You can't capture piece of yourself!");
            return false;
        }
        return true;
    }

    public int[] curPosition(){
        return this.position;
    }

    public char getName(){
        return this.name;
    }
    public char getUser(){
        return this.user;
    }

    public void firstStep(){
        this.firstStep = false;
    }
}
