import java.util.HashMap;
import java.util.Map;

public class ATMBuilder {
    private String name;
    private final Map<Bill, Integer> billsInATM;

    public ATMBuilder() {
        this.billsInATM = new HashMap<>();
    }

    public ATMBuilder name(String name) {
        this.name = name;

        return this;
    }

    public ATMBuilder addBillwithAmount(Bill billType, int quantity) {
        billsInATM.computeIfPresent(billType, (k, v) -> v + quantity);
        billsInATM.putIfAbsent(billType, quantity);

        return this;
    }

    public ATM build() {
        ATM atm = new ATM();
        atm.setName(this.name);
        atm.setBillsInATM(this.billsInATM);

        return atm;
    }
}
