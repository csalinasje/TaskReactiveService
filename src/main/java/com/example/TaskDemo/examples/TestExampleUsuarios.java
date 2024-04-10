package com.example.TaskDemo.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class TestExampleUsuarios {
    private static final Logger log= LoggerFactory.getLogger(TestExampleUsuarios.class);

    public static void main(String[] args) {
        Flux<String> nombres = Flux.just("Juan Lucas", "Antonio Javier","Mery Ramirez", "Americo Perico");
        Flux<Usuario> usuarios = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toLowerCase(),nombre.split(" ")[1].toUpperCase()))
                .filter(usuario -> !usuario.getApellido().equalsIgnoreCase("LUCAS"))
                .doOnNext( usuario -> {
                    if (usuario == null){ throw new RuntimeException("Los nombres no pueden estar empty");}
                    System.out.println(usuario.getNombre().concat("").concat(usuario.getApellido()));

                } )
                .map(usuario -> {
                    String name = usuario.getNombre().toUpperCase();
                            usuario.setNombre(name);
                    return usuario;
                });

        usuarios.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()),
                () -> log.info("Se ha finalizado la ejecución del obsrvable"));
    }
}
