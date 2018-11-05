package bg.sofia.uni.fmi.mjt.git;

public class Result {
    private String message;
    private boolean successful = false;
    private boolean duplication = false;
    
    public Result(){
        
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
    public void log(){
        //return currentBranch 's history
        
    }
    public void setDuplicationStatus(boolean duplication){
        this.duplication = duplication;
    }
    public boolean getDuplicationStatus() {
        return this.duplication;
    }

}
