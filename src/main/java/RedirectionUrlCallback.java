import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;

/**
 * Created by niuxianghui on 17/3/19.
 */
class RedirectionUrlCallback implements IAutodiscoverRedirectionUrl {
    public boolean autodiscoverRedirectionUrlValidationCallback(
            String redirectionUrl) {
        return redirectionUrl.toLowerCase().startsWith("https://");
    }
}
