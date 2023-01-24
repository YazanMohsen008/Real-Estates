package com.informationengineering.internetapplicationproject.Services;

import com.informationengineering.internetapplicationproject.Models.Estate;
import com.informationengineering.internetapplicationproject.Models.Parameter;
import com.informationengineering.internetapplicationproject.Models.User;
import com.informationengineering.internetapplicationproject.Repositories.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ParameterService {
    @Autowired
    ParameterRepository parameterRepository;

    @Cacheable("params")
    public String getValueOfParameter(String key) {
        System.out.println("USING DB TO GET DATA ");
        return parameterRepository.findByKey(key).getValue();
    }

    public void addParameter(Parameter parameter) {
        parameterRepository.save(parameter);
    }

    public List<Parameter> getAllParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameterRepository.findAll().iterator().forEachRemaining(parameters::add);
        return parameters;
    }


    public void deleteParameter(String key) {
        parameterRepository.deleteById(key);
    }

    public void updateParameter(Parameter parameter) {
        parameterRepository.save(parameter);
    }

}
