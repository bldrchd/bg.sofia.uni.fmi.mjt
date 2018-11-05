package bg.sofia.uni.fmi.mjt.git;

import java.util.LinkedHashSet;

public class Staging {
    
    private int filesChangedAdd = 0; 
    private int filesChangedRm = 0; 
    private int lastCommitChanges = 0;
    private String resultMessage = null;
    private Result result = null;
    private LinkedHashSet<String> storage = null;
    private boolean duplication = false;
    private String[] files;
    
    
    public Staging() {
        
    }
    public Staging(LinkedHashSet<String> storage, Result result, String... files) {
        this.storage = storage;
        this.result = result;
        this.files = files;
    } 
    
    public void stageAdd(){
/*        for (String file : files) {
            if (storage.contains(file)){
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
        }*/
    }
    
    public void stageRm(){
/*        for (String file : files) {
            if (storage.contains(file)) {
               duplication = true; //there is a match
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
            for (String file : files) { storage.remove(file); }
        } */
    }
    
    private String buildMessage(String action, String... files){
/*        String msg = null;
        switch(action) {
            case "add-t":
                msg = "added "+files+" to stage";
                break;
            case "rm-t":
                msg =  "added "+files+ " for removal"; 
                break;
            case "rm-f":
                msg = files+" did not match any files"; 
                break;
        }*/
        return null;
    }
    public int lastChanges(){
        return lastCommitChanges; 
    }
    public int countChangesAdd(){
        return filesChangedAdd;
    }
    public int countChangesRm(){
        return filesChangedRm;
    }
        
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }
    public LinkedHashSet<String> getStorage() {
        return storage;
    }
    public void setStorage(LinkedHashSet<String> storage) {
        this.storage = storage;
    }
}
