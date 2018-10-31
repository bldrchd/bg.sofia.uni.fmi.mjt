package bg.sofia.uni.fmi.mjt.git;

import java.util.LinkedHashMap;

public class Repository {
    
    private Branch branch = null;
    private int filesChanged = 0;
    private Result result;
    private LinkedHashMap<Integer, String[]> storage = null;
    private int currentPosition = 0;
    
    public Repository() {
       storage = new LinkedHashMap<>();
       setBranch(new Branch());
       result = new Result();
    }
    
    public Result add(String... files) {
        storage.put(currentPosition, files);
        currentPosition++;
        result.setMessage("added "+ files);
    return result;
    }
    public Result remove(String... files) {
        if (storage.values().contains(files)) {
            storage.remove(storage.values().contains(files));
            result.setMessage("removed "+files);
        } else {
            result.setMessage(files + "did not match any files");
        }
        return result;
    }
    public Result commit(String string) {
        if (filesChanged == 0) {
            result.setMessage("nothing to commit, working tree clean");
        } else {
           result.setMessage(filesChanged + " files changed");
        }
        result.getMessage();
        return null; //how many files are changed;
    }
    


    public void createBranch(String branchName) {
        //the branch is pointer to a commit
        if (branch.isExisting()){
            result.setMessage("branch " + branchName + "already exists");
        } else {
            branch = new Branch(branchName);
        }
        
    }

    public Result checkoutBranch(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    /*public Result getHead() {
        // TODO Auto-generated method stub
        return null;
   } */
    public Commit getHead() {
        return null;
    }
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    
}
