package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.global.dto.ErrorsListDto;
import pl.zetosoftware.user.dto.UserEditRequestDto;
import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.dto.UserResponseDto;

import java.util.List;
//
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserEditService userEditService;

    @Autowired
    public UserController(
            UserService userService,
            UserEditService userEditService) {
        this.userService = userService;
        this.userEditService = userEditService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public UserEditRequestDto getUserByEmail(@RequestParam String email){
        UserEntity userEntity = userEditService.getUserByEmail(email);
        return UserEditRequestDto.builder()
                .login(userEntity.getLogin().toString())
                .firstName(userEntity.getFirstName().toString())
                .secondName(userEntity.getSecondName().toString())
                .phoneNumber(userEntity.getPhoneNumber().toString())
                .email(userEntity.getEmail().toString())
                .pesel(userEntity.getPesel().toString())
                .build();
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ErrorsListDto changeUserByEmail(@RequestParam String email, @RequestBody UserEditRequestDto updatedUser){
       return userEditService.updateUserWithPutMapping(email, updatedUser);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto changeUserEmail(@PathVariable Long id, @RequestBody String email){
        return userService.updateUserEmail(id, email);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUserById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

}
