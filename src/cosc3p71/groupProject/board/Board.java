package cosc3p71.groupProject.board;

import java.util.Scanner;

import cosc3p71.groupProject.interfaces.Piece;
import cosc3p71.groupProject.pieces.*;

public class Board {
    //chess display and logic
    private Piece[][] board;//board
    private int[][] kingPosition;//record both user's king's current position, [0][] is Black and [1][] is White

    public Board() {
        this.board = new Piece[8][8];
        this.kingPosition = new int[2][2];
    }

    public Piece getPiece(String position) {
        int yposition = position.charAt(0) - '1';
        yposition = 7 - yposition;
        int xposition = position.charAt(1) - 'a';
        return this.board[yposition][xposition];
    }

    public Piece getPiece(int x, int y) {
        return this.board[x][y];
    }

    //default board setting
    public void defaultSetting() {
        //initialize default black pieces
        this.board[0][0] = new Rook(new int[]{0, 0}, 'b');
        this.board[0][7] = new Rook(new int[]{0, 7}, 'b');
        this.board[0][1] = new Knight(new int[]{0, 1}, 'b');
        this.board[0][6] = new Knight(new int[]{0, 6}, 'b');
        this.board[0][2] = new Bishop(new int[]{0, 2}, 'b');
        this.board[0][5] = new Bishop(new int[]{0, 5}, 'b');
        this.board[0][3] = new Queen(new int[]{0, 3}, 'b');
        this.board[0][4] = new King(new int[]{0, 4}, 'b');
        this.kingPosition[0][0] = 0;
        this.kingPosition[0][1] = 4;
        //the second row (all pawns)
        for (int j = 0; j < 8; j++) {
            this.board[1][j] = new Pawn(new int[]{1, j}, 'b');
        }

        //blank tiles will be filled with nullPieces
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new nullPiece(i, j);
            }
        }

        //initialize white pawns
        for (int j = 0; j < 8; j++) {
            this.board[6][j] = new Pawn(new int[]{6, j}, 'w');
        }
        //initialize other white pieces
        this.board[7][0] = new Rook(new int[]{7, 0}, 'w');
        this.board[7][7] = new Rook(new int[]{7, 7}, 'w');
        this.board[7][1] = new Knight(new int[]{7, 1}, 'w');
        this.board[7][6] = new Knight(new int[]{7, 6}, 'w');
        this.board[7][2] = new Bishop(new int[]{7, 2}, 'w');
        this.board[7][5] = new Bishop(new int[]{7, 5}, 'w');
        this.board[7][3] = new Queen(new int[]{7, 3}, 'w');
        this.board[7][4] = new King(new int[]{7, 4}, 'w');
        this.kingPosition[1][0] = 7;
        this.kingPosition[1][1] = 4;
    }

    //custom board setting
    public void customSetting() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new nullPiece(i, j);
            }
        }
        this.printBoard();
        System.out.println("Please follow the instruction to fill the board");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose one piece type:");
            System.out.println("1. Rook 2. Knight 3. Bishop 4. Queen 5. King 6. Pawn");
            int type = Integer.valueOf(scanner.nextLine());
            System.out.println("Please choose one color:");
            System.out.println("1. Black 2. White");
            int color = Integer.valueOf(scanner.nextLine());
            System.out.println("Please choose a position to put your piece (for example: 1a):");
            String position = scanner.nextLine();
            setPiece(type, color, position, 0);
            this.printBoard();
            System.out.println("1. Continue 2. Quit");
            int command = Integer.valueOf(scanner.nextLine());
            if (command == 2) {
                break;
            }
        }
    }

    //print the board
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                char temp = (char) ('a' - 1 + i);
                System.out.print(temp + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    int number = 8 - i;
                    System.out.print(number + " ");
                } else {
                    System.out.print(this.board[i][j - 1].getName() + " ");
                }
            }
            System.out.println();
        }
    }

    //set the specific position to specific piece
    public void setPiece(int type, int color, String position, int considerCover) {
        //convert string to int
        int yposition = position.charAt(0) - '1';
        yposition = 7 - yposition;
        int xposition = position.charAt(1) - 'a';
        //if the input is invalid then return
        if (yposition < 0 || yposition > 7 || xposition < 0 || xposition > 7) {
            System.out.println("Invalid position: out of index");
            return;
        }
        int[] pos = new int[]{yposition, xposition};
        //if the chosen position is not empty and we need to consider about it
        if (considerCover == 0) {
            if (this.board[yposition][xposition].getName() != '-') {
                Scanner s = new Scanner(System.in);
                System.out.println("There is already a piece at the position, do you wanna cover it?");
                System.out.println("1. Cover 2. Cancel");
                int command = Integer.valueOf(s.nextLine());
                if (command == 2) {
                    return;
                }
            }
        }
        //decide piece's type and color by the input parameters
        //Rook
        if (type == 1) {
            if (color == 1) {
                this.board[yposition][xposition] = new Rook(pos, 'b');
            } else {
                this.board[yposition][xposition] = new Rook(pos, 'w');
            }
        }
        //Knight
        else if (type == 2) {
            if (color == 1) {
                this.board[yposition][xposition] = new Knight(pos, 'b');
            } else {
                this.board[yposition][xposition] = new Knight(pos, 'w');
            }
        }
        //Bishop
        else if (type == 3) {
            if (color == 1) {
                this.board[yposition][xposition] = new Bishop(pos, 'b');
            } else {
                this.board[yposition][xposition] = new Bishop(pos, 'w');
            }
        }
        //Queen
        else if (type == 4) {
            if (color == 1) {
                this.board[yposition][xposition] = new Queen(pos, 'b');
            } else {
                this.board[yposition][xposition] = new Queen(pos, 'w');
            }
        }
        //King
        else if (type == 5) {
            if (color == 1) {
                this.board[yposition][xposition] = new King(pos, 'b');
                this.kingPosition[0][0] = yposition;
                this.kingPosition[0][1] = xposition;
            } else {
                this.board[yposition][xposition] = new King(pos, 'w');
                this.kingPosition[1][0] = yposition;
                this.kingPosition[1][1] = xposition;
            }
        }
        //Pawn
        else if (type == 6) {
            if (color == 1) {
                if (yposition == 7) {
                    System.out.println("invalid position: Black Pawn promotion is required");
                    return;
                }
                this.board[yposition][xposition] = new Pawn(pos, 'b');
            } else {
                if (yposition == 0) {
                    System.out.println("invalid position: White Pawn promotion is required");
                    return;
                }
                this.board[yposition][xposition] = new Pawn(pos, 'w');
            }
        }
    }

    public void setPiece(int y, int x, Piece piece) {
        this.board[y][x] = piece;
        piece.setCurPosition(y, x);
    }

    //judge if the piece can move
    public boolean canMove(String from, String to) {
        //convert string to int
        int fyposition = from.charAt(0) - '1';
        fyposition = 7 - fyposition;
        int fxposition = from.charAt(1) - 'a';
        //if out of index then return false
        if (fyposition < 0 || fyposition > 7 || fxposition < 0 || fxposition > 7) {
            System.out.println("Invalid position: out of index");
            return false;
        }
        //if choose null as from then return false
        if (this.board[fyposition][fxposition].getName() == '-') {
            System.out.println("Invalid position: no piece");
            return false;
        }
        //convert string to int
        int typosition = to.charAt(0) - '1';
        typosition = 7 - typosition;
        int txposition = to.charAt(1) - 'a';
        //if out of index then return false
        if (typosition < 0 || typosition > 7 || txposition < 0 || txposition > 7) {
            System.out.println("Invalid position: out of index");
            return false;
        }
        if (fyposition == typosition && fxposition == txposition) {
            System.out.println("Invalid position: you can't move the piece to where it is!");
            return false;
        }
        //call piece.canMove()
        return this.board[fyposition][fxposition].canMove(new int[]{typosition, txposition}, this.board);
    }

    //override canMove() when parameters are integers
    public boolean canMove(int fy, int fx, int ty, int tx) {
        //if out of index then return false
        if (fy < 0 || fy > 7 || fx < 0 || fx > 7) {
            System.out.println("Invalid position: out of index");
            return false;
        }
        //if choose null as from then return false
        if (this.board[fy][fx].getName() == '-') {
            System.out.println("Invalid position: no piece");
            return false;
        }
        //if out of index then return false
        if (ty < 0 || ty > 7 || tx < 0 || tx > 7) {
            System.out.println("Invalid position: out of index");
            return false;
        }
        //if the position of from and to are the same, return false
        if (fy == ty && fx == tx) {
            //System.out.println("Invalid position: you can't move the piece to where it is!");
            return false;
        }
        //call piece.canMove()
        if (this.board[fy][fx].canMove(new int[]{ty, tx}, this.board)) {
            return true;
        } else {
            return false;
        }
    }

    //move the piece
    //notice that this method is called only when the piece can move from one tile to another tile
    //provide user hint if the parameter hint is 1
    public boolean movePiece(String from, String to, int hint) {
        //convert string to int
        int fyposition = from.charAt(0) - '1';
        fyposition = 7 - fyposition;
        int fxposition = from.charAt(1) - 'a';
        int typosition = to.charAt(0) - '1';
        typosition = 7 - typosition;
        int txposition = to.charAt(1) - 'a';
        //store the piece that might be covered later
        Piece temp = this.board[typosition][txposition];
        //finish the movement
        this.board[typosition][txposition] = this.board[fyposition][fxposition];
        this.board[fyposition][fxposition] = new nullPiece(fyposition, fxposition);
        char user = this.board[typosition][txposition].getUser();

        //if the king is moved then update the king's position
        if (this.board[typosition][txposition].getName() == 'k') {
            this.kingPosition[1][0] = typosition;
            this.kingPosition[1][1] = txposition;
        }
        if (this.board[typosition][txposition].getName() == 'K') {
            this.kingPosition[0][0] = typosition;
            this.kingPosition[0][1] = txposition;
        }

        //if the user is checkmated after the movement then recover the movement
        if (user == 'b') {
            if (isCheckmated(1)) {
                if (hint == 1) {
                    System.out.println("Invalid movement: You are checkmated after this movement");
                }
                this.board[fyposition][fxposition] = this.board[typosition][txposition];
                this.board[typosition][txposition] = temp;
                if (this.board[typosition][txposition].getName() == 'K') {
                    this.kingPosition[0][0] = fyposition;
                    this.kingPosition[0][1] = fxposition;
                }
                return false;
            }
        } else if (user == 'w') {
            if (isCheckmated(2)) {
                if (hint == 1) {
                    System.out.println("Invalid movement: You are checkmated after this movement");
                }
                this.board[fyposition][fxposition] = this.board[typosition][txposition];
                this.board[typosition][txposition] = temp;
                if (this.board[typosition][txposition].getName() == 'k') {
                    this.kingPosition[1][0] = fyposition;
                    this.kingPosition[1][1] = fxposition;
                }
                return false;
            }
        }
        //if the movement is valid then update position information
        this.board[typosition][txposition].setCurPosition(typosition, txposition);

        //if there is pawn promotion:
        if (typosition == 0 && this.board[typosition][txposition].getName() == 'p') {
            System.out.println("Your pawn has the chance to promote now!");
            System.out.println("Please choose one type of piece: ");
            System.out.println("1. Rook 2. Knight 3. Bishop 4. Queen 5. King 6. Pawn");
            Scanner scanner = new Scanner(System.in);
            int type = Integer.valueOf(scanner.nextLine());
            setPiece(type, 2, to, 1);
        } else if (typosition == 7 && this.board[typosition][txposition].getName() == 'P') {
            System.out.println("Your pawn has the chance to promote now!");
            System.out.println("Please choose one type of piece: ");
            System.out.println("1. Rook 2. Knight 3. Bishop 4. Queen 5. King 6. Pawn");
            Scanner scanner = new Scanner(System.in);
            int type = Integer.valueOf(scanner.nextLine());
            setPiece(type, 1, to, 1);
        }

        //if there is enPassant
        if (this.board[typosition][txposition].getName() == 'p') {
            if (this.board[typosition + 1][txposition].enPassant()) {
                this.board[typosition + 1][txposition] = new nullPiece(typosition + 1, txposition);
            }
        }
        if (this.board[typosition][txposition].getName() == 'P') {
            if (this.board[typosition - 1][txposition].enPassant()) {
                this.board[typosition - 1][txposition] = new nullPiece(typosition - 1, txposition);
            }
        }
        this.board[typosition][txposition].firstStep();
        return true;
    }

    //override when parameters are integers (only used for checking if the game is over)
    public boolean movePiece(int fy, int fx, int ty, int tx) {
        //store the piece that might be covered later
        Piece temp = this.board[ty][tx];
        //finish the movement
        this.board[ty][tx] = this.board[fy][fx];
        this.board[fy][fx] = new nullPiece(fy, fx);
        char user = this.board[ty][tx].getUser();
        //if the king is moved then update the king's position
        if (this.board[ty][tx].getName() == 'k') {
            this.kingPosition[1][0] = ty;
            this.kingPosition[1][1] = tx;
        }
        if (this.board[ty][tx].getName() == 'K') {
            this.kingPosition[0][0] = ty;
            this.kingPosition[0][1] = tx;
        }
        //if the user is checkmated after the movement then recover the movement
        if (user == 'b') {
            if (this.isCheckmated(1)) {
                this.board[fy][fx] = this.board[ty][tx];
                this.board[ty][tx] = temp;
                if (this.board[fy][fx].getName() == 'K') {
                    this.kingPosition[0][0] = fy;
                    this.kingPosition[0][1] = fx;
                }
                return false;
            }
        } else if (user == 'w') {
            if (this.isCheckmated(2)) {
                this.board[fy][fx] = this.board[ty][tx];
                this.board[ty][tx] = temp;
                if (this.board[fy][fx].getName() == 'k') {
                    this.kingPosition[1][0] = fy;
                    this.kingPosition[1][1] = fx;
                }
                return false;
            }
        }
        this.board[fy][fx] = this.board[ty][tx];
        this.board[ty][tx] = temp;
        return true;
    }

    //check if one user's king is checkmated
    //user==1 ----check Black
    //user==2 ----check White
    public boolean isCheckmated(int user) {
        if (user == 1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.board[i][j].getName() != '-' && this.board[i][j].getUser() == 'w') {
                        if (canMove(i, j, this.kingPosition[0][0], this.kingPosition[0][1])) {
                            if(movePiece(i,j,this.kingPosition[0][0], this.kingPosition[0][1])){
                                return true;
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.board[i][j].getName() != '-' && this.board[i][j].getUser() == 'b') {
                        if (canMove(i, j, this.kingPosition[1][0], this.kingPosition[1][1])) {
                            if(movePiece(i,j,this.kingPosition[1][0], this.kingPosition[1][1])){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //check if one user's king is checkmated and there is no valid movement
    //user==1 ----check Black
    //user==2 ----check White
    public boolean isGameover(int user) {
        int gameover = 0;// if gameover = 0 in the end then the game is over
        //check if black have some pieces that can move without being checkmated
        if (user == 1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //if it is black piece
                    if (this.board[i][j].getName() != '-' && this.board[i][j].getUser() == 'b') {
                        //check if there is somewhere the piece can go
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                //if there is somewhere to go then gameover is 1
                                if (canMove(i, j, k, l)) {
                                    if (movePiece(i, j, k, l)) {
                                        gameover = 1;
                                        break;
                                    }
                                }
                            }
                            if (gameover == 1) {
                                break;
                            }
                        }
                    }
                    if (gameover == 1) {
                        break;
                    }
                }
                if (gameover == 1) {
                    break;
                }
            }
        }
        //same logic but for white
        else if (user == 2) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.board[i][j].getName() != '-' && this.board[i][j].getUser() == 'w') {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (canMove(i, j, k, l)) {
                                    if (movePiece(i, j, k, l)) {
                                        gameover = 1;
                                        break;
                                    }
                                }
                            }
                            if (gameover == 1) {
                                break;
                            }
                        }
                    }
                    if (gameover == 1) {
                        break;
                    }
                }
                if (gameover == 1) {
                    break;
                }
            }
        }
        return (gameover == 0);
    }

    //user == 1: black
    //user == 2: white
    public boolean canCastling(int user) {
        if (user == 1) {
            //if the king or the rook is already moved return false
            if (!board[this.kingPosition[0][0]][this.kingPosition[0][1]].isFirstStep()) {
                return false;
            }
            int temp = this.kingPosition[0][1];
            for (int i = 0; i < 8; i++) {
                if (board[0][i].getName() == 'R') {
                    //search for rook which is not moved yet
                    if (!board[0][i].isFirstStep()) {
                        continue;
                    } else {
                        if (i == 0) {
                            //if there is a piece between king and rook then return false
                            for (int j = 1; j < 4; j++) {
                                if (board[0][j].getName() != '-') {
                                    return false;
                                }
                            }
                            //if the king will be checkmated in the movement then return false
                            for (int j = 1; j < 3; j++) {
                                this.kingPosition[0][1]--;
                                if (isCheckmated(1)) {
                                    this.kingPosition[0][1] = temp;
                                    return false;
                                }
                            }
                            this.kingPosition[0][1] = temp;
                            return true;
                        } else if (i == 7) {
                            for (int j = 5; j < 7; j++) {
                                if (board[0][j].getName() != '-') {
                                    return false;
                                }
                            }
                            for (int j = 1; j < 3; j++) {
                                this.kingPosition[0][1]++;
                                if (isCheckmated(1)) {
                                    this.kingPosition[0][1] = temp;
                                    return false;
                                }
                            }
                            this.kingPosition[0][1] = temp;
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        //for white:
        //the logic is the same with black
        else {
            if (!board[this.kingPosition[1][0]][this.kingPosition[1][1]].isFirstStep()) {
                return false;
            }
            int temp = this.kingPosition[1][1];
            for (int i = 0; i < 8; i++) {
                if (board[7][i].getName() == 'r') {
                    if (!board[7][i].isFirstStep()) {
                        continue;
                    } else {
                        if (i == 0) {
                            for (int j = 1; j < 4; j++) {
                                if (board[7][j].getName() != '-') {
                                    return false;
                                }
                            }
                            for (int j = 1; j < 3; j++) {
                                this.kingPosition[1][1]--;
                                if (isCheckmated(2)) {
                                    this.kingPosition[1][1] = temp;
                                    return false;
                                }
                            }
                            this.kingPosition[1][1] = temp;
                            return true;
                        } else if (i == 7) {
                            for (int j = 5; j < 7; j++) {
                                if (board[7][j].getName() != '-') {
                                    return false;
                                }
                            }
                            for (int j = 1; j < 3; j++) {
                                this.kingPosition[1][1]++;
                                if (isCheckmated(2)) {
                                    this.kingPosition[1][1] = temp;
                                    return false;
                                }
                            }
                            this.kingPosition[1][1] = temp;
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    //user == 1: Black
    //user == 2: White
    public boolean castling(int user, String positionR) {
        //convert String to int
        int fyposition = positionR.charAt(0) - '1';
        fyposition = 7 - fyposition;
        int fxposition = positionR.charAt(1) - 'a';
        if (this.board[fyposition][fxposition].getName() != 'R' && this.board[fyposition][fxposition].getName() != 'r') {
            System.out.println("Invalid castling.");
            return false;
        }
        //check if black can castling
        if (user == 1) {
            int temp = this.kingPosition[0][1];
            //if the rook position is 8a
            if (fxposition == 0) {
                boolean canCastling = true;
                //if there is piece between rook and king then no castling
                for (int j = 1; j < 4; j++) {
                    if (board[0][j].getName() != '-') {
                        canCastling = false;
                        break;
                    }
                }
                //if the king will be checkmated along the path then no castling
                for (int j = 1; j < 3; j++) {
                    this.kingPosition[0][1]--;
                    if (isCheckmated(1)) {
                        this.kingPosition[0][1] = temp;
                        canCastling = false;
                        break;
                    }
                }
                //recover the king position
                this.kingPosition[0][1] = temp;
                if (canCastling) {
                    this.board[0][2] = new King(new int[]{0, 2}, 'b');
                    this.board[0][3] = new Rook(new int[]{0, 3}, 'b');
                    this.board[0][0] = new nullPiece(0, 0);
                    this.board[0][4] = new nullPiece(0, 4);
                    this.kingPosition[0][1] = 2;
                    return true;
                } else {
                    System.out.println("Invalid castling.");
                    return false;
                }
            }
            //if the rook's position is 8h
            else if (fxposition == 7) {
                boolean canCastling = true;
                for (int j = 5; j < 7; j++) {
                    if (board[0][j].getName() != '-') {
                        canCastling = false;
                        break;
                    }
                }
                for (int j = 1; j < 3; j++) {
                    this.kingPosition[0][1]++;
                    if (isCheckmated(1)) {
                        this.kingPosition[0][1] = temp;
                        canCastling = false;
                        break;
                    }
                }
                this.kingPosition[0][1] = temp;
                if (canCastling) {
                    this.board[0][6] = new King(new int[]{0, 6}, 'b');
                    this.board[0][5] = new Rook(new int[]{0, 5}, 'b');
                    this.board[0][7] = new nullPiece(0, 7);
                    this.board[0][4] = new nullPiece(0, 4);
                    this.kingPosition[0][1] = 6;
                    return true;
                } else {
                    System.out.println("Invalid castling.");
                    return false;
                }
            }
        } else {
            int temp = this.kingPosition[1][1];
            if (fxposition == 0) {
                boolean canCastling = true;
                for (int j = 1; j < 4; j++) {
                    if (board[7][j].getName() != '-') {
                        canCastling = false;
                        break;
                    }
                }
                for (int j = 1; j < 3; j++) {
                    this.kingPosition[1][1]--;
                    if (isCheckmated(1)) {
                        this.kingPosition[1][1] = temp;
                        canCastling = false;
                        break;
                    }
                }
                this.kingPosition[1][1] = temp;
                if (canCastling) {
                    this.board[7][2] = new King(new int[]{7, 2}, 'b');
                    this.board[7][3] = new Rook(new int[]{7, 3}, 'b');
                    this.board[7][0] = new nullPiece(7, 0);
                    this.board[7][4] = new nullPiece(7, 4);
                    this.kingPosition[1][1] = 2;
                    return true;
                }
                System.out.println("Invalid castling.");
                return false;
            } else if (fxposition == 7) {
                boolean canCastling = true;
                for (int j = 5; j < 7; j++) {
                    if (board[7][j].getName() != '-') {
                        canCastling = false;
                        break;
                    }
                }
                for (int j = 1; j < 3; j++) {
                    this.kingPosition[1][1]++;
                    if (isCheckmated(2)) {
                        this.kingPosition[1][1] = temp;
                        canCastling = false;
                        break;
                    }
                }
                this.kingPosition[0][1] = temp;
                if (canCastling) {
                    this.board[7][6] = new King(new int[]{7, 6}, 'b');
                    this.board[7][5] = new Rook(new int[]{7, 5}, 'b');
                    this.board[7][7] = new nullPiece(7, 7);
                    this.board[7][4] = new nullPiece(7, 4);
                    this.kingPosition[1][1] = 6;
                    return true;
                }
                System.out.println("Invalid castling.");
                return false;
            }
        }
        return false;
    }

    //user == 1: black
    //user == 2: white
    //if the user can en passant but didn't do it at that turn, then make all pawn's enPassant to false
    public void cancelEnPassant(int user) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j].getUser() == 'b' && user == 1) {
                    this.board[i][j].cancelEnPassant();
                } else if (this.board[i][j].getUser() == 'w' && user == 2) {
                    this.board[i][j].cancelEnPassant();
                }
            }
        }
    }

    //heuristic:
    //Pawn: 1 mark
    //Rook: 6 mark
    //Queen: 9 mark
    //Bishop: 3 mark
    //Knight: 3 mark
    public int heuristic(int user) {
        int sum = 0;
        if (user == 1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.board[i][j].getName() == 'P') {
                        sum += 1;
                    } else if (this.board[i][j].getName() == 'R') {
                        sum += 6;
                    } else if (this.board[i][j].getName() == 'Q') {
                        sum += 9;
                    } else if (this.board[i][j].getName() == 'B') {
                        sum += 3;
                    } else if (this.board[i][j].getName() == 'N') {
                        sum += 3;
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.board[i][j].getName() == 'p') {
                        sum += 1;
                    } else if (this.board[i][j].getName() == 'r') {
                        sum += 6;
                    } else if (this.board[i][j].getName() == 'q') {
                        sum += 9;
                    } else if (this.board[i][j].getName() == 'b') {
                        sum += 3;
                    } else if (this.board[i][j].getName() == 'n') {
                        sum += 3;
                    }
                }
            }
        }
        return sum;
    }

    //return a new board which is as same as this.board
    //totally new board, not a pointer,
    public Board copyBoard() {
        Board copy = new Board();
        copy.kingPosition[0][0] = this.kingPosition[0][0];
        copy.kingPosition[0][1] = this.kingPosition[0][1];
        copy.kingPosition[1][0] = this.kingPosition[1][0];
        copy.kingPosition[1][1] = this.kingPosition[1][1];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //todo: if there is a reference problem then just write logic for copying temp
                Piece temp = this.getPiece(i, j);
                //todo:
                //write logic to judge the type of temp: P,R,B,N,K,Q
                //get three information: position:i, j type: P,R,B,N,K,Q user:which user()
                //temp.getName()-- type R is black r is white
                //Piece ret = new Rook(new int[]{x,x}, user)
                //copy.setPiece(i,j,ret)
                copy.setPiece(i, j, temp.copyPiece());
            }
        }
        return copy;
    }
}
