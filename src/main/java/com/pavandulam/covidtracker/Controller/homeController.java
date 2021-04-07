package com.pavandulam.covidtracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pavandulam.covidtracker.models.locationStats;
import com.pavandulam.covidtracker.services.covidDataService;

@Controller
public class homeController {
	
	@Autowired
	covidDataService coviddataservice; 
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<locationStats> allstats = coviddataservice.getAllstats();
		int totalReportedCases=allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases=allstats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
		model.addAttribute("locationstats",allstats );
		model.addAttribute("totalReportedCases",totalReportedCases );
		model.addAttribute("totalNewCases",totalNewCases );
		//model.addAttribute("totalNewCases",allstats.g);
		return "home";
	}

}
