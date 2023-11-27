package au.edu.sydney.brawndo.erp.spfea.chain;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.ordering.Customer;
import au.edu.sydney.brawndo.erp.spfea.ContactMethod;
import au.edu.sydney.brawndo.erp.spfea.CustomerImpl;


/**
 * Abstract class for Chain of Responsibility concrete implementations.
 */
public abstract class Handler {

    /**
     * Uses existing enum for ContactMethods
     */
    public ContactMethod type;
    /**
     * Initialise nextHandler in the chain to null
     */
    Handler nextHandler = null;

    public Handler() {
        setType();
    }

    /**
     * Setter
     * @param nextHandler
     */
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract void setType();

    /**
     * Abstract method for sending invoice. Each concrete class in the chain will have its own implementation.
     * It will also pass to the next link if the current one is not the correct contact method.
     * @param token
     * @param customer
     * @param priority
     * @param data
     */
    public abstract void sendInvoice(AuthToken token, Customer customer, ContactMethod priority, String data);


}
