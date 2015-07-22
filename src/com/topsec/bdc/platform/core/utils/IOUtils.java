package com.topsec.bdc.platform.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;

import com.topsec.bdc.platform.core.configuration.IExtensionHandler;
import com.topsec.bdc.platform.core.exception.PlatformException;
import com.topsec.bdc.platform.log.PlatformLogger;


/**
 * @author baiyanwei
 * 
 *         Jul 13, 2013
 * 
 *         This is the platform general utility class.
 */
final public class IOUtils {

    //
    // theLogger
    //
    final private static PlatformLogger _logger = PlatformLogger.getLogger(IOUtils.class);

    final private static int BUFFER_SIZE = 2048;

    /**
     * This will turn an input stream into a String Buffer.
     * 
     * @param inputStream
     * @return
     */
    public static StringBuffer getInputStream2StringBuffer(InputStream inputStream) {

        StringBuffer stringBuffer = new StringBuffer();
        char[] cbuf = new char[BUFFER_SIZE];
        int len = 0;
        InputStreamReader utf8 = null;
        try {
            utf8 = new InputStreamReader(inputStream, Constants.UTF_8);
            while ((len = utf8.read(cbuf)) > 0) {
                stringBuffer.append(cbuf, 0, len);
            }
        } catch (UnsupportedEncodingException e) {
            _logger.exception(e);
        } catch (IOException e) {
            _logger.exception(e);
        } finally {
            if (utf8 != null) {
                try {
                    utf8.close();
                } catch (IOException e) {
                }
            }
        }
        return stringBuffer;
    }

    /**
     * Use the standard java GZip functionality.
     * 
     * returns a byte array of the compress data.
     * 
     * @param inputBytes
     * @return
     */
    public static byte[] compress(byte[] inputBytes) {

        ByteArrayOutputStream bytesOutputStream = null;
        GZIPOutputStream gZIPOutputStream = null;
        try {
            bytesOutputStream = new ByteArrayOutputStream();
            gZIPOutputStream = new GZIPOutputStream(bytesOutputStream);

            gZIPOutputStream.write(inputBytes, 0, inputBytes.length);
            return bytesOutputStream.toByteArray();
        } catch (IOException e) {
            _logger.exception(e);
        } finally {
            if (bytesOutputStream != null) {
                try {
                    bytesOutputStream.close();
                } catch (IOException e) {
                }
            }
            if (gZIPOutputStream != null) {
                try {
                    gZIPOutputStream.close();
                } catch (IOException e) {
                }
            }

        }
        return null;
    }

    /**
     * Uncompress a byte array of data
     * 
     * @param inputBytes
     * @return an uncompressed byte array.
     */
    public static byte[] uncompress(byte[] inputBytes) {

        ByteArrayInputStream bytesInputStream = null;
        GZIPInputStream gzipInputStream = null;
        ByteArrayOutputStream bytesOutputStream = null;
        try {
            bytesInputStream = new ByteArrayInputStream(inputBytes);
            gzipInputStream = new GZIPInputStream(bytesInputStream);
            bytesOutputStream = new ByteArrayOutputStream();

            byte[] bytes = new byte[BUFFER_SIZE];
            int len = 0;

            while ((len = gzipInputStream.read(bytes)) > 0) {
                bytesOutputStream.write(bytes, 0, len);
            }

            return bytesOutputStream.toByteArray();
        } catch (IOException e) {
            _logger.exception(e);
        } finally {
            if (gzipInputStream != null) {
                try {
                    gzipInputStream.close();
                } catch (IOException e) {
                }
            }
            if (bytesInputStream != null) {
                try {
                    bytesInputStream.close();
                } catch (IOException e) {
                }
            }
            if (bytesOutputStream != null) {
                try {
                    bytesOutputStream.close();
                } catch (IOException e) {
                }
            }

        }
        return null;
    }

    /**
     * traverse each extension and handle it.
     * 
     * @param extPnt
     *            extension point identifier
     * @param handler
     * @throws PlatformException
     * 
     * 
     */
    public static void traverseExtension(final String extPnt, IExtensionHandler handler) throws PlatformException {

        IExtensionRegistry registry = RegistryFactory.getRegistry();
        IExtensionPoint point = registry.getExtensionPoint(extPnt);
        if (point == null) {
            throw new PlatformException("The extension POINT for " + extPnt + " could not be found!!");
        }

        IExtension[] extensions = point.getExtensions();
        if (extensions == null) {
            throw new PlatformException("No extensions for " + extPnt + " could not be found!!");
        }

        for (int index = 0; index < extensions.length; index++) {
            IConfigurationElement[] elements = extensions[index].getConfigurationElements();
            for (int elementIndex = 0; elementIndex < elements.length; elementIndex++) {
                IConfigurationElement configurationElement = elements[elementIndex];
                // This is where we create the server object that will be
                handler.extend(configurationElement);
            }
        }

    }

    /**
     * Tests whether or not the url has a http or https prefix, if it does returns true else returns false.
     * 
     * @param url
     * @return
     */
    public static boolean testHttpPrefix(String url) {

        return url.toLowerCase().startsWith("http://") == true || url.toLowerCase().startsWith("https://") == true;
    }
}
