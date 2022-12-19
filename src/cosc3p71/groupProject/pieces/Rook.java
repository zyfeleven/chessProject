package cosc3p71.groupProject.pieces;

import cosc3p71.groupProject.interfaces.Piece;

public class Rook implements Piece {
    private int[] position;
    private final char user;
    private final char name;
    private boolean firstStep;

    public Rook(int[] position, char user) {
        this.position = position;
        this.user = user;
        if (user == 'b') {
            this.name = 'R';
        } else {
            this.name = 'r';
        }
        this.firstStep = true;
    }

    //Move rule for Rook: only move horizontally or vertically
    public boolean canMove(int[] pos, Piece[][] board) {
        //if the destination is not in the same line then return false
        if (this.position[0] != pos[0] && this.position[1] != pos[1]) {
            return false;
        } else {
            //check if there is other pieces between this rook and destination
            //if yes then return false
            if (this.position[0] == pos[0]) {
                if (this.position[1] < pos[1]) {
                    for (int i = this.position[1] + 1; i < pos[1]; i++) {
                        if (board[i][this.position[0]].getName() != '-') {
                            return false;
                        }
                    }
                } else {
                    for (int i = pos[1] + 1; i < this.position[1]; i++) {
                        if (board[i][this.position[0]].getName() != '-') {
                            return false;
                        }
                    }
                }
            } else {
                //check if there is other pieces between this rook and destination
                //if yes then return false
                if (this.position[0] < pos[0]) {
                    for (int i = this.position[0] + 1; i < pos[0]; i++) {
                        if (board[i][this.position[1]].getName() != '-') {
                            return false;
                        }
                    }
                } else {
                    for (int i = pos[0] + 1; i < this.position[0]; i++) {
                        if (board[i][this.position[1]].getName() != '-') {
                            return false;
                        }
                    }
                }
            }
            //todo: judge if castling possible
            //if the destination has friendly piece then return false
            //System.out.println("You can't capture piece of yourself!");
            return board[pos[0]][pos[1]].getUser() != this.user;
        }
    }

    public void setCurPosition(int i, int j) {
        this.position[0] = i;
        this.position[1] = j;
    }

    public char getName() {
        return this.name;
    }

    public char getUser() {
        return this.user;
    }

    public void firstStep() {
        this.firstStep = false;
    }

    public boolean isFirstStep() {
        return this.firstStep;
    }

    public boolean enPassant() {
        return false;
    }

    public void cancelEnPassant() {
    }

    @Override
    public Piece copyPiece() {
        Piece returnPiece = this;
        return returnPiece;
    }
}
