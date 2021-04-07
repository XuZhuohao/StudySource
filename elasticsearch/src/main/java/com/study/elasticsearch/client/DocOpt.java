package com.study.elasticsearch.client;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author XuZhuohao
 * @date 2021/4/7
 */
public class DocOpt {
    private RestHighLevelClient client;

    public DocOpt(RestHighLevelClient client) {
        this.client = client;
    }

    public IndexResponse addData(String index, String id, String data) throws IOException {
        IndexRequest request = new IndexRequest();
        request.id(id).index(index).source(data, XContentType.JSON);
        return client.index(request, RequestOptions.DEFAULT);
    }

    public UpdateResponse updateData(String index, String id, String data) throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.id(id).index(index).doc(data, XContentType.JSON);
        return client.update(request, RequestOptions.DEFAULT);
    }

    public DeleteResponse deleteData(String index, String id) throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.id(id).index(index);
        return client.delete(request, RequestOptions.DEFAULT);
    }

    public void search(String index){
        SearchRequest request = new SearchRequest();
//        request.indices(index)
    }
}
