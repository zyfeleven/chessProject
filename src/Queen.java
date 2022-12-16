public class Queen implements Piece{
    private int[] position;
    private final char user;
    private final char name;
    private boolean firstStep;

    Queen(int[] position, char user){
        this.position = position;
        this.user = user;
        if(user=='b'){
            this.name = 'Q';
        }
        else{
            this.name = 'q';
        }
        this.firstStep = true;
    }

    //Move rule for Queen: move horizontally or vertically or diagonally
    public boolean canMove(int[] pos, Piece[][] board){
        //if the destination is not in the same line
        if((this.position[0]!=pos[0] && this.position[1]!=pos[1])){
            //if the destination is also not in the same diagonal then return false
            if((Math.abs(pos[0]-this.position[0])!=Math.abs(pos[1]-this.position[1]))){
                return false;
            }
        }
            //check vertically
            if(this.position[0]==pos[0]){
                if(this.position[1]<pos[1]){
                    for(int i = this.position[1]+1;i<pos[1];i++){
                        if(board[i][this.position[0]].getName()!='-'){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = pos[1]+1;i<this.position[1];i++){
                        if(board[i][this.position[0]].getName()!='-'){
                            return false;
                        }
                    }
                }
            }
            else if(this.position[1] == pos[1]){
                //check horizontally
                if(this.position[0]<pos[0]){
                    for(int i = this.position[0]+1;i<pos[0];i++){
                        if(board[i][this.position[1]].getName()!='-'){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = pos[0]+1;i<this.position[0];i++){
                        if(board[i][this.position[1]].getName()!='-'){
                            return false;
                        }
                    }
                }
            }
            //check diagonally
            else if(pos[0]>this.position[0]&&pos[1]>this.position[1]){
                for(int i = 1;i<pos[0]-this.position[0];i++){
                    if(board[this.position[0]+i][this.position[1]+i].getName()!='-'){
                        return false;
                    }
                }
            }
            else if(pos[0]>this.position[0]){
                for(int i = 1;i<pos[0]-this.position[0];i++){
                    if(board[this.position[0]+i][this.position[1]-i].getName()!='-'){
                        return false;
                    }
                }
            }
            else if(pos[1]<this.position[1]){
                for(int i = 1;i<pos[0]-this.position[0];i++){
                    if(board[this.position[0]-i][this.position[1]-i].getName()!='-'){
                        return false;
                    }
                }
            }
            else {
                for(int i = 1;i<pos[0]-this.position[0];i++){
                    if(board[this.position[0]-i][this.position[1]+i].getName()!='-'){
                        return false;
                    }
                }
            }
        //if the destination has friendly piece then return false
        //System.out.println("You can't capture piece of yourself!");
        return board[pos[0]][pos[1]].getUser() != this.user;
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
