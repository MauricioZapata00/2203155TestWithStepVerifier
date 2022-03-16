package app.stepverifier.servicestests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;
import services.UppercaseConvert;

@SpringBootTest
public class UppercaseConvertTest {

    final TestPublisher<String> testPublisher = TestPublisher.create();

    @Test
    void testUpperCase(){
        UppercaseConvert uppercaseConvert = new UppercaseConvert(testPublisher.flux());
        StepVerifier.create(uppercaseConvert.getUpperCase())
                .then(() -> testPublisher.emit("datos", "GeNeRaDoS", "Sofka"))
                .expectNext("DATOS", "GENERADOS", "SOFKA")
                .verifyComplete();
    }
}
