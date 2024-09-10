package com.example.blockchain.network;

import com.example.blockchain.blockchain.Block;
import com.example.blockchain.blockchain.Blockchain;
import com.example.blockchain.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * The Node class represents a single node in the blockchain's peer-to-peer (P2P) network.
 * Each node maintains its own copy of the blockchain and communicates with other nodes to share transactions and blocks.
 */
public class Node {

    // A unique identifier for the node (e.g., a public key or node address)
    private final String nodeId;

    // The blockchain that this node manages
    private final Blockchain blockchain;

    // The list of peer nodes that this node is connected to
    private final List<Node> peerNodes;

    /**
     * Constructor for the Node class.
     * Initializes the node with a unique ID and creates a blockchain for the node to manage.
     *
     * @param nodeId The unique identifier for the node (e.g., node address or public key)
     * @param blockchain The blockchain that this node will manage
     */
    public Node(String nodeId, Blockchain blockchain) {
        this.nodeId = nodeId;
        this.blockchain = blockchain;
        this.peerNodes = new ArrayList<>();
    }

    /**
     * Adds a peer node to the list of connected peer nodes.
     * This allows the node to communicate and share data with other nodes.
     *
     * @param peerNode The peer node to connect to
     */
    public void addPeerNode(Node peerNode) {
        if (!peerNodes.contains(peerNode)) {
            peerNodes.add(peerNode);
            System.out.println("Node " + nodeId + " connected to peer node " + peerNode.getNodeId());
        }
    }

    /**
     * Broadcasts a new block to all connected peer nodes.
     * This ensures that all nodes in the network are kept up to date with the latest blocks.
     *
     * @param block The new block to broadcast
     */
    public void broadcastBlock(Block block) {
        for (Node peerNode : peerNodes) {
            peerNode.receiveBlock(block);
        }
    }

    /**
     * Receives a block from another node.
     * The node adds the block to its own blockchain if it is valid.
     *
     * @param block The block received from a peer node
     */
    public void receiveBlock(Block block) {
        if (blockchain.isChainValid()) {
            blockchain.addBlock(block);
            System.out.println("Node " + nodeId + " added block from peer.");
        } else {
            System.out.println("Node " + nodeId + " rejected invalid block.");
        }
    }

    /**
     * Broadcasts a transaction to all connected peer nodes.
     * This allows the transaction to be included in blocks across the network.
     *
     * @param transaction The transaction to broadcast
     */
    public void broadcastTransaction(Transaction transaction) {
        for (Node peerNode : peerNodes) {
            peerNode.receiveTransaction(transaction);
        }
    }

    /**
     * Receives a transaction from another node.
     * The transaction can later be included in a block.
     *
     * @param transaction The transaction received from a peer node
     */
    public void receiveTransaction(Transaction transaction) {
        // In a full implementation, the node would add the transaction to a transaction pool to be included in a block
        System.out.println("Node " + nodeId + " received transaction: " + transaction.toString());
    }

    /**
     * Retrieves the unique ID of the node.
     *
     * @return The node's unique identifier.
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Retrieves the list of peer nodes connected to this node.
     *
     * @return The list of peer nodes.
     */
    public List<Node> getPeerNodes() {
        return peerNodes;
    }

    /**
     * Retrieves the blockchain managed by this node.
     *
     * @return The blockchain managed by the node.
     */
    public Blockchain getBlockchain() {
        return blockchain;
    }
}
