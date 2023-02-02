package com.example.CSC340.Assignment2.Schnell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Sam Schnell
 */
@RestController
public class ApiController {

    /**
     * Get title, author, and line count from poetryDB.
     *
     * @return The poem json response
     */
    @GetMapping("/poem")
    public Object poetryFacts() {
        try {
            String poetryURL = "https://poetrydb.org/random/1/author,title,linecount";
            RestTemplate restTemp = new RestTemplate();
            ObjectMapper objMapper = new ObjectMapper();

            String jsonPoetry = restTemp.getForObject(poetryURL, String.class);
            JsonNode root = objMapper.readTree(jsonPoetry);

            // Print response to console
            System.out.println("Here!");
            System.out.println(root);
            System.out.println("!!!!!");

            //Parseing out info from the response.
            for (JsonNode object : root) {
                String poemTitle = object.get("title").asText();
                String poemAuthor = object.get("author").asText();
                //String poemLines = root.get("lines").asText();
                Integer lineCount = object.get("linecount").asInt();

                System.out.println("Title: " + poemTitle);
                System.out.println("Author: " + poemAuthor);
                //  System.out.println("Lines: " + poemLines);
                System.out.println("Line Count: " + lineCount);
            }

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /poem";
        }
    }
}
