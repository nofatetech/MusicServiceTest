package net.nofate.musicclient.service;

import net.nofate.musicclient.data.TrackRepository;
import net.nofate.musicclient.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {

    private final TrackRepository trackRepository;
    private SpotifyApiService spotifyApiService;

    @Autowired
    public MusicService(TrackRepository trackRepository, SpotifyApiService spotifyApiService) {
        this.trackRepository = trackRepository;
        this.spotifyApiService = spotifyApiService;
    }

    public Iterable<Track> tracksGetAll() {
        var tracks = this.trackRepository.findAll();
        return tracks;
    }

    public Track tracksCreate(String isrc) throws Exception {
        Track titem = this.trackRepository.findByIsrc(isrc);
        if (null != titem) {
            throw new Exception("track_exists"); // TODO enum or else for list of return status codes..
        } else {
            se.michaelthelin.spotify.model_objects.specification.Track remoteTrack = this.spotifyApiService.findTrack(isrc);
            if (null == remoteTrack) {
                throw new Exception("track_remote_not_found");
            } else {

                Track newTrack = new Track();
                newTrack.setIsrc(isrc);
                newTrack.setName(remoteTrack.getName());
                newTrack.setDuration(remoteTrack.getDurationMs());
                newTrack.setExplicit(remoteTrack.getIsExplicit());

                this.trackRepository.save(newTrack);

                return newTrack;
            }
        }
    }

    public Track tracksGetOne(String isrc) {
        return this.trackRepository.findByIsrc(isrc);
    }
}
