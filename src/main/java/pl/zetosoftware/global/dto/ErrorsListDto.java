package pl.zetosoftware.global.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ErrorsListDto implements Serializable {

    private List<String> errors;

    private String fieldName; //nazwy pola pod ktorymi wyswietlane beda errory

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
