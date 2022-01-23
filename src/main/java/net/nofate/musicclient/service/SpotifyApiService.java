package net.nofate.musicclient.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.IOException;

@Service
public class SpotifyApiService {

    @Value("${spotify.client-id}") String spotifyClientId;
    @Value("${spotify.client-secret}") String spotifyClientSecret;
    @Autowired
    SpotifyApi spotifyApi;

//    public SpotifyClientService() {
//    }

    private final void clientCredentialsSync() {
        try {
            ClientCredentialsRequest clientCredentialsRequest;
            if (this.spotifyApi != null) {
                clientCredentialsRequest = this.spotifyApi.clientCredentials().build();
                var clientCredentials = clientCredentialsRequest.execute();
                this.spotifyApi.setAccessToken(clientCredentials != null ? clientCredentials.getAccessToken() : null);
                System.out.println("Expires in: " + (clientCredentials != null ? clientCredentials.getExpiresIn() : null));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


    public Track findTrack(String q) {
        this.clientCredentialsSync();

        try {
            SearchTracksRequest searchTracksRequest = this.spotifyApi.searchTracks("isrc:" + q).build();
            Paging trackPaging = searchTracksRequest != null ? searchTracksRequest.execute() : null;
            if (trackPaging != null && trackPaging.getTotal() > 0) {
                System.out.println("Total: " + trackPaging.getTotal());
                return ((Track[])trackPaging.getItems())[0];
            }
            return null;
        } catch (Exception e) {
            return null;

        }

    }


}
