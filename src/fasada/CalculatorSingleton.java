package fasada;

public class CalculatorSingleton {
    private static CalculatorSingleton instance;
    private CalculatorSingleton() {}
    public static CalculatorSingleton getInstance() {
        if (instance == null) {
            instance = new CalculatorSingleton();
        }
        return instance;
    }

    public int calculateOppositeHouse(int housesNumber, int counter) {
        return housesNumber + (housesNumber - counter);
    }
}