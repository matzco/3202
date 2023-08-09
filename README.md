# Assignment 2: Design Patterns

## Solutions to the Key Issues

## SID 
####

### RAM Issue

#### Flyweight DP

- FlyweightFactory: FlyweightFactory.java
- Client: ProductImpl.java
- ConcreteFlyweight: ConcreteData.java

### Too Many Orders

#### Strategy DP

- Context: CompOrder.java, SPFEAFacade.java
- Strategy: CustomerStrategy.java, DescriptionStrategy.java, DiscountStrategy.java (interfaces)
- ConcreteStrategy: BusinessStrategy.java, IndividualStrategy.java, OrderStrategy.java, SubscriptionStrategy.java, FlatDiscountStrategy.java, BulkDiscountStrategy.java

### Bulky Contact Method

#### Chain of Responsibility DP

- Handler: Handler.java (abstract)
- ConcreteHandler: SMSHandler.java, MailHandler.java, EmailHandler.java, PhonecallHandler.java, MerchandiserHandler.java, CarrierPigeonHandler.java
- Client: ContactHandler.java

### System Lag

#### Lazy Loading DP (Lazy Initialisation)

- Client: SPFEAFacade.java, SMSHandler.java, MailHandler.java, EmailHandler.java, PhonecallHandler.java, MerchandiserHandler.java, CarrierPigeonHandler.java
- Lazy Loader: CustomerImpl.java

### Hard to Compare Products

#### Value Object DP

- ValueObject: ProductValue.java


## Notes About the Submission

For the Lazy Loading DP: Given the restrictions on modifying the codebase, the CustomerImpl class stores the AuthToken provided by the SPFEAFacade class, which is not strictly ideal. However, as it currently stands, the only instances where this token is utilised are within scope of the SPFEAFacade methods, such as database calls from ConcreteHandlers, which are initialised by ContactHandler, which is itself provided the same AuthToken and calls from SPFEAFacade. 
This is due to the simulated immutability of the Customer interface, which would require modifications to the method signatures to accept the AuthToken parameter. An alternate option was to rebuild the CustomerImpl class without implementing the Customer interface, but this was dismissed, as that would also necessiate changing method signatures in SPFEAFacade, and these are critical for the automated test suite. 
Unless implementing some notable or specific behaviour, setter and getter methods were not commented or explained. Comments were added only to refactored or new code, which also allows for easier revision checking.
