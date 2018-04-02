/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.api.records;

import java.util.Set;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.ApplicationClientProtocol;
import org.apache.hadoop.yarn.util.Records;

/**
 * {@code NodeReport} is a summary of runtime information of a node
 * in the cluster.
 * <p>
 * It includes details such as:
 * <ul>
 *   <li>{@link NodeId} of the node.</li>
 *   <li>HTTP Tracking URL of the node.</li>
 *   <li>Rack name for the node.</li>
 *   <li>Used guaranteed {@link Resource} on the node.</li>
 *   <li>Used opportunistic {@link Resource} on the node.</li>
 *   <li>Total available {@link Resource} of the node.</li>
 *   <li>Number of total running containers on the node.</li>
 *   <li>Number of running guaranteed containers on the node.</li>
 *   <li>Number of running opportunistic containers on the node.</li>
 * </ul>
 *
 * @see ApplicationClientProtocol#getClusterNodes(org.apache.hadoop.yarn.api.protocolrecords.GetClusterNodesRequest)
 */
@Public
@Stable
public abstract class NodeReport {
  
  @Private
  @Unstable
  public static NodeReport newInstance(NodeId nodeId, NodeState nodeState,
      String httpAddress, String rackName, Resource guaranteedUsed,
      Resource capability, int numGuaranteedContainers, String healthReport,
      long lastHealthReportTime) {
    return newInstance(nodeId, nodeState, httpAddress, rackName, guaranteedUsed,
        capability, numGuaranteedContainers, healthReport, lastHealthReportTime,
        null, null, null, null, 0);
  }

  @Private
  @Unstable
  public static NodeReport newInstance(NodeId nodeId, NodeState nodeState,
      String httpAddress, String rackName, Resource guaranteedUsed,
      Resource capability, int numGuaranteedContainers, String healthReport,
      long lastHealthReportTime, Set<String> nodeLabels,
      Integer decommissioningTimeout, NodeUpdateType nodeUpdateType,
      Resource opportunisticUsed, int numOpportunisticContainers) {
    NodeReport nodeReport = Records.newRecord(NodeReport.class);
    nodeReport.setNodeId(nodeId);
    nodeReport.setNodeState(nodeState);
    nodeReport.setHttpAddress(httpAddress);
    nodeReport.setRackName(rackName);
    nodeReport.setCapability(capability);
    nodeReport.setGuaranteedResourceUsed(guaranteedUsed);
    nodeReport.setNumGuaranteedContainers(numGuaranteedContainers);
    nodeReport.setHealthReport(healthReport);
    nodeReport.setLastHealthReportTime(lastHealthReportTime);
    nodeReport.setNodeLabels(nodeLabels);
    nodeReport.setDecommissioningTimeout(decommissioningTimeout);
    nodeReport.setNodeUpdateType(nodeUpdateType);
    nodeReport.setOpportunisticResourceUsed(opportunisticUsed);
    nodeReport.setNumOpportunisticContainers(numOpportunisticContainers);
    return nodeReport;
  }

  /**
   * Get the <code>NodeId</code> of the node.
   * @return <code>NodeId</code> of the node
   */
  @Public
  @Stable
  public abstract NodeId getNodeId();
  
  @Private
  @Unstable
  public abstract void setNodeId(NodeId nodeId);
  
  /**
   * Get the <code>NodeState</code> of the node.
   * @return <code>NodeState</code> of the node
   */
  @Public
  @Stable
  public abstract NodeState getNodeState();
  
  @Private
  @Unstable
  public abstract void setNodeState(NodeState nodeState);
  
  /**
   * Get the <em>http address</em> of the node.
   * @return <em>http address</em> of the node
   */
  @Public
  @Stable
  public abstract String getHttpAddress();
  
  @Private
  @Unstable
  public abstract void setHttpAddress(String httpAddress);
  
  /**
   * Get the <em>rack name</em> for the node.
   * @return <em>rack name</em> for the node
   */
  @Public
  @Stable
  public abstract String getRackName();
  
  @Private
  @Unstable
  public abstract void setRackName(String rackName);
  
  /**
   * Get <em>guaranteed</em> <code>Resource</code> used on the node.
   * @return <em>guaranteed</em> <code>Resource</code> used on the node
   */
  @Public
  @Stable
  @Deprecated
  public abstract Resource getUsed();
  
