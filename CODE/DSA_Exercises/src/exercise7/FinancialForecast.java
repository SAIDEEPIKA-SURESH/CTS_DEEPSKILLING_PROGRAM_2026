package exercise7;

public class FinancialForecast {

    static double futureValue(double amount,
                              double rate,
                              int years) {

        if(years==0)
            return amount;

        return futureValue(
                amount*(1+rate),
                rate,
                years-1);
    }

    public static void main(String[] args) {

        double result =
            futureValue(10000,0.10,5);

        System.out.println(
            "Future Value = " + result);
    }
}
