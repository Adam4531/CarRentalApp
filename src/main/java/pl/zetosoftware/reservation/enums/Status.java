package pl.zetosoftware.reservation.enums;

public enum Status {
    FREE("Free"),
    RESERVED("Reserved"),
    TAKEN("Taken");

    private String name;

    Status(String name) {
        this.name = name;
    }
}
