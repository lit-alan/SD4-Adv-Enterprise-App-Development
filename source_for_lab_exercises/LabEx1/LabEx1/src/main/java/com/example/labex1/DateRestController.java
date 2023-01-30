package com.example.labex1;

import org.springframework.web.bind.annotation.*;

//@RestController
@RequestMapping("/date")
public class DateRestController {

   int[] arr;

   public DateRestController() {
        arr = FileParser.fillArray();
   }

    @GetMapping("/popularity/{day}/{month}")
    public String getPopularity(@PathVariable String day,
                                @PathVariable String month) {

       if (DateUtility.isValidDate(day + "/" + month)) {
            int dayOfYear = DateUtility.getDayOfYear(day + "/" + month);
            return Integer.toString(arr[dayOfYear]);
        }
        else {
            return "Invalid Date Format";
        }
    }

    @PostMapping("/popularity/{day}/{month}")
    public String getPopularityWithSuffix(@PathVariable String day,
                                @PathVariable String month) {

        if (DateUtility.isValidDate(day + "/" + month)) {
            int dayOfYear = DateUtility.getDayOfYear(day + "/" + month);
            return DateUtility.getSuffix(arr[dayOfYear]);
        }
        else {
            return "Invalid Date Format";
        }
    }
}
