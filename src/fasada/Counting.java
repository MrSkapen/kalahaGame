package fasada;

import java.util.ArrayList;

public class Counting {

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
