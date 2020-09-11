package com.itcast;

import com.amazonaws.DefaultRequest;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.Signer;
import com.amazonaws.http.HttpMethodName;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.apache.http.protocol.HttpCoreContext.HTTP_TARGET_HOST;

/*
https://www.yiibai.com/apache_httpclient/apache_httpclient_interceptors.html
 https://github.com/PRASANTHRAJENDRAN/spring-boot-starter-data-elasticsearch-with-aws-demo/blob/24f81c4f09/src/main/java/com/demo/aws/elasticsearch/data/configuration/AWSRequestSigningApacheInterceptor.java
 */
public class AWSRequestSigningApacheInterceptor implements HttpRequestInterceptor {

    /**
     * The service that we're connecting to. Technically not necessary.
     * Could be used by a future Signer, though.
     */
    private final String service;

    /**
     * The particular signer implementation.
     */
    private final Signer signer;

    /**
     * The source of AWS credentials for signing.
     */
    private final AWSCredentialsProvider awsCredentialsProvider;

    public AWSRequestSigningApacheInterceptor(String service, AWS4Signer signer, AWSCredentialsProvider awsCredentialsProvider) {
        this.service = service;
        this.signer = signer;
        this.awsCredentialsProvider = awsCredentialsProvider;
    }

    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(request.getRequestLine().getUri());
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URI", e);
        }

        // Copy Apache HttpRequest to AWS DefaultRequest
        DefaultRequest<?> signableRequest = new DefaultRequest<>(service);

        HttpHost host = (HttpHost) context.getAttribute(HTTP_TARGET_HOST);
        if (host != null) {
            signableRequest.setEndpoint(URI.create(host.toURI()));
        }
        final HttpMethodName httpMethod =
                HttpMethodName.fromValue(request.getRequestLine().getMethod());
        signableRequest.setHttpMethod(httpMethod);


    }
}
