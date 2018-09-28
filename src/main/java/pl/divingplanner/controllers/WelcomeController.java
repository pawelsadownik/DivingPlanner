package pl.divingplanner.controllers;


import org.springframework.web.servlet.view.RedirectView;
import pl.divingplanner.emailService.EmailService;
import pl.divingplanner.excelService.DataColecting;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.divingplanner.excelService.ExcelReader;
import pl.divingplanner.model.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private DataColecting dataColecting;
    private EmailService emailService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profileForm(Model model) throws IOException, InvalidFormatException {
        model.addAttribute("profile", new Profile());

        return "profile";
    }

    @PostMapping("/profile")
    public String getProfile(@ModelAttribute Profile profile, BindingResult errors, Model model) throws IOException, InvalidFormatException {

        DivingProces divingProces = new DivingProces();
        ExcelReader excelReader = new ExcelReader();

        divingProces.depthStopsList.clear();
        divingProces.timeStopsList.clear();
        divingProces.depthStopsListBreak.clear();
        divingProces.timeStopsListBreak.clear();

        dataColecting.getStopsByDeapth(profile);

        List<Integer> time = new LinkedList<>();
        int sum=0;
        for (int i=0;i<divingProces.getTimeStopsList().size();i++){
            sum = sum + divingProces.getTimeStopsList().get(i);
            time.add(sum);
        }

        List<Integer> depth = new LinkedList<>(divingProces.getDepthStopsList());
        List<Integer> timeBreak = new LinkedList<>(divingProces.getTimeStopsListBreak());
        List<Integer> depthBreak = new LinkedList<>(divingProces.getDepthStopsListBreak());

        model.addAttribute("time", time);
        model.addAttribute("depth", depth);
        model.addAttribute("timeBreak", timeBreak);
        model.addAttribute("depthBreak", depthBreak);

        Email email = new Email();

        model.addAttribute("email", email);

        return "result";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute Email email, BindingResult errors, Model model) {

       // email.setContent( z sesji );
        //pl.divingplanner.emailService.send(email);
       //model.addAttribute("address", address);
        return "email";
    }
    @PostMapping(value = "/profile", params = "obliczenia")
    public String obliczenia() {

        return "calculations";
    }
}


