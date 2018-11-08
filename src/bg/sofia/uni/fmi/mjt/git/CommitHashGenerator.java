package bg.sofia.uni.fmi.mjt.git;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public class CommitHashGenerator {
    
    public CommitHashGenerator(){
        
    }
    
    private String hexDigest(String input) throws GeneralSecurityException {
        MessageDigest  digest = MessageDigest.getInstance("SHA-1");
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

    public String generateHash(String message) {
        String hex = null;
        try {
            return hex = hexDigest(message); 
        } catch (GeneralSecurityException gse) {
            gse.getCause();
        }
         return hex; 
    }
}
