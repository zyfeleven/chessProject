package cosc3p71.groupProject.interfaces;

public interface Piece {
    public boolean canMove(int[] position, Piece[][] board);//decide if the piece can move to the target position

    public void setCurPosition(int i, int j);//set piece's current position

    public char getName();

    public char getUser();

    public void firstStep();

    public boolean isFirstStep();

    public boolean enPassant();

    public void cancelEnPassant();

    public Piece copyPiece();
}
