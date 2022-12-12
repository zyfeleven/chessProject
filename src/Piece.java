public interface Piece {
    public boolean canMove(int[] position, Piece[][] board);//decide if the piece can move to the target position
    public int[] curPosition();//return piece's current position

    public char getName();
    public char getUser();
    public void firstStep();
}
