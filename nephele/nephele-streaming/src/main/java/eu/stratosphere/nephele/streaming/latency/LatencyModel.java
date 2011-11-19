package eu.stratosphere.nephele.streaming.latency;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.nephele.executiongraph.ExecutionGraph;
import eu.stratosphere.nephele.executiongraph.ExecutionGroupVertex;
import eu.stratosphere.nephele.streaming.PathLatency;

public class LatencyModel {
	
	private static Log LOG = LogFactory.getLog(LatencyModel.class);  
	
	private ExecutionGraph executionGraph;
	
	private LatencySubgraph latencySubgraph;

	public LatencyModel(ExecutionGraph executionGraph) {
		this.executionGraph = executionGraph;
		
		// FIXME naive implementation until we can annotate the job
		// subgraphStart and subgraphEnd should be derived from the annotations
		ExecutionGroupVertex subgraphStart = this.executionGraph.getInputVertex(0).getGroupVertex();
		ExecutionGroupVertex subgraphEnd = this.executionGraph.getOutputVertex(0).getGroupVertex();
		
		this.latencySubgraph = new LatencySubgraph(executionGraph, subgraphStart, subgraphEnd);
	}
	
	public void refreshEdgeLatency(PathLatency pathLatency) {
		LOG.info("Received " + pathLatency);
	}
}
