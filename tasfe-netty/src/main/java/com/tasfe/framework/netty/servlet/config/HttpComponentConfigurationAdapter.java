package com.tasfe.framework.netty.servlet.config;

import java.util.regex.Pattern;

import javax.servlet.ServletException;

import com.tasfe.framework.netty.servlet.impls.ConfigAdapter;
import com.tasfe.framework.netty.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpComponentConfigurationAdapter<Component, Config extends ConfigAdapter> {

    private static final Logger log = LoggerFactory.getLogger(HttpComponentConfigurationAdapter.class);

    private static final String DEFAULT_URL_PATTERN = "/*";

    private String[] sanitizedUrlPatterns;

    private Pattern[] regexPatterns;

    protected Component component;

    protected Config config;

    private boolean initialized = false;

    public HttpComponentConfigurationAdapter(Class<? extends Component> componentClazz) {
        this(Utils.newInstance(componentClazz), DEFAULT_URL_PATTERN);
    }

    public HttpComponentConfigurationAdapter(Class<? extends Component> servletClazz, String... urlPatterns) {
        this(Utils.newInstance(servletClazz), urlPatterns);
    }

    public HttpComponentConfigurationAdapter(Component servlet) {
        this(servlet, DEFAULT_URL_PATTERN);
    }

    @SuppressWarnings("unchecked")
    public HttpComponentConfigurationAdapter(Component component, String... urlPatterns) {
        if (urlPatterns == null || urlPatterns.length == 0)
            throw new IllegalStateException("No url patterns were assigned to http component: " + component);

        this.regexPatterns = new Pattern[urlPatterns.length];
        this.sanitizedUrlPatterns = new String[urlPatterns.length];

        for (int i = 0; i < urlPatterns.length; i++) {
            String regex = urlPatterns[i].replaceAll("\\*", ".*");
            this.regexPatterns[i] = Pattern.compile(regex);
            this.sanitizedUrlPatterns[i] = urlPatterns[i].replaceAll("\\*", "");
            if (this.sanitizedUrlPatterns[i].endsWith("/"))
                this.sanitizedUrlPatterns[i] = this.sanitizedUrlPatterns[i].substring(0,
                        this.sanitizedUrlPatterns[i].length() - 1);
        }

        this.component = component;
        this.config = newConfigInstance((Class<? extends Component>) component.getClass());
    }

    protected abstract Config newConfigInstance(Class<? extends Component> componentClazz);

    public void init() {
        try {

            log.debug("Initializing http component: {}", this.component.getClass());

            this.doInit();
            this.initialized = true;

        } catch (ServletException e) {

            this.initialized = false;
            log.error("Http component '" + this.component.getClass() + "' was not initialized!", e);
        }
    }

    public void destroy() {
        try {

            log.debug("Destroying http component: {}", this.component.getClass());

            this.doDestroy();
            this.initialized = false;

        } catch (ServletException e) {

            this.initialized = false;
            log.error("Http component '" + this.component.getClass() + "' was not destroyed!", e);
        }
    }

    protected abstract void doInit() throws ServletException;

    protected abstract void doDestroy() throws ServletException;

    public boolean matchesUrlPattern(String uri) {
        return getMatchingUrlPattern(uri) != null;
    }

    public String getMatchingUrlPattern(String uri) {
        int indx = uri.indexOf('?');

        String path = indx != -1 ? uri.substring(0, indx) : uri.substring(0);
        if (!path.endsWith("/"))
            path += "/";

        for (int i = 0; i < regexPatterns.length; i++) {
            Pattern pattern = regexPatterns[i];
            if (pattern.matcher(path).matches()) {
                return sanitizedUrlPatterns[i];
            }
        }

        return null;

    }

    protected void addConfigInitParameter(String name, String value) {
        this.config.addInitParameter(name, value);
    }

    public Component getHttpComponent() {
        return this.component;
    }

    public Config getConfig() {
        return this.config;
    }

    public boolean isInitialized() {
        return initialized;
    }

}
