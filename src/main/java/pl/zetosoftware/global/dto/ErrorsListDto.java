package pl.zetosoftware.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorsListDto {

    List<String> errors;

    public boolean isListOfErrorsEmpty(){
        return this.errors.size()==0;
    }

    public void add(String error){
        errors.add(error);
    }
}
