package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.RemainingUseDto;
import edu.sabanciuniv.nanuvcell.exception.RemainingUseNotFoundException;
import edu.sabanciuniv.nanuvcell.model.*;
import edu.sabanciuniv.nanuvcell.repository.RemainingUseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemainingUseService {

    private final RemainingUseRepository repository;

    public RemainingUseService(RemainingUseRepository repository) {
        this.repository = repository;
    }

    public RemainingUseDto findRemainingUse(Long id) {
        RemainingUse remainingUse = repository.findRemainingUseByUserId(id)
                .orElseThrow(
                        () -> new RemainingUseNotFoundException("User dont have any remaining use"));

        return RemainingUseDto.convert(remainingUse);
    }

    public RemainingUseDto findRemainingUseById(Long id) {
        List<RemainingUseDto> remainingUseDtoList = repository.findAll()
                .stream()
                .filter(r -> r.getUser().getId() == id)
                .map(RemainingUseDto::convert)
                .collect(Collectors.toList());

        return remainingUseDtoList.get(0);
    }

    protected void createRemainingUse(User user, MobileTariff mobileTariff) {
        RemainingUse remainingUse = RemainingUse.builder()
                .remainingMessage(mobileTariff.getMessageQuota())
                .remainingSpeaking(mobileTariff.getSpeakingQuota())
                .user(user)
                .mobileTariff(mobileTariff)
                .build();

        repository.save(remainingUse);
    }
}
