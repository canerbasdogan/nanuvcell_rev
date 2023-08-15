package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Announcement;

import java.time.LocalDateTime;

public record AnnouncementDto(Long id,
                              String announcement,
                              LocalDateTime announcementDate) {

    public static AnnouncementDto convert(Announcement from) {
        return new AnnouncementDto(
                from.getId(),
                from.getAnnouncement(),
                from.getAnnouncementDate());
    }
}
