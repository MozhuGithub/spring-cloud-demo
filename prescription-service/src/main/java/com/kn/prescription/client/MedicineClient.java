package com.kn.prescription.client;

import com.kn.prescription.model.Medicine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: MedicineClient
 * @Description TODO:
 * @Date: 2019/12/14 15:36
 * @Author: Kn
 */
@Component
@Slf4j
public class MedicineClient {

    @Autowired
    private RestTemplate restTemplate;

    public Medicine getMedicineFromRemote(String medicineId) {
        ResponseEntity<Medicine> responseEntity = this.restTemplate.exchange("http://medicine-service/medicine/{medicineId}",
                HttpMethod.GET, null, Medicine.class, medicineId);
        log.info("远程调用Medicine-Service返回的Http状态值:" + responseEntity.getStatusCodeValue());
        return responseEntity.getBody();
    }
}
