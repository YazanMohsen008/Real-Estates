package com.informationengineering.internetapplicationproject.Services;

import com.informationengineering.internetapplicationproject.Models.Estate;
import com.informationengineering.internetapplicationproject.Models.User;
import com.informationengineering.internetapplicationproject.Repositories.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional(propagation = Propagation.NEVER)
public class EstateService {

    @Autowired
    EstateRepository estateRepository;
    @Autowired
    ParameterService parameterService;

    public void addEstate(Estate estate) {
        estate.setCreatedAt(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        estate.setCreatedBy(user.getUsername());
        estateRepository.save(estate);
    }

    public List<Estate> getAllEstates() {
        List<Estate> estates = new ArrayList<>();
        estateRepository.findAll().iterator().forEachRemaining(estates::add);
        return estates;
    }

    public Estate getEstateById(Integer estateId) {
        return estateRepository.findById(estateId).get();
    }

    public void deleteEstateById(Integer id) {
        estateRepository.deleteById(id);
    }

    public void updateEstate(Estate estate) {

        Estate oldEstate = estateRepository.findById(estate.getId()).get();
        estate.setCreatedBy(oldEstate.getCreatedBy());
        estate.setCreatedAt(oldEstate.getCreatedAt());
        estate.setUpdatedAt(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        estate.setUpdatedBy(user.getUsername());
        estateRepository.save(estate);
    }

    @Transactional
    public void sellEstate(Estate estate) throws TransactionException {
        try {
            Estate oldEstate = estateRepository.findByIdAndVersion(estate.getId(), estate.getVersion());
            if (oldEstate == null) {
                System.out.println("Transaction Error");
                throw new TransactionException("Transaction Error");
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            System.out.println(user.getUsername() + "TRANSACTION . ... ..");
            oldEstate.setSaleDate(estate.getSaleDate());
            oldEstate.setSalePrice(estate.getSalePrice());
            oldEstate.setBuyerName(estate.getBuyerName());
            Integer updatedVersion = oldEstate.getVersion() + 1;
            oldEstate.setVersion(updatedVersion);
            estateRepository.save(oldEstate);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

//    @Transactional
//    public void sell(Estate estate) {
//        try {
//            Estate oldEstate = entityManager.find(Estate.class, estate.getId());
//            entityManager.lock(oldEstate, LockModeType.OPTIMISTIC);
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            User user = (User) authentication.getPrincipal();
//            System.out.println(user.getUsername() + "TRANSACTION . ... ..");
//            oldEstate.setSaleDate(estate.getSaleDate());
//            oldEstate.setSalePrice(estate.getSalePrice());
//            oldEstate.setBuyerName(estate.getBuyerName());
//            estateRepository.save(oldEstate);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//
//    }

    public List<Estate> getUnSelledEstates() {
        return estateRepository.findAllByBuyerNameIsNull();
    }

}
