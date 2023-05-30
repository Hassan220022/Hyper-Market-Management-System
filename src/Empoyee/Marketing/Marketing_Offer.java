package Empoyee.Marketing;

public class Marketing_Offer {
    private int id;
    private int employeeId;
    private int productId;
    private String offerText;
    ////// TODO: lessa ma3amaltesh fih haga

    public Marketing_Offer(int employeeId, int productId, String offerText) {
        this.employeeId = employeeId;
        this.productId = productId;
        this.offerText = offerText;
    }

    public Marketing_Offer(int id, int employeeId, int productId, String offerText) {
        this.id = id;
        this.employeeId = employeeId;
        this.productId = productId;
        this.offerText = offerText;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getProductId() {
        return productId;
    }

    public String getOfferText() {
        return offerText;
    }
}