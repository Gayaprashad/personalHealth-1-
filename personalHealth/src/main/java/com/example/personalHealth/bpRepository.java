package com.example.personalHealth;

import com.example.personalHealth.bp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bpRepository extends JpaRepository<bp, Long> {
}
