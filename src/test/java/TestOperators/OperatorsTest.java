package TestOperators;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class OperatorsTest {
    @Test
    public void testTransformMap(){
        List<String> listaNombres = Arrays.asList("google","abs","fs","StackOverFlodw");
        Flux<String> nombresFlux = Flux.fromIterable(listaNombres)
                .filter(name->name.length()>5)
                .map(String::toUpperCase)
                .log();

        StepVerifier.create(nombresFlux)
                .expectNext("GOOGLE")
                .expectNext("STACKOVERFLODW")
                .expectComplete()
                .verify();

    }

    @Test
    public void testTransformFlatMap(){
        List<String> listaNombres = Arrays.asList("google","abs","fs","StackOverFlodw");
        Flux<String> nombresFlux = Flux.fromIterable(listaNombres)
                .filter(name->name.length()>5)
                .flatMap(name->{
                    return Mono.just(name.toUpperCase());
                })
                .log();

        StepVerifier.create(nombresFlux)
                .expectNext("GOOGLE")
                .expectNext("STACKOVERFLODW")
                .expectComplete()
                .verify();

    }

    @Test
    public void TstCombinarFlujosUsandoMerge(){
        Flux<String> flux1 = Flux.just("google","abs","fs","StackOverFlodw");
        Flux<String> flux2 = Flux.just("pride","Monk","Walker");
        Flux<String> fluxMerge = Flux.merge(flux1,flux2)
                .log();

        StepVerifier.create(fluxMerge)
                .expectSubscription()
                .expectNext("google","abs","fs","StackOverFlodw","pride","Monk","Walker")
                .expectComplete()
                .verify();

    }

    @Test
    public void TestCombinarFlujosUsandoMergeConDelay(){
        Flux<String> flux1 = Flux.just("google","abs","fs","StackOverFlodw")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("pride","Monk","Walker")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> fluxMerge = Flux.merge(flux1,flux2)
                .log();

        StepVerifier.create(fluxMerge)
                .expectSubscription()
                .expectNextCount(7)
                .expectComplete()
                .verify();

    }

    @Test
    public void TestCombinarFlujosUsandoMergeConDelayConcat(){
        Flux<String> flux1 = Flux.just("google","abs","fs","StackOverFlodw")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("pride","Monk","Walker")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> fluxConcat = Flux.concat(flux1,flux2)
                .log();

        StepVerifier.create(fluxConcat)
                .expectSubscription()
                .expectNext("google","abs","fs","StackOverFlodw","pride","Monk","Walker")
                .expectComplete()
                .verify();

    }

    @Test
    public void TestCombinarFlujosUsandoMergeConDelayZip(){
        Flux<String> flux1 = Flux.just("google","abs","fs","StackOverFlodw")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("pride","Monk","Walker","Mike")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxZip = Flux.zip(flux1,flux2, (f1,f2)-> f1.concat(" ").concat(f2))
                .log();

        StepVerifier.create(fluxZip)
                .expectSubscription()
                .expectNext("google pride","abs Monk","fs Walker","StackOverFlodw Mike")
                .expectComplete()
                .verify();

    }

}
