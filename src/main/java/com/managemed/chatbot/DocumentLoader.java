package com.managemed.chatbot;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DocumentLoader implements CommandLineRunner {
    @Autowired
    private VectorStore vectorStore;
    private List<Document> documents = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading proprietary documents...");
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("managemed-documents.json");
        try (InputStream inputStream = resource.getInputStream()) {
            JsonNode root = mapper.readTree(inputStream);
            JsonNode docsNode = root.get("documents");
            System.out.println("Found " + docsNode.size() + " documents in JSON file.");
            for (JsonNode node : docsNode) {
                String text = node.get("text").asText();
                Document document = new Document(
                        text
                // Map.of("id", id) // metadata
                );
                documents.add(document);
            }
            System.out.println("Loaded proprietary documents: "
                    + documents.size());
            vectorStore.add(documents);

        }
    }

}
