package com.bookmyshow.Repository;

import com.bookmyshow.entity.ATM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmRepository extends JpaRepository<ATM,Integer> {
}
