public enum Bill {
    THOUSAND(1000),
    FIVE_HUNDRED(500),
    ONE_HUNDRED(100);

    public final int value;

    Bill(int value) {
        this.value = value;
    }
}
