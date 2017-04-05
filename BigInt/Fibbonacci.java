public class Fibbonacci {

    private static BigInteger fibo1;
    private static BigInteger fibo2;
    private static BigInteger carry;

    public static BigInteger fibbonacci ( int val ){
        fibo1 = new BigInteger("0");
        fibo2 = new BigInteger("1");
        if (val == 0) {
            System.out.println(fibo1.toString());
            return fibo1;
        } else if (val == 1) {
            System.out.println(fibo2.toString());
            return fibo2;
        }
        for (int i = 0 ;i < val - 1 ; i++) {
            carry = fibo2;
            fibo2 = fibo2.plus(fibo1);
            fibo1 = carry;

        }
        System.out.println(fibo2.toString());
        return fibo2;
    }

    public static void main(String[] args) {
        fibbonacci(Integer.parseInt(args[0]));
    }
}
