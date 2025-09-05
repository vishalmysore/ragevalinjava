package org.rag.eval;



import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.annotations.Prompt;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
@Agent(groupName = "washing machine", groupDescription = "documents related to washing machine operations")
public class WashingMachineManual {
    @Autowired
    private AgenticRagService vectorService;

    @Action(description = "Store instructions for washing machine with model number")
    public String storeInstructionsForWashingMachine(@Prompt(describe = "not more than 10 lines of instructions") String instructions, String modelNumber) {
        // i need to trim as i am using free mongo version i dont want to overload you can do you validation here
        if (instructions.length() > 300) {
            instructions = instructions.substring(0, 300);
        }
        log.info("Storing instructions for washing machine model: " + modelNumber);
        vectorService.storeData(instructions + "\n model number is " + modelNumber);
        return "Instructions for washing machine model " + modelNumber + " stored successfully. Instructions: " + instructions;
    }

    @Action(description = "Retrieve instructions for washing machine with model number")
    public String retrieveInstructionsForWashingMachine(String modelNumber) {
        log.info("Retrieving instructions for washing machine model: " + modelNumber);
        String instructions = vectorService.getSimilarDocuments(modelNumber).get(0).getFormattedContent();
        if (instructions == null || instructions.isEmpty()) {
            return "No instructions found for washing machine model " + modelNumber;
        }
        return "Instructions for washing machine model " + modelNumber + ": " + instructions;
    }
}