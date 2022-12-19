package cosc3p71.groupProject.interfaces;

public interface Piece {
    public boolean canMove(int[] position, Piece[][] board);//decide if the piece can move to the target position

    public void setCurPosition(int i, int j);//set piece's current position

    public char getName();//get the name of this piece

    public char getUser();//get the user of this piece, 'b' is Black and 'w' is White

    public void firstStep();//set the firstStep variable to false (used for en passant and castling)

    public boolean isFirstStep();//return the firstStep variable (used for en passant and castling)

    public boolean enPassant();// return the enPassant (only used in Pawn, for en passant checking)

    public void cancelEnPassant();// set the enPassant to false (if the user didn't en passant at the first turn then no en passant next turn)

    public Piece copyPiece();//return a copy of this piece(a new object, not a pointer)
}
