public class King implements Piece{
    private int[] position;
    private boolean isCaptured;
    private char user;// 'b' is Black and 'w' is white

    private char name;
    private boolean firstStep;

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
        if(Math.abs(position[1]-this.position[1])>1){
            return false;
        }
        if(board[position[1]][position[0]].getUser() == this.user){
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
