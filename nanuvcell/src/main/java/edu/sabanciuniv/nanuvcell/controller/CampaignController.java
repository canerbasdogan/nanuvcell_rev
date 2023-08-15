package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.CampaignDto;
import edu.sabanciuniv.nanuvcell.dto.CreateCampaignRequest;
import edu.sabanciuniv.nanuvcell.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService service;

    public CampaignController(CampaignService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CampaignDto>> findAllCampaigns() {
        return ResponseEntity.ok(service.findAllCampaigns());
    }

    @PostMapping
    public void createCampaign(@RequestBody CreateCampaignRequest request) {
        service.createCampaign(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDto> findCampaignById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findCampaignById(id));
    }
}
