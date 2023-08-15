package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.AnnouncementDto;
import edu.sabanciuniv.nanuvcell.exception.AnnouncementNotFoundException;
import edu.sabanciuniv.nanuvcell.model.Announcement;
import edu.sabanciuniv.nanuvcell.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    private final AnnouncementRepository repository;

    public AnnouncementService(AnnouncementRepository repository) {
        this.repository = repository;
    }

    public List<AnnouncementDto> findAllAnnouncements() {
        return repository.findAll()
                .stream()
                .map(AnnouncementDto::convert)
                .collect(Collectors.toList());
    }

    public AnnouncementDto findAnnouncementById(Long id) {
        Announcement announcement= findAnnouncementByGivenId(id);

        return AnnouncementDto.convert(announcement);
    }

    private Announcement findAnnouncementByGivenId(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new AnnouncementNotFoundException("Announcement not find by id : " + id));
    }
}
