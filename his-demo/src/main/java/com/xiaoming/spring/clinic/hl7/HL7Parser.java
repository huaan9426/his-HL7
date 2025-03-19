package com.xiaoming.spring.clinic.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.TS;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.parser.PipeParser;
import com.xiaoming.spring.entity.MedicalEvent;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class HL7Parser {
    private final PipeParser parser = new PipeParser();

    public MedicalEvent parseADTMessage(String hl7Message) throws HL7Exception {
        // 声明变量用于存储解析结果
        String patientId = null;
        String messageType = null;
        LocalDateTime eventTime = null;

        Message message = parser.parse(hl7Message);
        if (message instanceof ADT_A01) {
            ADT_A01 adt = (ADT_A01) message;

            // 解析患者ID
            if (adt.getPID().getPid3_PatientIdentifierList().length > 0) {
                CX pid3 = adt.getPID().getPid3_PatientIdentifierList(0);
                patientId = pid3.getCx1_IDNumber().getValue();
            }

            // 解析消息类型
            if (adt.getMSH().getMsh9_MessageType().getMessageCode() != null) {
                messageType = adt.getMSH().getMsh9_MessageType().getMessageCode().getValue();
            }

            // 解析事件时间
            TS recordedTime = adt.getEVN().getEvn6_EventOccurred();
            if (recordedTime != null) {
                eventTime = parseHL7Date(recordedTime);
            }
        }
        // 构建返回对象
        return MedicalEvent.builder()
                .patientId(patientId)
                .eventType(messageType)
                .eventTime(eventTime)
                .build();
    }

    private LocalDateTime parseHL7Date(TS ts) throws HL7Exception {
        if (ts == null || ts.getTime() == null) {
            return null; // 或抛出特定异常
        }

        try {
            String timeStr = ts.getTime().getValue();
            return LocalDateTime.parse(
                    timeStr,
                    DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            );
        } catch (DateTimeParseException e) {
            throw new HL7Exception("Invalid timestamp format: " + e.getMessage());
        }
    }
}
