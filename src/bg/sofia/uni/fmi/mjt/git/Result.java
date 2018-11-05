package bg.sofia.uni.fmi.mjt.git;

public class Result {
    private String message;
    private boolean duplication = false;
    public Result(){
        
    }
    public boolean isSuccessful() {
         if (duplication) {
             return false;  
         } else {
             return true;
         } 
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
