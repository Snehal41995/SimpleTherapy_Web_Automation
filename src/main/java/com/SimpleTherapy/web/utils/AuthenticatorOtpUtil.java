package com.simpleTherapy.web.utils;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

public class AuthenticatorOtpUtil {

    public static String generateOtp(String base32Secret) {
        try {
            long timeStep = 30;
            long counter = Instant.now().getEpochSecond() / timeStep;

            byte[] key = new Base32().decode(base32Secret);
            byte[] data = ByteBuffer.allocate(8).putLong(counter).array();

            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec(key, "HmacSHA1"));

            byte[] hmac = mac.doFinal(data);

            int offset = hmac[hmac.length - 1] & 0xF;
            int binary =
                    ((hmac[offset] & 0x7f) << 24) |
                            ((hmac[offset + 1] & 0xff) << 16) |
                            ((hmac[offset + 2] & 0xff) << 8) |
                            (hmac[offset + 3] & 0xff);

            int otp = binary % 1_000_000;
            return String.format("%06d", otp);

        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException("OTP generation failed", e);
        }
    }
}
