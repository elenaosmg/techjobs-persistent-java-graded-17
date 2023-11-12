package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

// TODO: Add a field
//  employerRepository annotated with @Autowired.
    @Autowired //EO needed for Spring to automatically use data from EmployerRepository to add to this field
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }


    // TODO: A user will select an employer when they create a job. Add the employer
    //  data from employerRepository into the form template. The add job form already
    //  includes an employer selection option. Be sure your variable name for the
    //  employer data matches that already used in templates/add.



    // TODO: A user will select skills when they create a job. add skills data from
    //  skillsRepository into the form template.


    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");

    List <Employer> employers = (List<Employer>) employerRepository.findAll();
    List <Skill>skills = (List<Skill>) skillRepository.findAll();

    model.addAttribute("employers", employers); //EO here we pass
        // the list of employers to the model;
    model.addAttribute ("skills",skills);

        model.addAttribute(new Job());
        return "add";
    }

    // TODO: add code inside processAddJobForm to select
    //  the employer object that has been chosen to be affiliated with the
    //  new job, select the employer using the request parameter you’ve
    //  added to the method.
    // do the same for skills

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        }

        //EO task3 for jobs
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        if (optionalEmployer.isPresent()) {
            Employer selectedEmployer = optionalEmployer.get();
            selectedEmployer.addJob(newJob); // EO: Add the job to the employer's list of jobs
            employerRepository.save(selectedEmployer); //EO: Save the updated employer

           newJob.setEmployer(selectedEmployer); // EO: connect the employer with the new job
        }
        //EO task4 for skills
        List <Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        //EO get the skills by ID
        newJob.setSkills(skillObjs); //EO Set skills for the new job

        jobRepository.save(newJob ); //  EO: Save the new job

        return "redirect:";
    }


    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional <Job> optionalJob = jobRepository.findById (jobId);
        if (optionalJob.isPresent()) {
            Job selectedJob = optionalJob.get();
            model.addAttribute("job", selectedJob);

        }

            return "view";
    }

}
