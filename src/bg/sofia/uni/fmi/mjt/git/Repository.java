package bg.sofia.uni.fmi.mjt.git;

import java.util.Collections;
import java.util.LinkedHashSet;

public class Repository {

    private LinkedHashSet<String> staging;
    private Branch branch;
    private String resultMessage;
    private int filesChangedAdd; //TODO - change to filesChanged : both from add and remove

    public Repository() {
        staging = new LinkedHashSet<>();
        branch = new Branch();
    }

    public Result add(String... files) {

        filesChangedAdd = 0;
        boolean duplication = false;
        Result result = new Result();

        for (String file : files) {
            if (staging.contains(file)) {
                resultMessage = (file + "already exists");
                duplication = true;
                break;
            }
        }
        if (!duplication) {
            Collections.addAll(staging, files); // TODO - check if working; have
                                                // to add all files to staging
            resultMessage = buildMessage("add-t", files);
            filesChangedAdd++;
        }
        result.setMessage(resultMessage);
        return result;
    }

    public Result commit(String string) {
        Result result = new Result();
        if (string == null) {
                    System.out.println("Enter commit message");
                    return null;
                } else {
                    if (filesChangedAdd == 0){ //TODO - last changes from commit?
                        resultMessage = "nothing to commit, working tree clean";
                        result.setMessage(resultMessage);
                        result.setSuccessful(false);
                    } else {
                        resultMessage = filesChangedAdd + " files changed";
                        result.setMessage(resultMessage);
                        result.setSuccessful(true);
                    }
                }
        return result;
      }

    public Result remove(String... files) {
        boolean existing = true;
        Result result = new Result();
        for (String file : files) {
            if (staging.contains(file)) {
                continue;
            } else {
                existing = false;
                resultMessage = file + " did not match any files";
                result.setMessage(resultMessage);
                result.setSuccessful(false);
            }
        }
        if (existing){
            int filesChanged = files.length;
            //TODO - check working or change to foreach?
            resultMessage = "added " + filesChanged + " for removal.";
            result.setSuccessful(true);
            result.setMessage(resultMessage);
        }
        return result;
    }

    public void createBranch(String branchName) {
        /*
         * // the branch is pointer to a commit if (branch.isExisting()) {
         * result.setMessage("branch " + branchName + "already exists"); } else
         * { branch = new Branch(branchName); }
         */
    }

    public Result checkoutBranch(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * public Result getHead() { // TODO Auto-generated method stub return null;
     * }
     */
    public Commit getHead() {
        return null;
    }

    /*
     * public Branch getBranch() { return branch; }
     */

    /*
     * public void setBranch(Branch branch) { this.branch = branch; }
     */

    private String buildMessage(String action, String... files) {
        String msg = null;
        switch (action) {
        case "add-t":
            msg = "added " + files + " to stage";
            break;
        case "rm-t":
            msg = "added " + files + " for removal";
            break;
        case "rm-f":
            msg = files + " did not match any files";
            break;
        }
        return msg;

    }
}
