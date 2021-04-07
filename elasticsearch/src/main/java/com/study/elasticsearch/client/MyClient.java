package com.study.elasticsearch.client;

import com.alibaba.fastjson.JSON;
import com.study.elasticsearch.dto.UserDto;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.support.master.ShardsAcknowledgedResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author XuZhuohao
 * @date 2021/4/7
 */
public class MyClient {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.240.63", 9200, "http")));


        /*---index---*/
        //IndexOpt indexOpt = new IndexOpt(client);
        /// create
        /*CreateIndexResponse user = indexOpt.create("user");
        System.out.println(user.isAcknowledged());*/
        /// search
        /*GetIndexResponse user = indexOpt.search("user");
        System.out.println(Arrays.toString(user.getIndices()));
        System.out.println(user.getMappings());
        System.out.println(user.getAliases());*/
        /// delete
        /*AcknowledgedResponse user = indexOpt.delete("user");
        System.out.println(user.isAcknowledged());*/

        /*---document---*/
        DocOpt docOpt = new DocOpt(client);
        UserDto userDto = UserDto.builder().address("地址").age(10).name("小明").build();
        /// add
        /*IndexResponse response = docOpt.addData("user", "1", JSON.toJSONString(userDto));
        System.out.println(response.status());
        System.out.println(response.toString());*/
        /// update
        /*userDto.setAge(11);
        UpdateResponse response = docOpt.updateData("user", "1", JSON.toJSONString(userDto));
        System.out.println(response.toString());
        System.out.println(response.status());*/
        /// delete
        /*DeleteResponse response = docOpt.deleteData("user", "1");
        System.out.println(response.toString());
        System.out.println(response.status());*/


        client.close();
    }
}
