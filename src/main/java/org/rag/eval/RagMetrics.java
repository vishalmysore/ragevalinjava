package org.rag.eval;



import lombok.Data;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.List;

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
    static boolean isRelevant(String retrieved, String relevant) {
        LevenshteinDistance ld = new LevenshteinDistance();
        int distance = ld.apply(retrieved, relevant);
        double similarity = 1.0 - ((double) distance / Math.max(retrieved.length(), relevant.length()));
        return similarity >= 0.8;
    }

    public static RagMetrics calculate(List<String> relevantDocs, List<String> retrievedDocs, int k) {
        RagMetrics metrics = new RagMetrics();

        // Count relevant retrieved docs
        long relevantRetrieved = retrievedDocs.stream()
                .filter(doc -> relevantDocs.stream()
                        .anyMatch(gt -> isRelevant(doc, gt)))
                .count();

        // Precision = Relevant Retrieved / Total Retrieved
        metrics.precision = retrievedDocs.isEmpty() ? 0 : (double) relevantRetrieved / retrievedDocs.size();

        // Recall = Relevant Retrieved / All Relevant
        metrics.recall = relevantDocs.isEmpty() ? 0 : (double) relevantRetrieved / relevantDocs.size();

        // F1 Score = 2 * (P * R) / (P + R)
        metrics.f1Score = (metrics.precision + metrics.recall == 0) ?
                0 : 2 * (metrics.precision * metrics.recall) / (metrics.precision + metrics.recall);

        // MRR = 1 / rank of first relevant doc
        metrics.mrr = 0;
        for (int i = 0; i < retrievedDocs.size(); i++) {
            if (relevantDocs.contains(retrievedDocs.get(i))) {
                metrics.mrr = 1.0 / (i + 1);
                break;
            }
        }

        // nDCG = sum(1/log2(rank+1)) for relevant docs / ideal nDCG
        metrics.ndcg = calculateNDCG(relevantDocs, retrievedDocs);

        // Hit Rate / Recall@k = at least one relevant doc in top-k
        metrics.hitRate = retrievedDocs.stream().limit(k).anyMatch(relevantDocs::contains) ? 1.0 : 0.0;

        return metrics;
    }

    private static double calculateNDCG(List<String> relevantDocs, List<String> retrievedDocs) {
        double dcg = 0.0;
        for (int i = 0; i < retrievedDocs.size(); i++) {
            if (relevantDocs.contains(retrievedDocs.get(i))) {
                dcg += 1.0 / (Math.log(i + 2) / Math.log(2)); // log base 2
            }
        }
        double idcg = 0.0;
        for (int i = 0; i < relevantDocs.size(); i++) {
            idcg += 1.0 / (Math.log(i + 2) / Math.log(2));
        }
        return idcg == 0 ? 0 : dcg / idcg;
    }
}
