package bot;

public class ProxyInfo {
    public String proxyAddress;
    public int proxyPort;
    public String proxyUsername;
    public String proxyPassword;
    public ProxyInfo(String proxyAddress, int proxyPort, String proxyUsername, String proxyPassword) {
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.proxyUsername = proxyUsername;
        this.proxyPassword = proxyPassword;
    }


}
