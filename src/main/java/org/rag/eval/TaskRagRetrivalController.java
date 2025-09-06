package org.rag.eval;

import client.StoreDocumentsWithA2A;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TaskRagRetrivalController {




        @Autowired
        private AgenticRagService vectorService;

        @GetMapping("/getDocuments")
        public List<Document> getDocument(@RequestParam("documentText") String documentText) {

            return vectorService.getSimilarDocuments(documentText);
        }
        @GetMapping("/evalRag")
        public RagMetrics evalRag(@RequestParam("documentText") String documentText)  {
            return vectorService.evaluateRag(documentText, GroundTruthData.GROUND_TRUTH_DOCS);
        }

        @PostMapping("/storeDocument")
        public String storeDocument(@RequestParam("documentText") String documentText)  {
            vectorService.storeData(documentText);
            GroundTruthData.GROUND_TRUTH_DOCS.add(documentText);
            return "Document stored successfully";
        }
}
