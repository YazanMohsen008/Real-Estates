package com.informationengineering.internetapplicationproject.Repositories;

import com.informationengineering.internetapplicationproject.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);
}
