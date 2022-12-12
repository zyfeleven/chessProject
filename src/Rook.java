public class Rook implements Piece{
    private int[] position;
    private boolean isCaptured;
    private char user;
    private char name;
    private boolean firstStep;

    Rook(int[] position, char user){
        this.position = position;
        this.user = user;
        this.isCaptured = false;
        if(user=='b'){
            this.name = 'R';
        }
        else{
            this.name = 'r';
        }
        this.firstStep = true;
    }

    //Move rule for Rook: only move horizontally or vertically
    public boolean canMove(int[] position, Piece[][] board){
        //if the destination is not in the same line then return false
        if(this.position[0]!=position[0]&&this.position[1]!=position[1]){
            return false;
        }
        else{
            //check if there is other pieces between this rook and destination
            //if yes then return false
            if(this.position[0]==position[0]){
                if(this.position[1]<position[1]){
                    for(int i = this.position[1]+1;i<position[1];i++){
                        if(board[i][this.position[0]].getName()!='-'){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = position[1]+1;i<this.position[1];i++){
                        if(board[i][this.position[0]].getName()!='-'){
                            return false;
                        }
                    }
                }
            }
            else{
                //check if there is other pieces between this rook and destination
                //if yes then return false
                if(this.position[0]<position[0]){
                    for(int i = this.position[0]+1;i<position[0];i++){
                        if(board[i][this.position[1]].getName()!='-'){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = position[0]+1;i<this.position[0];i++){
                        if(board[i][this.position[1]].getName()!='-'){
                            return false;
                        }
                    }
                }
            }
            //todo: judge if castling possible
            //if the destination has friendly piece then return false
            if(board[position[0]][position[1]].getUser() == this.user){
                System.out.println("You can't capture piece of yourself!");
                return false;
            }
            return true;
        }
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
}
