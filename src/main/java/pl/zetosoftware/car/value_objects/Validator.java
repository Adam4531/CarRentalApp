package pl.zetosoftware.car.value_objects;

public interface Validator {

    default boolean containsValidCharacters(String stringToCheck, String pattern) {
        return stringToCheck.matches(pattern);
    }

    default boolean isValidLength(String stringToCheck, int minLength, int maxLength) {
        return stringToCheck.length() > minLength - 1 && stringToCheck.length() < maxLength + 1;
    }

}
