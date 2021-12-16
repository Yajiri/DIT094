package ScoreBoard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScoreManager {

    public void initiateScoreBoard() {

        JSONObject score1 = new JSONObject();
        JSONObject score2 = new JSONObject();
        JSONObject score3 = new JSONObject();

        score1.put("score", 20);
        score2.put("score", 75);
        score3.put("score", 40);

        JSONArray scoreBoard = new JSONArray();
        scoreBoard.add(score1);
        scoreBoard.add(score2);
        scoreBoard.add(score3);

        try (FileWriter file = new FileWriter("scores.Json")){

            file.write(scoreBoard.toJSONString());
            file.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Long> readScores(){

        JSONParser jsonParser = new JSONParser();
        ArrayList<Long> scores = new ArrayList<>();

        try (FileReader fileReader = new FileReader("scores.Json")){
            Object object = jsonParser.parse(fileReader);
            JSONArray scoreBoard = (JSONArray) object;

            for (int i = 0; i < scoreBoard.size(); i++){
                JSONObject obj = (JSONObject) scoreBoard.get(i);
                Long score = (long) obj.get("score");
                scores.add(score);
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return scores;
    }

    public void addScore(int score){
        JSONParser jsonParser = new JSONParser();
        JSONObject newScore = new JSONObject();
        newScore.put("score", score);

        try (FileReader fileReader = new FileReader("scores.Json")) {
            Object object = jsonParser.parse(fileReader);
            JSONArray scoreBoard = (JSONArray) object;
            scoreBoard.add(newScore);

            FileWriter file = new FileWriter("scores.Json");
            file.write(scoreBoard.toJSONString());
            file.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
