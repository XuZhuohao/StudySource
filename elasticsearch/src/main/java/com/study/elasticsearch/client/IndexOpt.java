package com.study.elasticsearch.client;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @author XuZhuohao
 * @date 2021/4/7
 */
public class IndexOpt {
    private IndicesClient indices;

    public IndexOpt(RestHighLevelClient client) {
        this.indices = client.indices();
    }

    public CreateIndexResponse create(String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        return indices.create(request, RequestOptions.DEFAULT);
    }

    public GetIndexResponse search(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return indices.get(request, RequestOptions.DEFAULT);
    }

    public AcknowledgedResponse delete(String indexName) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        return indices.delete(request, RequestOptions.DEFAULT);
    }

}
