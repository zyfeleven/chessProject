public class Pawn implements Piece {
    private int[] position;
    private final char user;
    private final char name;
    private boolean firstStep;
    private boolean enPassant;

    Pawn(int[] position, char user) {
        this.position = position;
        this.user = user;
        this.enPassant = false;
        if (user == 'b') {
            this.name = 'P';
        } else {
            this.name = 'p';
        }
        this.firstStep = true;
    }

    ////Move rule for Pawn: only move forward one tile or two tiles(only for the first step)
    public boolean canMove(int[] pos, Piece[][] board) {
        if (this.user == 'w') {
            if (pos[1] != this.position[1]) {
                if (this.position[0] - pos[0] == 1 && Math.abs(this.position[1] - pos[1]) == 1) {
                    if (board[pos[0]][pos[1]].getUser() == 'b') {
                        return true;
                    }
                    if (board[pos[0]][pos[1]].getUser() == '-' && board[pos[0] + 1][pos[1]].enPassant()) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
            if (this.position[0] - pos[0] != 1 && this.position[0] - pos[0] != 2) {
                return false;
            }
            if (this.position[0] - pos[0] == 2 && !firstStep) {
                return false;
            }
        } else {
            if (pos[1] != this.position[1]) {
                if (pos[0] - this.position[0] == 1 && Math.abs(pos[1] - this.position[1]) == 1) {
                    if (board[pos[0]][pos[1]].getUser() == 'w') {
                        return true;
                    }
                    if (board[pos[0]][pos[1]].getUser() == '-' && board[pos[0] - 1][pos[1]].enPassant()) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
            if (pos[0] - this.position[0] != 1 && pos[0] - this.position[0] != 2) {
                return false;
            }
            if (pos[0] - this.position[0] == 2 && !firstStep) {
                return false;
            }
        }

        //if the destination has piece then return false
        if (board[pos[0]][pos[1]].getName() != '-') {
            //System.out.println("You can't capture piece of yourself!");
            return false;
        }
        if (Math.abs(pos[0] - this.position[0]) == 2) {
            this.enPassant = true;
        }
        return true;
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
        return this.enPassant;
    }

    public void cancelEnPassant() {
        this.enPassant = false;
    }
}
