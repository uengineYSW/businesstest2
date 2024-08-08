package businesstest.infra;

import businesstest.config.kafka.KafkaProcessor;
import businesstest.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCreated'"
    )
    public void wheneverOrderCreated_UpdateUserGrade(
        @Payload OrderCreated orderCreated
    ) {
        OrderCreated event = orderCreated;
        System.out.println(
            "\n\n##### listener UpdateUserGrade : " + orderCreated + "\n\n"
        );

        // Comments //
        //user의 누적결제 금액이 5만원 이상이면 회원등급은 "Bronze" / 10만원 이상이면 "Silver" / 20만원 이상이면 "Gold"

        // Sample Logic //
        User.updateUserGrade(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
