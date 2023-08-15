package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Campaign;

import java.time.LocalDateTime;

public record CampaignDto(Long id,
                          String campaignName,
                          String campaignDetail,
                          LocalDateTime campaignStartDate,
                          LocalDateTime campaignEndDate,
                          double internetQuota,
                          double speakingQuota,
                          int messageQuota,
                          double discountFactor,
                          Long mobileTariffId,
                          String tariffName,
                          double tariffInternetQuota,
                          double tariffPrice) {

    public static CampaignDto convert(Campaign from) {
        return new CampaignDto(
                from.getId(),
                from.getCampaignName(),
                from.getCampaignDetail(),
                from.getCampaignStartDate(),
                from.getCampaignEndDate(),
                from.getInternetQuota(),
                from.getSpeakingQuota(),
                from.getMessageQuota(),
                from.getDiscountFactor(),
                from.getMobileTariff().getId(),
                from.getMobileTariff().getTariffName(),
                from.getMobileTariff().getInternetQuota(),
                from.getMobileTariff().getTariffPrice());
    }
}
