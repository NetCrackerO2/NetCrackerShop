package sql;

public interface Fields {
    String CLIENT_TABLE = "client";
    String CLIENT_NAME = "client_name";
    String CLIENT_ADDRESS = "default_address";

    String PRODUCT_NAME = "prod_name";
    String PRODUCT_TABLE = "product";
    String PRODUCT_PRICE = "price";
    String PRODUCT_COUNT = "prod_count";
    String PRODUCT_DESCRIPTION = "description";

    String CATEGORY_TABLE = "category";
    String CATEGORY_NAME = "category_name";

    String ORDER_TABLE = "order_prod";
    String ORDER_ADDRESS = "address";
    String ORDER_DATE = "ord_date";
    String ORDER_STATUS = "status";

    String ORDERITEM_TABLE = "orderitem";
    String ORDERITEM_PRICE = "price";
    String ORDERITEM_COUNT = "count";
}