  @Private
  @Unstable
  @Deprecated
  public abstract void setUsed(Resource used);

  /**
   * Get <em>guaranteed</em> <code>Resource</code> used on the node.
   * @return <em>guaranteed</em> <code>Resource</code> used on the node
   */
  @Public
  @Unstable
  public abstract Resource getGuaranteedResourceUsed();

  @Private
  @Unstable
  public abstract void setGuaranteedResourceUsed(Resource guaranteed);

  /**
   * Get <em>opportunistic</em> <code>Resource</code> used on the node.
   * @return <em>opportunistic</em> <code>Resource</code> used on the node
   */
  @Public
  @Unstable
  public abstract Resource getOpportunisticResourceUsed();

  @Private
  @Unstable
  public abstract void setOpportunisticResourceUsed(Resource opportunistic);

  /**
   * Get the <em>total</em> <code>Resource</code> on the node.
   * @return <em>total</em> <code>Resource</code> on the node
   */
  @Public
  @Stable
  public abstract Resource getCapability();
  
  @Private
  @Unstable
  public abstract void setCapability(Resource capability);
  
  /**
   * Get the <em>number of guaranteed containers</em> allocated on the node.
   * @return <em>number of guaranteed containers</em> allocated on the node
   */
  @Private
  @Unstable
  public abstract int getNumGuaranteedContainers();
  
  @Private
  @Unstable
  public abstract void setNumGuaranteedContainers(int numContainers);

  /**
   * Get the <em>number of opportunistic containers</em> allocated on the node.
   * @return <em>number of opportunistic containers</em> allocated on the node
   */
  @Private
  @Unstable
  public abstract int getNumOpportunisticContainers();

  @Private
  @Unstable
  public abstract void setNumOpportunisticContainers(int numContainers);

  /**
   * Get the <em>number of containers</em> allocated on the node.
   * @return <em>number of containers</em> allocated on the node
   */
  @Private
  @Unstable
  public int getNumTotalContainers() {
    return getNumGuaranteedContainers() + getNumOpportunisticContainers();
  }

  /**
   * Get the <em>diagnostic health report</em> of the node.
   * @return <em>diagnostic health report</em> of the node
   */
  @Public
  @Stable
  public abstract String getHealthReport();

  @Private
  @Unstable
  public abstract void setHealthReport(String healthReport);

  /**
   * Get the <em>last timestamp</em> at which the health report was received.
   * @return <em>last timestamp</em> at which the health report was received
   */
  @Public
  @Stable
  public abstract long getLastHealthReportTime();

  @Private
  @Unstable
  public abstract void setLastHealthReportTime(long lastHealthReport);
  
  /**
   * Get labels of this node.
   * @return labels of this node.
   */
  @Public
  @Stable
  public abstract Set<String> getNodeLabels();
  
  @Private
  @Unstable
  public abstract void setNodeLabels(Set<String> nodeLabels);

  /**
   * Get containers aggregated resource utilization in a node.
   * @return containers resource utilization.
   */
  @Public
  @Stable
  public ResourceUtilization getAggregatedContainersUtilization() {
    throw new UnsupportedOperationException(
        "subclass must implement this method");
  }

  @Private
  @Unstable
  public void setAggregatedContainersUtilization(ResourceUtilization
      containersUtilization) {
    throw new UnsupportedOperationException(
        "subclass must implement this method");
  }

  /**
   * Get node resource utilization.
   * @return node resource utilization.
   */
  @Public
  @Stable
  public abstract ResourceUtilization getNodeUtilization();

  @Private
  @Unstable
  public abstract void setNodeUtilization(ResourceUtilization nodeUtilization);

  /**
   * Optional decommissioning timeout in seconds (null indicates absent
   * timeout).
   * @return the decommissioning timeout in second.
   */
  public Integer getDecommissioningTimeout() {
    return null;
  }

  /**
   * Set the decommissioning timeout in seconds (null indicates absent timeout).
   * */
  public void setDecommissioningTimeout(Integer decommissioningTimeout) {}

  /**
   * Optional node update type (null indicates absent update type).
   * @return the node update.
   */
  public NodeUpdateType getNodeUpdateType() {
    return NodeUpdateType.NODE_UNUSABLE;
  }

  /**
   * Set the node update type (null indicates absent node update type).
   * */
  public void setNodeUpdateType(NodeUpdateType nodeUpdateType) {}
}
