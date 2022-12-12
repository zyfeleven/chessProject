import java.util.Scanner;

public class Game {
    // game control process : start, choose user(PVP OR PV AI), judge if the game is over, restart...
    private Board board;
    Game(){
        System.out.println("Welcome to the chess game!");
        System.out.println("Please choose mode: ");
        System.out.println("1. User vs User  2. User vs AI  3. Practice mode");
        Scanner scanner = new Scanner(System.in);
        int mode = Integer.parseInt(scanner.nextLine());
        if(mode == 1){
            this.pvp();
        }
    }

    public void pvp(){
        this.board = new Board();
        board.defaultSetting();
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        while(!this.gameover()){
            //black movement
            while(true){
                String from = "";
                String to = "";
                while(true){
                    this.board.printBoard();
                    System.out.println("Black's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if(board.getPiece(from).getUser()!='b'){
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is "+from);
                    System.out.println("1.Sure 2.Cancel");
                    int command = Integer.valueOf(scanner.nextLine());
                    if(command == 1){
                        break;
                    }
                }
                while(true){
                    this.board.printBoard();
                    System.out.println("You have selected "+from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is "+to);
                    System.out.println("1.Sure 2.Cancel");
                    int command = Integer.valueOf(scanner.nextLine());
                    if(command == 1){
                        break;
                    }
                }
                if(this.board.canMove(from, to)){
                    this.board.movePiece(from, to);
                    break;
                }
            }
            if(this.gameover()){
                winner = "Black";
                break;
            }
            //white movement
            while(true){
                String from = "";
                String to = "";
                while(true){
                    this.board.printBoard();
                    System.out.println("White's turn, please choose a piece (for example: 1a):");
                    from = scanner.nextLine();
                    if(board.getPiece(from).getUser()!='w'){
                        System.out.println("You can not choose your opponent's piece!");
                        continue;
                    }
                    System.out.println("Your choice is "+from);
                    System.out.println("1.Sure 2.Cancel");
                    int command = Integer.parseInt(scanner.nextLine());
                    if(command == 1){
                        break;
                    }
                }
                while(true){
                    this.board.printBoard();
                    System.out.println("You have selected "+from);
                    System.out.println("Please choose a position to go (for example: 1a):");
                    to = scanner.nextLine();
                    System.out.println("Your choice is "+to);
                    System.out.println("1.Sure 2.Cancel");
                    int command = Integer.parseInt(scanner.nextLine());
                    if(command == 1){
                        break;
                    }
                }
                if(this.board.canMove(from, to)){
                    this.board.movePiece(from, to);
                    break;
                }
                else{
                    System.out.println("Invalid movement: please follow the rule");
                }
            }
        }
    }

    public boolean gameover(){
        //todo: method gameover()
        return false;
    }


}
