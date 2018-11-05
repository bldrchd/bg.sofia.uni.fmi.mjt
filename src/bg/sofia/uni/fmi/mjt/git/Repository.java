package bg.sofia.uni.fmi.mjt.git;

import java.util.LinkedHashSet;

public class Repository {

    private Branch branch = null;
    private Result result;
    private LinkedHashSet<String> storage = null;
    private Staging st;
    private int lastCommitChanges = 0;
    private boolean duplication;
    private String resultMessage = null;;
    private int filesChangedAdd = 0;
    private int filesChangedRm = 0;
    private Commit commit;

    public Repository() {
        storage = new LinkedHashSet<>();
        setBranch(new Branch());
        result = new Result();
        commit = new Commit();
    }

    public Result add(String... files) {

        // st = new Staging(storage, result, files);
        // st.stageAdd();
        for (String file : files) {
            if (storage.contains(file)) {
                resultMessage = (file + "already exists");
                duplication = true;
                break;
            } else {
                storage.add(file);
                filesChangedAdd++;
            }
        }
        result.setDuplicationStatus(duplication);
        if (duplication) {
            result.setMessage(resultMessage);
        } else {
            lastCommitChanges = filesChangedAdd;
            result.setMessage(buildMessage("add-t", files));
        }

        return result;
    }

    public Result remove(String... files) {
        // st = new Staging(storage, result, files);
        // st.stageRm();
        for (String file : files) {
            if (storage.contains(file)) {
                duplication = true; // there is a match
                filesChangedRm++;
            } else {
                duplication = false;
                buildMessage("rm-f", file);
                break;
            }
            result.setDuplicationStatus(duplication);
        }
        if (duplication) {
            result.setMessage(buildMessage("rm-t", files));
            lastCommitChanges = filesChangedRm;
            for (String file : files) {
                storage.remove(file);
            }
        }

        return result;
    }

    public Result commit(String string) {
        if (string == null) {
            System.out.println("Enter commit message");
            return null;
        } else {
            lastCommitChanges = st.lastChanges();
            if (result.isSuccessful() && lastCommitChanges != 0) {
                //TODO try to apply changes
                commit.setCurrentBranch(branch);
                commit.setMessage(string);
                result.setMessage(lastCommitChanges + " files changed");
            } else {
                result.setMessage("nothing to commit, working tree clean");
            }
        }
        return result;
    }

    public void createBranch(String branchName) {
        // the branch is pointer to a commit
        if (branch.isExisting()) {
            result.setMessage("branch " + branchName + "already exists");
        } else {
            branch = new Branch(branchName);
        }

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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

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
