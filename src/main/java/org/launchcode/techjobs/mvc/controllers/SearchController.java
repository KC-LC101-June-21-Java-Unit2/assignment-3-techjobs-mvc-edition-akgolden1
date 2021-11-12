package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Array;
import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")

public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping ("results")//from form tag
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){


    //if the user enters all, or leave the search box empty, call findAll() from JobData. Need a variable to store that.
        ArrayList<Job> jobs = new ArrayList<>();
    if (searchTerm.toLowerCase().equals("all") || searchTerm.equals ("")){
        jobs = JobData.findAll();
    }
    else {
        jobs = JobData.findByColumnAndValue(searchType,searchTerm); //call findByColumnAndValue, pass it search terms from above.
    }
        model.addAttribute("jobs",jobs); //hopefully pass jobs to search.html using model
        model.addAttribute("columns", columnChoices); //not sure about second param
        return "search"; //returns the template. Params above taken from form and text
    }
}
