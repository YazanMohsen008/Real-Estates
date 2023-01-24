package com.informationengineering.internetapplicationproject.Controllers;

import com.informationengineering.internetapplicationproject.Models.Parameter;
import com.informationengineering.internetapplicationproject.Services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/parameter")
public class ParameterController {

    @Autowired
    ParameterService parameterService;



    @RequestMapping(value = "/getParameterPage", method = RequestMethod.GET)
    String getParameterPage(Model model) {
        List<Parameter> parameters = parameterService.getAllParameters();
        model.addAttribute("parameters", parameters);
        return "parameters";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String addParameter(@ModelAttribute Parameter parameter, Model model) {
        parameterService.addParameter(parameter);
        return getParameterPage(model);

    }

    @RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
    String deleteParameter(@PathVariable String key, Model model) {
        parameterService.deleteParameter(key);
        return getParameterPage(model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    String updateParameter(Parameter parameter, Model model) {
        parameterService.updateParameter(parameter);
        return getParameterPage(model);

    }



}
