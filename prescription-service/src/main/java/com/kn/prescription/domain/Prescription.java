package com.kn.prescription.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @ClassName: Prescription
 * @Description TODO: 领域对象-处方
 * @Date: 2019/12/12 16:16
 * @Author: Kn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Prescription implements Serializable {
    private static final long serialVersionUID = -7318381065287572447L;
    @Id
    @Column(length = 32, nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String prescriptionCode;

    private String prescriptionName;

    private String userId;

    private String userName;

    private String userCode;

    private String medicineId;

    private String medicineName;

    private String medicineCode;

}
