package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("skills")
public class SkillController {


    //TODO: Add a private field of EmployerRepository type called employerRepository.
    // Give this field an @Autowired annotation.


    @Autowired //EO needed for Spring to automatically use data from EmployerRepository to add to this field
    private SkillRepository skillRepository; //EO +want to save a new employer, for this added down there employerRepository.save(newEmployer);


    // TODO: Add an index method that responds to the path /employers with a list of all
    //  employers in the database. This method should use the template
    //  employers/index. To figure out the name of the model attribute you
    //  should use to pass employers into the view, review this template.

    @GetMapping("/") // EO: to handle HTTP GET request
    public String index(Model model) {
        Iterable <Skill> skills = skillRepository.findAll();
        model.addAttribute("skills", skills);
        return "skills/index";

    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }


//   TODO: processAddEmployerForm already takes care of sending the form back
//    if any of the submitted employer object information is invalid.
//    Write code to save a valid object. Use employerRepository and the appropriate method.


    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill, // @ModelAttribute tells to use the use's input to populate newEmployer object
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        }

        // EO added in addition to employerRepository field. It saves the new employer to the
        // database using the repository
        skillRepository.save(newSkill);

        return "redirect:";
    }

    // TODO: displayViewEmployer will be in charge of rendering a page to view
    //  the contents of an individual employer object. It will make use of
    //  that employer object’s id field to grab the correct information from
    //  employerRepository. optEmployer should use .findById() method with
    //  the right argument to look for the given employer object from the data
    //  layer.

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional<Skill> optSkill = skillRepository.findById(skillId);
        //EO Optional because may or may not contain data

        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }

    }
}

