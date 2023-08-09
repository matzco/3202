package au.edu.sydney.brawndo.erp.spfea.chain;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.Mail;
import au.edu.sydney.brawndo.erp.ordering.Customer;
import au.edu.sydney.brawndo.erp.spfea.ContactMethod;

/**
 * Concrete Handler class for transmitting message to client via Mail. Belongs to CoR
 */
public class MailHandler extends Handler{

    /**
     * Called by constructor, sets type to default enum MAIL.
     */
    @Override
    protected void setType() {
        this.type = ContactMethod.MAIL;
    }

    /**
     * Sends invoice via Mail. If the priority is not MAIL, the request is passed to the next Handler in the chain.
     * @param token authenticator
     * @param customer Customer that allows for lazy loading
     * @param priority enum ContactMethod
     * @param data the invoice
     */
    @Override
    public void sendInvoice(AuthToken token, Customer customer, ContactMethod priority, String data) {
        if (this.type.equals(priority)) {

            String address = customer.getAddress();
            String suburb = customer.getSuburb();
            String state = customer.getState();
            String postcode = customer.getPostCode();

            if (null != address && null != suburb &&
                    null != state && null != postcode) {
                Mail.sendInvoice(token, customer.getfName(), customer.getlName(), data, address, suburb, state, postcode);
            }
        } else {
            this.nextHandler.sendInvoice(token, customer, priority, data);
        }

    }


}
