package cosc3p71.groupProject.board;

public class Move {
    //this class is for storing movement information in order to keep tracking in minimax
    private int[] from;
    private int[] to;

    public Move(int i, int j, int k, int l){
        this.from = new int[2];
        this.to = new int[2];
        from[0] = i;
        from[1] = j;
        to[0] = k;
        to[1] = l;
    }

    public int[] getFrom(){
        return this.from;
    }

    public int[] getTo(){
        return this.to;
    }

}
