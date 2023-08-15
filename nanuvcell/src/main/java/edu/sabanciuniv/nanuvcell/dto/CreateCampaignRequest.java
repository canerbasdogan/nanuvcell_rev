package edu.sabanciuniv.nanuvcell.dto;

import java.time.LocalDateTime;

public record CreateCampaignRequest(String campaignName,
                                    String campaignDetail,
                                    String campaignStartDate,
                                    String campaignEndDate,
                                    double internetQuota,
                                    double speakingQuota,
                                    int messageQuota,
                                    double discountFactor,
                                    Long mobileTariffId) {
}
