package net.nofate.musicclient.controller;

import net.nofate.musicclient.model.Track;
import net.nofate.musicclient.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ApiController {
    MusicService musicService;

    @Autowired
    public ApiController(MusicService musicService) {
        this.musicService = musicService;

//        MusicServiceConfig musicServiceConfig = new MusicServiceConfig();
    }


    @GetMapping({"/api"}) // TODO group /api route
    public String home() {
        return "";
    }

    @GetMapping({"/api/tracks/find"})
    public Track tracksGetOne(@RequestParam("isrc") String isrc) {
//        @PathVariable("objectId") Long objectId;
        return this.musicService.tracksGetOne(isrc);
    }

    @GetMapping({"/api/tracks"})
    public Iterable<Track> tracksGetAll() {
        return this.musicService.tracksGetAll();
    }

    @PostMapping({"/api/tracks"})
    public Track tracksCreate(@RequestParam("isrc") String isrc){
        try {
            return this.musicService.tracksCreate(isrc);
        } catch (Exception e) {
//            e.printStackTrace();
            switch (e.getMessage()) {
                case "track_exists":
                    throw new ResponseStatusException(409, "This Track already exists!", null);
                default:
                    throw new ResponseStatusException(400, "Unknown error", null); // TODO find best status code
            }
        }
    }

}
