package com.topsec.bdc.platform.core.message;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

import com.topsec.bdc.platform.core.services.IService;
import com.topsec.bdc.platform.core.services.ServiceInfo;
import com.topsec.bdc.platform.log.PlatformLogger;


/**
 * 
 * Message formatter.
 * 
 * This class provides functionality that makes it easy to use resource bundles and Message Formatters to create strings. NO ONE should ever use a hard coded string concatenation with in the router.
 * 
 * @title MessagePreparer
 * @package com.byw.dalek.platform.core.message
 * @author baiyanwei
 * @version
 * @date Feb 18, 2015
 * 
 */
@ServiceInfo(description = "Provides the ability to merge extension properties with configured properties.")
public class MessagePreparerService implements IService {

    /**
     * theLogger.
     */
    private static PlatformLogger _logger = PlatformLogger.getLogger(MessagePreparerService.class);

    /**
     * The suffix string of message formatter file.
     */
    private static final String SUFFIX = "Res.properties";

    private static final String CLASS_SPLITE = ".";

    /**
     * The target class cache.
     */
    private static HashMap<Class<?>, PropertyResourceBundle> _classBundlesMap = new HashMap<Class<?>, PropertyResourceBundle>();

    /**
     * The message formatter cache.
     */
    private static HashMap<String, MessageFormat> _propertyFormattersMap = new HashMap<String, MessageFormat>();

    @Override
    public void start() throws Exception {

        _logger.info("startService", "MessagePreparerService");

    }

    @Override
    public void stop() throws Exception {

        _logger.info("stopService", "MessagePreparerService");

    }

    /**
     * according to the class and key ,format the messages, the configuration will be defined into property file which name is suffix with Res.properties
     * 
     * @param clazz
     * @param key
     * @param arguments
     * @return
     */
    public String format(Class<?> clazz, String key, Object... arguments) {

        return formatMessageInCache(clazz, key, arguments);
    }

    //
    /**
     * Format the message from cache.
     * 
     * @param clazz
     * @param key
     * @param arguments
     * @return
     */
    private String formatMessageInCache(Class<?> clazz, String key, Object... arguments) {

        String formatterKey = createFormatterKey(clazz, key);

        MessageFormat messageFormatter = _propertyFormattersMap.get(formatterKey);

        // If the formatter doesn't exist then we want to create a new one. First see if we loaded the bundle and then create the formatter.
        if (messageFormatter == null) {
            // Load the resource Bundle.
            PropertyResourceBundle bundle = _classBundlesMap.get(clazz);
            if (bundle == null) {
                bundle = loadResourceBundle(clazz);
            }
            if (bundle == null)
                return "";

            // Load the property from the bundle using the supplied key.
            String value = null;

            try {
                value = bundle.getString(key);
                if (value == null) {
                    return "";
                }
            } catch (MissingResourceException e) {
                _logger.error("missingProperty", clazz.getName(), key);
                return "";
            }

            // Create the message formatter and format the message.
            messageFormatter = new MessageFormat(value);
            _propertyFormattersMap.put(formatterKey, messageFormatter);
        }

        return messageFormatter.format(arguments);
    }

    /**
     * This method will use the supplied class to load the resource bundle and add it to the HashMap.
     * 
     * @param clazz
     * @return
     */
    private PropertyResourceBundle loadResourceBundle(Class<?> clazz) {

        InputStream inputStream = null;
        try {
            inputStream = clazz.getResourceAsStream(clazz.getSimpleName() + SUFFIX);
            //
            if (inputStream != null) {
                PropertyResourceBundle bundle = new PropertyResourceBundle(inputStream);
                synchronized (_classBundlesMap) {
                    _classBundlesMap.put(clazz, bundle);
                }
                return bundle;
            } else {
                _logger.error("loadingBundleError", clazz.getSimpleName(), SUFFIX);
            }
        } catch (IOException e) {
            _logger.exception(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    /**
     * This method simply creates a key based on the class name and the key. Used to pull and place stuff in the _propertyFormatters Object.
     * 
     * @param clazz
     * @param key
     * @return
     */
    private String createFormatterKey(Class<?> clazz, String key) {

        return clazz.getName() + CLASS_SPLITE + key;
    }

}
