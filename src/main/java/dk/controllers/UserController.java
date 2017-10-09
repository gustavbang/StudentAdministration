package dk.controllers;

import dk.models.entities.Student;
import dk.models.repositories.IStudentRepository;
import dk.models.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;


@Controller
public class UserController {

    
    @Autowired
    IStudentRepository studentRepo = new StudentRepository();

    HashMap<String, Student> students = new HashMap();

    // Read All
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students", studentRepo.readAll());
        return "index";
    }

    // Read One
    @GetMapping("/details")
    // her er muligvis fejl med string userId
    // requestparam sørger for at sende videre til details.html
    public String details(@RequestParam("studentId") String userId, Model model){
        int intId = Integer.parseInt(userId);
        model.addAttribute("stu", studentRepo.read(intId));
        return "details";
    }

    // Create
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("student", new Student());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Student stu){
        studentRepo.create(stu);
        return "redirect:/";
    }

    // Update
    @GetMapping("/update")
    // her er muligvis fejl med id
    //requestparam skal udover at skulle sende videre til update.html
    //hedde det samme som parameteren Student altså studentId præcist
    public String update(@RequestParam("studentId") String userId, Model model){
        int intId = Integer.parseInt(userId);
        model.addAttribute("student", studentRepo.read(intId));
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Student stu){
        studentRepo.update(stu);
        return "redirect:/";
    }

    // Delete
    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") String userId, Model model){
        int intId = Integer.parseInt(userId);
        studentRepo.delete(intId);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("student", new Student());
        return "login";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute Student skr){

       Student studentMember = studentRepo.login(skr);
       if(studentMember.getCpr() == null && studentMember.getPassword() == null) {
           return "loginFail";
       }

       students.put(RequestContextHolder.currentRequestAttributes().getSessionId(), studentMember);

        return "ExtremelySecret";

    }
}
