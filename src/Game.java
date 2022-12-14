import java.util.Scanner;

public class Game {
    // game control process : start, choose user(PVP OR PV AI), judge if the game is over, restart...
    private Board board;
    private boolean isCheckmatedB;
    private boolean isCheckmatedW;
    private boolean gameover;
    Game(){
        System.out.println("Welcome to the chess game!");
        System.out.println("Please choose mode: ");
        System.out.println("1. User vs User  2. User vs AI  3. Practice mode");
        Scanner scanner = new Scanner(System.in);
        int mode = Integer.parseInt(scanner.nextLine());
        if(mode == 1){
            this.pvp();
        }
        else if(mode == 2){
            System.out.println("nothing so far");
        }
        else if(mode == 3){
            this.practice();
        }
    }

    public void pvp(){
        this.board = new Board();
        board.customSetting();
        this.isCheckmatedB = false;
        this.isCheckmatedW = false;
        this.gameover = false;
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        while(!this.gameover){
            //black movement
            while(!this.gameover){
                String from = "";
                String to = "";
                while(!this.gameover){
                    this.board.printBoard();
                    isCheckmatedB = this.board.isCheckmated(1);
                    if(isCheckmatedB){
                        if(this.board.isGameover(1)){
                            winner = "White";
                            this.gameover = true;
                            break;
                        }
                        System.out.println("You're checkmated!");
                    }
                    System.out.println("Black's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if(board.getPiece(from).getUser()!='b'){
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is "+from);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if(str.length()!=1){
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if(command == 1){
                        break;
                    }
                    else{
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                while(!this.gameover){
                    this.board.printBoard();
                    System.out.println("You have selected "+from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is "+to);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if(str.length()!=1){
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if(command == 1){
                        break;
                    }
                    else{
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                if(this.gameover){
                    break;
                }
                if(this.board.canMove(from, to)){
                    if(this.board.movePiece(from, to,1)){
                        break;
                    }
                    else{
                        System.out.println("Invalid movement: please follow the rule");
                    }
                }
                else{
                    System.out.println("Invalid movement: please follow the rule");
                }
            }
            //white movement
            while(!this.gameover){
                String from = "";
                String to = "";
                while(!this.gameover){
                    this.board.printBoard();
                    isCheckmatedW = this.board.isCheckmated(2);
                    if(isCheckmatedW){
                        if(this.board.isGameover(2)){
                            winner = "Black";
                            this.gameover = true;
                            break;
                        }
                        System.out.println("You're checkmated!");
                    }
                    System.out.println("White's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if(board.getPiece(from).getUser()!='w'){
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is "+from);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if(str.length()!=1){
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if(command == 1){
                        break;
                    }
                    else{
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                while(!this.gameover){
                    this.board.printBoard();
                    System.out.println("You have selected "+from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is "+to);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if(str.length()!=1){
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if(command == 1){
                        break;
                    }
                    else{
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                if(this.gameover){
                    break;
                }
                if(this.board.canMove(from, to)){
                    if(this.board.movePiece(from, to,1)){
                        break;
                    }
                    else{
                        System.out.println("Invalid movement: please follow the rule");
                    }
                }
                else{
                    System.out.println("Invalid movement: please follow the rule");
                }
            }
        }
        System.out.println("Game over! The winner is "+winner+"!");
    }

    public void practice(){
        this.board = new Board();
        board.defaultSetting();
        this.isCheckmatedB = false;
        this.isCheckmatedW = false;
        this.gameover = false;
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        while(!this.gameover){
            //black movement
            while(!this.gameover){
                String from = "";
                String to = "";
                while(!this.gameover){
                    this.board.printBoard();
                    isCheckmatedB = this.board.isCheckmated(1);
                    if(isCheckmatedB){
                        if(this.board.isGameover(1)){
                            winner = "White";
                            this.gameover = true;
                            break;
                        }
                        System.out.println("You're checkmated!");
                    }
                    System.out.println("Black's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if(board.getPiece(from).getUser()!='b'){
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is "+from);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if(str.length()!=1){
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if(command == 1){
                        break;
                    }
                    else{
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                while(!this.gameover){
                    this.board.printBoard();
                    System.out.println("You have selected "+from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is "+to);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if(str.length()!=1){
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if(command == 1){
                        break;
                    }
                    else{
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                if(this.gameover){
                    break;
                }
                if(this.board.canMove(from, to)){
                    if(this.board.movePiece(from, to,1)){
                        break;
                    }
                    else{
                        System.out.println("Invalid movement: please follow the rule");
                    }
                }
                else{
                    System.out.println("Invalid movement: please follow the rule");
                }
            }
            //white movement
            while(!this.gameover){
                String from = "";
                String to = "";
                while(!this.gameover){
                    this.board.printBoard();
                    isCheckmatedW = this.board.isCheckmated(2);
                    if(isCheckmatedW){
                        if(this.board.isGameover(2)){
                            winner = "Black";
                            this.gameover = true;
                            break;
                        }
                        System.out.println("You're checkmated!");
                    }
                    System.out.println("White's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if(board.getPiece(from).getUser()!='w'){
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is "+from);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if(str.length()!=1){
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if(command == 1){
                        break;
                    }
                    else{
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                while(!this.gameover){
                    this.board.printBoard();
                    System.out.println("You have selected "+from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is "+to);
                    System.out.println("1.Sure 2.Cancel");
                    String str = scanner.nextLine();
                    if(str.length()!=1){
                        System.out.println("Invalid command! Please try again!");
                        continue;
                    }
                    int command = Integer.valueOf(str);
                    if(command == 1){
                        break;
                    }
                    else{
                        System.out.println("Invalid command! Please try again!");
                    }
                }
                if(this.gameover){
                    break;
                }
                if(this.board.canMove(from, to)){
                    if(this.board.movePiece(from, to,1)){
                        break;
                    }
                    else{
                        System.out.println("Invalid movement: please follow the rule");
                    }
                }
                else{
                    System.out.println("Invalid movement: please follow the rule");
                }
            }
        }
        System.out.println("Game over! The winner is "+winner+"!");
    }


}
