import java.util.HashMap;
import java.util.Scanner;

public class Board {
    //chess display and logic
    private Piece[][] board;//board
    private int[][] kingPosition;//record both user's king's current position, [0][] is Black and [1][] is White

    Board(){
        this.board = new Piece[8][8];
        this.kingPosition = new int[2][2];
    }

    public Piece getPiece(String position){
        int yposition = position.charAt(0) - '1';
        yposition = 7 - yposition;
        int xposition = position.charAt(1) - 'a';
        return this.board[yposition][xposition];
    }

    //default board setting
    public void defaultSetting(){
        //initialize default black pieces
        this.board[0][0] = new Rook(new int[]{0,0}, 'b');
        this.board[0][7] = new Rook(new int[]{0,7}, 'b');
        this.board[0][1] = new Knight(new int[]{0,1}, 'b');
        this.board[0][6] = new Knight(new int[]{0,6}, 'b');
        this.board[0][2] = new Bishop(new int[]{0,2}, 'b');
        this.board[0][5] = new Bishop(new int[]{0,5}, 'b');
        this.board[0][3] = new Queen(new int[]{0,3}, 'b');
        this.board[0][4] = new King(new int[]{0,4}, 'b');
        this.kingPosition[0][0] = 0;
        this.kingPosition[0][1] = 4;
        //the second row (all pawns)
        for(int j = 0;j<8;j++){
            this.board[1][j] = new Pawn(new int[]{1,j}, 'b');
        }

        //blank tiles will be filled with nullPieces
        for(int i = 2;i<6;i++){
            for(int j = 0;j<8;j++){
                this.board[i][j] = new nullPiece(i,j);
            }
        }

        //initialize white pawns
        for(int j = 0;j<8;j++){
            this.board[6][j] = new Pawn(new int[]{6,j}, 'w');
        }
        //initialize other white pieces
        this.board[7][0] = new Rook(new int[]{7,0}, 'w');
        this.board[7][7] = new Rook(new int[]{7,7}, 'w');
        this.board[7][1] = new Knight(new int[]{7,1}, 'w');
        this.board[7][6] = new Knight(new int[]{7,6}, 'w');
        this.board[7][2] = new Bishop(new int[]{7,2}, 'w');
        this.board[7][5] = new Bishop(new int[]{7,5}, 'w');
        this.board[7][3] = new Queen(new int[]{7,3}, 'w');
        this.board[7][4] = new King(new int[]{7,4}, 'w');
        this.kingPosition[1][0] = 7;
        this.kingPosition[1][1] = 4;
    }

