package com.topsec.bdc.platform.core.services;

/**
 * @author baiyanwei
 * 
 *         Jan 1, 2014
 * 
 */
public interface IConfiguration {

    public static final String ID_CONF_TITLE = "id";
    public static final String NAME_CONF_TITLE = "name";
    public static final String DESCRIPTION_CONF_TITLE = "description";
    public static final String IMPLEMENT_CLASS_CONF_TITLE = "implement_class";
    public static final String PROPERTY_CONF_TITLE = "property";
    public static final String PROPERTY_KEY_CONF_TITLE = "name";
    public static final String PROPERTY_VALUE_CONF_TITLE = "value";

    /**
     * @param id
     */
    public void setID(String id);

    /**
     * @return
     */
    public String getID();

    /**
     * @param name
     */
    public void setName(String name);

    /**
     * @return
     */
    public String getName();

    /**
     * @param description
     */
    public void setDescription(String description);

    /**
     * @return
     */
    public String getDescription();

}
