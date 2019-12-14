package com.kn.prescription.repository.prescription;

import com.kn.prescription.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: PrescriptionRepository
 * @Description TODO:
 * @Date: 2019/12/12 10:19
 * @Author: Kn
 */
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {

}
