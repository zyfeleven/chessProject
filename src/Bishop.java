public class Bishop implements Piece{
    private int[] position;
    private boolean isCaptured;
    private char user;
    private char name;
    private boolean firstStep;

    Bishop(int[] position, char user){
        this.position = position;
        this.user = user;
        this.isCaptured = false;
        if(user=='b'){
            this.name = 'B';
        }
        else{
            this.name = 'b';
        }
        this.firstStep = true;
    }

    //Move rule for Bishop: only move diagonally
    public boolean canMove(int[] position, Piece[][] board){
        //if the destination is also not in the same diagonal then return false
        if((Math.abs(position[0]-this.position[0])!=Math.abs(position[1]-this.position[1]))){
            return false;
        }
        //check diagonally
        if(position[0]>this.position[0]&&position[1]>this.position[1]){
            for(int i = 1;i<position[0]-this.position[0];i++){
                if(board[this.position[0]+i][this.position[1]+i].getName()!='-'){
                    return false;
                }
            }
        }
        else if(position[0]>this.position[0]&&position[1]<this.position[1]){
            for(int i = 1;i<position[0]-this.position[0];i++){
                if(board[this.position[0]+i][this.position[1]-i].getName()!='-'){
                    return false;
                }
            }
        }
        else if(position[0]<this.position[0]&&position[1]<this.position[1]){
            for(int i = 1;i<position[0]-this.position[0];i++){
                if(board[this.position[0]-i][this.position[1]-i].getName()!='-'){
                    return false;
                }
            }
        }
        else if(position[0]<this.position[0]&&position[1]>this.position[1]){
            for(int i = 1;i<position[0]-this.position[0];i++){
                if(board[this.position[0]-i][this.position[1]+i].getName()!='-'){
                    return false;
                }
            }
        }
        //if the destination has friendly piece then return false
        if(board[position[0]][position[1]].getUser() == this.user){
            //System.out.println("You can't capture piece of yourself!");
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
