package businesstest.infra;
import businesstest.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/users")
@Transactional
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/users/{id}/api/users/{id}",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public User updateUser(@PathVariable(value = "id")  id, @RequestBody UpdateUserCommand updateUserCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /user/updateUser  called #####");
            Optional<User> optionalUser = userRepository.findById(id);
            
            optionalUser.orElseThrow(()-> new Exception("No Entity Found"));
            User user = optionalUser.get();
            user.updateUser(updateUserCommand);
            
            userRepository.save(user);
            return user;
            
    }
}
//>>> Clean Arch / Inbound Adaptor
