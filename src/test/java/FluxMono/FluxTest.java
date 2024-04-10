package FluxMono;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {
    @Test
    public void tstFlux(){
        Flux<Integer> lista = Flux.just(1,2,3,4);
        StepVerifier.create(lista)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectComplete()
                .verify();
    }
    @Test
    public void testFluxString(){
        Flux<String> nameList = Flux.just("Juanasdf", "Johenaf","Asd","asf")
                .filter(name -> name.length() <= 5)
                .map(String::toUpperCase);
        StepVerifier.create(nameList)
                .expectNext("ASD")
                .expectNextMatches(name->name.startsWith("A"))
                .expectComplete()
                .verify();
    }
}
