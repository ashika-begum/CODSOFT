import java.util.*;

class Question {
    String questionText;
    String[] options;
    char correctAnswer;

    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApp {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static List<String> summary = new ArrayList<>();

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        
        
        questions.add(new Question("What is the capital of France?", new String[]{"A. Paris", "B. London", "C. Rome", "D. Berlin"}, 'A'));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"A. Earth", "B. Venus", "C. Mars", "D. Jupiter"}, 'C'));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"A. Charles Dickens", "B. William Shakespeare", "C. Mark Twain", "D. Jane Austen"}, 'B'));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"A. Atlantic", "B. Indian", "C. Arctic", "D. Pacific"}, 'D'));
        questions.add(new Question("Which language is primarily used for Android Development?", new String[]{"A. Python", "B. Swift", "C. Java", "D. C#"}, 'C'));

        System.out.println("Welcome to the Quiz!");
        System.out.println("You will have 15 seconds to answer each question.\n");

        for (int i = 0; i < questions.size(); i++) {
            askQuestion(questions.get(i), i + 1);
        }

        displayResults(questions);
    }

    public static void askQuestion(Question q, int qNumber) {
        System.out.println("Q" + qNumber + ": " + q.questionText);
        for (String option : q.options) {
            System.out.println(option);
        }

        long startTime = System.currentTimeMillis();
        String answer = "";
        boolean timedOut = false;

        while (true) {
            System.out.print("Your answer (A/B/C/D): ");
            answer = scanner.nextLine().toUpperCase();
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

            if (elapsedTime > 15) {
                System.out.println("Time's up!");
                timedOut = true;
                break;
            }

            if (answer.matches("[A-D]")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter A, B, C, or D.");
            }
        }

        if (timedOut) {
            summary.add("Q" + qNumber + ": ❌ (Timed Out)");
        } else if (answer.charAt(0) == q.correctAnswer) {
            score++;
            summary.add("Q" + qNumber + ": ✅");
        } else {
            summary.add("Q" + qNumber + ": ❌");
        }

        System.out.println();
    }

    public static void displayResults(List<Question> questions) {
        System.out.println("===== Quiz Completed =====");
        System.out.println("Your Score: " + score + "/" + questions.size());
        System.out.println("\nSummary:");
        for (String s : summary) {
            System.out.println(s);
        }
    }
}
