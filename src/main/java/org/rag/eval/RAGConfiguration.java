package org.rag.eval;

import com.mongodb.client.MongoClients;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class RAGConfiguration {
    private final EmbeddingModel embeddingModel;

    @Value("${spring.data.mongodb.uri}")
    private String mongoDBURI;
    @Value("${spring.data.mongodb.database}")
    private String mongoDBName;

    public RAGConfiguration(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create(mongoDBURI), mongoDBName);
    }




}
