package org.milkteaboy.simplefarm.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.milkteaboy.simplefarm"})
public class GameApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

}
