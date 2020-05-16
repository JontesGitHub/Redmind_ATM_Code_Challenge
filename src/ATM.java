import java.util.Map;

public class ATM {

    private String name;
    private Map<Bill, Integer> billsInATM;

    public void withdraw(int requestedWithdraw) {
        WithdrawOperation withdrawOperation = new WithdrawOperation(requestedWithdraw, billsInATM);
        if (withdrawOperation.containsEnoughMoneyinATM()) {
            withdrawOperation.startTransaction();

            if (withdrawOperation.isTransactionSuccessful()) {
                billsInATM = withdrawOperation.getBillsRemaining();
            }
        }
        withdrawOperation.logResult();
    }

    public void logMoneyinATM() {
        int moneyLeft = billsInATM.entrySet().stream()
                .mapToInt(entry -> entry.getKey().value * entry.getValue())
                .sum();
        System.out.println(String.format("ATM INFO: %s has now: %dkr left.", this.name, moneyLeft));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBillsInATM(Map<Bill, Integer> billsInATM) {
        this.billsInATM = billsInATM;
    }
}
