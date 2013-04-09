package play.modules.coalmine;

import com.coalmine.connector.Connector;
import com.coalmine.connector.SimpleConnector;
import com.coalmine.connector.notification.LoggedNotification;
import com.coalmine.connector.play.v1.HttpRequest;
import play.Logger;
import play.Play;
import play.PlayPlugin;
import play.mvc.Http.Request;

import java.util.Map;

public class CoalminePlugin extends PlayPlugin {
    private static final String PROPERTY_ENABLED_ENVIRONMENTS = "coalmine.enabledEnvironments";
    private static final String PROPERTY_ENVIRONMENT = "coalmine.environment";
    private static final String PROPERTY_SIGNATURE = "coalmine.signature";
    private static final String PROPERTY_TIMEOUT = "coalmine.timeout";
    private static final String PROPERTY_URL = "coalmine.url";
    private static final String PROPERTY_VERSION = "coalmine.version";
    
    private static final String DEFAULT_ENABLED_ENVIRONMENTS = "production,staging";
    private static final String DEFAULT_URL = "https://coalmineapp.com/notify";
    private static final String DEFAULT_TIMEOUT = "5000";
    private static final String DEFAULT_VERSION = "1.0.0";
    
    private Connector connector;
    
    @Override
    public void onConfigurationRead() {
        String signature = Play.configuration.getProperty(PROPERTY_SIGNATURE);
        
        if (signature == null) {
            return; // Not configured properly
        }
        
        String environment = null;
        if (Play.mode.isProd()) {
            environment = "production";
        } else if (Play.mode.isDev()) {
            environment = "development";
        } else {
            environment = Play.configuration.getProperty(PROPERTY_ENVIRONMENT);
        }
        
        String enabledEnvironmentsString = Play.configuration.getProperty(
            PROPERTY_ENABLED_ENVIRONMENTS, DEFAULT_ENABLED_ENVIRONMENTS);
        String[] enabledEnvironments = enabledEnvironmentsString.split(",");
        
        String timeoutString = Play.configuration.getProperty(
            PROPERTY_TIMEOUT, DEFAULT_TIMEOUT);
        int timeout = Integer.parseInt(timeoutString);
        
        String url = Play.configuration.getProperty(
            PROPERTY_URL, DEFAULT_URL);
        
        String version = Play.configuration.getProperty(
            PROPERTY_VERSION, DEFAULT_VERSION);
        
        this.connector = new SimpleConnector(signature);
        this.connector.setApplicationEnvironment(environment);
        this.connector.setTimeout(timeout);
        this.connector.setUrl(url);
        this.connector.setVersion(version);
        
        for (String enabledEnvironment : enabledEnvironments) {
            this.connector.addEnabledEnvironment(enabledEnvironment);
        }
    }
    
    @Override
    public void onInvocationException(Throwable e) {
        this.notify(e);
    }
    
    public void notify(Throwable e) {
        if (this.connector == null) {
            Logger.warn("Cannot report error to Coalmine: '%s' must be set in application.conf", PROPERTY_SIGNATURE);
            return;
        }
        
        LoggedNotification notification = new LoggedNotification(e);
        HttpRequest coalmineRequest = new HttpRequest(Request.current());
        notification.setRequest(coalmineRequest);
        this.connector.send(notification);
    }
}
