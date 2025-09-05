package org.rag.eval;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
@Agent(groupName = "eating preferences", groupDescription = "documents related to air conditioner operations")
public class SimpleService {
    // This class can be used to define simple actions or methods related to eating preferences.
    // Currently, it does not have any specific functionality but can be extended as needed.

    // Example method (can be expanded later):
    @Action(description = "Retrieve eating preference based on user name")
    public String getEatingPreference(String nameoftheUser) {
        log.info("Retrieving eating preference: " + nameoftheUser);
        return "Your eating preference is aloo gobi: " + nameoftheUser;
    }
}
