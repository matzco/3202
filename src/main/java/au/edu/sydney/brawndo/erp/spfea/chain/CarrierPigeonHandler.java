package au.edu.sydney.brawndo.erp.spfea.chain;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.CarrierPigeon;
import au.edu.sydney.brawndo.erp.ordering.Customer;
import au.edu.sydney.brawndo.erp.spfea.ContactMethod;

/**
 * Concrete Handler class for transmitting message to client via carrier pigeon. Belongs to CoR
 */
public class CarrierPigeonHandler extends Handler {
    /**
     * Called by constructor, sets type to default enum CARRIER_PIGEON.
     */
    @Override
    protected void setType() {
        this.type = ContactMethod.CARRIER_PIGEON;
    }

    /**
     * Sends invoice via carrier pigeon. If the priority is not pigeon, the request is passed to the next Handler in the chain.
     * @param token authenticator
     * @param customer Customer that allows for lazy loading
     * @param priority enum ContactMethod
     * @param data the invoice
     */
    @Override
    public void sendInvoice(AuthToken token, Customer customer, ContactMethod priority, String data) {
        if (this.type.equals(priority)) {
            String pigeonCoopID = customer.getPigeonCoopID();
            if (null != pigeonCoopID) {
                CarrierPigeon.sendInvoice(token, customer.getfName(), customer.getlName(), data, pigeonCoopID);
            }

        } else {
            this.nextHandler.sendInvoice(token, customer, priority, data);
        }

    }
}
