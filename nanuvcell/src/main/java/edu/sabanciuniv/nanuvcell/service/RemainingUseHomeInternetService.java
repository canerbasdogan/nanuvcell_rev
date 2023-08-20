package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.RemainingUseDto;
import edu.sabanciuniv.nanuvcell.dto.RemainingUseHomeInternetDto;
import edu.sabanciuniv.nanuvcell.exception.RemainingUseNotFoundException;
import edu.sabanciuniv.nanuvcell.model.HomeInternet;
import edu.sabanciuniv.nanuvcell.model.RemainingUse;
import edu.sabanciuniv.nanuvcell.model.RemainingUseHomeInternet;
import edu.sabanciuniv.nanuvcell.model.User;
import edu.sabanciuniv.nanuvcell.repository.RemainingUseHomeInternetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemainingUseHomeInternetService {

    private final RemainingUseHomeInternetRepository remainingUseHomeInternetRepository;

    public RemainingUseHomeInternetService(RemainingUseHomeInternetRepository remainingUseHomeInternetRepository) {
        this.remainingUseHomeInternetRepository = remainingUseHomeInternetRepository;
    }

    public RemainingUseHomeInternetDto findRemainingUseHomeInternet(Long id) {
        RemainingUseHomeInternet remainingUseHomeInternet = remainingUseHomeInternetRepository.findRemainingUseByUserId(id)
                .orElseThrow(
                        () -> new RemainingUseNotFoundException("User dont have any remaining use"));

        return RemainingUseHomeInternetDto.convert(remainingUseHomeInternet);
    }

    public RemainingUseHomeInternetDto findRemainingUseHomeInternetById(Long id) {
        List<RemainingUseHomeInternetDto> remainingUseHomeInternetDtoList = remainingUseHomeInternetRepository.findAll()
                .stream()
                .filter(h -> h.getUser().getId() == id)
                .map(RemainingUseHomeInternetDto::convert)
                .collect(Collectors.toList());

        return remainingUseHomeInternetDtoList.get(0);
    }

    protected void createRemainingUseHomeInternet(User user, HomeInternet homeInternet) {
        RemainingUseHomeInternet remainingUseHomeInternet = RemainingUseHomeInternet.builder()
                .remainingInternet(homeInternet.getInternetQuota())
                .user(user)
                .homeInternet(homeInternet)
                .build();

        remainingUseHomeInternetRepository.save(remainingUseHomeInternet);
    }

    public void deleteRemainingUseHomeInternetById(Long id) {

        RemainingUseHomeInternet remainingUseHomeInternet = remainingUseHomeInternetRepository.findRemainingUseByUserId(id)
                .orElseThrow(
                        () -> new RemainingUseNotFoundException("User dont have any remaining use"));
        remainingUseHomeInternetRepository.deleteById(remainingUseHomeInternet.getId());
    }
}
