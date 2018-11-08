package bg.sofia.uni.fmi.mjt.git;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Repository {

    private static LinkedHashSet<String> repo; // to be used by all instances of
                                               // repository
    private static LinkedList<Commit> commitHistory;
    private static LinkedList<Branch> branches;
    private static TreeMap<String, Commit> repoTree;
    private Branch branch;
    private int filesChanged = 0;
    private Commit head;

    public Repository() {
        repo = new LinkedHashSet<>();
        commitHistory = new LinkedList<Commit>();
        repoTree = new TreeMap<>();
        branches = new LinkedList<>();
        branch = new Branch();
        this.head = null;
        branches.add(branch);
        repoTree.put(branch.getBranchName(), null); //when initializing repo, there are no commits in master
    }

    public Result add(String... files) {
        for (String file : files) {
            if (repo.contains(file)) {
                filesChanged = 0;
                return new Result(false, file + "already exists");
            }
        filesChanged++;
        }
        Collections.addAll(repo, files);
        return new Result(true, "added " + Arrays.toString(files).substring(1, Arrays.toString(files).length()-1)+ " to stage");
    }
    
    public Result remove(String... files) {

        for (String file : files) {
            if (!repo.contains(file)) {
                return new Result(false, "'"+ file +"'"+ " did not match any files");
            }
        }
        for (String file : files) {
            filesChanged++;
            repo.remove(file); 
        }
        return new Result(true, "added " + filesChanged + " for removal");
    }

    public Result commit(String string) { 
        if (string == null) {
            return new Result(false, "Enter commit message");
        }
        if (filesChanged > 0) { 
            Commit com = createCommit(string);
            commitHistory.add(com);
            repoTree.put(branch.getBranchName(), com);
            head = com;
            //commitChanges(string);
            String msg = filesChanged + " files changed";
            filesChanged = 0; // counter of changes is 0 after commit
            return new Result(true, msg);
        }
        return new Result(false, "nothing to commit, working tree clean");
    }

    private Commit createCommit(String commitMessage) {
        CommitHashGenerator cm = new CommitHashGenerator();
        String hashValue = cm.generateHash(commitMessage);
        
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E LLL dd HH:mm:ss u");
        String formatDate = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(formatDate, formatter);
        
        String commitMsg = commitMessage;
        Commit com = new Commit(branch, hashValue, parsedDate, commitMsg);
        return com;
        
    }
    
    public Commit getHead() { 
        return head;
    }

    public Result log() {
        return getHistory();
    }

    public Result getHistory() {
        for (Commit commit : commitHistory) {
            if (commit.getBranch().getBranchName().equals(branch.getBranchName())) {
                return new Result(true, commit.toString());
            }
        }
        return new Result(false, ("branch " + branch + "does not have any commits yet"));
    }

    public String getBranch() {
        return branch.getBranchName();
    }

    public Result createBranch(String branchName) { //TODO - check flow of branches and commits!!!
        if (branches.contains(branchName)){
            return new Result(false, branchName + " already exist");
        } 
        Branch b = new Branch(branchName);
       branches.add(b);
       
       List<Commit> lc = new ArrayList<Commit>();//= getCommitHistoryInBranch(branch); //get from current branch all existing commits
       for ( Commit com : repoTree.values()){
           if (com.getBranch().getBranchName().equals(branch.getBranchName())){
               lc.add(com);
           }
       }
       if (!lc.isEmpty()) 
           for (Commit com : lc) {
               repoTree.put(branchName, com);
       } else {
           repoTree.put(branchName, null);
       }
       head = repoTree.get(branchName);
      return new Result(true, "created branch " + branchName);
    }
    
    public Result checkoutBranch(String branchName) {
        for (Branch b : branches){
            if(b.getBranchName().equals(branchName)){
        //if (branches.contains()) {
           branch = b;
           head = repoTree.get(branch.getBranchName());
           return new Result(true, "switched to branch " + branchName);
            }
        }
        return new Result(false, "branch " + branchName + " does not exist");
    }

    public Result checkoutCommit(String hash) {
        if (commitHistory.contains(hash)) {
            for (Commit commit : commitHistory) {
                if (commit.getHashValue().equals(hash)) {
                    head = commit;
                    return new Result(true, "HEAD is now at " + hash);
                }
            }
        }
        return new Result(false, hash + " does not exist");
    }


}
