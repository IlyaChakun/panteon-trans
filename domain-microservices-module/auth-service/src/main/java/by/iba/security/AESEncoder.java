package by.iba.security;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Builder
public class AESEncoder implements PasswordHashEncoder {

    private final byte[] inputBuffer = "buffer".getBytes(StandardCharsets.UTF_8);

    @SneakyThrows
    @Override
    public String encode(CharSequence charSequence) {

        byte[] salt = {
                15, 0, 0, 0, 0, 16, 0, 9, 3, 0,
                0, 12, 11, 59, 8, 1};

        return Arrays.toString(encryptPassword(charSequence, salt));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    private byte[] encryptPassword(CharSequence charSequence, byte[] salt)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {


        KeySpec spec = new PBEKeySpec(charSequence.toString().toCharArray(), salt, 65536, 128);

        IvParameterSpec ivParamSpec = new IvParameterSpec(salt);
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 10000, ivParamSpec);

        SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");
        SecretKey secretKey = kf.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_128");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParamSpec);

        byte[] encrypted = cipher.doFinal(inputBuffer);
        String encryptedBase64 = new Base64Encoder().encode(encrypted);

        return encryptedBase64.getBytes(StandardCharsets.UTF_8);

    }

}
