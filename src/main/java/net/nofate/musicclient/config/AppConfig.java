package net.nofate.musicclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.michaelthelin.spotify.SpotifyApi;

@Configuration
public class AppConfig {
    SpotifyApi spotifyApi;

    @Bean
    @Qualifier("spotApi1")
    SpotifyApi spotifyApi(@Value("${spotify.client-id}") String clientId, @Value("${spotify.client-secret}") String clientSecret) {
        return (new SpotifyApi.Builder())
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }


}