    //custom board setting
    public void customSetting(){
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                this.board[i][j] = new nullPiece(i,j);
            }
        }
        this.printBoard();
        System.out.println("Please follow the instruction to fill the board");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Please choose one piece type:");
            System.out.println("1. Rook 2. Knight 3. Bishop 4. Queen 5. King 6. Pawn");
            int type = Integer.valueOf(scanner.nextLine());
            System.out.println("Please choose one color:");
            System.out.println("1. Black 2. White");
            int color = Integer.valueOf(scanner.nextLine());
            System.out.println("Please choose a position to put your piece (for example: 1a):");
            String position = scanner.nextLine();
            setPiece(type, color, position);
            this.printBoard();
            System.out.println("1. Continue 2. Quit");
            int command = Integer.valueOf(scanner.nextLine());
            if(command == 2){
                break;
            }
        }
    }

    //print the board
    public void printBoard(){
        for(int i = 0;i<9;i++){
            if(i==0){
                System.out.print("  ");
            }
            else{
                char temp = (char) ('a'-1+i);
                System.out.print(temp+" ");
            }
        }
        System.out.println();
        for(int i = 0;i<8;i++){
            for(int j = 0;j<9;j++){
                if(j == 0){
                    int number = 8-i;
                    System.out.print(number+" ");
                }
                else{
                    System.out.print(this.board[i][j-1].getName()+" ");
                }
            }
            System.out.println();
        }
    }

    //set the specific position to specific piece
    public void setPiece(int type, int color, String position){
        //convert string to int
        int yposition = position.charAt(0) - '1';
        yposition = 7 - yposition;
        int xposition = position.charAt(1) - 'a';
        //if the input is invalid then return
        if(yposition<0||yposition>7||xposition<0||xposition>7){
            System.out.println("Invalid position: out of index");
            return;
        }
        int[] pos = new int[]{yposition,xposition};
        //if the chosen position is not empty
        if(this.board[yposition][xposition].getName()!='-'){
            Scanner s = new Scanner(System.in);
            System.out.println("There is already a piece at the position, do you wanna cover it?");
            System.out.println("1. Cover 2. Cancel");
            int command = Integer.valueOf(s.nextLine());
            if(command == 2){
                return;
            }
        }
        //decide piece's type and color by the input parameters
        //Rook
        if(type == 1){
            if(color == 1){
                this.board[yposition][xposition] = new Rook(pos,'b');
            }
            else {
                this.board[yposition][xposition] = new Rook(pos,'w');
            }
        }
        //Knight
        else if(type == 2){
            if(color == 1){
                this.board[yposition][xposition] = new Knight(pos,'b');
            }
            else {
                this.board[yposition][xposition] = new Knight(pos,'w');
            }
        }
        //Bishop
        else if(type == 3){
            if(color == 1){
                this.board[yposition][xposition] = new Bishop(pos,'b');
            }
            else {
                this.board[yposition][xposition] = new Bishop(pos,'w');
            }
        }
        //Queen
        else if(type == 4){
            if(color == 1){
                this.board[yposition][xposition] = new Queen(pos,'b');
            }
            else {
                this.board[yposition][xposition] = new Queen(pos,'w');
            }
        }
        //King
        else if(type == 5){
            if(color == 1){
                this.board[yposition][xposition] = new King(pos,'b');
                this.kingPosition[0][0] = yposition;
                this.kingPosition[0][1] = xposition;
            }
            else {
                this.board[yposition][xposition] = new King(pos,'w');
                this.kingPosition[1][0] = yposition;
                this.kingPosition[1][1] = xposition;
            }
        }
        //Pawn
        else if(type == 6){
            if(color == 1){
                if(yposition == 7){
                    System.out.println("invalid position: Black Pawn promotion is required");
                    return;
                }
                this.board[yposition][xposition] = new Pawn(pos,'b');
            }
            else {
                if(yposition == 0){
                    System.out.println("invalid position: White Pawn promotion is required");
                    return;
                }
                this.board[yposition][xposition] = new Pawn(pos,'w');
            }
        }
    }

    //judge if the piece can move
    public boolean canMove(String from, String to){
        //convert string to int
        int fyposition = from.charAt(0) - '1';
        fyposition = 7 - fyposition;
        int fxposition = from.charAt(1) - 'a';
        //if out of index then return false
        if(fyposition<0||fyposition>7||fxposition<0||fxposition>7){
            System.out.println("Invalid position: out of index");
            return false;
        }
        //if choose null as from then return false
        if(this.board[fyposition][fxposition].getName()=='-'){
            System.out.println("Invalid position: no piece");
            return false;
        }
        //convert string to int
        int typosition = to.charAt(0) - '1';
        typosition = 7 - typosition;
        int txposition = to.charAt(1) - 'a';
        //if out of index then return false
        if(typosition<0||typosition>7||txposition<0||txposition>7){
            System.out.println("Invalid position: out of index");
            return false;
        }
        if(fyposition==typosition&&fxposition==txposition){
            System.out.println("Invalid position: you can't move the piece to where it is!");
            return false;
        }
        //call piece.canMove()
        if(this.board[fyposition][fxposition].canMove(new int[]{typosition, txposition}, this.board)){
            return true;
        }
        else{
            return false;
        }
    }

    //override canMove() when parameters are integers
    public boolean canMove(int fy, int fx, int ty, int tx){
        //if out of index then return false
        if(fy<0||fy>7||fx<0||fx>7){
            System.out.println("Invalid position: out of index");
            return false;
        }
        //if choose null as from then return false
        if(this.board[fy][fx].getName()=='-'){
            System.out.println("Invalid position: no piece");
            return false;
        }
        //if out of index then return false
        if(ty<0||ty>7||tx<0||tx>7){
            System.out.println("Invalid position: out of index");
            return false;
        }
        //if the position of from and to are the same, return false
        if(fy==ty&&fx==tx){
            System.out.println("Invalid position: you can't move the piece to where it is!");
            return false;
        }
        //call piece.canMove()
        if(this.board[fy][fx].canMove(new int[]{ty, tx}, this.board)){
            return true;
        }
        else{
            return false;
        }
    }

    //move the piece
    //notice that this method is called only when the piece can move from one tile to another tile
    public void movePiece(String from, String to){
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
        this.board[fyposition][fxposition] = new nullPiece(fyposition,fxposition);
        char user = this.board[typosition][txposition].getUser();
        //if the user is checkmated after the movement then recover the movement
        if(user=='b'){
            if(isCheckmated(1)){
                System.out.println("Invalid movement: You are checkmated after this movement");
                this.board[fyposition][fxposition] = this.board[typosition][txposition];
                this.board[typosition][txposition] = temp;
                return;
            }
        }
        else if(user=='w'){
            if(isCheckmated(2)){
                System.out.println("Invalid movement: You are checkmated after this movement");
                this.board[fyposition][fxposition] = this.board[typosition][txposition];
                this.board[typosition][txposition] = temp;
                return;
            }
        }
        //if the king is moved then update the king's position
        if(this.board[typosition][txposition].getName()=='k'){
            this.kingPosition[1][0] = typosition;
            this.kingPosition[1][1] = txposition;
        }
        if(this.board[typosition][txposition].getName()=='K'){
            this.kingPosition[0][0] = typosition;
            this.kingPosition[0][1] = txposition;
        }
        this.board[typosition][txposition].firstStep();
    }

    //check if one user's king is checkmated
    //user==1 ----check Black
    //user==2 ----check White
    public boolean isCheckmated(int user){
        if(user==1){
            for(int i = 0;i<8;i++){
                for(int j = 0;j<8;j++){
                    if(this.board[i][j].getName()!='-'&&this.board[i][j].getUser()=='w'){
                        if(canMove(i,j,this.kingPosition[0][0],this.kingPosition[0][1])){
                            return true;
                        }
                    }
                }
            }
        }
        else{
            for(int i = 0;i<8;i++){
                for(int j = 0;j<8;j++){
                    if(this.board[i][j].getName()!='-'&&this.board[i][j].getUser()=='b'){
                        if(canMove(i,j,this.kingPosition[0][0],this.kingPosition[0][1])){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
