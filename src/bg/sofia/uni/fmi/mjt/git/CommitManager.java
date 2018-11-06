package bg.sofia.uni.fmi.mjt.git;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public class CommitManager {
    
    public CommitManager(){
        
    }
    public String hexDigest(String input) throws GeneralSecurityException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] bytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return convertBytesToHex(bytes);
    }

    private String convertBytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte current : bytes) {
            hex.append(String.format("%02x", current));
        }

        return hex.toString();
    }

    private String generateHash(String message) throws GeneralSecurityException{
           return hexDigest(message);
    }
}
