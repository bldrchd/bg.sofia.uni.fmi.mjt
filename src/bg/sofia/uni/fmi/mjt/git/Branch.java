package bg.sofia.uni.fmi.mjt.git;

public class Branch {
    private String branchName;
    
    public static String defaultBranch = "master";
    
    public Branch(){
        setBranchName(defaultBranch);
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
    public void checkoutBranch(){
        //TODO
    }
    public boolean isExisting() {
        // TODO Auto-generated method stub
        return false;
    }
}
