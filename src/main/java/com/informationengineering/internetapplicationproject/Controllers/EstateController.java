package com.informationengineering.internetapplicationproject.Controllers;

import com.informationengineering.internetapplicationproject.Models.Estate;
import com.informationengineering.internetapplicationproject.Services.EstateService;
import com.informationengineering.internetapplicationproject.Services.ParameterService;
import com.informationengineering.internetapplicationproject.Services.TransactionException;
import com.informationengineering.internetapplicationproject.Utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/estate")
public class EstateController {

    @Autowired
    EstateService estateService;
    @Autowired
    ParameterService parameterService;


    @RequestMapping(value = "/addEstatePage", method = RequestMethod.GET)
    String addEstatePage(Model model) {
        model.addAttribute("stockCount", parameterService.getValueOfParameter(Const.STOCK_COUNT_KEY));
        return "estate/addEstate";
    }



    @RequestMapping(value = "/getEstatePage", method = RequestMethod.GET)
    String getEstatePage(Model model) {
        List<Estate> estates = estateService.getAllEstates();
        model.addAttribute("estates", estates);
        return "estate/displayEstates";
    }

    @RequestMapping(value = "/updateEstatePage/{id}", method = RequestMethod.GET)
    String updateEstatePage(@PathVariable Integer id, Model model) {
        Estate Estate = estateService.getEstateById(id);
        model.addAttribute("estate", Estate);
        return "estate/updateEstate";
    }

    @RequestMapping(value = "/unSelledEstatesPage", method = RequestMethod.GET)
    String unSelledEstatesPage(Model model) {
        List<Estate> unSelledEstates = estateService.getUnSelledEstates();
        model.addAttribute("estates", unSelledEstates);
        model.addAttribute("ProfitRatio", parameterService.getValueOfParameter(Const.PROFIT_RATIO_KEY));
        return "estate/unSelledEstates";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String addEstate(@ModelAttribute Estate estate, Model model) {
        estateService.addEstate(estate);
        return getEstatePage(model);

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    String deleteEstate(@PathVariable Integer id, Model model) {
        estateService.deleteEstateById(id);
        return getEstatePage(model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    String updateEstate(Estate estate, Model model) {
        estateService.updateEstate(estate);
        return getEstatePage(model);

    }

    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    public String sellEstate(@ModelAttribute Estate estate, Model model) {
        try {
            estateService.sellEstate(estate);
        } catch (TransactionException transactionException) {
            transactionException.printStackTrace();
            model.addAttribute("error", "transactionError");
            return unSelledEstatesPage(model);
        }
        return getEstatePage(model);
    }


}
