package net.nofate.musicclient.data;

import net.nofate.musicclient.model.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {
    Track findByIsrc(String isrc);
}
