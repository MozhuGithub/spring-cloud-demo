package com.kn.prescription.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Medicine
 * @Description TODO: 服务对象-药品
 * @Date: 2019/12/12 10:12
 * @Author: Kn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Medicine implements Serializable {
    private static final long serialVersionUID = 7493513293390659801L;
    private String id;

    private String medicineCode;

    private String medicineName;
}
