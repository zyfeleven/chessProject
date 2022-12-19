package cosc3p71.groupProject.pieces;

import cosc3p71.groupProject.interfaces.Piece;

public class Knight implements Piece {
    private int[] position;
    private final char user;
    private final char name;
    private boolean firstStep;

    public Knight(int[] position, char user) {
        this.position = position;
        this.user = user;
        if (user == 'b') {
            this.name = 'N';
        } else {
            this.name = 'n';
        }
        this.firstStep = true;
    }

    ////Move rule for Knight: move in L-shape pattern
    public boolean canMove(int[] pos, Piece[][] board) {
        if (Math.abs(pos[0] - this.position[0]) > 2) {
            return false;
        }
        if (Math.abs(pos[1] - this.position[1]) > 2) {
            return false;
        }
        if (Math.abs(pos[0] - this.position[0]) <= 1 && Math.abs(pos[1] - this.position[1]) <= 1) {
            return false;
        }
        if (Math.abs(pos[0] - this.position[0]) == 2 && Math.abs(pos[1] - this.position[1]) == 2) {
            return false;
        }
        if (pos[0] == this.position[0] || pos[1] == this.position[1]) {
            return false;
        }
        //if the destination has friendly piece then return false
        //System.out.println("You can't capture piece of yourself!");
        return board[pos[0]][pos[1]].getUser() != this.user;
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
