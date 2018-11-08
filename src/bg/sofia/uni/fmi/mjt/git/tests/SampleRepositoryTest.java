package bg.sofia.uni.fmi.mjt.git.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bg.sofia.uni.fmi.mjt.git.Commit;
import bg.sofia.uni.fmi.mjt.git.Repository;
import bg.sofia.uni.fmi.mjt.git.Result;

public class SampleRepositoryTest {

    private Repository repo;

    @Before
    public void setUp() {
        repo = new Repository();
    }
    @After
    public void cleanUp(){
        repo = null;
    }

    @Test
    public void testAdd_MultipleFiles() {
        Result actual = repo.add("foo.txt", "bar.txt", "baz.txt");
        assertSuccess("added foo.txt, bar.txt, baz.txt to stage", actual);
    }

    @Test
    public void testRemove_DoesNothingWhenAnyFileIsMissing() {
        repo.add("foo.txt", "bar.txt");

        Result actual = repo.remove("foo.txt", "baz.txt");
        assertFail("'baz.txt' did not match any files", actual);

        actual = repo.commit("After removal");
        assertSuccess("2 files changed", actual);
    }
    @Test
    public void testEmptyCommitMessage(){
        repo.add("myFile");
        Result actual = repo.commit(null);
        assertEquals("Enter commit message", actual.getMessage());
    }

    @Test
    public void testCheckoutBranch_CanSwitchBranches() {
        repo.add("src/Main.java");
        repo.commit("Add Main.java");

        repo.createBranch("dev");
        Result actual = repo.checkoutBranch("dev");
        assertSuccess("switched to branch dev", actual);

        repo.remove("src/Main.java");
        repo.commit("Remove Main.java");
        assertEquals("Remove Main.java", repo.getHead().getMessage());

        actual = repo.checkoutBranch("master");
        assertSuccess("switched to branch master", actual);
        System.out.println(repo.getHead().getMessage());
        assertEquals("Add Main.java", repo.getHead().getMessage());
    }
    @Test 
    public void testLog(){
        repo.add("asd.txt");
        repo.commit("Add asd");
        repo.createBranch("NB");
        repo.checkoutBranch("NB");
        Result res = repo.log();
        String actual = res.getMessage();
        System.out.println(actual);
        assertNotNull(actual);
    }
    @Test
    public void testHead(){
        repo.add("new");
        repo.commit("Adding new");
        repo.createBranch("myBranch");
        Commit actual = repo.getHead();
        System.out.println(actual.getHashValue() + " " + actual.getMessage());
        assertEquals("Adding new", actual.getMessage());
    }
    @Test
    public void testCurrentBranchReturn(){
        assertEquals("master", repo.getBranch());
    }

    private static void assertFail(String expected, Result actual) {
        assertFalse(actual.isSuccessful());
        assertEquals(expected, actual.getMessage());
    }

    private static void assertSuccess(String expected, Result actual) {
        assertTrue(actual.isSuccessful());
        assertEquals(expected, actual.getMessage());
    }
}