package businesstest.domain;

import businesstest.MemberApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String username;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    private Long amoutpayment;

    private String membergrade;

    public static UserRepository repository() {
        UserRepository userRepository = MemberApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateUser(UpdateUserCommand updateUserCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void updateUserGrade(OrderCreated orderCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        User user = new User();
        repository().save(user);

        UpdateGrade updateGrade = new UpdateGrade(user);
        updateGrade.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCreated.get???()).ifPresent(user->{
            
            user // do something
            repository().save(user);

            UpdateGrade updateGrade = new UpdateGrade(user);
            updateGrade.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
