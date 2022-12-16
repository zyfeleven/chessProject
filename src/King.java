public class King implements Piece{
    private int[] position;
    private boolean isCaptured;
    private char user;// 'b' is Black and 'w' is white

    private char name;
    public boolean firstStep;

    King(int[] position, char user){
        this.position = position;
        this.user = user;
        this.isCaptured = false;
        if(user=='b'){
            this.name = 'K';
        }
        else{
            this.name = 'k';
        }
        this.firstStep = true;
    }
    //Move rule for King: only move to one tile nearby
    public boolean canMove(int[] position, Piece[][] board){

        if(Math.abs(position[0]-this.position[0])>1){
            return false;
        }
        if(board[position[0]][position[1]].getUser() == this.user){
            //System.out.println("You can't capture piece of yourself!");
            return false;
        }
        if(Math.abs(position[1]-this.position[1])>1){
            return false;
        }
        return true;
    }

    public void setCurPosition(int i, int j){
        this.position[0] = i;
        this.position[1] = j;
        return;
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

    public boolean isFirstStep(){
        return this.firstStep;
    }
    public boolean enPassant(){
        return false;
    }
    public void cancelEnPassant(){
        return;
    }

}
