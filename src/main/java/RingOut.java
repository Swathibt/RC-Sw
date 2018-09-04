import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import com.ringcentral.RestClient;
import com.ringcentral.RestException;
import com.ringcentral.definitions.*;
import io.restassured.response.Response;

public class RingOut {

	public static void main(String[] args) throws IOException, RestException {
		
		
		String clientId = "**clientId**";
		String clientSecret = "**clientsecret**";
		String server = "**server**";
		String username = "**username**";
		String password = "**password**";
		String extension = "**extension**";
		String from_Phone = "**123**";
		String to_Phone= "**456**";
		String accountId="**accountid**";
	    String extensionId="**extension**";	
	    
		
		RestClient s = new RestClient(clientId ,clientSecret,server);
		try {
			s.authorize(username,extension,password);
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (RestException e) {			
			e.printStackTrace();
		}
		
		//Ring Out
		 MakeRingOutRequest requestBody = new MakeRingOutRequest();
		 requestBody.from(new MakeRingOutCallerInfoRequestFrom().phoneNumber(from_Phone));
         requestBody.to(new MakeRingOutCallerInfoRequestTo().phoneNumber(to_Phone));
         Response resp =  (Response) s.post("/restapi/v1.0/account/"+accountId+"/extension/"+extensionId+"/ring-out", requestBody);
         assertEquals(resp.statusCode(),"200");
         String respbody = resp.toString();
         assertTrue(respbody.contains("In Progress"));     
         // Get Call log
         Response callresp = (Response) s.get("/restapi/v1.0/account/"+accountId+"/extension/"+extensionId+"/call-log?phoneNumber="+to_Phone+"&direction=Outbound&view=Detailed&page=1&perPage=100");
         assertEquals(callresp.statusCode(),"200");                
         
			
	}

}
