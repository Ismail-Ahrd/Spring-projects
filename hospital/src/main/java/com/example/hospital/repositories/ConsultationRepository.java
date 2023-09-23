package com.example.hospital.repositories;

import com.example.hospital.entities.Consultation;
import com.example.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
