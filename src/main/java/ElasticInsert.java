import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticInsert {
    public static void main(String[] args) throws UnknownHostException {
        Client client = new PreBuiltTransportClient(
                Settings.builder().put("client.transport.sniff", true)
                        .put("cluster.name","elasticsearch").build())
                .addTransportAddress(new TransportAddress(
                        InetAddress.getByName("localhost"), 9300));
        String jsonObject = "{\"age\":10,\"dateOfBirth\":1471466076564,"
                +"\"fullName\":\"John Doe\"}";
        IndexResponse response = client.prepareIndex("people", "Doe")
                .setSource(jsonObject, XContentType.JSON).get();
        String id = response.getId();
        System.out.println("id=" + id);
    }
}
