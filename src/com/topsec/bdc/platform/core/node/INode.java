package com.topsec.bdc.platform.core.node;

/**
 * @author baiyanwei
 * 
 *         Oct 22, 2013
 * 
 *         node's behavior. one node is one application in platform.
 * 
 */
public interface INode {

    /**
     * register node to console.
     */
    public void registerNode();

    /**
     * unregister node to console.
     */
    public void unregisterNode();

    /**
     * get region of node
     * 
     * @return
     */
    public String getNodeRegion();

    /**
     * set region to node.
     * 
     * @param region
     * @return
     */
    public String setNodeRegion(String region);

    /**
     * get node's ID.
     * 
     * @return
     */
    public String getNodeID();

    /**
     * set node's ID.
     * 
     * @param nodeID
     */
    public void setNodeID(String nodeID);

    /**
     * Your Methods description is in here.
     * 
     */
    public void sendHeartBeat() throws Exception;
}
