package com.informationengineering.internetapplicationproject;

import com.informationengineering.internetapplicationproject.Models.Estate;
import com.informationengineering.internetapplicationproject.Models.Parameter;
import com.informationengineering.internetapplicationproject.Repositories.EstateRepository;
import com.informationengineering.internetapplicationproject.Repositories.ParameterRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InternetApplicationProjectApplicationTests {

    @Autowired
    EstateRepository estateRepository;
    @Mock
    ParameterRepository parameterRepository;


    @Test
    @Order(1)
    public void addEstate() {
        String estateName = "testEstate";
        double price = 12.0;
//        estateRepository = new BeanFactory().getBean("estate")
        when(parameterRepository.findByKey("stockCountDefault")).thenReturn(new Parameter("stockCountDefault", "10"));
        Integer stocksCount = Integer.parseInt(parameterRepository.findByKey("stockCountDefault").getValue());
        Estate estate = new Estate(estateName, price, stocksCount);
        estateRepository.save(estate);
        Estate insertedEstate = estateRepository.findByName(estateName);
        assertThat(insertedEstate).isNotNull();
        System.out.println("inserted Estate ID:" + insertedEstate.getId());
        System.out.println("inserted Estate Name:" + insertedEstate.getName());
        System.out.println("inserted Estate Price:" + insertedEstate.getPrice());
        System.out.println("inserted Estate Stocks Count:" + insertedEstate.getStocksCount());
    }


    @Test
    @Order(2)
    public void updateEstate() {
        String name = "updateTestEstate";
        Estate estate = estateRepository.findById(1).get();
        estate.setName(name);
        estateRepository.save(estate);
        Estate updatedEstate = estateRepository.findById(1).get();
        assertThat(updatedEstate.getName()).isEqualTo(name);
        System.out.println("Update Estate ID:" + updatedEstate.getId());
        System.out.println("Update Estate Name:" + updatedEstate.getName());
    }

    @Test
    @Order(3)
    public void DeleteEstate() {
        estateRepository.deleteById(1);
        Estate insertedEstate = estateRepository.findById(1).orElse(null);
        assertThat(insertedEstate).isNull();
    }
}
