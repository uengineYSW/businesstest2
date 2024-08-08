package businesstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import businesstest.config.kafka.KafkaProcessor;
import businesstest.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public class UpdateUserGradeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        UpdateUserGradeTest.class
    );

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MessageVerifier<Message<?>> messageVerifier;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:

        entity.setId("1");
        entity.setUsername("example_user");
        entity.setEmail("user@example.com");
        entity.setPassword("********");
        entity.setAmountpayment(60000L);
        entity.setMembergrade("Bronze");

        repository.save(entity);

        //when:

        OrderCreated event = new OrderCreated();

        event.setOrderNumber("123456");
        event.setCustomerName("John Doe");
        event.setTotalAmount(60000L);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String msg = objectMapper.writeValueAsString(event);

            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build(),
                    "businesstest"
                );

            //then:

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "businesstest",
                        5000,
                        TimeUnit.MILLISECONDS
                    );

            assertNotNull("Resulted event must be published", receivedMessage);

            UpdateGrade outputEvent = objectMapper.readValue(
                receivedMessage.getPayload(),
                UpdateGrade.class
            );

            LOGGER.info("Response received: {}", receivedMessage.getPayload());

            assertEquals(outputEvent.getId(), "1");
            assertEquals(outputEvent.getUsername(), "example_user");
            assertEquals(outputEvent.getEmail(), "user@example.com");
            assertEquals(outputEvent.getPassword(), "********");
            assertEquals(outputEvent.getAmountpayment(), 60000L);
            assertEquals(outputEvent.getMembergrade(), "Bronze");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test1() {
        //given:

        entity.setId("2");
        entity.setUsername("another_user");
        entity.setEmail("another_user@example.com");
        entity.setPassword("********");
        entity.setAmountpayment(150000L);
        entity.setMembergrade("Silver");

        repository.save(entity);

        //when:

        OrderCreated event = new OrderCreated();

        event.setOrderNumber("789012");
        event.setCustomerName("Jane Smith");
        event.setTotalAmount(150000L);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String msg = objectMapper.writeValueAsString(event);

            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build(),
                    "businesstest"
                );

            //then:

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "businesstest",
                        5000,
                        TimeUnit.MILLISECONDS
                    );

            assertNotNull("Resulted event must be published", receivedMessage);

            UpdateGrade outputEvent = objectMapper.readValue(
                receivedMessage.getPayload(),
                UpdateGrade.class
            );

            LOGGER.info("Response received: {}", receivedMessage.getPayload());

            assertEquals(outputEvent.getId(), "2");
            assertEquals(outputEvent.getUsername(), "another_user");
            assertEquals(outputEvent.getEmail(), "another_user@example.com");
            assertEquals(outputEvent.getPassword(), "********");
            assertEquals(outputEvent.getAmountpayment(), 150000L);
            assertEquals(outputEvent.getMembergrade(), "Silver");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test2() {
        //given:

        entity.setId("3");
        entity.setUsername("third_user");
        entity.setEmail("third_user@example.com");
        entity.setPassword("********");
        entity.setAmountpayment(250000L);
        entity.setMembergrade("Gold");

        repository.save(entity);

        //when:

        OrderCreated event = new OrderCreated();

        event.setOrderNumber("345678");
        event.setCustomerName("David Johnson");
        event.setTotalAmount(250000L);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String msg = objectMapper.writeValueAsString(event);

            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build(),
                    "businesstest"
                );

            //then:

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "businesstest",
                        5000,
                        TimeUnit.MILLISECONDS
                    );

            assertNotNull("Resulted event must be published", receivedMessage);

            UpdateGrade outputEvent = objectMapper.readValue(
                receivedMessage.getPayload(),
                UpdateGrade.class
            );

            LOGGER.info("Response received: {}", receivedMessage.getPayload());

            assertEquals(outputEvent.getId(), "3");
            assertEquals(outputEvent.getUsername(), "third_user");
            assertEquals(outputEvent.getEmail(), "third_user@example.com");
            assertEquals(outputEvent.getPassword(), "********");
            assertEquals(outputEvent.getAmountpayment(), 250000L);
            assertEquals(outputEvent.getMembergrade(), "Gold");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }
}
