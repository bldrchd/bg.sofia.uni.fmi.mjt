package bg.sofia.uni.fmi.mjt.git;

import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Repository {

    private LinkedHashSet<String> staging;
    private LinkedHashSet<?> repo;
    private LinkedList<Commit> commitHistory;
    private TreeSet<String> branches;

    private Branch currentBranch;
    private Branch branch;
    private String resultMessage;
    private int filesChangedAdd; // TODO - change to filesChanged : both from
                                 // add and remove
    private Commit commit;

    public Repository() {
        staging = new LinkedHashSet<>();
        commitHistory = new LinkedList<>();
        branches = new TreeSet();
        repo = new LinkedHashSet<>();
        branch = new Branch();
        commit = new Commit(branch);
        currentBranch = branch;
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

    public Result commit(String string) throws GeneralSecurityException { //TODO - check construction of result if could be better
        if (string == null) {
            System.out.println("Enter commit message");
            return new Result(false, "Enter commit message");
        } else {
            if (filesChangedAdd == 0) { // TODO - last changes from commit?
                return new Result(false, "nothing to commit, working tree clean");
            } else {
                    commitChanges(staging, string);
                    return new Result(true, filesChangedAdd + " files changed");
            }
        }
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
        if (existing) {
            int filesChanged = files.length;
            // TODO - check working or change to foreach?
            resultMessage = "added " + filesChanged + " for removal.";
            result.setSuccessful(true);
            result.setMessage(resultMessage);
        }
        return result;
    }

    public Result log() {
        return getHistory(branch);
    }

    public Result getHistory(Branch branch) {
        for (Commit commit : commitHistory) {
            if (!commitHistory.contains(branch)) {
               return new Result(false, ("branch " + branch + "does not have any commits yet"));
            } else {
                if (this.branch == branch) {
                    // result.setMessage( commit.history(commit.currentBranch,
                    // commit.hashValue, commit.date, commit.commitMsg));
                }
            }
        }
        return result;
    }

    public String getBranch() {
        return branch.getBranchName();
    }

    public Result createBranch(String branchName) {
        if (branches.contains(branchName)) {
            return new Result(false, branchName + " already exist");
        } else {
            if (createNewBranch(branchName)){
                return new Result(true, "created branch " + branchName);
            } else {
                return new Result(false,"cannot create branch" + branchName);
            }
        }
    }

    public Result checkoutBranch(String string) {

        return null;
    }

    public Commit getHead() {
        // should be from current branch
        return commitHistory.getLast();
    }
    public String history(Branch branch, String hashValue, LocalDateTime date, String commitMessage) {
        return "commit " + hashValue + "\nDate: " + date + "\n\n\t" + message;
        
    }
    public void commitChanges(LinkedHashSet<String> staging, String commitMessage) throws GeneralSecurityException {
        try {
            hashValue = generateHash(commitMessage);
            date = LocalDateTime.now();
            commitMsg = commitMessage;
            commitHistory.add(new Commit(currentBranch, hashValue, date, commitMsg));
        } catch (GeneralSecurityException e) {
            throw new GeneralSecurityException(e.getMessage());
        }
}
    public String isExisting(String branchName) {
        String result;
        if (branches.contains(branchName)) {
            result = branchName + " already exist";
        } else {
            createNewBranch(branchName);
            result = "created branch " + branchName;
        }
        return result;
    }
    public boolean createNewBranch(String branchName) {
        branches.add(branchName);        
    }

}
