package cosc3p71.groupProject.board;

import java.util.ArrayList;

public class Record {
    private ArrayList<Move> move;
    private int v;

    public Record(ArrayList<Move> move, int v){
        this.v = v;
        this.move = move;
    }

    public ArrayList<Move> getMove(){
        return this.move;
    }

    public int getV(){
        return this.v;
    }

    public Record copy(){
        ArrayList<Move> temp = new ArrayList<>();
        for(Move e: this.move){
            temp.add(e);
        }
        Record ret = new Record(temp,this.v);
        return ret;
    }

    public void setV(int v){
        this.v = v;
    }

    public void addMove(Move move){
        this.move.add(move);
    }
}
