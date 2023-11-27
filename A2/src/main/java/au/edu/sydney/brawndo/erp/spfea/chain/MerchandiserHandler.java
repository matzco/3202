package au.edu.sydney.brawndo.erp.spfea.chain;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.Merchandiser;
import au.edu.sydney.brawndo.erp.ordering.Customer;
import au.edu.sydney.brawndo.erp.spfea.ContactMethod;

/**
 * Concrete Handler class for transmitting message to client via Merchandiser. Belongs to CoR
 */
public class MerchandiserHandler extends Handler {
    /**
     * Called by constructor, sets type to default enum MERCHANDISER.
     */
    @Override
    protected void setType() {
        this.type = ContactMethod.MERCHANDISER;
    }

    /**
     * Sends invoice via merchandiser. If the priority is not merchandiser, the request is passed to the next Handler in the chain.
     * @param token authenticator
     * @param customer Customer that allows for lazy loading
     * @param priority enum ContactMethod
     * @param data the invoice
     */
    @Override
    public void sendInvoice(AuthToken token, Customer customer, ContactMethod priority, String data) {
        if (this.type.equals(priority)) {

            String merchandiser = customer.getMerchandiser();
            String businessName = customer.getBusinessName();
            if (null != merchandiser && null != businessName) {
                Merchandiser.sendInvoice(token, customer.getfName(), customer.getlName(), data, merchandiser,businessName);
            }

        } else {
            this.nextHandler.sendInvoice(token, customer, priority, data);
        }

    }
}
