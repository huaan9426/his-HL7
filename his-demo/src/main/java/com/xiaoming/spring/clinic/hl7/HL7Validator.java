package com.xiaoming.spring.clinic.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.validation.ValidationException;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;

public class HL7Validator {
    public void validateADT(ADT_A01 message) throws ValidationException {
        try {
            // 显式处理可能抛出HL7Exception的代码段
            if (message.getMSH().getSendingApplication().isEmpty()) {
                throw new ValidationException("Missing sending application");
            }

            // 其他需要校验的字段
            // 示例：校验患者ID是否存在
            if (message.getPID().getPatientID().isEmpty()) {
                throw new ValidationException("Missing patient ID");
            }

        } catch (HL7Exception e) {
            // 将底层HL7异常转换为业务校验异常
            throw new ValidationException("HL7协议解析失败: " + e.getMessage(), e);
        }
    }
}
