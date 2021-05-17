package com.yjlee.portfolio.service.publ;

import com.yjlee.portfolio.web.dto.publ.MeetCountryCountDto;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@NoArgsConstructor
@Service
public class MeetCountryCountService {

    private final String API_URL = "http://apis.data.go.kr";
    private final String COUNTRY_MEET_PATH = "/6430000/actPdtStcLstRsgService/getActPdtStcLstRsg";
    private final String COUNTRY_MEET_KEY = "VkbS03Eav4J5PRInBdAVd3toRCXyS%2BJdjZX%2BUfzI0S9eBcS7T48m4bG%2FJ5FDmuHGRWv85Dd60H4eH5%2BY4xEa5g%3D%3D";


    public String getCountryMeetCnt(String year) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(API_URL);
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        UriBuilder uriBuilder = uriBuilderFactory.builder();
        uriBuilder
                .path(COUNTRY_MEET_PATH)
                .queryParam("serviceKey", COUNTRY_MEET_KEY)
                .queryParam("currentPage", "1")
                .queryParam("perPage", "1000")
                .queryParam("YEAR_ACCTO", year);

        ResponseEntity responseEntity = restTemplate.exchange(uriBuilder.build(), HttpMethod.GET, null, String.class);

        String response = (String) responseEntity.getBody();
        System.out.println(response);

        return response;

    }
}
