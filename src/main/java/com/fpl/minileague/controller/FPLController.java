package com.fpl.minileague.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/fpl")
@CrossOrigin
public class FPLController {

	private String fplURL_table = "https://fantasy.premierleague.com/api/leagues-h2h/{leagueID}/standings/";
	private String fplURL_fixtures = "https://fantasy.premierleague.com/api/leagues-h2h-matches/league/{leagueID}/?event=";

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/table")
	public Object GetMiniLeagueStandings(@RequestParam String leagueID) {

		return restTemplate.getForEntity(fplURL_table, Object.class, leagueID).getBody();

	}

	@GetMapping(value = "/fixtures")
	public Object GetMiniLeagueFixtures(@RequestParam String gameweek, @RequestParam String leagueID) {

		return restTemplate.getForEntity(fplURL_fixtures+gameweek, Object.class,leagueID).getBody();

	}
}
