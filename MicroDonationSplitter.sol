// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

/**
 * @title MicroDonationSplitter
 * @dev Automated micro-donation splitting contract for distributing donations
 *      among multiple registered NGOs based on predefined allocation percentages.
 * @author Assignment Submission
 * @notice This contract enables transparent, automated donation distribution
 */

contract MicroDonationSplitter {
    // ========== DATA STRUCTURES ==========

    /// @dev Structure to represent an NGO beneficiary
    struct NGO {
        address wallet;              // NGO's Ethereum wallet address
        uint256 allocationPercent;   // Percentage of donations (0-100)
        uint256 accumulatedBalance;  // Total funds owed to NGO
        bool isActive;               // Whether NGO is currently receiving donations
        uint256 registrationTime;    // When NGO was registered
    }

    /// @dev Structure to track individual donations
    struct DonationRecord {
        address donor;               // Address of donor
        uint256 totalAmount;         // Total donation amount in wei
        uint256 timestamp;           // Block timestamp of donation
        bool processed;              // Whether funds were distributed
    }

    // ========== STATE VARIABLES ==========

    address public owner;                              // Contract owner/admin
    uint256 public totalDonationsReceived;             // Cumulative donations
    uint256 public totalDistributed;                   // Cumulative distributions

    // Mapping of NGO wallet address to NGO data
    mapping(address => NGO) public ngoRegistry;
    address[] public ngoList;                          // List of all registered NGOs

    // Mapping to track donation history
    mapping(uint256 => DonationRecord) public donations;
    uint256 public donationCount;

    // ========== EVENTS ==========

    /// @notice Emitted when a new NGO is registered
    event NGORegistered(
        address indexed ngoWallet,
        uint256 allocationPercent,
        uint256 timestamp
    );

    /// @notice Emitted when NGO allocation percentages are updated
    event AllocationUpdated(
        address indexed ngoWallet,
        uint256 newAllocationPercent,
        uint256 timestamp
    );

    /// @notice Emitted when a donation is received
    event DonationReceived(
        address indexed donor,
        uint256 amount,
        uint256 timestamp,
        uint256 donationId
    );

    /// @notice Emitted when funds are distributed to NGOs
    event FundsDistributed(
        address indexed ngoWallet,
        uint256 amountDistributed,
        uint256 timestamp
    );

    /// @notice Emitted when an NGO status changes
    event NGOStatusChanged(
        address indexed ngoWallet,
        bool isActive,
        uint256 timestamp
    );

    /// @notice Emitted when an NGO withdraws funds
    event WithdrawalCompleted(
        address indexed ngoWallet,
        uint256 amountWithdrawn,
        uint256 timestamp
    );

    /// @notice Emitted when contract receives Ether
    event ContractFunded(address indexed funder, uint256 amount);

    // ========== MODIFIERS ==========

    /// @dev Ensures only contract owner can call function
    modifier onlyOwner() {
        require(msg.sender == owner, "Only owner can call this function");
        _;
    }

    /// @dev Ensures address is valid (non-zero)
    modifier validAddress(address _address) {
        require(_address != address(0), "Invalid address: cannot be zero address");
        _;
    }

    /// @dev Ensures NGO is registered
    modifier onlyRegisteredNGO(address _ngoWallet) {
        require(ngoRegistry[_ngoWallet].wallet != address(0), "NGO not registered");
        _;
    }

    // ========== CONSTRUCTOR ==========

    /// @notice Initialize contract with owner as deployer
    constructor() {
        owner = msg.sender;
        totalDonationsReceived = 0;
        totalDistributed = 0;
        donationCount = 0;
    }

    // ========== NGO MANAGEMENT FUNCTIONS ==========

    /// @notice Register a new NGO beneficiary
    /// @param _ngoWallet Address of the NGO wallet
    /// @param _allocationPercent Percentage of donations to allocate to this NGO (0-100)
    /// @dev Only owner can register NGOs. Total allocations must equal 100%
    function registerNGO(
        address _ngoWallet,
        uint256 _allocationPercent
    ) external onlyOwner validAddress(_ngoWallet) {
        // Input validation
        require(_allocationPercent > 0, "Allocation percent must be greater than 0");
        require(_allocationPercent <= 100, "Allocation percent cannot exceed 100");
        require(
            ngoRegistry[_ngoWallet].wallet == address(0),
            "NGO already registered"
        );

        // Verify total allocation won't exceed 100%
        uint256 totalAllocation = _allocationPercent;
        for (uint256 i = 0; i < ngoList.length; i++) {
            if (ngoRegistry[ngoList[i]].isActive) {
                totalAllocation += ngoRegistry[ngoList[i]].allocationPercent;
            }
        }
        require(totalAllocation <= 100, "Total allocation would exceed 100%");

        // Register NGO
        ngoRegistry[_ngoWallet] = NGO({
            wallet: _ngoWallet,
            allocationPercent: _allocationPercent,
            accumulatedBalance: 0,
            isActive: true,
            registrationTime: block.timestamp
        });

        ngoList.push(_ngoWallet);

        emit NGORegistered(_ngoWallet, _allocationPercent, block.timestamp);
    }

    /// @notice Update NGO allocation percentage
    /// @param _ngoWallet Address of NGO to update
    /// @param _newAllocationPercent New allocation percentage
    /// @dev Owner only. Total allocations must equal 100%
    function updateAllocation(
        address _ngoWallet,
        uint256 _newAllocationPercent
    ) external onlyOwner onlyRegisteredNGO(_ngoWallet) {
        require(_newAllocationPercent > 0, "Allocation must be greater than 0");
        require(_newAllocationPercent <= 100, "Allocation cannot exceed 100");

        // Calculate total allocation with new value
        uint256 totalAllocation = _newAllocationPercent;
        for (uint256 i = 0; i < ngoList.length; i++) {
            if (ngoList[i] != _ngoWallet && ngoRegistry[ngoList[i]].isActive) {
                totalAllocation += ngoRegistry[ngoList[i]].allocationPercent;
            }
        }
        require(totalAllocation <= 100, "Total allocation would exceed 100%");

        ngoRegistry[_ngoWallet].allocationPercent = _newAllocationPercent;

        emit AllocationUpdated(_ngoWallet, _newAllocationPercent, block.timestamp);
    }

    /// @notice Deactivate an NGO (stops receiving donations)
    /// @param _ngoWallet Address of NGO to deactivate
    function deactivateNGO(address _ngoWallet)
        external
        onlyOwner
        onlyRegisteredNGO(_ngoWallet)
    {
        require(ngoRegistry[_ngoWallet].isActive, "NGO already inactive");
        ngoRegistry[_ngoWallet].isActive = false;

        emit NGOStatusChanged(_ngoWallet, false, block.timestamp);
    }

    /// @notice Reactivate a previously deactivated NGO
    /// @param _ngoWallet Address of NGO to reactivate
    function reactivateNGO(address _ngoWallet)
        external
        onlyOwner
        onlyRegisteredNGO(_ngoWallet)
    {
        require(!ngoRegistry[_ngoWallet].isActive, "NGO already active");

        // Verify total allocation won't exceed 100%
        uint256 totalAllocation = ngoRegistry[_ngoWallet].allocationPercent;
        for (uint256 i = 0; i < ngoList.length; i++) {
            if (ngoRegistry[ngoList[i]].isActive) {
                totalAllocation += ngoRegistry[ngoList[i]].allocationPercent;
            }
        }
        require(totalAllocation <= 100, "Total allocation would exceed 100%");

        ngoRegistry[_ngoWallet].isActive = true;

        emit NGOStatusChanged(_ngoWallet, true, block.timestamp);
    }

    // ========== DONATION FUNCTIONS ==========

    /// @notice Accept donation and automatically split among active NGOs
    /// @dev Anyone can donate. Funds are split based on allocation percentages
    function donate() external payable {
        require(msg.value > 0, "Donation amount must be greater than 0");
        require(ngoList.length > 0, "No NGOs registered");

        // Verify at least one active NGO
        bool hasActiveNGO = false;
        for (uint256 i = 0; i < ngoList.length; i++) {
            if (ngoRegistry[ngoList[i]].isActive) {
                hasActiveNGO = true;
                break;
            }
        }
        require(hasActiveNGO, "No active NGOs to receive donations");

        uint256 donationId = donationCount;
        donationCount++;

        // Record donation
        donations[donationId] = DonationRecord({
            donor: msg.sender,
            totalAmount: msg.value,
            timestamp: block.timestamp,
            processed: false
        });

        totalDonationsReceived += msg.value;

        emit DonationReceived(msg.sender, msg.value, block.timestamp, donationId);

        // Distribute funds to active NGOs
        _distributeFunds(msg.value);

        donations[donationId].processed = true;
    }

    /// @notice Internal function to distribute donation amount among active NGOs
    /// @param _donationAmount Total amount to distribute
    /// @dev Uses rounding to ensure all funds are distributed
    function _distributeFunds(uint256 _donationAmount) internal {
        // Count active NGOs
        uint256 activeNGOCount = 0;
        for (uint256 i = 0; i < ngoList.length; i++) {
            if (ngoRegistry[ngoList[i]].isActive) {
                activeNGOCount++;
            }
        }

        require(activeNGOCount > 0, "No active NGOs");

        uint256 totalDistributed_local = 0;

        // Distribute to all but the last NGO
        for (uint256 i = 0; i < ngoList.length; i++) {
            address ngoWallet = ngoList[i];

            if (ngoRegistry[ngoWallet].isActive) {
                uint256 allocation = ngoRegistry[ngoWallet].allocationPercent;

                // Calculate allocation amount
                uint256 allocatedAmount = (_donationAmount * allocation) / 100;

                // Add to accumulated balance
                ngoRegistry[ngoWallet].accumulatedBalance += allocatedAmount;
                totalDistributed_local += allocatedAmount;

                emit FundsDistributed(ngoWallet, allocatedAmount, block.timestamp);
            }
        }

        // Handle any remainder (due to rounding) - give to last active NGO
        uint256 remainder = _donationAmount - totalDistributed_local;
        if (remainder > 0) {
            for (uint256 i = ngoList.length; i > 0; i--) {
                address ngoWallet = ngoList[i - 1];
                if (ngoRegistry[ngoWallet].isActive) {
                    ngoRegistry[ngoWallet].accumulatedBalance += remainder;
                    emit FundsDistributed(ngoWallet, remainder, block.timestamp);
                    break;
                }
            }
        }

        totalDistributed += _donationAmount;
    }

    /// @notice Allow NGO to withdraw their accumulated balance
    /// @dev Only registered NGO can withdraw their funds
    function withdrawFunds() external {
        address ngoWallet = msg.sender;
        require(
            ngoRegistry[ngoWallet].wallet != address(0),
            "You are not a registered NGO"
        );

        uint256 balance = ngoRegistry[ngoWallet].accumulatedBalance;
        require(balance > 0, "No funds available for withdrawal");

        // Update balance before transfer (checks-effects-interactions)
        ngoRegistry[ngoWallet].accumulatedBalance = 0;

        // Transfer funds
        (bool success, ) = payable(ngoWallet).call{value: balance}("");
        require(success, "Withdrawal failed");

        emit WithdrawalCompleted(ngoWallet, balance, block.timestamp);
    }

    // ========== VIEW FUNCTIONS ==========

    /// @notice Get total number of registered NGOs
    /// @return Number of NGOs
    function getNGOCount() external view returns (uint256) {
        return ngoList.length;
    }

    /// @notice Get NGO details
    /// @param _ngoWallet Address of NGO
    /// @return NGO details
    function getNGODetails(address _ngoWallet)
        external
        view
        onlyRegisteredNGO(_ngoWallet)
        returns (NGO memory)
    {
        return ngoRegistry[_ngoWallet];
    }

    /// @notice Get accumulated balance for an NGO
    /// @param _ngoWallet Address of NGO
    /// @return Current accumulated balance
    function getNGOBalance(address _ngoWallet) external view returns (uint256) {
        return ngoRegistry[_ngoWallet].accumulatedBalance;
    }

    /// @notice Get all registered NGO addresses
    /// @return Array of NGO wallet addresses
    function getAllNGOs() external view returns (address[] memory) {
        return ngoList;
    }

    /// @notice Get allocation percentage for an NGO
    /// @param _ngoWallet Address of NGO
    /// @return Allocation percentage
    function getAllocationPercent(address _ngoWallet)
        external
        view
        returns (uint256)
    {
        return ngoRegistry[_ngoWallet].allocationPercent;
    }

    /// @notice Get total allocation percentage of all active NGOs
    /// @return Total allocation percentage
    function getTotalAllocation() external view returns (uint256) {
        uint256 total = 0;
        for (uint256 i = 0; i < ngoList.length; i++) {
            if (ngoRegistry[ngoList[i]].isActive) {
                total += ngoRegistry[ngoList[i]].allocationPercent;
            }
        }
        return total;
    }

    /// @notice Get donation record
    /// @param _donationId ID of donation
    /// @return Donation details
    function getDonationRecord(uint256 _donationId)
        external
        view
        returns (DonationRecord memory)
    {
        require(_donationId < donationCount, "Donation not found");
        return donations[_donationId];
    }

    /// @notice Get contract balance
    /// @return Current contract balance in wei
    function getContractBalance() external view returns (uint256) {
        return address(this).balance;
    }

    /// @notice Check if address is registered NGO
    /// @param _address Address to check
    /// @return true if address is registered NGO
    function isRegisteredNGO(address _address) external view returns (bool) {
        return ngoRegistry[_address].wallet != address(0);
    }

    /// @notice Check if NGO is active
    /// @param _ngoWallet Address of NGO
    /// @return true if NGO is active
    function isNGOActive(address _ngoWallet) external view returns (bool) {
        return ngoRegistry[_ngoWallet].isActive;
    }

    // ========== RECEIVE FUNCTION ==========

    /// @notice Accept Ether sent directly to contract
    receive() external payable {
        emit ContractFunded(msg.sender, msg.value);
    }
}
