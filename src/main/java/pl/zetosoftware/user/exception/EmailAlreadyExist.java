package pl.zetosoftware.user.exception;

public class EmailAlreadyExist extends RuntimeException{

    private static final String MESSAGE = "Account with this email already exist!";
    public EmailAlreadyExist(){
        super(MESSAGE);
    }

}
