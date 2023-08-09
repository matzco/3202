package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.*;
import au.edu.sydney.brawndo.erp.ordering.Customer;
import au.edu.sydney.brawndo.erp.spfea.chain.*;

import java.util.Arrays;
import java.util.List;

/**
 * Class for managing the different ways of sending data to clients.
 */
public class ContactHandler {

    /**
     * Static method that sends data to a client via a method. Methods are ordered by priority, ideally the first one is used.
     * Uses Chain Of Responsibility to pass data to the correct Handler.
     * @param token authenticator
     * @param customer the client
     * @param priority a list of the different enum methods for contacting clients, sorted by priority
     * @param data the invoice
     * @return true or false
     */
    public static boolean sendInvoice(AuthToken token, Customer customer, List<ContactMethod> priority, String data) {

        Handler chain = buildChain();

        for (ContactMethod method : priority) {

            chain.sendInvoice(token, customer, method, data);
            return true;
        }
        return false;
    }
    public static List<String> getKnownMethods() {
        return Arrays.asList(
                "Carrier Pigeon",
                "Email",
                "Mail",
                "Merchandiser",
                "Phone call",
                "SMS"
        );
    }

    /**
     * Builds the chain of responsibility. First all concrete handlers are created, then linked. The sequence is always the same
     *
     * @return the smsHandler obj at the root of the chain
     */
    protected static Handler buildChain() {

        SMSHandler smsHandler = new SMSHandler();
        MailHandler mailHandler = new MailHandler();
        EmailHandler emailHandler = new EmailHandler();
        PhonecallHandler phonecallHandler = new PhonecallHandler();
        MerchandiserHandler merchandiserHandler = new MerchandiserHandler();
        CarrierPigeonHandler carrierPigeonHandler = new CarrierPigeonHandler();

        smsHandler.setNextHandler(mailHandler);
        mailHandler.setNextHandler(emailHandler);
        emailHandler.setNextHandler(phonecallHandler);
        phonecallHandler.setNextHandler(merchandiserHandler);
        merchandiserHandler.setNextHandler(carrierPigeonHandler);

        return smsHandler;
    }

}
