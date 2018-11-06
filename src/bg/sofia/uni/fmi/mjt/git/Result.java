package bg.sofia.uni.fmi.mjt.git;

public class Result {
    
    private String message;
    private boolean successful = false;
    
    public Result(){
        
    }
    public Result(boolean successful, String message){
        this.message = message;
        this.successful = successful;
    }
    
    public boolean isSuccessful() {
        return successful;
    }
    
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
    public String getMessage() {
        return message;
    } 
    
    public void setMessage(String _message){
        this.message = _message;
    }

}
