package net.nofate.musicclient.model;

import se.michaelthelin.spotify.SpotifyApi;

import javax.persistence.Entity;

@Entity
public class MusicServiceConfig {
    public String clientId;
    public String clientSecret;
    public SpotifyApi spotifyApi;

}
