package au.edu.sydney.brawndo.erp.spfea.chain;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.PhoneCall;
import au.edu.sydney.brawndo.erp.ordering.Customer;
import au.edu.sydney.brawndo.erp.spfea.ContactMethod;


/**
 * Concrete Handler class for transmitting message to client via phonecall. Belongs to CoR
 */
public class PhonecallHandler extends Handler{

    /**
     * Called by constructor, sets type to default enum PHONECALL.
     */
    @Override
    protected void setType() {
        this.type = ContactMethod.PHONECALL;
    }

    /**
     * Sends invoice via phonecall. If the priority is not phonecall, the request is passed to the next Handler in the chain.
     * @param token authenticator
     * @param customer Customer that allows for lazy loading
     * @param priority enum ContactMethod
     * @param data the invoice
     */
    @Override
    public void sendInvoice(AuthToken token, Customer customer, ContactMethod priority, String data) {
        if (this.type.equals(priority)) {

            String phone = customer.getPhoneNumber();
            if (null != phone) {
                PhoneCall.sendInvoice(token, customer.getfName(), customer.getlName(), data, phone);
            }

        } else {
            this.nextHandler.sendInvoice(token, customer, priority, data);
        }

    }

}
