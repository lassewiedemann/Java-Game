import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

public class ScoreManager {

    private static final String SCORE_FILE = "score.txt";
    private static final String KEY_URL = "http://45.81.235.110/key.txt";

    public static int getScore() {
        try {
            String key = fetchKeyFromServer(KEY_URL);
            return readScoreFromFile(SCORE_FILE, key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void saveScore(int newScore) {
        try {
            String key = fetchKeyFromServer(KEY_URL);
            int currentScore = readScoreFromFile(SCORE_FILE, key);

            if (newScore > currentScore) {
                writeScoreToFile(SCORE_FILE, newScore, key);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String fetchKeyFromServer(String keyUrl) throws IOException {
        URL url = new URL(keyUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            return scanner.useDelimiter("\\A").next();
        }
    }

    private static int readScoreFromFile(String fileName, String key) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String encodedScore = reader.readLine();
            if (encodedScore == null || encodedScore.isEmpty()) {
                return 0;
            }
            String decodedScore = new String(Base64.getDecoder().decode(encodedScore));
            return Integer.parseInt(xorWithKey(decodedScore, key));
        }
    }

    private static void writeScoreToFile(String fileName, int score, String key) throws IOException {
        String scoreString = String.valueOf(score);
        String encodedScore = Base64.getEncoder().encodeToString(xorWithKey(scoreString, key).getBytes());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(encodedScore);
        }
    }

    private static String xorWithKey(String input, String key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }
        return output.toString();
    }
}
