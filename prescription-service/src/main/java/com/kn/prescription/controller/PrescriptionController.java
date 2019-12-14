package com.kn.prescription.controller;

import com.kn.prescription.domain.Prescription;
import com.kn.prescription.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: PrescriptionController
 * @Description TODO:
 * @Date: 2019/12/12 10:24
 * @Author: Kn
 */
@RequestMapping("/prescription")
@RestController
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/")
    public List<Prescription> getAllUsers() {
        return this.prescriptionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> findById(@PathVariable("id") String id) {
        return this.prescriptionService.findById(id);
    }

    @PostMapping("/{userId}/{medicineId}")
    public ResponseEntity<Prescription> createPrescription(@PathVariable("userId") String userId,
                                                           @PathVariable("medicineId") String medicineId,
                                                           @RequestBody Prescription prescription) {

        return this.prescriptionService.createPrescription(prescription,userId,medicineId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescriptionById(@PathVariable("id") String id, @RequestBody Prescription prescription) {
        return this.prescriptionService.updatePrescriptionById(id, prescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Prescription> deletePrescriptionById(@PathVariable("id") String id) {
        return this.prescriptionService.deletePrescriptionById(id);
    }

}
