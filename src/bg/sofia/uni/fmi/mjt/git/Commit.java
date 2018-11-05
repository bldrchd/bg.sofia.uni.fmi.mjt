package bg.sofia.uni.fmi.mjt.git;

public class Commit {
    private String commitMsg = null;
    private Branch currentBranch = null;
    
    public Commit() {
        
    }
    
    public String getHash() {
    //the hash value of the commitment
        return null;
    }
    public String getMessage() {
        //the commit msg of the created commit
        return this.commitMsg;
    }
    public void setMessage(String msg){
        this.commitMsg = msg;
    }

    public Branch getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(Branch currentBranch) {
        this.currentBranch = currentBranch;
    }
}
