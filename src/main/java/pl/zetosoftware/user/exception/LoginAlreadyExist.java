package pl.zetosoftware.user.exception;

public class LoginAlreadyExist extends RuntimeException {

    private static final String MESSAGE = "Account with this login already exist!";
    public LoginAlreadyExist(){
        super(MESSAGE);
    }

}
