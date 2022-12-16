public class Pawn implements Piece{
    private int[] position;
    private boolean isCaptured;
    private char user;
    private char name;
    private boolean firstStep;
    private boolean enPassant;

    Pawn(int[] position, char user){
        this.position = position;
        this.user = user;
        this.isCaptured = false;
        this.enPassant = false;
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
                if(this.position[0] - position[0] == 1 && Math.abs(this.position[1] - position[1]) == 1){
                    if(board[position[0]][position[1]].getUser()=='b'){
                        return true;
                    }
                    if(board[position[0]][position[1]].getUser()=='-'&&board[position[0]+1][position[1]].enPassant()){
                        return true;
                    }
                }
                else{
                    return false;
                }
            }
            if(this.position[0] - position[0] != 1 && this.position[0] - position[0] != 2){
                return false;
            }
            if(this.position[0] - position[0] == 2 && !firstStep){
                return false;
            }
        }
        else{
            if(position[1]!=this.position[1]){
                if(position[0] - this.position[0] == 1 && Math.abs(position[1] - this.position[1]) == 1){
                    if(board[position[0]][position[1]].getUser()=='w'){
                        return true;
                    }
                    if(board[position[0]][position[1]].getUser()=='-'&&board[position[0]-1][position[1]].enPassant()){
                        return true;
                    }
                }
                else{
                    return false;
                }
            }
            if(position[0] - this.position[0] != 1 && position[0] - this.position[0]!=2){
                return false;
            }
            if(position[0] - this.position[0] == 2 && !firstStep){
                return false;
            }
        }

        //if the destination has piece then return false
        if(board[position[0]][position[1]].getName() != '-'){
            //System.out.println("You can't capture piece of yourself!");
            return false;
        }
        if(Math.abs(position[0] - this.position[0]) == 2){
            this.enPassant = true;
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
        return this.enPassant;
    }

    public void cancelEnPassant(){
        this.enPassant = false;
        return;
    }
}
