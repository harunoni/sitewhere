/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.grpc.client.spi;

import java.util.List;

import com.sitewhere.grpc.client.ApiChannel;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.server.lifecycle.ITenantEngineLifecycleComponent;

/**
 * Demulitiplexes API calls across multiple {@link IApiChannel} in order to
 * provide high availability and increased throughput.
 * 
 * @author Derek
 */
@SuppressWarnings("rawtypes")
public interface IApiDemux<T extends IApiChannel> extends ITenantEngineLifecycleComponent {

    /**
     * Get microservice identifier that will trigger creation of an API channel.
     * 
     * @return
     */
    public String getTargetIdentifier();

    /**
     * List of available {@link IApiChannel} that can be used for routing.
     * 
     * @return
     */
    public List<T> getApiChannels();

    /**
     * Get an API channel based on the routing strategy.
     * 
     * @return
     */
    public T getApiChannel();

    /**
     * Wait for an API channel to become available.
     * 
     * @return
     */
    public T waitForApiChannel();

    /**
     * Create an API channel to the given host.
     * 
     * @param host
     * @return
     * @throws SiteWhereException
     */
    public T createApiChannel(String host) throws SiteWhereException;

    /**
     * Initialize a new API channel for the given host.
     * 
     * @param host
     * @throws SiteWhereException
     */
    public void initializeApiChannel(String host) throws SiteWhereException;

    /**
     * Remove API channel for the given host.
     * 
     * @param host
     * @throws SiteWhereException
     */
    public T removeApiChannel(String host) throws SiteWhereException;

    /**
     * Get strategy used for demulitplexing API calls across multiple
     * {@link ApiChannel}.
     * 
     * @return
     */
    public IApiDemuxRoutingStrategy getRoutingStrategy();
}