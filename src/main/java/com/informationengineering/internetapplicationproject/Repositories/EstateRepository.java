package com.informationengineering.internetapplicationproject.Repositories;

import com.informationengineering.internetapplicationproject.Models.Estate;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface EstateRepository extends CrudRepository<Estate, Integer> {

    List<Estate> findAllByBuyerNameIsNull();

    Estate findByName(String name);

    Estate findByIdAndVersion(Integer id, Integer version);
}
