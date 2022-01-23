package net.nofate.musicclient.service;

import net.nofate.musicclient.data.TrackRepository;
import net.nofate.musicclient.model.MusicServiceConfig;
import net.nofate.musicclient.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class MusicService {

    @Autowired
    private final TrackRepository trackRepository;
    private final MusicServiceConfig musicServiceConfig;
//    private final ArtistRepository artistRepository;
//     CRUDs for tracks, artists, etc.

    public MusicService(TrackRepository trackRepository, MusicServiceConfig musicServiceConfig) {
        this.trackRepository = trackRepository;
        this.musicServiceConfig = musicServiceConfig;
    }

    public Iterable<Track> tracksGetAll() {
        var tracks = this.trackRepository.findAll();
        return tracks;
    }

    public Track tracksCreate(@RequestParam("isrc") String isrc) throws Exception {
        Track localItem = this.trackRepository.findByIsrc(isrc);
        if (null != localItem) {
            throw new Exception("track_exists"); // TODO enum or else for status codes..
        } else {

        }
        return null;
    }
}
