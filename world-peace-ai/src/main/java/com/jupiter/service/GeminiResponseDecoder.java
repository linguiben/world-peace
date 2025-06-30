package com.jupiter.service;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/30
 */

import com.google.cloud.vertexai.api.Candidate;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.Part;

import java.util.List;

public class GeminiResponseDecoder {

    public static String decode(GenerateContentResponse response) {
        // **Simulate the GenerateContentResponse (Replace with your actual response)**
        // You will receive the 'response' object from your vertexAI call.
//        GenerateContentResponse response = createSimulatedResponse();  // Replace this

        StringBuilder decodedText = new StringBuilder();
        // Check if the response is valid and contains candidates
        if (response != null && !response.getCandidatesList().isEmpty()) {
            // Iterate through the candidates in the response
            for (Candidate candidate : response.getCandidatesList()) {
                Content content = candidate.getContent();

                // Check if the content is not null and has parts
                if (content != null) {
                    List<Part> parts = content.getPartsList();

                    // Iterate over parts and extract the text
                    for (Part part : parts) {
                        String text = part.getText();

                        // Decode and print the text
                        if (text != null) {
                            System.out.println("Decoded Text: " + text);
                            decodedText.append(text);
                        } else {
                            System.out.println("Part contains no text.");
                        }
                    }
                } else {
                    System.out.println("Candidate content is null.");
                }
            }
        } else {
            System.out.println("No candidates found in the response or response is null.");
        }

        // Print metadata information

        if (response != null && response.hasUsageMetadata()) {
            System.out.println("Prompt Token Count: " + response.getUsageMetadata().getPromptTokenCount());
            System.out.println("Candidates Token Count: " + response.getUsageMetadata().getCandidatesTokenCount());
            System.out.println("Total Token Count: " + response.getUsageMetadata().getTotalTokenCount());
        }
        if (response != null) {
            System.out.println("Model Version: " + response.getModelVersion());
            System.out.println("Response ID: " + response.getResponseId());
            System.out.println("Create Time seconds: " + response.getCreateTime().getSeconds());
            System.out.println("Create Time nanos: " + response.getCreateTime().getNanos());
        }
        return decodedText.toString();
    }

    // **Important:**  Replace this with your actual GenerateContentResponse from Vertex AI.
    private static GenerateContentResponse createSimulatedResponse() {
        // This is only for demonstration purposes.  You'll get
        // the actual 'response' object from 'model.generateContent()'.
        // You'll need to use the Protobuf builders to manually create
        // the GenerateContentResponse object if you don't have a real response.

        // **This is an incomplete example** and needs to be adapted to match the structure
        // of your actual GenerateContentResponse object.  You'll likely have to use
        // the Builders for each nested object (Candidate, Content, Part, etc.).
        // See the Google Cloud AI Platform documentation for the Protobuf definitions.

        // Example with a single candidate and a single part with the encoded text.
        // **Important**:  The protobuf library can automatically decode the UTF-8,
        // so you do NOT need to manually decode "\345\245\275..."

        // Use builders to construct the required protobuf objects
        Part.Builder partBuilder = Part.newBuilder().setText("量纠缠是一种独特的量子力学现象。"); // example

        Content.Builder contentBuilder = Content.newBuilder()
                .setRole("model")
                .addParts(partBuilder.build());

        Candidate.Builder candidateBuilder = Candidate.newBuilder()
                .setContent(contentBuilder.build());

        GenerateContentResponse.Builder responseBuilder = GenerateContentResponse.newBuilder()
                .addCandidates(candidateBuilder.build());

        return responseBuilder.build();
    }
}
