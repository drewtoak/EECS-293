package orange;

/**
 * The public enumerated type class, ProductType, lists all of the products.
 *
 * @author Andrew Hwang
 * @version 1.0 Sept 5, 2015.
 */
public enum ProductType {

    /**
     * The enum list of all the product types and their strings.
     */
    OPOD("oPod"), OPAD("oPad"), OPHONE("oPhone"), OWATCH("oWatch"), OTV("oTv");

    /**
     * Fields:
     * String field labeled name.
     */
    String name;

    /**
     * Assigns a String variable to a Product type.
     * @param name
     */
    ProductType(String name){
        this.name = name;
    }

    /**
     * Returns the name of the product type as a String.
     * @return name
     */
    public String getName(){
        return this.name;
    }

}
