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
