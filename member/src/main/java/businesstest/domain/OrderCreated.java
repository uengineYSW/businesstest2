package businesstest.domain;

import businesstest.domain.*;
import businesstest.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderCreated extends AbstractEvent {

    private String orderNumber;
    private String customerName;
    private Long totalAmount;
}
