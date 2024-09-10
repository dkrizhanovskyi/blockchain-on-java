# Blockchain-on-Java

### Version: 1.1.0  
**Author**: Daniil Krizhanovskiyi  
**Date**: September 2024  

## Project Overview

Blockchain-on-Java is a basic blockchain system built using Java. The project supports two consensus algorithms: **Proof of Work (PoW)** and **Proof of Stake (PoS)**. The blockchain is decentralized, allowing multiple nodes to communicate through a peer-to-peer (P2P) network, propagate blocks, and process transactions securely.

---

## Project Structure

The project follows a modular architecture adhering to **SOLID** principles for maintainability and scalability.

```bash
blockchain-on-java
├── pom.xml                  # Maven configuration file
├── README.md                # Project documentation
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── example
│   │               └── blockchain
│   │                   ├── blockchain
│   │                   │   ├── Block.java        # Core blockchain block class
│   │                   │   ├── Blockchain.java   # Blockchain management class
│   │                   ├── consensus
│   │                   │   ├── Consensus.java    # Consensus algorithm interface
│   │                   │   ├── PoWConsensus.java # Proof of Work consensus implementation
│   │                   │   ├── PoSConsensus.java # Proof of Stake consensus implementation
│   │                   ├── cryptography
│   │                   │   ├── CryptoUtil.java   # Utility for cryptographic functions (signing, hashing)
│   │                   │   ├── StringUtil.java   # SHA-256 hashing utility
│   │                   ├── network
│   │                   │   ├── Node.java         # Peer-to-peer node implementation
│   │                   │   ├── P2PNetwork.java   # Network management for nodes
│   │                   ├── transactions
│   │                   │   ├── Transaction.java  # Transaction representation
│   └── test
│       └── java
│           └── com
│               └── blockchain
│                   ├── BlockTest.java            # Unit tests for the Block class
│                   ├── BlockchainTest.java       # Unit tests for the Blockchain class
│                   ├── ConsensusTest.java        # Unit tests for PoW and PoS consensus mechanisms
│                   ├── TransactionTest.java      # Unit tests for the Transaction class
```

---

## Core Components

### 1. **Blockchain**
The `Blockchain.java` class manages the blockchain, which includes adding blocks, validating the chain's integrity, and mining blocks according to the consensus algorithm in use.

### 2. **Block**
The `Block.java` class represents a block in the blockchain. It contains:
- A list of transactions.
- A timestamp.
- A nonce for mining.
- A hash generated using **SHA-256**.

### 3. **Transactions**
The `Transaction.java` class represents a transaction between two entities in the blockchain. It includes:
- **Sender**: The sender's address.
- **Recipient**: The recipient's address.
- **Amount**: The value being transferred.

---

## Consensus Mechanisms

### 1. **Proof of Work (PoW)**
`PoWConsensus.java` implements the Proof of Work algorithm. Miners must solve a cryptographic puzzle to create a new block by finding a nonce that satisfies the block's hash difficulty.

### 2. **Proof of Stake (PoS)**
`PoSConsensus.java` implements the Proof of Stake algorithm, where validators are selected to mine blocks based on the amount of cryptocurrency they hold (their stake).

---

## Cryptography

### 1. **SHA-256 Hashing**
`StringUtil.java` provides SHA-256 hashing functionality used to generate block and transaction hashes.

### 2. **Digital Signatures**
`CryptoUtil.java` implements digital signatures using RSA, allowing transactions to be securely signed by the sender and verified by others.

---

## Peer-to-Peer (P2P) Network

### 1. **Node**
`Node.java` represents a node in the blockchain's P2P network. Nodes can:
- Connect to peer nodes.
- Broadcast transactions and blocks.
- Receive transactions and blocks from other nodes.

### 2. **P2PNetwork**
`P2PNetwork.java` manages the decentralized network of nodes, allowing them to communicate and synchronize the blockchain.

---

## Installation and Setup

### 1. **Prerequisites**
- **Java 17** or later
- **Maven** for dependency management

### 2. **Build the Project**
To build the project, run the following command:
```bash
mvn clean install
```

### 3. **Run the Application**
To run the blockchain simulation, navigate to `Blockchain.java` in the `src/main/java/com/example/blockchain/blockchain/` directory and execute it.

---

## Running Tests

The project includes comprehensive unit tests to ensure correctness. Tests cover blockchain functionality, consensus mechanisms, transaction handling, and networking.

To run the tests:
```bash
mvn test
```

---

## Future Enhancements

### 1. **Smart Contracts**
Implement smart contracts on top of the blockchain for automated execution of agreements.

### 2. **Scalability**
Introduce Layer-2 solutions, such as sharding, to improve the scalability of the blockchain.

### 3. **Zero-Knowledge Proofs (ZKP)**
Add ZKPs to improve privacy and allow for the validation of transactions without revealing details.

---

## Conclusion

Blockchain-on-Java is a foundational project designed to demonstrate core blockchain principles. It can be extended with advanced features such as smart contracts, dynamic consensus adjustments, and improved cryptography to support real-world use cases.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


