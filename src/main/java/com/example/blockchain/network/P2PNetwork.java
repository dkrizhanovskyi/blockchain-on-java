package com.example.blockchain.network;

import java.util.HashMap;
import java.util.Map;
import com.example.blockchain.blockchain.Block;
import com.example.blockchain.transactions.Transaction;

/**
 * The P2PNetwork class manages the overall peer-to-peer (P2P) network in the blockchain system.
 * It allows nodes to join the network, connect to peers, and facilitates communication between nodes.
 * This class represents a decentralized network of nodes that maintain the blockchain.
 */
public class P2PNetwork {

    // A map of all nodes in the network (key: node ID, value: Node object)
    private final Map<String, Node> nodesInNetwork;

    /**
     * Constructor for the P2PNetwork class.
     * Initializes an empty network of nodes.
     */
    public P2PNetwork() {
        this.nodesInNetwork = new HashMap<>();
    }

    /**
     * Adds a new node to the P2P network.
     * If the node already exists, it will not be added again.
     *
     * @param node The node to be added to the network
     */
    public void addNodeToNetwork(Node node) {
        if (!nodesInNetwork.containsKey(node.getNodeId())) {
            nodesInNetwork.put(node.getNodeId(), node);
            System.out.println("Node " + node.getNodeId() + " added to the network.");
        } else {
            System.out.println("Node " + node.getNodeId() + " already exists in the network.");
        }
    }

    /**
     * Connects two nodes in the network.
     * This establishes a peer-to-peer connection between them, allowing communication.
     *
     * @param nodeId1 The ID of the first node
     * @param nodeId2 The ID of the second node
     */
    public void connectNodes(String nodeId1, String nodeId2) {
        Node node1 = nodesInNetwork.get(nodeId1);
        Node node2 = nodesInNetwork.get(nodeId2);

        if (node1 != null && node2 != null) {
            node1.addPeerNode(node2);  // Add node2 as a peer of node1
            node2.addPeerNode(node1);  // Add node1 as a peer of node2
            System.out.println("Nodes " + nodeId1 + " and " + nodeId2 + " are now connected.");
        } else {
            System.out.println("One or both nodes do not exist in the network.");
        }
    }

    /**
     * Broadcasts a block to all nodes in the network.
     * This ensures that all nodes receive the new block and add it to their local blockchain.
     *
     * @param block The block to be broadcast to all nodes
     */
    public void broadcastBlockToNetwork(Block block) {
        for (Node node : nodesInNetwork.values()) {
            node.broadcastBlock(block);  // Each node broadcasts the block to its peers
        }
    }

    /**
     * Broadcasts a transaction to all nodes in the network.
     * This ensures that the transaction can be processed by any node and included in a block.
     *
     * @param transaction The transaction to be broadcast to all nodes
     */
    public void broadcastTransactionToNetwork(Transaction transaction) {
        for (Node node : nodesInNetwork.values()) {
            node.broadcastTransaction(transaction);  // Each node broadcasts the transaction to its peers
        }
    }

    /**
     * Displays all the nodes currently in the network.
     * This can be used for debugging or network visualization purposes.
     */
    public void displayNetworkNodes() {
        System.out.println("Nodes in the P2P network:");
        for (String nodeId : nodesInNetwork.keySet()) {
            System.out.println(" - Node ID: " + nodeId);
        }
    }

    /**
     * Retrieves the list of all nodes in the network.
     *
     * @return A map of node IDs and their corresponding Node objects.
     */
    public Map<String, Node> getNodesInNetwork() {
        return nodesInNetwork;
    }
}
