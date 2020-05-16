public class Main {
    public static void main(String[] args) {
        /*
         * An ATM Builder, in this case we wanted the ATM
         * to start with a predetermined amount of each bill;
         * 2 x 1000
         * 3 x 500
         * 5 x 100
         * And I gave the ATM a name variable as well
         */
        ATM atm = new ATMBuilder()
                .name("ATM-Redmind")
                .addBillwithAmount(Bill.THOUSAND, 2)
                .addBillwithAmount(Bill.FIVE_HUNDRED, 3)
                .addBillwithAmount(Bill.ONE_HUNDRED, 5)
                .build();

        /*
         * Seven withdrawals in a predetermined order
         */
        atm.withdraw(1500);
        atm.withdraw(700);
        atm.withdraw(400);
        atm.withdraw(1100);
        atm.withdraw(1000);
        atm.withdraw(700);
        atm.withdraw(300);
        atm.logMoneyinATM();
    }
}