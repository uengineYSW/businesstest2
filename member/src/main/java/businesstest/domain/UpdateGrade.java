package businesstest.domain;

import businesstest.domain.*;
import businesstest.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class UpdateGrade extends AbstractEvent {

    private String id;
    private String username;
    private Email email;
    private Password password;
    private Long amoutpayment;
    private String membergrade;

    public UpdateGrade(User aggregate) {
        super(aggregate);
    }

    public UpdateGrade() {
        super();
    }
}
//>>> DDD / Domain Event
