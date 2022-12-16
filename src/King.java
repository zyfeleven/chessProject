public class King implements Piece{
    private int[] position;
    private final char user;// 'b' is Black and 'w' is white

    private final char name;
    public boolean firstStep;

    King(int[] position, char user){
        this.position = position;
        this.user = user;
        if(user=='b'){
            this.name = 'K';
        }
        else{
            this.name = 'k';
        }
        this.firstStep = true;
    }
    //Move rule for King: only move to one tile nearby
    public boolean canMove(int[] pos, Piece[][] board){

        if(Math.abs(pos[0]-this.position[0])>1){
            return false;
        }
        if(board[pos[0]][pos[1]].getUser() == this.user){
            //System.out.println("You can't capture piece of yourself!");
            return false;
        }
        return Math.abs(pos[1] - this.position[1]) <= 1;
    }

    public void setCurPosition(int i, int j){
        this.position[0] = i;
        this.position[1] = j;
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
    }

}
