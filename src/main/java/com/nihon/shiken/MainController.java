package com.nihon.shiken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nihon.shiken.model.mKanji;
import com.nihon.shiken.model.mMultipleChoice;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

@Controller
@RequestMapping("/api")
public class MainController {
    public static List<mKanji> kanjiList = new ArrayList<>();

    @GetMapping("/index")
    public String Index(){

        return "asdsad";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping("/data")
    @ResponseBody
    public String data() {
//        return new MyResponse("John Doe", 30);  // Returns JSON response
        return "{\"message\":\"Hello from Spring Boot!\"}";
    }

    @GetMapping("/getkanji")
    @ResponseBody
    public String getKanji(String path) {
        Resource csvFile = new ClassPathResource(path);
        String line = "";
        String csvSplitBy = ","; // Delimiter (comma by default)


        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(csvSplitBy);

                mKanji k = new mKanji();
                k.setKanji(values[1]);
                k.setHiragana(values[0]);
                k.setMeaning(values[2]);
                k.setSentry(false);
                kanjiList.add(k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper objMapper = new ObjectMapper();
        String json = null;
        try {
            json = objMapper.writeValueAsString(kanjiList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    @GetMapping("/random")
    @ResponseBody
    public String random(){
        Random random = new Random();
        int rand = random.nextInt(kanjiList.size());

        mKanji toAnswer = kanjiList.get(rand);

        mMultipleChoice multipleChoice = new mMultipleChoice();
        List<String> mul = new ArrayList<>();

        multipleChoice.setHiragana(toAnswer.getHiragana());
        multipleChoice.setKanji(toAnswer.getKanji());
        multipleChoice.setMeaning(toAnswer.getMeaning());
        

        for(int i = 0; i < 4; i++)
        {
            if(i == 0){
                mul.add(toAnswer.getMeaning());
            }
            else{
                int ran = random.nextInt(kanjiList.size());
                mKanji m = kanjiList.get(ran);
                mul.add(m.getMeaning());
            }
        }

        Collections.shuffle(mul);
        multipleChoice.setSelection1(mul.get(0));
        multipleChoice.setSelection2(mul.get(1));
        multipleChoice.setSelection3(mul.get(2));
        multipleChoice.setSelection4(mul.get(3));

        ObjectMapper objMapper = new ObjectMapper();
        String json = null;
        try {
            json = objMapper.writeValueAsString(multipleChoice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return  json;
    }

    @GetMapping("/n5kanji")
    public String showN5Kanji(){
        String path = "source/kanjiN5.csv";
        getKanji(path);
        return "n5kanji";
    }
    @GetMapping("/n4kanji")
    public String showN4Kanji(){
        String path = "source/KanjiN4.csv";
        getKanji(path);
        return "n4kanji";
    }
    @GetMapping("/n4radical")
    public String showN4radial(){
        String path = "source/RadicalN4.csv";
        getKanji(path);
        return "n4radical";
    }
    @GetMapping("/n4vocab")
    public String showN4vocab(){
        String path = "source/VocabN4.csv";
        getKanji(path);

        return "n4vocab";
    }
}
