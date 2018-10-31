package bg.sofia.uni.fmi.mjt.git;

public class Result {
    private String message;
    
    public Result(){
        
    }
    public boolean isSuccessful() {
        return false;
    }
    
    public String getMessage() {
        if (isSuccessful()) {
            message = "added {files} to stage.";
        } else {
            message = "{file} already exists.";
        }
        return message;
    } 
    
    public void setMessage(String _message){
        this.message = _message;
    }
    public void log(){
        //return currentBranch 's history
    }

}
