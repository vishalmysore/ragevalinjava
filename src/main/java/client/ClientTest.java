package client;

import io.github.vishalmysore.a2a.client.A2AAgent;
import lombok.extern.java.Log;

@Log
public class ClientTest {
    public static void main(String[] args) {
        //String json = "(jsonrpc=2.0, id=999789, result=Task(id=999789, sessionId=sdf44555, status=TaskStatus(id=null, state=COMPLETED, message=Message(id=null, role=agent, parts=[TextPart(id=null, type=text, text= Your Task with id 999789 is submitted, metadata={}), TextPart(id=null, type=text, text=\"Instructions for refrigerator model RF1234XYZ stored successfully. Instructions: Store perishable items on the top shelf, keep dairy products in their designated compartment, place raw meat on the bottom shelf to avoid cross-contamination, use clear containers for leftovers, keep fruits and vegetables in separate crisper drawers, ensure items are well-sealed to prevent odor tran\", metadata={})], metadata=null), timestamp=2025-06-14T21:19:48.737015100Z), history=[Message(id=null, role=user, parts=[TextPart(id=null, type=text, text=Please provide the instructions for storing items in the refrigerator (not exceeding 10 lines) and the model number of the refrigerator. For example: Store perishable items on the top shelf, keep dairy products in their designated compartment, and ensure the door is closed tightly. Model Number: RF1234XYZ., metadata=null)], metadata=null)], artifacts=null, metadata=null, pushNotificationConfig=null, pushNotificationUrl=null, subscribed=false, subscriptionDateNow=null, cancelled=false), error=null)"
        A2AAgent agent = new A2AAgent();
        agent.connect("http://localhost:7860");
      //  log.info(agent.remoteMethodCall("can you get store these instructions for washing meachine with model number 786hhyy -open the door , put the cloth and start washing").getTextResult());
        log.info(agent.remoteMethodCall("can you get eating preference of vishal ").toString());
    }
}
