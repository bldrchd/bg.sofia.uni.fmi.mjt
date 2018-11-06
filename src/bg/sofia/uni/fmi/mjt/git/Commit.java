package bg.sofia.uni.fmi.mjt.git;

import java.time.LocalDateTime;

public class Commit {
    
    private Branch branch;
    private String hashValue;
    private LocalDateTime date;
    private String commitMsg;
    
    public Commit() {
        
    }
    public Commit(Branch branch) {
        this.branch = branch;
    }
    
    public Commit(Branch branch, String hash, LocalDateTime date, String commitMsg){
        this.branch = branch;
        this.hashValue = hash;
        this.date = date;
        this.commitMsg = commitMsg;
        
    }
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public String getHashValue() {
        return hashValue;
    }
    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public String getMessage() {
        return commitMsg;
    }
    public void setCommitMsg(String commitMsg) {
        this.commitMsg = commitMsg;
    }
}
