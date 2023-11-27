package au.edu.sydney.brawndo.erp.spfea.chain;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.Email;
import au.edu.sydney.brawndo.erp.ordering.Customer;
import au.edu.sydney.brawndo.erp.spfea.ContactMethod;

/**
 * Concrete Handler class for transmitting message to client via EMail. Belongs to CoR
 */
public class EmailHandler extends Handler {

    /**
     * Called by constructor, sets type to default enum EMAIL.
     */
    @Override
    protected void setType() {
        this.type = ContactMethod.EMAIL;
    }

    /**
     * Sends invoice via email. If the priority is not email, the request is passed to the next Handler in the chain.
     * @param token authenticator
     * @param customer Customer that allows for lazy loading
     * @param priority enum ContactMethod
     * @param data the invoice
     */
    @Override
    public void sendInvoice(AuthToken token, Customer customer, ContactMethod priority, String data) {
        if (this.type.equals(priority)) {
            String email = customer.getEmailAddress();
            if (null != email) {
                Email.sendInvoice(token, customer.getfName(), customer.getlName(), data, email);
            }
        } else {
            this.nextHandler.sendInvoice(token, customer, priority, data);
        }
    }
}
