package businesstest.domain;

import businesstest.domain.*;
import businesstest.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderUpdated extends AbstractEvent {

    private String orderNumber;

    public OrderUpdated(Order aggregate) {
        super(aggregate);
    }

    public OrderUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
