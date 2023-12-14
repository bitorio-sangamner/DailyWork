package futureTrade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebsocketConfig {

     @Autowired
     WebsocketClient websocketClient;
    private  String SERVICE_URL="wss://wspap.okx.com:8443/ws/v5/private?brokerId=9999";
    private  String API_KEY = "455577ff-5177-4177-9e27-9485836b0b14";
    private  String SECRET_KEY = "ADF5FF955F2A370A1C50DF85B4CB5753";
    private  String PASSPHRASE = "80c60758-beA16";

    public void loginConnect()
    {
        System.out.println(SERVICE_URL);
        websocketClient.connection(SERVICE_URL);

        if(API_KEY!=null && !API_KEY.equals("") && SECRET_KEY!=null && !SECRET_KEY.equals("") && PASSPHRASE!=null && !PASSPHRASE.equals(""))
        {
            websocketClient.loginToOkx(API_KEY,SECRET_KEY,PASSPHRASE);
        }
    }
}
