package com.tictactoe.di;

import com.tictactoe.datasource.repository.GameRepository;
import com.tictactoe.datasource.repository.GameStorageImpl;
import com.tictactoe.domain.service.GameService;
import com.tictactoe.domain.service.GameServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public GameStorageImpl gameStorage() {
        return new GameStorageImpl();
    }

    @Bean
    public GameRepository gameRepository(GameStorageImpl gameStorage) {
        return new GameRepository(gameStorage);
    }

    @Bean
    public GameService gameService(GameRepository gameRepository) {
        return new GameServiceImpl(gameRepository);
    }
}
