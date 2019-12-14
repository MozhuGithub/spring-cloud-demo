package com.kn.medicine.service;

import com.kn.medicine.domain.Medicine;
import com.kn.medicine.repository.MedicineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: MedicineService
 * @Description TODO:
 * @Date: 2019/12/12 10:21
 * @Author: Kn
 */
@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> findAll() {
        return this.medicineRepository.findAll();
    }

    public ResponseEntity<Medicine> findById(String id) {
        return ResponseEntity.of(this.medicineRepository.findById(id));
    }
    /**
     * @Description TODO: 新增时避免id存在生产错误数据
     * @Param:  [medicine]
     * @Return:  org.springframework.http.ResponseEntity<com.kn.medicine.domain.Medicine>
     * @Date:
     * @Author: Kn
     */
    public ResponseEntity<Medicine> createMedicine(Medicine medicine) {
        return ResponseEntity.ok(
                this.medicineRepository.save(
                        medicine.toBuilder()
                                .id(null)
                                .build()
                )
        );
    }

    /**
     * @Description TODO: 修改之前确保对象信息存在，不存在则会先通过切面切出Http状态码404
     * @Param:  [id, medicine]
     * @Return:  org.springframework.http.ResponseEntity<com.kn.medicine.domain.Medicine>
     * @Date:
     * @Author: Kn
     */
    public ResponseEntity<Medicine> updateMedicineById(String id, Medicine medicine) {
        return ResponseEntity.ok(
                this.medicineRepository.save(
                        this.medicineRepository
                                .findById(id)
                                .get()
                                .toBuilder()
                                .medicineCode(medicine.getMedicineCode())
                                .medicineName(medicine.getMedicineName())
                                .build()
                )
        );
    }

    public ResponseEntity<Medicine> deleteMedicineById(String id) {
        this.medicineRepository.delete(
                this.medicineRepository.findById(id).get()
        );
        return ResponseEntity.ok(null);
    }

//    public ResponseEntity<Medicine> findByMedicineCode(String medicineCode) {
//        return ResponseEntity.of(this.medicineRepository.findByMedicineCode(medicineCode));
//    }
}
