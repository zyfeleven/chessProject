public class nullPiece implements Piece{
    private int[] position;
    private char name;
    private char user;
    private boolean firstStep;

    nullPiece(int x, int y){
        this.position = new int[]{x,y};
        this.name = '-';
        this.user = ' ';
        this.firstStep = false;
    }

    public boolean canMove(int[] position, Piece[][] board){
        return false;
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
