import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ElasticGet {
    public static void main(String[] args) throws UnknownHostException {
        Client client = new PreBuiltTransportClient(
                Settings.builder().put("client.transport.sniff", true)
                        .put("cluster.name","elasticsearch").build())
                .addTransportAddress(new TransportAddress(
                        InetAddress.getByName("localhost"), 9300));
        GetResponse getResponse = client.prepareGet("people","Doe","SuqYtG0BXzhQLsFrfM0e").get();
        if (getResponse.isExists()) {
            Map<String,Object> sourceMap = getResponse.getSource();
            for (String key : sourceMap.keySet())
                System.out.println(key + ":" + sourceMap.get(key));
        }
        client.close();
    }
}
