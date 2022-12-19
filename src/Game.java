import cosc3p71.groupProject.board.Board;
import cosc3p71.groupProject.board.Move;
import cosc3p71.groupProject.board.Record;
import cosc3p71.groupProject.interfaces.Piece;
import cosc3p71.groupProject.pieces.Queen;
import cosc3p71.groupProject.pieces.nullPiece;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // game control process : start, choose user(PVP OR PV AI), judge if the game is over, restart...
    private Board board;
    private boolean isCheckedB;
    private boolean isCheckedW;
    private boolean gameOver;

    Game() {
        System.out.println("Welcome to the chess game!");
        System.out.println("Please choose mode: ");
        System.out.println("1. User vs User  2. User vs AI  3. Practice mode 4. AI vs AI (just for fun)");
        Scanner scanner = new Scanner(System.in);
        int mode = Integer.parseInt(scanner.nextLine());
        if (mode == 1) {
            this.pvp();
        } else if (mode == 2) {
            this.pvAi();
        } else if (mode == 3) {
            this.practice();
        }
        else if(mode == 4){
            this.AIvAI();
        }
    }

    public void pvp() {
        this.board = new Board();
        board.defaultSetting();
        this.isCheckedB = false;
        this.isCheckedW = false;
        this.gameOver = false;
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        while (!this.gameOver) {
            //white movement
            while (!this.gameOver) {
                String from = "";
                String to = "";
                boolean castling = false;
                //ask user to choose a piece
                while (!this.gameOver) {
                    this.board.printBoard();
                    isCheckedW = this.board.isChecked(2);
                    //if game is over then break
                    if (this.board.isGameover(2)) {
                        winner = "Black";
                        this.gameOver = true;
                        break;
                    }
                    //give the hint for check
                    if (isCheckedW) {
                        System.out.println("You're checked!");
                    } else {
                        // if the user can castling then provide the option for castling
                        if (this.board.canCastling(2)) {
                            System.out.println("White's turn, you have the option of castling now, do you wanna castling?");
                            System.out.println("1. Yes 2. No");
                            int choice = Integer.valueOf(scanner.nextLine());
                            if (choice == 1) {
                                System.out.println("Please input the position of the rook in castling: ");
                                String rook = scanner.nextLine();
                                castling = this.board.castling(2, rook);
                            }
                        }
                    }
                    if (castling) {
                        break;
                    }
                    System.out.println("White's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if (board.getPiece(from).getUser() != 'w') {
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is " + from);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if (str.length() != 1) {
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if (command == 1) {
                        break;
                    } else {
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                //ask the user which position to go
                while (!this.gameOver) {
                    if (castling) {
                        break;
                    }
                    this.board.printBoard();
                    System.out.println("You have selected " + from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is " + to);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if (str.length() != 1) {
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if (command == 1) {
                        break;
                    } else {
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                if (this.gameOver) {
                    break;
                }
                //move the piece and cancel the en passant(if the user didn't do it then there is no en passant anymore)
                if (this.board.canMove(from, to)) {
                    if (this.board.movePiece(from, to, 1)) {
                        this.board.cancelEnPassant(1);
                        break;
                    } else {
                        System.out.println("Invalid movement: please follow the rule");
                    }
                } else {
                    System.out.println("Invalid movement: please follow the rule");
                }

            }
            //black movement
            //most logics are the same but in a different side(different color)
            while (!this.gameOver) {
                String from = "";
                String to = "";
                boolean castling = false;
                while (!this.gameOver) {
                    this.board.printBoard();
                    isCheckedB = this.board.isChecked(1);
                    if (this.board.isGameover(1)) {
                        winner = "White";
                        this.gameOver = true;
                        break;
                    }
                    //hint for checkmated
                    if (isCheckedB) {
                        System.out.println("You're checked!");
                    } else {
                        //if the user can have castling
                        if (this.board.canCastling(1)) {
                            System.out.println("Black's turn, you have the option of castling now, do you wanna castling?");
                            System.out.println("1. Yes 2. No");
                            int choice = Integer.valueOf(scanner.nextLine());
                            if (choice == 1) {
                                System.out.println("Please input the position of the rook in castling: ");
                                String rook = scanner.nextLine();
                                castling = this.board.castling(1, rook);
                            }
                        }
                    }
                    if (castling) {
                        break;
                    }
                    System.out.println("Black's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if (board.getPiece(from).getUser() != 'b') {
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is " + from);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if (str.length() != 1) {
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if (command == 1) {
                        break;
                    } else {
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                while (!this.gameOver) {
                    if (castling) {
                        break;
                    }
                    this.board.printBoard();
                    System.out.println("You have selected " + from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is " + to);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if (str.length() != 1) {
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if (command == 1) {
                        break;
                    } else {
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                if (this.gameOver) {
                    break;
                }
                if (castling) {
                    break;
                }
                if (this.board.canMove(from, to)) {
                    if (this.board.movePiece(from, to, 1)) {
                        this.board.cancelEnPassant(2);
                        break;
                    } else {
                        System.out.println("Invalid movement: please follow the rule");
                    }
                } else {
                    System.out.println("Invalid movement: please follow the rule");
                }
            }
        }
        System.out.println("Game over! The winner is " + winner + "!");
    }

    public void practice() {
        this.board = new Board();
        board.customSetting();
        this.isCheckedB = false;
        this.isCheckedW = false;
        this.gameOver = false;
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        while (!this.gameOver) {
            //white movement
            while (!this.gameOver) {
                String from = "";
                String to = "";
                boolean castling = false;
                while (!this.gameOver) {
                    this.board.printBoard();
                    isCheckedW = this.board.isChecked(2);
                    if (this.board.isGameover(2)) {
                        winner = "Black";
                        this.gameOver = true;
                        break;
                    }
                    if (isCheckedW) {
                        System.out.println("You're checked!");
                    } else {
                        if (this.board.canCastling(2)) {
                            System.out.println("White's turn, you have the option of castling now, do you wanna castling?");
                            System.out.println("1. Yes 2. No");
                            int choice = Integer.valueOf(scanner.nextLine());
                            if (choice == 1) {
                                System.out.println("Please input the position of the rook in castling: ");
                                String rook = scanner.nextLine();
                                castling = this.board.castling(2, rook);
                            }
                        }
                    }
                    if (castling) {
                        break;
                    }
                    System.out.println("White's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if (board.getPiece(from).getUser() != 'w') {
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is " + from);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if (str.length() != 1) {
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if (command == 1) {
                        break;
                    } else {
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                while (!this.gameOver) {
                    if (castling) {
                        break;
                    }
                    this.board.printBoard();
                    System.out.println("You have selected " + from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is " + to);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if (str.length() != 1) {
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if (command == 1) {
                        break;
                    } else {
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                if (this.gameOver) {
                    break;
                }
                if (this.board.canMove(from, to)) {
                    if (this.board.movePiece(from, to, 1)) {
                        this.board.cancelEnPassant(1);
                        break;
                    } else {
                        System.out.println("Invalid movement: please follow the rule");
                    }
                } else {
                    System.out.println("Invalid movement: please follow the rule");
                }
            }
            //black movement
            while (!this.gameOver) {
                String from = "";
                String to = "";
                boolean castling = false;
                while (!this.gameOver) {
                    this.board.printBoard();
                    isCheckedB = this.board.isChecked(1);
                    if (this.board.isGameover(1)) {
                        winner = "White";
                        this.gameOver = true;
                        break;
                    }
                    //hint for checkmated
                    if (isCheckedB) {
                        System.out.println("You're checked!");
                    } else {
                        //if the user can have castling
                        if (this.board.canCastling(1)) {
                            System.out.println("Black's turn, you have the option of castling now, do you wanna castling?");
                            System.out.println("1. Yes 2. No");
                            int choice = Integer.valueOf(scanner.nextLine());
                            if (choice == 1) {
                                System.out.println("Please input the position of the rook in castling: ");
                                String rook = scanner.nextLine();
                                castling = this.board.castling(1, rook);
                            }
                        }
                    }
                    if (castling) {
                        break;
                    }
                    System.out.println("Black's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if (board.getPiece(from).getUser() != 'b') {
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is " + from);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if (str.length() != 1) {
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if (command == 1) {
                        break;
                    } else {
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                while (!this.gameOver) {
                    if (castling) {
                        break;
                    }
                    this.board.printBoard();
                    System.out.println("You have selected " + from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is " + to);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if (str.length() != 1) {
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if (command == 1) {
                        break;
                    } else {
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                if (this.gameOver) {
                    break;
                }
                if (castling) {
                    break;
                }
                if (this.board.canMove(from, to)) {
                    if (this.board.movePiece(from, to, 1)) {
                        this.board.cancelEnPassant(2);
                        break;
                    } else {
                        System.out.println("Invalid movement: please follow the rule");
                    }
                } else {
                    System.out.println("Invalid movement: please follow the rule");
                }
            }
        }
        System.out.println("Game over! The winner is " + winner + "!");
    }

    //vs AI, same logic
    public void pvAi() {
        this.board = new Board();
        board.defaultSetting();
        this.isCheckedB = false;
        this.isCheckedW = false;
        this.gameOver = false;
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        //set the max depth
        System.out.println("Please set the depth of search:");
        int depth = Integer.valueOf(scanner.nextLine());
        //choose the side
        System.out.println("Please choose one side:");
        System.out.println("1. Black 2. White");
        int choice = Integer.valueOf(scanner.nextLine());
        //AI is the white
        if (choice == 1) {
            while (!this.gameOver) {
                //AI movement
                this.board.isGameover(2);
                if(this.gameOver){
                    winner = "Black";
                    break;
                }
                this.board.printBoard();
                System.out.println("AI is thinking...");
                minimaxAB(this.board, depth, 2);
                //black movement
                while (!this.gameOver) {
                    String from = "";
                    String to = "";
                    boolean castling = false;
                    while (!this.gameOver) {
                        this.board.printBoard();
                        isCheckedB = this.board.isChecked(1);
                        if (this.board.isGameover(1)) {
                            winner = "White";
                            this.gameOver = true;
                            break;
                        }
                        //hint for checkmated
                        if (isCheckedB) {
                            System.out.println("You're checked!");
                        } else {
                            //if the user can have castling
                            if (this.board.canCastling(1)) {
                                System.out.println("Black's turn, you have the option of castling now, do you wanna castling?");
                                System.out.println("1. Yes 2. No");
                                int ifCastling = Integer.valueOf(scanner.nextLine());
                                if (ifCastling == 1) {
                                    System.out.println("Please input the position of the rook in castling: ");
                                    String rook = scanner.nextLine();
                                    castling = this.board.castling(1, rook);
                                }
                            }
                        }
                        if (castling) {
                            break;
                        }
                        System.out.println("Black's turn, please choose a piece (for example: 1a):");
                        from = scanner.nextLine();
                        if (board.getPiece(from).getUser() != 'b') {
                            System.out.println("You can not choose your opponent's piece!");
                            continue;
                        }
                        System.out.println("Your choice is " + from);
                        System.out.println("1.Sure 2.Cancel");
                        String str = scanner.nextLine();
                        if (str.length() != 1) {
                            System.out.println("Invalid command! Please try again!");
                            continue;
                        }
                        int command = Integer.valueOf(str);
                        if (command == 1) {
                            break;
                        } else {
                            System.out.println("Invalid command! Please try again!");
                        }
                    }
                    while (!this.gameOver) {
                        if (castling) {
                            break;
                        }
                        this.board.printBoard();
                        System.out.println("You have selected " + from);
                        System.out.println("Please choose a position to go (for example: 1a):");
                        to = scanner.nextLine();
                        System.out.println("Your choice is " + to);
                        System.out.println("1.Sure 2.Cancel");
                        String str = scanner.nextLine();
                        if (str.length() != 1) {
                            System.out.println("Invalid command! Please try again!");
                            continue;
                        }
                        int command = Integer.valueOf(str);
                        if (command == 1) {
                            break;
                        } else {
                            System.out.println("Invalid command! Please try again!");
                        }
                    }
                    if (this.gameOver) {
                        break;
                    }
                    if (castling) {
                        break;
                    }
                    if (this.board.canMove(from, to)) {
                        if (this.board.movePiece(from, to, 1)) {
                            this.board.cancelEnPassant(2);
                            break;
                        } else {
                            System.out.println("Invalid movement: please follow the rule");
                        }
                    } else {
                        System.out.println("Invalid movement: please follow the rule");
                    }
                }
            }
            System.out.println("GAME OVER! Winner is "+winner);
        }
        //AI is the black
        else {
            while (!this.gameOver) {
                //white movement
                while (!this.gameOver) {
                    String from = "";
                    String to = "";
                    boolean castling = false;
                    while (!this.gameOver) {
                        this.board.printBoard();
                        isCheckedW = this.board.isChecked(2);
                        if (this.board.isGameover(2)) {
                            winner = "Black";
                            this.gameOver = true;
                            break;
                        }
                        if (isCheckedW) {
                            System.out.println("You're checked!");
                        } else {
                            if (this.board.canCastling(2)) {
                                System.out.println("White's turn, you have the option of castling now, do you wanna castling?");
                                System.out.println("1. Yes 2. No");
                                int ifCastling = Integer.valueOf(scanner.nextLine());
                                if (ifCastling == 1) {
                                    System.out.println("Please input the position of the rook in castling: ");
                                    String rook = scanner.nextLine();
                                    castling = this.board.castling(2, rook);
                                }
                            }
                        }
                        if (castling) {
                            break;
                        }
                        System.out.println("White's turn, please choose a piece (for example: 1a):");
                        from = scanner.nextLine();
                        if (board.getPiece(from).getUser() != 'w') {
                            System.out.println("You can not choose your opponent's piece!");
                            continue;
                        }
                        System.out.println("Your choice is " + from);
                        System.out.println("1.Sure 2.Cancel");
                        String str = scanner.nextLine();
                        if (str.length() != 1) {
                            System.out.println("Invalid command! Please try again!");
                            continue;
                        }
                        int command = Integer.valueOf(str);
                        if (command == 1) {
                            break;
                        } else {
                            System.out.println("Invalid command! Please try again!");
                        }
                    }
                    while (!this.gameOver) {
                        if (castling) {
                            break;
                        }
                        this.board.printBoard();
                        System.out.println("You have selected " + from);
                        System.out.println("Please choose a position to go (for example: 1a):");
                        to = scanner.nextLine();
                        System.out.println("Your choice is " + to);
                        System.out.println("1.Sure 2.Cancel");
                        String str = scanner.nextLine();
                        if (str.length() != 1) {
                            System.out.println("Invalid command! Please try again!");
                            continue;
                        }
                        int command = Integer.valueOf(str);
                        if (command == 1) {
                            break;
                        } else {
                            System.out.println("Invalid command! Please try again!");
                        }
                    }
                    if (this.gameOver) {
                        break;
                    }
                    if (this.board.canMove(from, to)) {
                        if (this.board.movePiece(from, to, 1)) {
                            this.board.cancelEnPassant(1);
                            break;
                        } else {
                            System.out.println("Invalid movement: please follow the rule");
                        }
                    } else {
                        System.out.println("Invalid movement: please follow the rule");
                    }
                }
                this.board.isGameover(1);
                if(this.gameOver){
                    winner = "White";
                    break;
                }
                this.board.printBoard();
                System.out.println("AI is thinking...");
                minimaxAB(this.board, depth, 1);
            }
            System.out.println("GAME OVER! The winner is "+winner);
        }
    }

    public void AIvAI(){
        this.board = new Board();
        board.defaultSetting();
        this.isCheckedB = false;
        this.isCheckedW = false;
        this.gameOver = false;
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        //set the max depth
        System.out.println("Please set the depth of search for Black:");
        int depthB = Integer.valueOf(scanner.nextLine());
        //choose the side
        System.out.println("Please set the depth of search for White:");
        int depthW = Integer.valueOf(scanner.nextLine());
        while(!this.gameOver){
            this.board.isGameover(1);
            if(this.gameOver){
                winner = "White";
                break;
            }
            this.board.printBoard();
            System.out.println("AI is thinking...");
            minimaxAB(this.board, depthW, 1);

            this.board.isGameover(2);
            if(this.gameOver){
                winner = "Black";
                break;
            }
            this.board.printBoard();
            System.out.println("AI is thinking...");
            minimaxAB(this.board, depthB, 2);
        }
        System.out.println("GAME OVER! The winner is "+winner);
    }
    //minimax with Alpha-Beta pruning
    public void minimaxAB(Board board, int maxDepth, int user) {
        //use record to store the movement order
        Record record = new Record(new ArrayList<Move>(), Integer.MAX_VALUE);
        record = maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE, maxDepth, 0, user, record);
        ArrayList<Move> move = record.getMove();
        //get the first step
        int i = move.get(0).getFrom()[0];
        int j = move.get(0).getFrom()[1];
        int k = move.get(0).getTo()[0];
        int l = move.get(0).getTo()[1];
        //make the movement
        Piece temp = board.getPiece(i, j);
        temp.firstStep();
        temp.setCurPosition(k,l);
        //promotion
        //since in the heuristic Queen always has the highest mark, so all promotions for AI is to become a Queen
        if(temp.getName()=='p' && k == 0){
            temp = new Queen(new int[]{k,l}, 'w');
        }
        if(temp.getName()=='P' && k == 7){
            temp = new Queen(new int[]{k,l}, 'b');
        }
        board.setPiece(k, l, temp);
        board.setPiece(i, j, new nullPiece(i, j));
        //after the move en passant is not available anymore
        this.board.cancelEnPassant(3-user);
        return;
    }

    public Record maxValue(Board board, int alpha, int beta, int maxDepth, int curDepth, int user, Record record) {
        //copy the record
        Record state = record.copy();
        //if current depth is more than the max depth then return
        if (curDepth > maxDepth) {
            state.setV(Integer.MAX_VALUE);
            return state;
        }
        //if the game is over
        if (board.isGameover(user)) {
            state.setV(Integer.MAX_VALUE);
            return state;
        }
        //if the game is over
        if (board.isGameover(3 - user)) {
            state.setV(Integer.MIN_VALUE);
            return state;
        }
        //calculate current heuristic
        int v = this.board.heuristic(user);
        state.setV(v);
        //set up alpha and beta
        int Alpha = alpha;
        int Beta = beta;
        //for all pieces on the board, find the AI's pieces and then find all possible movement
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j).getUser() == 'b' && user == 1) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            //if there is a legal move
                            if (board.canMove(i, j, k, l)) {
                                if (board.movePiece(i, j, k, l)) {
                                    //copy the board and do the movement, then continue the minimax
                                    Board newBoard = board.copyBoard();
                                    Piece temp = newBoard.getPiece(i, j);
                                    newBoard.setPiece(k, l, temp);
                                    newBoard.setPiece(i, j, new nullPiece(i, j));
                                    Move moveTemp = new Move(i,j,k,l);
                                    Record recordTemp = state.copy();
                                    recordTemp.addMove(moveTemp);
                                    Record minValueRet = minValue(newBoard, Alpha, Beta, maxDepth, curDepth + 1, user,recordTemp);
                                    if(minValueRet.getV() >= state.getV()){
                                        state = minValueRet;
                                    }
                                    //pruning
                                    if (state.getV() >= Beta) {
                                        return state;
                                    }
                                    Alpha = Math.max(Alpha, state.getV());
                                }
                            }
                        }
                    }
                }
                //same logic as above but the user is different
                else if (board.getPiece(i, j).getUser() == 'w' && user == 2) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (board.canMove(i, j, k, l)) {
                                if (board.movePiece(i, j, k, l)) {
                                    Board newBoard = board.copyBoard();
                                    Piece temp = newBoard.getPiece(i, j);
                                    newBoard.setPiece(k, l, temp);
                                    newBoard.setPiece(i, j, new nullPiece(i, j));
                                    Move moveTemp = new Move(i,j,k,l);
                                    Record recordTemp = state.copy();
                                    recordTemp.addMove(moveTemp);
                                    Record minValueRet = minValue(newBoard, Alpha, Beta, maxDepth, curDepth + 1, user,recordTemp);
                                    if(minValueRet.getV() >= state.getV()){
                                        state = minValueRet;
                                    }
                                    if (state.getV() >= Beta) {
                                        return state;
                                    }
                                    Alpha = Math.max(Alpha, state.getV());
                                }
                            }
                        }
                    }
                }
            }
        }
        return state;
    }

    //almost the same logic as maxValue()
    public Record minValue(Board board, int alpha, int beta, int maxDepth, int curDepth, int user, Record record) {
        Record state = record.copy();
        if (curDepth > maxDepth) {
            state.setV(Integer.MIN_VALUE);
            return state;
        }
        if (board.isGameover(user)) {
            state.setV(Integer.MIN_VALUE);
            return state;
        }
        if (board.isGameover(3 - user)) {
            state.setV(Integer.MAX_VALUE);
            return state;
        }
        int v = this.board.heuristic(user);
        state.setV(v);
        int Alpha = alpha;
        int Beta = beta;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j).getUser() == 'b' && user == 1) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (board.canMove(i, j, k, l)) {
                                if (board.movePiece(i, j, k, l)) {
                                    Board newBoard = board.copyBoard();
                                    Piece temp = newBoard.getPiece(i, j);
                                    newBoard.setPiece(k, l, temp);
                                    newBoard.setPiece(i, j, new nullPiece(i, j));
                                    Move moveTemp = new Move(i,j,k,l);
                                    Record recordTemp = state.copy();
                                    recordTemp.addMove(moveTemp);
                                    Record maxValueRet = maxValue(newBoard, Alpha, Beta, maxDepth, curDepth + 1, user,recordTemp);
                                    if(maxValueRet.getV() <= state.getV()){
                                        state = maxValueRet;
                                    }
                                    if (state.getV() <= Alpha) {
                                        return state;
                                    }
                                    Beta = Math.min(Beta, state.getV());
                                }
                            }
                        }
                    }
                }
                if (board.getPiece(i, j).getUser() == 'w' && user == 2) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (board.canMove(i, j, k, l)) {
                                if (board.movePiece(i, j, k, l)) {
                                    Board newBoard = board.copyBoard();
                                    Piece temp = newBoard.getPiece(i, j);
                                    newBoard.setPiece(k, l, temp);
                                    newBoard.setPiece(i, j, new nullPiece(i, j));
                                    Move moveTemp = new Move(i,j,k,l);
                                    Record recordTemp = state.copy();
                                    recordTemp.addMove(moveTemp);
                                    Record maxValueRet = maxValue(newBoard, Alpha, Beta, maxDepth, curDepth + 1, user,recordTemp);
                                    if(maxValueRet.getV() <= state.getV()){
                                        state = maxValueRet;
                                    }
                                    if (state.getV() <= Alpha) {
                                        return state;
                                    }
                                    Beta = Math.min(Beta, state.getV());
                                }
                            }
                        }
                    }
                }
            }
        }
        return state;
    }


}
