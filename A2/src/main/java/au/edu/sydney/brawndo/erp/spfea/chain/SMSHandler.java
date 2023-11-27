package au.edu.sydney.brawndo.erp.spfea.chain;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.SMS;
import au.edu.sydney.brawndo.erp.ordering.Customer;
import au.edu.sydney.brawndo.erp.spfea.ContactMethod;

/**
 * Concrete Handler class for transmitting message to client via SMS. Belongs to CoR
 */
public class SMSHandler extends Handler{

    /**
     * Called by constructor, sets type to default enum SMS.
     */
    @Override
    protected void setType() {
        this.type = ContactMethod.SMS;
    }

    /**
     * Sends invoice via SMS. If the priority is not sms, the request is passed to the next Handler in the chain.
     * @param token authenticator
     * @param customer Customer that allows for lazy loading
     * @param priority enum ContactMethod
     * @param data the invoice
     */
    @Override
    public void sendInvoice(AuthToken token, Customer customer, ContactMethod priority, String data) {
        if (this.type.equals(priority)) {

            String smsPhone = customer.getPhoneNumber();
            if (null != smsPhone) {
                SMS.sendInvoice(token, customer.getfName(), customer.getlName(), data, smsPhone);
            }

        } else {
            this.nextHandler.sendInvoice(token, customer, priority, data);
        }

    }

}
