package org.rag.eval;



import lombok.Data;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.List;
/**
 * RagMetricsEvaluator
 *
 * <p>
 * A utility class for evaluating RAG (Retrieval-Augmented Generation) systems
 * using standard IR (Information Retrieval) metrics.
 * This implementation uses <b>LevenshteinDistance</b> for measuring document similarity.
 * </p>
 *
 * <p>
 * **Why LevenshteinDistance?**
 * <ul>
 *   <li>Simple and lightweight → No embeddings or heavy models needed</li>
 *   <li>Good for evaluating near-matching documents in small-scale demos</li>
 *   <li>Ideal when retrieved documents are short and structured</li>
 * </ul>
 *
 * ⚡ **Limitations**:
 * <ul>
 *   <li>Levenshtein is purely syntactic → doesn't understand semantic similarity</li>
 *   <li>Works poorly when retrieved docs are paraphrased differently</li>
 * </ul>
 *
 * **Real-World RAG Evaluation**:
 * <ul>
 *   <li>Use **embeddings-based similarity** (e.g., cosine similarity over OpenAI, Cohere, or HuggingFace vectors)</li>
 *   <li>Use **LLM-as-a-Judge** for human-like document relevance scoring</li>
 *   <li>Use hybrid scoring → combine embeddings + Levenshtein for better accuracy</li>
 * </ul>
 *
 * <p>
 * **Metrics Supported**:
 * <ul>
 *   <li>Precision </li>
 *   <li>Recall </li>
 *   <li>F1 Score </li>
 *   <li>MRR (Mean Reciprocal Rank) </li>
 *   <li>nDCG (Normalized Discounted Cumulative Gain) </li>
 *   <li>Hit Rate / Recall@k </li>
 * </ul>
 * </p>
 *
 * <p><b>Author:</b> Vishal Mysore</p>
 */
@Data
public class RagMetrics {

    private double precision;
    private double recall;
    private double f1Score;
    private double mrr;     // Mean Reciprocal Rank
    private double ndcg;    // Normalized Discounted Cumulative Gain
    private double hitRate; // Recall@k

    /**
     * we cant use embeddings for cost reasons , you can use a string similarity algorithm:
     *
     * we will Use Apache Commons Text → FuzzyScore or LevenshteinDistance
     *
     * If similarity ≥ 80%, count as relevant.
     * @param retrieved
     * @param relevant
     * @return
     */
    private static final LevenshteinDistance ld = new LevenshteinDistance();

    static boolean isRelevant(String retrieved, String relevant) {
        int distance = ld.apply(retrieved, relevant);
        double similarity = 1.0 - ((double) distance / Math.max(retrieved.length(), relevant.length()));
        return similarity >= 0.8; // ✅ 80% threshold
    }

    public static RagMetrics calculate(List<String> relevantDocs, List<String> retrievedDocs, int k) {
        RagMetrics metrics = new RagMetrics();

        // ✅ Count relevant retrieved docs using fuzzy matching
        long relevantRetrieved = retrievedDocs.stream()
                .filter(doc -> relevantDocs.stream().anyMatch(gt -> isRelevant(doc, gt)))
                .count();

        // ✅ Precision = Relevant Retrieved / Total Retrieved
        metrics.precision = retrievedDocs.isEmpty() ? 0 : (double) relevantRetrieved / retrievedDocs.size();

        // ✅ Recall = Relevant Retrieved / All Relevant
        metrics.recall = relevantDocs.isEmpty() ? 0 : (double) relevantRetrieved / relevantDocs.size();

        // ✅ F1 Score = 2 * (P * R) / (P + R)
        metrics.f1Score = (metrics.precision + metrics.recall == 0) ?
                0 : 2 * (metrics.precision * metrics.recall) / (metrics.precision + metrics.recall);

        // ✅ Fix MRR — use fuzzy matching
        metrics.mrr = 0;
        for (int i = 0; i < retrievedDocs.size(); i++) {
            String doc = retrievedDocs.get(i);
            if (relevantDocs.stream().anyMatch(gt -> isRelevant(doc, gt))) {
                metrics.mrr = 1.0 / (i + 1);
                break;
            }
        }

        // ✅ Fix Hit Rate / Recall@k — use fuzzy matching
        metrics.hitRate = retrievedDocs.stream()
                .limit(k)
                .anyMatch(doc -> relevantDocs.stream().anyMatch(gt -> isRelevant(doc, gt)))
                ? 1.0 : 0.0;

        // ✅ Keep your nDCG calculation — but we should later improve it for fuzzy ranking
        metrics.ndcg = calculateNDCG(relevantDocs, retrievedDocs);

        return metrics;
    }

    private static double calculateSimilarity(String a, String b) {
        int distance = ld.apply(a, b);
        return 1.0 - ((double) distance / Math.max(a.length(), b.length()));
    }

    private static double calculateNDCG(List<String> relevantDocs, List<String> retrievedDocs) {
        double dcg = 0.0;

        // ✅ Calculate DCG based on graded similarity scores
        for (int i = 0; i < retrievedDocs.size(); i++) {
            String retrieved = retrievedDocs.get(i);
            // Find best similarity score among all relevant docs
            double maxSim = relevantDocs.stream()
                    .mapToDouble(gt -> calculateSimilarity(retrieved, gt))
                    .max()
                    .orElse(0.0);

            if (maxSim > 0.5) { // ✅ Minimum threshold to consider relevant
                dcg += (Math.pow(2, maxSim) - 1) / (Math.log(i + 2) / Math.log(2));
            }
        }

        // ✅ Calculate IDCG assuming perfect ordering
        double idcg = 0.0;
        for (int i = 0; i < relevantDocs.size(); i++) {
            // Ideal relevance = 1.0 for top relevant docs
            idcg += (Math.pow(2, 1.0) - 1) / (Math.log(i + 2) / Math.log(2));
        }

        return idcg == 0 ? 0 : dcg / idcg;
    }
}
