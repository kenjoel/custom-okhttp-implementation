package okclient;

import org.conscrypt.Conscrypt;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;

public class ConscryptSocketFactoryWrapper extends SSLSocketFactory {
    private final SSLSocketFactory delegate;
    private final SSLParameters forcedParams;

    public ConscryptSocketFactoryWrapper(SSLSocketFactory delegate) {
        this.delegate = delegate;
        try {
            SSLContext ctx = SSLContext.getInstance("TLS", "Conscrypt");
            ctx.init(null, null, new SecureRandom());
            this.forcedParams = ctx.getDefaultSSLParameters();
            forcedParams.setApplicationProtocols(new String[]{"h2", "http/1.1"});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    // Create Socket wrappers:
    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        Socket socket = delegate.createSocket(s, host, port, autoClose);
        configureSocket(socket);
        return socket;
    }

    @Override
    public Socket createSocket() throws IOException {
        Socket socket = delegate.createSocket();
        configureSocket(socket);
        return socket;
    }

    @Override
    public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
        return null;
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
        return null;
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return null;
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
        return null;
    }


    private void configureSocket(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sslSocket = (SSLSocket) socket;

            // Instead of forcedParams.clone():
            SSLParameters cloned = copySSLParameters(forcedParams);

            sslSocket.setSSLParameters(cloned);

            // Turn on session tickets for Conscrypt:
            Conscrypt.setUseSessionTickets(sslSocket, true);
        }
    }


    private static SSLParameters copySSLParameters(SSLParameters original) {
        SSLParameters copy = new SSLParameters();

        copy.setCipherSuites(original.getCipherSuites());
        copy.setProtocols(original.getProtocols());

        // If your code needs client/server auth:
        copy.setNeedClientAuth(original.getNeedClientAuth());
        copy.setWantClientAuth(original.getWantClientAuth());

        // ALPN in Java 9+:
        if (original.getApplicationProtocols() != null) {
            copy.setApplicationProtocols(original.getApplicationProtocols());
        }

        // SNI, if needed:
        if (original.getServerNames() != null) {
            copy.setServerNames(original.getServerNames());
        }
        if (original.getSNIMatchers() != null) {
            copy.setSNIMatchers(original.getSNIMatchers());
        }

        // If you’re doing custom endpoint identification, etc.:
        copy.setEndpointIdentificationAlgorithm(
                original.getEndpointIdentificationAlgorithm());


        return copy;
    }

}