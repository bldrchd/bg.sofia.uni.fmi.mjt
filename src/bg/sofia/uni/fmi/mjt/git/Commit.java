package bg.sofia.uni.fmi.mjt.git;

import java.time.LocalDate;

public class Commit {
    
    private Branch branch;
    private String hashValue;
    private LocalDate date;
    private String commitMsg;
    
    public Commit() {
        
    }
    public Commit(Branch branch) {
        this.branch = branch;
    }
    
    public Commit(Branch branch, String hash, LocalDate date, String commitMsg){
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getMessage() {
        return commitMsg;
    }
    public void setCommitMsg(String commitMsg) {
        this.commitMsg = commitMsg;
    }
    
    public String toString() {
        return "commit "+ hashValue + "\nDate: "+ date + "\n\n\t"+commitMsg;  
    }
}
