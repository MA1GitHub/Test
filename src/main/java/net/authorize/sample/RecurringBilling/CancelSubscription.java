package net.authorize.sample.RecurringBilling;

import net.authorize.data.arb.*;
import java.math.BigDecimal;
import net.authorize.Environment;
import net.authorize.data.Customer;
import net.authorize.api.contract.v1.*;
import net.authorize.api.controller.base.ApiOperationBase;
import net.authorize.api.controller.ARBCancelSubscriptionController;
import javax.xml.datatype.XMLGregorianCalendar;

public class CancelSubscription {

    public static void run(String apiLoginId, String transactionKey) {
        //Common code to set for all requests
        ApiOperationBase.setEnvironment(Environment.SANDBOX);
        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType() ;
        merchantAuthenticationType.setName(apiLoginId);
        merchantAuthenticationType.setTransactionKey(transactionKey);
        ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

        // Make the API Request
        ARBCancelSubscriptionRequest apiRequest = new ARBCancelSubscriptionRequest();
        apiRequest.setSubscriptionId("100748");
        ARBCancelSubscriptionController controller = new ARBCancelSubscriptionController(apiRequest);
        controller.execute();
        ARBCancelSubscriptionResponse response = controller.getApiResponse();
        if (response!=null) {

             if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {

                System.out.println(response.getMessages().getMessage().get(0).getCode());
                System.out.println(response.getMessages().getMessage().get(0).getText());
            }
            else
            {
                System.out.println("Failed to cancel Subscription:  " + response.getMessages().getResultCode());
            }
        }
    }
}