package com.kn.prescription.service;

import com.kn.prescription.client.MedicineClient;
import com.kn.prescription.client.UserClient;
import com.kn.prescription.domain.Prescription;
import com.kn.prescription.model.Medicine;
import com.kn.prescription.model.User;
import com.kn.prescription.repository.prescription.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PrescriptionService
 * @Description TODO:
 * @Date: 2019/12/12 10:21
 * @Author: Kn
 */
@Service
public class PrescriptionService {

    @Autowired
    private MedicineClient medicineClient;

    @Autowired
    private UserClient userClient;

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> findAll() {
        return this.prescriptionRepository.findAll();
    }

    public ResponseEntity<Prescription> findById(String id) {
        return ResponseEntity.of(this.prescriptionRepository.findById(id));
    }

    /**
     * @Description TODO: 新增的时候用户和药品的信息来自远程调用和缓存
     * @Param:  [prescription, userId, medicineId]
     * @Return:  org.springframework.http.ResponseEntity<com.kn.prescription.domain.Prescription>
     * @Date:
     * @Author: Kn
     */
    public ResponseEntity<Prescription> createPrescription(Prescription prescription, String userId, String medicineId) {
        Medicine medicine = medicineClient.getMedicineFromRemote(medicineId);
        User user = userClient.getUserFromCache(userId);
        return ResponseEntity.ok(
                this.prescriptionRepository.save(
                        prescription.toBuilder()
                                .id(null)
                                .medicineId(medicine.getId())
                                .medicineCode(medicine.getMedicineCode())
                                .medicineName(medicine.getMedicineName())
                                .userId(user.getId())
                                .userCode(user.getUserCode())
                                .userName(user.getUserName())
                                .build()
                )
        );
    }

    /**
     * @Description TODO:  修改之前确保对象信息存在，不存在则会先通过切面切出Http状态码404
     * @Param:  [id, prescription]
     * @Return:  org.springframework.http.ResponseEntity<com.kn.prescription.domain.Prescription>
     * @Date:
     * @Author: Kn
     */
    public ResponseEntity<Prescription> updatePrescriptionById(String id, Prescription prescription) {
        return ResponseEntity.ok(
                this.prescriptionRepository.save(
                        this.prescriptionRepository.findById(id)
                                .get()
                                .toBuilder()
                                .prescriptionCode(prescription.getPrescriptionCode())
                                .prescriptionName(prescription.getPrescriptionCode())
                                .build()
                )
        );
    }

    public ResponseEntity<Prescription> deletePrescriptionById(String id) {
        this.prescriptionRepository.delete(
                this.prescriptionRepository.findById(id).get()
        );
        return ResponseEntity.ok(null);
    }

}
