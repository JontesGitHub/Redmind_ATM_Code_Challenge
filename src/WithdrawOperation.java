import java.util.HashMap;
import java.util.Map;

public class WithdrawOperation {
    private boolean withdrawSucceeded = false;
    private String errorMessage;
    private final int requestedWithdraw;
    private final Map<Bill, Integer> billsLeft;

    public WithdrawOperation(int requestedMoney, Map<Bill, Integer> billsInATM) {
        this.requestedWithdraw = requestedMoney;
        this.billsLeft = new HashMap<>(billsInATM);
    }

    public boolean containsEnoughMoneyinATM() {
        int moneyLeft = billsLeft.entrySet().stream()
                .mapToInt(entry -> entry.getKey().value * entry.getValue())
                .sum();

        if (moneyLeft == 0) {
            errorMessage = "No money left in ATM";
        } else if (moneyLeft < requestedWithdraw) {
            errorMessage = "Not enough money in ATM for your specific withdrawal request";
        }

        return moneyLeft >= requestedWithdraw;
    }

    public void startTransaction() {
        int leftToWithdraw = requestedWithdraw;

        for (Bill billType : Bill.values()) {

            if (billsLeft.containsKey(billType) && (leftToWithdraw != 0)) {
                int billCountNeeded = leftToWithdraw / billType.value;

                if (billCountNeeded > 0) {
                    for (int i = 0; i < billCountNeeded && i < billsLeft.get(billType); i++) {
                        leftToWithdraw -= billType.value;
                    }

                    if (billsLeft.get(billType) - billCountNeeded == 0) {
                        billsLeft.remove(billType);
                    } else {
                        billsLeft.compute(billType, (k, v) -> v - billCountNeeded);
                    }
                }
            }
        }
        if (leftToWithdraw != 0) {
            errorMessage = "ATM does not have the required bills for your specific withdrawal request";
        } else {
            withdrawSucceeded = true;
        }
    }

    public void logResult() {
        String outputMessage = withdrawSucceeded ?
                String.format("Transaction INFO: Your withdrawal of %dkr succeeded.", requestedWithdraw) :
                String.format("Transaction INFO: Your withdrawal of %dkr failed. Caused by: %s.", requestedWithdraw, errorMessage);

        System.out.println(outputMessage);
    }

    public Map<Bill, Integer> getBillsRemaining() {
        return billsLeft;
    }

    public boolean isTransactionSuccessful() {
        return withdrawSucceeded;
    }
}
