package pl.zetosoftware.car.enums;

public enum BodyType {
    Coupe("Coupe"),
    Sedan("Sedan"),
    SUV("SUV"),
    Station_Wagon("Station_Wagon"),
    Hatchback("Hatchback"),
    Cabriolet("Cabriolet");

    private String name;

    BodyType(String name) {
        this.name = name;
    }
}
