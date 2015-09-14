package com.topsec.bdc.platform.core.node;

import com.topsec.bdc.platform.core.metrics.AbstractMetricMBean;
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
public class NodeService extends AbstractMetricMBean implements IService, INode {

    /**
     * theLogger.
     */
    private static PlatformLogger _logger = PlatformLogger.getLogger(NodeService.class);

    public String nodeName = "";

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {

    }

    @Override
    public void registerNode() {

        // TODO Auto-generated method stub

    }

    @Override
    public void unregisterNode() {

        // TODO Auto-generated method stub

    }

    @Override
    public String getNodeRegion() {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String setNodeRegion(String region) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNodeID() {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setNodeID(String nodeID) {

        // TODO Auto-generated method stub

    }

    @Override
    public void sendHeartBeat() throws Exception {

        // TODO Auto-generated method stub

    }

}
