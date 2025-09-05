package org.rag.eval;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.annotations.Prompt;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
@Agent(groupName = "microwave", groupDescription = "documents related to microwave operations")
public class MicrowaveManual {
    @Autowired
    private AgenticRagService vectorService;

    @Action(description = "Store instructions for microwave with model number")
    public String storeInstructionsForMicrowave(@Prompt(describe = "not more than 10 lines of instructions") String instructions, String modelNumber) {
        // Trim instructions to prevent overloading the free MongoDB instance
        if (instructions.length() > 300) {
            instructions = instructions.substring(0, 300);
        }
        log.info("Storing instructions for microwave model: " + modelNumber);
        vectorService.storeData(instructions + "\n model number is " + modelNumber);
        return "Instructions for microwave model " + modelNumber + " stored successfully. Instructions: " + instructions;
    }

    @Action(description = "Retrieve instructions for microwave with model number")
    public String retrieveInstructionsForMicrowave(String modelNumber) {
        log.info("Retrieving instructions for microwave model: " + modelNumber);
        String instructions = vectorService.getSimilarDocuments(modelNumber).get(0).getFormattedContent();
        if (instructions == null || instructions.isEmpty()) {
            return "No instructions found for microwave model " + modelNumber;
        }
        return "Instructions for microwave model " + modelNumber + ": " + instructions;
    }
}
