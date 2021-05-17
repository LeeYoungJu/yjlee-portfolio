package com.yjlee.portfolio.web;

import com.yjlee.portfolio.service.publ.MeetCountryCountService;
import com.yjlee.portfolio.web.dto.publ.MeetCountryCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class PublicDataApiController {

    private final MeetCountryCountService meetCountryCountService;

    @GetMapping("/public/api/meet/country/{year}")
    public String getMeetCnt(@PathVariable String year)  {
        return meetCountryCountService.getCountryMeetCnt(year);
    }

    @GetMapping("/public/api/meet/country")
    public String getMeetCntFullYear()  {
        return meetCountryCountService.getCountryMeetCnt("");
    }
}
