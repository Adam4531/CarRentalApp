package pl.zetosoftware.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRegisterValidDto {

    List<ErrorDto> errors;

    public boolean isListOfErrorsEmpty(){
        return this.errors.size()==0;
    }
}
