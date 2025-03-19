package com.xiaoming.spring.security;
import com.xiaoming.spring.util.SpringContextUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.beans.factory.InitializingBean;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
@Converter(autoApply = true)
public class MedicalDataEncryptor implements AttributeConverter<String, String>, InitializingBean {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;
    private SecretKeySpec key;
    private Environment env;
    @Override
    public void afterPropertiesSet() {
        // Spring属性注入完成后初始化
        initializeKey();
    }
    private void initializeKey() {
        try {
            // 直接从注入的Environment获取配置
            String base64Key = env.getProperty("medical.encrypt.key");

            if (base64Key == null) {
                throw new IllegalArgumentException("未配置加密密钥，请检查application.properties");
            }

            byte[] keyBytes = Base64.getDecoder().decode(base64Key);
            validateKeyLength(keyBytes);
            this.key = new SecretKeySpec(keyBytes, "AES");
        } catch (IllegalArgumentException e) {
            throw new SecurityException("安全配置错误", e);
        }
    }

    private void validateKeyLength(byte[] keyBytes) {
        if (keyBytes.length != 32) {
            throw new IllegalArgumentException("密钥长度必须为32字节（当前："
                    + keyBytes.length + "字节）\n生成方式：openssl rand -base64 32");
        }
    }

    // 自动注入Environment
    public MedicalDataEncryptor(Environment env) {
        this.env = env;
    }

    @Override
    public String convertToDatabaseColumn(String plaintext) {
        try {
            byte[] iv = new byte[IV_LENGTH];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH, iv));

            byte[] cipherText = cipher.doFinal(plaintext.getBytes());
            ByteBuffer buffer = ByteBuffer.allocate(iv.length + cipherText.length);
            buffer.put(iv);
            buffer.put(cipherText);

            return Base64.getEncoder().encodeToString(buffer.array());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("加密失败", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String ciphertext) {
        try {
            byte[] decoded = Base64.getDecoder().decode(ciphertext);
            ByteBuffer buffer = ByteBuffer.wrap(decoded);

            byte[] iv = new byte[IV_LENGTH];
            buffer.get(iv);

            byte[] cipherBytes = new byte[buffer.remaining()];
            buffer.get(cipherBytes);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH, iv));

            return new String(cipher.doFinal(cipherBytes));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("解密失败", e);
        }
    }
}
