package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.AnnouncementDto;
import edu.sabanciuniv.nanuvcell.service.AnnouncementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService service;

    public AnnouncementController(AnnouncementService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDto>> findAllAnnouncements() {
        return ResponseEntity.ok(service.findAllAnnouncements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDto> findAnnouncementById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAnnouncementById(id));
    }
}
