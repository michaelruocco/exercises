package uk.co.mruoc.exercises.naughtsandcrosses.board;

public interface BoardState {

    int getSize();

    String getToken(int x, int y);

    String getToken(String locationKey);
}
