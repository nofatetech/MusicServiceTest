package net.nofate.musicclient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public final class Track {
    @Id
    public Long id;
    @Column(nullable = false)
    private String isrc;
    @Column(nullable = false)
    private String name;
}
