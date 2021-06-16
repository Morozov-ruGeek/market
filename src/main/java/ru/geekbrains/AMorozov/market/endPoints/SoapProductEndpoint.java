package ru.geekbrains.AMorozov.market.endPoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.AMorozov.market.services.ProductService;
import ru.geekbrains.AMorozov.market.soap.soapproduct.GetAllProductsRequest;
import ru.geekbrains.AMorozov.market.soap.soapproduct.GetAllProductsResponse;
import ru.geekbrains.AMorozov.market.soap.soapproduct.GetProductByIdRequest;
import ru.geekbrains.AMorozov.market.soap.soapproduct.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class SoapProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.amorozov.com/spring/ws/soapProduct";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByNameRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setSoapProduct(productService.getById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStudentsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().forEach(response.getSoapProduct()::add);
        return response;
    }
}
