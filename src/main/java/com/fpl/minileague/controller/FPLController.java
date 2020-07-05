package com.fpl.minileague.controller;

import java.util.ArrayList;

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

		return restTemplate.getForEntity(fplURL_fixtures + gameweek, Object.class, leagueID).getBody();

	}

	@GetMapping(value = "/draw")
	public String GetCupFixtures(@RequestParam int teams) {

		String drawResult = "";
		ArrayList<Integer> generated = new ArrayList<>();
		while (generated.size() < teams) {
			int num = (int) Math.floor(Math.random() * teams)+1;
			if (!(generated.contains(num))) {
				generated.add(num);
			}
		}

		for (int index = 0; index < generated.size(); index++) {
			drawResult += generated.get(index);
			if (index % 2 == 0)
				drawResult += "\tvs\t";
			else
				drawResult += "<br><br>";

		}
		return drawResult;
	}

}
