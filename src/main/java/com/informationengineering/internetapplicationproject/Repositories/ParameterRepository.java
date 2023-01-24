package com.informationengineering.internetapplicationproject.Repositories;

import com.informationengineering.internetapplicationproject.Models.Parameter;
import com.informationengineering.internetapplicationproject.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends CrudRepository<Parameter,String> {
    Parameter findByKey(String key);
}
