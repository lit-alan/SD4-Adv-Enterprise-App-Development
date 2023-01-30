package com.example.labex1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DateMVCController {

    int[] arr;

    public DateMVCController() {
        arr = FileParser.fillArray();
     }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/pop")
    public ModelAndView getPopularity(@RequestParam String day,
                                      @RequestParam String month) {
        String message = "";
        if (DateUtility.isValidDate(day + "/" + month)) {
            int dayOfYear = DateUtility.getDayOfYear(day + "/" + month);
            message = "Your date is the " + DateUtility.getSuffix(arr[dayOfYear]) + " most popular";
        }
        else {
            message = "Invalid Date Format";
        }
         return new ModelAndView("result", "message", message);
    }
}
