package com.fpl.minileague.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fpl.model.FPLResponse;

@RestController
@RequestMapping(value = "/fpl")
@CrossOrigin
public class FPLController {

	private String fplURL = "https://fantasy.premierleague.com/api/leagues-h2h/{leagueID}/standings/";

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/minileague")
	public Object GetMiniLeagueStandings(@RequestParam String leagueID) {
		
		return restTemplate.getForEntity(fplURL, Object.class, leagueID).getBody();

	}
}
