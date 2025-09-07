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
            documentText = documentText.substring(0, Math.min(documentText.length(), 200)); // Trim to first 200 chars
            vectorService.storeData(documentText);
            GroundTruthData.GROUND_TRUTH_DOCS.add(documentText);
            return "Document stored successfully 200 chars "+ documentText;
        }


    @GetMapping("/optimizedDocuments")
    public List<Document> optimizedDocs(@RequestParam("documentText") String documentText) {
     //   String optimizedQuery = llmService.rewriteQuery(documentText);
        return vectorService.getSimilarDocuments(documentText);
    }

    @GetMapping("/rerankedDocs")
    public List<Document> rerankedDocs(@RequestParam("documentText") String documentText) {
        List<Document> retrievedDocs = vectorService.getSimilarDocuments(documentText);
      //  return embeddingService.rerankByEmbedding(retrievedDocs, documentText);
        return retrievedDocs; // placeholder until reranking is implemented
    }







    @GetMapping("/groundTruth")
        public List<String> groundTruth()  {
            return GroundTruthData.GROUND_TRUTH_DOCS;
        }
}
