package pl.zetosoftware.car.enums;

public enum TypeOfFuel {
    BENZINE("Benzine"),
    Diesel("Diesel"),
    Electric("Electric"),
    LPG("LPG");

    private String name;

    TypeOfFuel(String name) {
        this.name = name;
    }
}
