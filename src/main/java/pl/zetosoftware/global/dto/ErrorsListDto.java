package pl.zetosoftware.global.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
public class ErrorsListDto implements Serializable {

    private List<String> errors;

    public ErrorsListDto(List<String> errors) {
        this.errors = errors;
    }

    public boolean isListOfErrorsEmpty(){
        return this.errors.size()==0;
    }

    public void add(String error){
        errors.add(error);
    }

}
