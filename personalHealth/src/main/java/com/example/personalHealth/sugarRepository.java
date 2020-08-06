package com.example.personalHealth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.personalHealth.sugar;

@Repository
public interface sugarRepository extends JpaRepository<sugar ,Long> {
}
