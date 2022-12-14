public class Queen implements Piece{
    private int[] position;
    private boolean isCaptured;
    private char user;
    private char name;
    private boolean firstStep;

    Queen(int[] position, char user){
        this.position = position;
        this.isCaptured = false;
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
    public boolean canMove(int[] position, Piece[][] board){
        //if the destination is not in the same line
        if((this.position[0]!=position[0] && this.position[1]!=position[1])){
            //if the destination is also not in the same diagonal then return false
            if((Math.abs(position[0]-this.position[0])!=Math.abs(position[1]-this.position[1]))){
                return false;
            }
        }
            //check vertically
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
            else if(this.position[1] == position[1]){
                //check horizontally
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
            //check diagonally
            else if(position[0]>this.position[0]&&position[1]>this.position[1]){
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

}
