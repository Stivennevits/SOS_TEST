package com.sos_assistance.ecommerce.common.router;


public class Router {
    private Router() {
        throw new IllegalStateException("Router");
    }

    private static final String API = "/api/sos-assistance";

    public static class ProductAPI {
        private ProductAPI() {
            throw new IllegalStateException("ProductAPI");
        }

        public static final String ROOT = API + "/product";
        public static final String GET_BY_ID = "/{id}";
        public static final String UPDATE = "/{id}";
    }

    public static class OrderAPI {
        private OrderAPI() {
            throw new IllegalStateException("OrderAPI");
        }

        public static final String ROOT = API + "/order";
        public static final String GET_ALL_PAGING = "/paging";
        public static final String GET_ALL = "/all";
        public static final String GET_BY_ID = "/{id}";
        public static final String UPDATE = "/{id}";
        public static final String DELETE = "/{id}";
    }

}