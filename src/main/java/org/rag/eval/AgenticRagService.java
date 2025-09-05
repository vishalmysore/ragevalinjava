package org.rag.eval;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AgenticRagService {

    @Autowired
    private VectorStore vectorStore;

    @Autowired
    ObjectStringSummarizer summarizer;

    public void addTask(Object task) {
        String summary = summarizer.summarize(task.toString());
        log.info("Adding task to vector store: " + summary);
        vectorStore.add(List.of(new Document(task.toString())));
    }
    public void storeData(Object task) {
        String summary = summarizer.summarize(task.toString());
        log.info("Adding task to vector store: " + summary);
        vectorStore.add(List.of(new Document(task.toString())));
    }

    public List<Document> getSimilarDocuments(String taskText) {

        List<Document> results = vectorStore.similaritySearch(SearchRequest.builder().query(taskText).topK(1).build());
        //you can do additional processing here if needed
        return results;
    }

    public RagMetrics evaluateRag(String taskText, List<String> groundTruthDocs) {
        List<Document> retrievedDocs = vectorStore.similaritySearch(
                SearchRequest.builder().query(taskText).topK(5).build()
        );

        // Convert retrieved documents to Strings
        List<String> retrievedDocStrings = retrievedDocs.stream()
                .map(doc -> {
                    try {
                        // Try getText() first; fallback to toString()
                        return doc.getText() != null ? doc.getText() : doc.toString();
                    } catch (Exception e) {
                        log.warn("Unable to extract document content, falling back to toString()", e);
                        return doc.toString();
                    }
                })
                .collect(Collectors.toList());

        // Calculate metrics
        RagMetrics metrics = RagMetrics.calculate(groundTruthDocs, retrievedDocStrings, 3);

        log.info("RAG Metrics: Precision={}, Recall={}, F1={}, MRR={}, nDCG={}, HitRate={}",
                metrics.getPrecision(), metrics.getRecall(), metrics.getF1Score(),
                metrics.getMrr(), metrics.getNdcg(), metrics.getHitRate());

        return metrics;
    }


    }
