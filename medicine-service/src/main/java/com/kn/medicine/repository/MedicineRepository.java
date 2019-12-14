package com.kn.medicine.repository;

import com.kn.medicine.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @ClassName: MedicineRepository
 * @Description TODO:
 * @Date: 2019/12/12 10:19
 * @Author: Kn
 */
public interface MedicineRepository extends JpaRepository<Medicine, String> {

    Optional<Medicine> findByMedicineCode(String medicineCode);
}
