package fasada;

import java.util.ArrayList;

public class Counting {

    public boolean isEmptyBoard(ArrayList<Integer> board) {
        boolean isEmpty = true;
        for (int i = 0; i < board.size() - 1; i++) {
            if (board.get(i) != 0) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    public Integer counting(ArrayList<Integer> board){
        int sumOne = 0;
        for (Integer integer : board) {
            sumOne += integer;
        }
        return sumOne;
    }

    public Integer getCounter(int number_of_house,int number_of_seeds){
        int counter = number_of_house + 1;
        counter = counter + number_of_seeds-1;
        return counter;
    }

}
