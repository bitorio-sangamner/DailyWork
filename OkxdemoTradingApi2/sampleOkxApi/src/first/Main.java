package first;
import com.okex.open.api.config.APIConfiguration;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        APIConfiguration config = new APIConfiguration();
        config.setEndpoint("https://www.okx.com/");
        //secretKey,api
        config.setApiKey("d5fda288-b395-4289-b288-d9e915e82e59");
        config.setSecretKey("B59FFC134E2A1E33559E650B3C0DFA3E");
        //Passphrase
        config.setPassphrase("75c60758-beA16");
        config.setPrint(true);
    }
}