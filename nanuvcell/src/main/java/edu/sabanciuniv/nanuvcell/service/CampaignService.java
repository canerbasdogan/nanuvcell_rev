package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.CampaignDto;
import edu.sabanciuniv.nanuvcell.dto.CreateCampaignRequest;
import edu.sabanciuniv.nanuvcell.exception.CampaignNotFoundException;
import edu.sabanciuniv.nanuvcell.model.Campaign;
import edu.sabanciuniv.nanuvcell.model.MobileTariff;
import edu.sabanciuniv.nanuvcell.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignService {

    private final CampaignRepository repository;
    private final MobileTariffService mobileTariffService;

    public CampaignService(CampaignRepository repository, MobileTariffService mobileTariffService) {
        this.repository = repository;
        this.mobileTariffService = mobileTariffService;
    }

    public void createCampaign(CreateCampaignRequest request) {
        MobileTariff mobileTariff = mobileTariffService.findMobileTariffById(request.mobileTariffId());

        DateTimeFormatter formatter
                = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        Campaign campaign = Campaign.builder()
                .campaignName(request.campaignName())
                .campaignDetail(request.campaignDetail())
                .campaignStartDate(LocalDateTime.parse(request.campaignStartDate(), formatter))
                .campaignEndDate(LocalDateTime.parse(request.campaignEndDate(), formatter))
                .internetQuota(request.internetQuota())
                .speakingQuota(request.speakingQuota())
                .messageQuota(request.messageQuota())
                .discountFactor(request.discountFactor())
                .mobileTariff(mobileTariff)
                .build();

        repository.save(campaign);
    }

    public List<CampaignDto> findAllCampaigns() {
        return repository.findAll()
                .stream()
                .map(CampaignDto::convert)
                .collect(Collectors.toList());
    }

    public CampaignDto findCampaignById(Long id) {
        Campaign campaign = repository.findById(id)
                .orElseThrow(
                        () -> new CampaignNotFoundException("Campaign not found by id : " + id));

        return CampaignDto.convert(campaign);
    }
}
