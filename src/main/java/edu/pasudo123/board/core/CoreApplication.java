package edu.pasudo123.board.core;

import edu.pasudo123.board.core.config.ProfileConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CoreApplication implements CommandLineRunner {

    private final ProfileConfiguration profileConfiguration;

    public static void main(String[] args) {

        SpringApplication.run(CoreApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(profileConfiguration);


    }
}
