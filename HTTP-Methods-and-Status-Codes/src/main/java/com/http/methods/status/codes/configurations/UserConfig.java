package com.http.methods.status.codes.configurations;

import com.http.methods.status.codes.models.User;
import com.http.methods.status.codes.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User mohammed = User.builder()
                    .firstName("Mohammed")
                    .lastName("Alaoui")
                    .email("mohammed.alaoui@gmail.com")
                    .build();

            User ahmed = User.builder()
                    .firstName("Ahmed")
                    .lastName("Fathaoui")
                    .email("ahmed.fathaoui@gmail.com")
                    .build();

            userRepository.saveAll(List.of(mohammed, ahmed));
        };
    }
}
