package com.kn.medicine.controller;

import com.kn.medicine.domain.Medicine;
import com.kn.medicine.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: MedicineController
 * @Description TODO:
 * @Date: 2019/12/12 10:24
 * @Author: Kn
 */
@RequestMapping("/medicine")
@RestController
public class MedicineController {
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/")
    public List<Medicine> getAllMedicines() {
        return this.medicineService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> findMedicineById(@PathVariable("id") String id) {
        return this.medicineService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        return this.medicineService.createMedicine(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable("id") String id, @RequestBody Medicine medicine) {
        return this.medicineService.updateMedicineById(id, medicine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medicine> deleteMedicine(@PathVariable("id") String id) {
        return this.medicineService.deleteMedicineById(id);
    }

//    @GetMapping("/{medicineCode}")
//    public ResponseEntity<Medicine> findMedicineByCode(@PathVariable("medicineCode") String medicineCode) {
//        return this.medicineService.findByMedicineCode(medicineCode);
//    }
}
