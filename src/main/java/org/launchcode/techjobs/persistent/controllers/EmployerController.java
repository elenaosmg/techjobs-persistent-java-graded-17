package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {


 //TODO: Add a private field of EmployerRepository type called employerRepository.
 // Give this field an @Autowired annotation.


    @Autowired //EO needed for Spring to automatically use data from EmployerRepository to add to this field
    private EmployerRepository employerRepository; //EO +want to save a new employer, for this added down there employerRepository.save(newEmployer);


    // TODO: Add an index method that responds to the path /employers with a list of all
    //  employers in the database. This method should use the template
    //  employers/index. To figure out the name of the model attribute you
    //  should use to pass employers into the view, review this template.

    @GetMapping ("/")  // EO: to handle HTTP GET request
    public String index(Model model) {
        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("employers", employers);
        return "employers/index";

    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }


//   TODO: processAddEmployerForm already takes care of sending the form back
//    if any of the submitted employer object information is invalid.
//    Write code to save a valid object. Use employerRepository and the appropriate method.


    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer, // @ModelAttribute tells to use the use's input to populate newEmployer object
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }

        // EO added in addition to employerRepository field. It saves the new employer to the
            // database using the repository
        employerRepository.save(newEmployer);

        return "redirect:";
    }

    // TODO: displayViewEmployer will be in charge of rendering a page to view
    //  the contents of an individual employer object. It will make use of
    //  that employer object’s id field to grab the correct information from
    //  employerRepository. optEmployer should use .findById() method with
    //  the right argument to look for the given employer object from the data
    //  layer.

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional <Employer> optEmployer = employerRepository.findById(employerId);
        //EO Optional because may or may not contain data

        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }

    }
}
