package com.nihon.shiken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nihon.shiken.model.mKanji;
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
                k.setKanji(values[0]);
                k.setHiragana(values[1]);
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
//        getKanji();
        Random random = new Random();
        int rand = random.nextInt(kanjiList.size());

        mKanji toAnswer = kanjiList.get(rand);

        ObjectMapper objMapper = new ObjectMapper();
        String json = null;
        try {
            json = objMapper.writeValueAsString(toAnswer);
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
}
