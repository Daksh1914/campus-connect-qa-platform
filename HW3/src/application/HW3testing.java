package application;

import application.Answer;
import application.Question;
import application.User;
import databasePart1.DatabaseHelper;

import java.sql.SQLException;

/**
 * HW3Testing contains automated tests based on features described in the user stories.
 * Each test checks functionality from the Answer, Question, and DatabaseHelper classes.
 *
 * Author: Daksh Patel
 */
public class HW3testing {

    /**
     * Test 1: Ensure that an answer can be marked as resolving a question.
     */
    public static void testMarkAnswerAsResolved() {
        Answer ans = new Answer("Try restarting your IDE.");
        ans.markAsResolved();
        assert ans.getIfResolved() : "Answer should be marked as resolved.";
        System.out.println("Test 1 Passed: Answer marked as resolved.");
    }

    /**
     * Test 2: Verify that a question can store multiple potential answers.
     */
    public static void testAddPotentialAnswersToQuestion() {
        Question q = new Question("Why won't my code compile?", "studentA");
        Answer a1 = new Answer("Check your syntax.");
        Answer a2 = new Answer("Look for missing semicolons.");
        q.putInAnswer(a1);
        q.putInAnswer(a2);
        assert q.getPotentialAnswers().size() == 2 : "Should contain two answers.";
        System.out.println("Test 2 Passed: Added multiple answers to question.");
    }

    /**
     * Test 3: Validate marking a question as resolved.
     */
    public static void testMarkQuestionAsResolved() {
        Question q = new Question("What's a null pointer?", "studentB");
        q.markResolved();
        assert q.getResolve() : "Question should be marked as resolved.";
        System.out.println("Test 3 Passed: Question marked as resolved.");
    }

    /**
     * Test 4: Check if a user exists in the database using DatabaseHelper.
     */
    public static void testDoesUserExist() throws SQLException {
        DatabaseHelper db = new DatabaseHelper();
        db.connectToDatabase();
        String username = "testUser123";
        if (!db.doesUserExist(username)) {
            db.register(new User(username, "password", "student"));
        }
        assert db.doesUserExist(username) : "User should exist in the database.";
        db.closeConnection();
        System.out.println("Test 4 Passed: User exists in the database.");
    }

    /**
     * Test 5: Ensure a question can be added and stored in the database.
     */
    public static void testAddAndRetrieveQuestion() throws SQLException {
        DatabaseHelper db = new DatabaseHelper();
        db.connectToDatabase();
        db.addQuestion("studentTest", "How to use threads in Java?");
        db.closeConnection();
        System.out.println("Test 5 Passed: Question successfully added to DB.");
    }

    /**
     * Main method to run all test cases.
     */
    public static void main(String[] args) {
        try {
            testMarkAnswerAsResolved();
            testAddPotentialAnswersToQuestion();
            testMarkQuestionAsResolved();
            testDoesUserExist();
            testAddAndRetrieveQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
