package l6.source.sd4.controllers;


import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/message")
public class MessageController {

    String message = "Hello World";

    @ModelAttribute
    public void setUp(Model model) {
        model.addAttribute("title", "Page Title");
        model.addAttribute("message", "Hello World");
    }

    @ModelAttribute
    public void setUpFooter(Model model) {
        LocalDate now = LocalDate.now();
        model.addAttribute("footer", "(C) TUS " + now.getYear());

    }

    @GetMapping("/hello")
    public String handleRequest() {
        return "/hello";
    }

    @GetMapping("/")
    @ResponseBody
    public String index() { return message; }

    @GetMapping("/english")
    public String handleEN() {
        message = "Hello World";
        return "redirect:/message/";
    }

    @GetMapping("/irish")
    public String handleIE() {
        message = "Dia duit ar domhan";
        return "redirect:/message/";
    }

    @GetMapping("/french")
    public String handleFR() {
        message = "Bonjour le monde";
        return "forward:/message/";
    }

//    @GetMapping("/")
//    @ResponseBody
//    public String index(@RequestParam("message") String message, @ModelAttribute("flashmessage") String flashmessage) {
//        return message + ", " + flashmessage;
//    }
//
//    @GetMapping("/english")
//    public ModelAndView handleEN(RedirectAttributes attributes) {
//        attributes.addAttribute("message", "Hello World");
//        attributes.addFlashAttribute("flashmessage", "soft day thank god");
//        return new ModelAndView("redirect:/message/");
//    }
//
//    @GetMapping("/irish")
//    public ModelAndView handleIE(RedirectAttributes attributes) {
//
//        attributes.addAttribute("message", "Dia duit ar domhan");
//        attributes.addFlashAttribute("flashmessage", "lá bog buíochas le dia");
//        return new ModelAndView("redirect:/message/");
//    }
//
//    @GetMapping("/french")
//    public ModelAndView handleFR(RedirectAttributes attributes) {
//        attributes.addAttribute("message", "Bonjour le monde");
//        attributes.addFlashAttribute("flashmessage", "douce journée merci dieu");
//        return new ModelAndView("redirect:/message/");
//    }
}