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
        board.defaultSetting();
        this.isCheckmatedB = false;
        this.isCheckmatedW = false;
        this.gameover = false;
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        while(!this.gameover){
            //white movement
            while(!this.gameover){
                String from = "";
                String to = "";
                boolean castling = false;
                while(!this.gameover){
                    this.board.printBoard();
                    isCheckmatedW = this.board.isCheckmated(2);
                    if(this.board.isGameover(2)){
                        winner = "Black";
                        this.gameover = true;
                        break;
                    }
                    if(isCheckmatedW){
                        System.out.println("You're checkmated!");
                    }
                    else{
                        if(this.board.canCastling(2)){
                            System.out.println("White's turn, you have the option of castling now, do you wanna castling?");
                            System.out.println("1. Yes 2. No");
                            int choice = Integer.valueOf(scanner.nextLine());
                            if(choice == 1){
                                System.out.println("Please input the position of the rook in castling: ");
                                String rook = scanner.nextLine();
                                castling = this.board.castling(2, rook);
                            }
                        }
                    }
                    if(castling){
                        break;
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
                    if(castling){
                        break;
                    }
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
                        this.board.cancelEnPassant(1);
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
            //black movement
            while(!this.gameover){
                String from = "";
                String to = "";
                boolean castling = false;
                while(!this.gameover){
                    this.board.printBoard();
                    isCheckmatedB = this.board.isCheckmated(1);
                    if(this.board.isGameover(1)){
                        winner = "White";
                        this.gameover = true;
                        break;
                    }
                    //hint for checkmated
                    if(isCheckmatedB){
                        System.out.println("You're checkmated!");
                    }
                    else{
                        //if the user can have castling
                        if(this.board.canCastling(1)){
                            System.out.println("Black's turn, you have the option of castling now, do you wanna castling?");
                            System.out.println("1. Yes 2. No");
                            int choice = Integer.valueOf(scanner.nextLine());
                            if(choice == 1){
                                System.out.println("Please input the position of the rook in castling: ");
                                String rook = scanner.nextLine();
                                castling = this.board.castling(1, rook);
                            }
                        }
                    }
                    if(castling){
                        break;
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
                    if(castling){
                        break;
                    }
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
                if(castling){
                    break;
                }
                if(this.board.canMove(from, to)){
                    if(this.board.movePiece(from, to,1)){
                        this.board.cancelEnPassant(2);
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
        board.customSetting();
        this.isCheckmatedB = false;
        this.isCheckmatedW = false;
        this.gameover = false;
        String winner = "";
        Scanner scanner = new Scanner(System.in);
        while(!this.gameover){
            //white movement
            while(!this.gameover){
                String from = "";
                String to = "";
                boolean castling = false;
                while(!this.gameover){
                    this.board.printBoard();
                    isCheckmatedW = this.board.isCheckmated(2);
                    if(this.board.isGameover(2)){
                        winner = "Black";
                        this.gameover = true;
                        break;
                    }
                    if(isCheckmatedW){
                        System.out.println("You're checkmated!");
                    }
                    else{
                        if(this.board.canCastling(2)){
                            System.out.println("White's turn, you have the option of castling now, do you wanna castling?");
                            System.out.println("1. Yes 2. No");
                            int choice = Integer.valueOf(scanner.nextLine());
                            if(choice == 1){
                                System.out.println("Please input the position of the rook in castling: ");
                                String rook = scanner.nextLine();
                                castling = this.board.castling(2, rook);
                            }
                        }
                    }
                    if(castling){
                        break;
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
                    if(castling){
                        break;
                    }
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
                        this.board.cancelEnPassant(1);
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
            //black movement
            while(!this.gameover){
                String from = "";
                String to = "";
                boolean castling = false;
                while(!this.gameover){
                    this.board.printBoard();
                    isCheckmatedB = this.board.isCheckmated(1);
                    if(this.board.isGameover(1)){
                        winner = "White";
                        this.gameover = true;
                        break;
                    }
                    //hint for checkmated
                    if(isCheckmatedB){
                        System.out.println("You're checkmated!");
                    }
                    else{
                        //if the user can have castling
                        if(this.board.canCastling(1)){
                            System.out.println("Black's turn, you have the option of castling now, do you wanna castling?");
                            System.out.println("1. Yes 2. No");
                            int choice = Integer.valueOf(scanner.nextLine());
                            if(choice == 1){
                                System.out.println("Please input the position of the rook in castling: ");
                                String rook = scanner.nextLine();
                                castling = this.board.castling(1, rook);
                            }
                        }
                    }
                    if(castling){
                        break;
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
                    if(castling){
                        break;
                    }
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
                if(castling){
                    break;
                }
                if(this.board.canMove(from, to)){
                    if(this.board.movePiece(from, to,1)){
                        this.board.cancelEnPassant(2);
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

    //vs AI, same logic
    public void pvAi(){
        this.board = new Board();
        board.defaultSetting();
        this.isCheckmatedB = false;
        this.isCheckmatedW = false;
        this.gameover = false;
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
        if(choice == 1){
            while(!this.gameover){
                minimaxAB(this.board, depth,2);
                //black movement
                while(!this.gameover){
                    String from = "";
                    String to = "";
                    boolean castling = false;
                    while(!this.gameover){
                        this.board.printBoard();
                        isCheckmatedB = this.board.isCheckmated(1);
                        if(this.board.isGameover(1)){
                            winner = "White";
                            this.gameover = true;
                            break;
                        }
                        //hint for checkmated
                        if(isCheckmatedB){
                            System.out.println("You're checkmated!");
                        }
                        else{
                            //if the user can have castling
                            if(this.board.canCastling(1)){
                                System.out.println("Black's turn, you have the option of castling now, do you wanna castling?");
                                System.out.println("1. Yes 2. No");
                                int ifCastling = Integer.valueOf(scanner.nextLine());
                                if(ifCastling == 1){
                                    System.out.println("Please input the position of the rook in castling: ");
                                    String rook = scanner.nextLine();
                                    castling = this.board.castling(1, rook);
                                }
                            }
                        }
                        if(castling){
                            break;
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
                        if(castling){
                            break;
                        }
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
                    if(castling){
                        break;
                    }
                    if(this.board.canMove(from, to)){
                        if(this.board.movePiece(from, to,1)){
                            this.board.cancelEnPassant(2);
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
        }
        //AI is the black
        else{
            while(!this.gameover) {
                //white movement
                while(!this.gameover){
                    String from = "";
                    String to = "";
                    boolean castling = false;
                    while(!this.gameover){
                        this.board.printBoard();
                        isCheckmatedW = this.board.isCheckmated(2);
                        if(this.board.isGameover(2)){
                            winner = "Black";
                            this.gameover = true;
                            break;
                        }
                        if(isCheckmatedW){
                            System.out.println("You're checkmated!");
                        }
                        else{
                            if(this.board.canCastling(2)){
                                System.out.println("White's turn, you have the option of castling now, do you wanna castling?");
                                System.out.println("1. Yes 2. No");
                                int ifCastling = Integer.valueOf(scanner.nextLine());
                                if(ifCastling == 1){
                                    System.out.println("Please input the position of the rook in castling: ");
                                    String rook = scanner.nextLine();
                                    castling = this.board.castling(2, rook);
                                }
                            }
                        }
                        if(castling){
                            break;
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
                        if(castling){
                            break;
                        }
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
                            this.board.cancelEnPassant(1);
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
                minimaxAB(this.board, depth,1);
            }
        }
    }

    public void minimaxAB(Board board, int maxDepth, int user){
        int v = maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE, maxDepth, 0, user);

    }

    public int maxValue(Board board, int alpha, int beta, int maxDepth, int curDepth, int user){
        if(curDepth>maxDepth){
            return Integer.MAX_VALUE;
        }
        if(board.isGameover(user)){
            return Integer.MAX_VALUE;
        }
        if(board.isGameover(3-user)){
            return Integer.MIN_VALUE;
        }
        int v = Integer.MIN_VALUE;
        int Alpha = alpha;
        int Beta = beta;
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                if(board.getPiece(i,j).getUser() == 'b'&&user==1){
                    for(int k = 0;k<8;k++){
                        for(int l = 0;l<8;l++){
                            if(board.canMove(i,j,k,l)){
                                if(board.movePiece(i,j,k,l)){
                                    Board newBoard = board.copyBoard();
                                    Piece temp = newBoard.getPiece(i,j);
                                    newBoard.setPiece(k,l,temp);
                                    newBoard.setPiece(i,j,new nullPiece(i,j));
                                    v = Math.max(v, minValue(newBoard, Alpha, Beta, maxDepth, curDepth+1, 3-user));
                                    if(v>=Beta){
                                        return v;
                                    }
                                    Alpha = Math.max(Alpha, v);
                                }
                            }
                        }
                    }
                }
                if(board.getPiece(i,j).getUser() == 'w'&&user==2){
                    for(int k = 0;k<8;k++){
                        for(int l = 0;l<8;l++){
                            if(board.canMove(i,j,k,l)){
                                if(board.movePiece(i,j,k,l)){
                                    Board newBoard = board.copyBoard();
                                    Piece temp = newBoard.getPiece(i,j);
                                    newBoard.setPiece(k,l,temp);
                                    newBoard.setPiece(i,j,new nullPiece(i,j));
                                    v = Math.max(v, minValue(newBoard, Alpha, Beta, maxDepth, curDepth+1, 3-user));
                                    if(v>=Beta){
                                        return v;
                                    }
                                    Alpha = Math.max(Alpha, v);
                                }
                            }
                        }
                    }
                }
            }
        }
        return v;
    }

    public int minValue(Board board, int alpha, int beta, int maxDepth, int curDepth, int user){
        if(curDepth>maxDepth){
            return Integer.MIN_VALUE;
        }
        if(board.isGameover(1)||board.isGameover(2)){
            return Integer.MIN_VALUE;
        }
        int v = Integer.MAX_VALUE;
        int Alpha = alpha;
        int Beta = beta;
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                if(board.getPiece(i,j).getUser() == 'b'&&user==1){
                    for(int k = 0;k<8;k++){
                        for(int l = 0;l<8;l++){
                            if(board.canMove(i,j,k,l)){
                                if(board.movePiece(i,j,k,l)){
                                    Board newBoard = board.copyBoard();
                                    Piece temp = newBoard.getPiece(i,j);
                                    newBoard.setPiece(k,l,temp);
                                    newBoard.setPiece(i,j,new nullPiece(i,j));
                                    v = Math.min(v, maxValue(newBoard, Alpha, Beta, maxDepth, curDepth+1, 3-user));
                                    if(v<=Alpha){
                                        return v;
                                    }
                                    Beta = Math.min(Beta, v);
                                }
                            }
                        }
                    }
                }
                if(board.getPiece(i,j).getUser() == 'w'&&user==2){
                    for(int k = 0;k<8;k++){
                        for(int l = 0;l<8;l++){
                            if(board.canMove(i,j,k,l)){
                                if(board.movePiece(i,j,k,l)){
                                    Board newBoard = board.copyBoard();
                                    Piece temp = newBoard.getPiece(i,j);
                                    newBoard.setPiece(k,l,temp);
                                    newBoard.setPiece(i,j,new nullPiece(i,j));
                                    v = Math.min(v, maxValue(newBoard, Alpha, Beta, maxDepth, curDepth+1, 3-user));
                                    if(v<=Alpha){
                                        return v;
                                    }
                                    Beta = Math.min(Beta, v);
                                }
                            }
                        }
                    }
                }
            }
        }
        return v;
    }


}
