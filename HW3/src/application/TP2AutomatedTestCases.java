package application;
import java.util.ArrayList;
import java.util.List;

public class TP2AutomatedTestCases{
	
	private static int numPassed;
	private static int numFailed;
	private static  List<Thread> threads = new ArrayList<>();

	public static void main(String[] args) {
        /************** Test case **************/
		performSubmitThread(1, 1, "Is the test case working properly", "Alonzo", true);  // Valid thread creation
		performSubmitReply(2, 101, 1, "Seems fine to me!", "Raymond", true);
		// Test case: Attempt to edit a thread by the author
		performUpdateThread(3, 1, "Alonzo", "This is a thread by Alonzo", "Alonzo", "Updated thread content by Alonzo", true);
        
        // Test case: Attempt to edit a thread by a non-author user
        performUpdateThread(4, 2, "Raymond", "This is a thread by Rayond", "Alonzo", "Attempted update by Alonzo", true);
        performUpdateThreadWithInvalidData(5, 1, "Alonzo", "Original Thread Title", "Alonzo", "", true);  // Empty title update
    }
	
	 private static void performSubmitThread(int testCase, int threadId, String title, String author, boolean expectedPass) {
	        /************** Display an individual test case header **************/
	        System.out.println("Test case: " + testCase);
	        System.out.println("Thread ID: " + threadId + ", Title: \"" + title + "\", Author: \"" + author + "\"");
	        System.out.println("______________");

	        /************** Create the thread **************/
	        Threads newThread = null;
	        boolean isTestPassed = false;

	        try {
	            // Create the thread
	            newThread = new Threads(threadId, title, author);

	            // Check if the created thread has the expected values
	            if (newThread.getId() == threadId && newThread.getTitle().equals(title) && newThread.getAuthor().equals(author)) {
	                isTestPassed = true;  // If values match, the test is passed
	            } else {
	                isTestPassed = false;  // If values don't match, the test fails
	            }

	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }

	        /************** Interpret the result **************/
	        if (isTestPassed) {
	            System.out.println("***Success*** The thread was created correctly and values match.");
	            numPassed++;
	        } else {
	            System.out.println("***Failure*** The thread creation failed or values do not match.");
	            numFailed++;
	        }

	        // Display the result summary
	        System.out.println("Number of tests passed: " + numPassed);
	        System.out.println("Number of tests failed: " + numFailed);
	        System.out.println("=====================================");
	    }
	 private static void performSubmitReply(int testCase, int replyId, int threadId, String text, String author, boolean expectedPass) {
	        /************** Display an individual test case header **************/
	        System.out.println("Test case: " + testCase);
	        System.out.println("Reply ID: " + replyId + ", Thread ID: " + threadId + ", Author: \"" + author + "\", Text: \"" + text + "\"");
	        System.out.println("______________");

	        /************** Create the reply **************/
	        Replies newReply = null;
	        boolean isTestPassed = false;

	        try {
	            // Create the reply
	            newReply = new Replies(replyId, threadId, text, author);

	            // Check if the created reply has the expected values
	            if (newReply.getId() == replyId && newReply.getThreadId() == threadId 
	                && newReply.getAuthor().equals(author) && newReply.getText().equals(text)) {
	                isTestPassed = true;  // If values match, the test is passed
	            } else {
	                isTestPassed = false;  // If values don't match, the test fails
	            }

	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }

	        /************** Interpret the result **************/
	        if (isTestPassed) {
	            System.out.println("***Success*** The reply was created correctly and values match.");
	            numPassed++;
	        } else {
	            System.out.println("***Failure*** The reply creation failed or values do not match.");
	            numFailed++;
	        }

	        // Display the result summary
	        System.out.println("Number of tests passed: " + numPassed);
	        System.out.println("Number of tests failed: " + numFailed);
	        System.out.println("=====================================");
	    }
	 private static void performUpdateThread(int testCase, int threadId, String author, String originalText, String editor, String newContent, boolean expectedPass) {
	        /************** Test case execution **************/
	        System.out.println("Test case: " + testCase);
	        System.out.println("Thread ID: " + threadId + ", Author: \"" + author + "\", Editor: \"" + editor + "\", Expected Pass: " + expectedPass);
	        System.out.println("______________");

	        // Create a thread instance for testing
	        Threads threadToEdit = new Threads(threadId, author, originalText);

	        // Attempt to edit the thread
	        boolean isTestPassed = false;
	        if (author.equals(editor)) {
	            // If the editor is the author, allow the edit
	            threadToEdit.setTitle(newContent);
	            System.out.println("Thread content successfully updated to: " + threadToEdit.getTitle());
	            isTestPassed = true; // The edit should succeed
	        } else {
	            // If the editor is not the author, prevent the edit
	            System.out.println("Error: " + editor + " cannot edit " + author + "'s thread.");
	            isTestPassed = false; // The edit should fail
	        }

	        // Evaluate the result
	        if (isTestPassed == expectedPass) {
	            System.out.println("***Success*** The thread edit attempt was handled as expected.");
	            numPassed++;
	        } else {
	            System.out.println("***Failure*** The thread edit attempt was not handled correctly.");
	            numFailed++;
	        }

	        // Display the results of this test case
	        System.out.println("Number of tests passed: " + numPassed);
	        System.out.println("Number of tests failed: " + numFailed);
	    }
	 private static void performUpdateThreadWithInvalidData(int testCase, int threadId, String author, String originalText, String editor, String newContent, boolean expectedPass) {
		    /************** Test case execution **************/
		    System.out.println("Test case: " + testCase);
		    System.out.println("Thread ID: " + threadId + ", Author: \"" + author + "\", Editor: \"" + editor + "\", Expected Pass: " + expectedPass);
		    System.out.println("______________");

		    // Create a thread instance for testing
		    Threads threadToEdit = new Threads(threadId, author, originalText);

		    // Attempt to update the thread with invalid data (empty title)
		    boolean isTestPassed = false;
		    if (newContent.isEmpty()) {
		        System.out.println("Error: Cannot update thread with an empty title.");
		        isTestPassed = false;  // The update should fail because the title is empty
		    } else {
		        // If the title is valid, proceed with updating the thread
		        threadToEdit.setTitle(newContent);
		        System.out.println("Thread content successfully updated to: " + threadToEdit.getTitle());
		        isTestPassed = true;  // The update should succeed
		    }

		    // Evaluate the result
		    if (isTestPassed == expectedPass) {
		        System.out.println("***Success*** The thread update attempt was handled as expected.");
		        numPassed++;
		    } else {
		        System.out.println("***Failure*** The thread update attempt was not handled correctly.");
		        numFailed++;
		    }

		    // Display the results of this test case
		    System.out.println("Number of tests passed: " + numPassed);
		    System.out.println("Number of tests failed: " + numFailed);
		}
	 

	 
	
	
}
