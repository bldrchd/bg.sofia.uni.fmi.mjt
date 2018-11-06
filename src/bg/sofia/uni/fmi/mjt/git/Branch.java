package bg.sofia.uni.fmi.mjt.git;

public class Branch {
    
    private String branchName;
    public static final String DEFAULT_BRANCH = "master";
    
    public Branch(){
        setBranchName(DEFAULT_BRANCH);
    }
    
    public Branch(String newBranch) {
        setBranchName(newBranch);
    }
    
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
}
