package businesstest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateUserCommand {

    private String username;
    private Email email;
    private Password password;
}
