package client;

import io.github.vishalmysore.a2a.client.A2AAgent;
import lombok.extern.slf4j.Slf4j;
import org.rag.eval.GroundTruthData;

import java.util.Arrays;
import java.util.List;

/**Store the instructions for various appliances in a RAG system using an A2A agent. */

@Slf4j
public class StoreDocumentsWithA2A {

    // ✅ Centralized Ground Truth Documents


    public static void main(String[] args) {
        A2AAgent agent = new A2AAgent();
        agent.connect("http://localhost:7860");

        // ✅ Store all ground truth documents in RAG
        for (String instructions : GroundTruthData.GROUND_TRUTH_DOCS) {
            log.info("Storing instructions: {}", instructions);
            String response = agent.remoteMethodCall("Store these instructions: " + instructions).getTextResult();
            log.info("Agent response: {}", response);
        }

        log.info("✅ All documents stored successfully!");
    }
}
