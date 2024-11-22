package com.sos_assistance.ecommerce.common.constants;

public class ErrorMessages {
    private ErrorMessages() {
        throw new IllegalStateException("ErrorMessages");
    }
    public static final String HANDLER_UNKNOWN_ERROR = "error.unknown";
    public static final String HANDLER_UNKNOWN_RESOURCE = "error.unknown.resource";
    public static final String HANDLER_NO_HANDLER_ERROR = "error.unknown.handler";
    public static final String HANDLER_UNKNOWN_MEDIA_TYPE = "error.unknown.media-type";
    public static final String HANDLER_MISSING_PARAMETER = "error.parameter.missing";
    public static final String HANDLER_VALIDATION_ERROR = "error.validation.fields";
    public static final String HANDLER_ARGUMENT_TYPE_ERROR = "error.argument.type";
    public static final String HANDLER_UNACCEPTABLE_MEDIA_TYPE = "error.unacceptable.media.type";
    public static final String PRODUCT_ALREADY_EXIST= "error.product.already.exist";
    public static final String PRODUCT_NOT_FOUND = "error.product.not.found";
    public static final String INVALID_ORDER =  "error.invalid.order";
    public static final String ORDER_NOT_FOUND = "error.order.not.found";
    public static final String INVALID_STATUS =  "error.invalid.status";

}