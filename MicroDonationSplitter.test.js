const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("MicroDonationSplitter Contract", function () {
  let contract;
  let owner, donor1, donor2, ngo1, ngo2, ngo3, nonRegistered;

  // ========== FIXTURES ==========

  async function deployContractFixture() {
    const [ownerAddr, addr1, addr2, addr3, addr4, addr5, addr6] =
      await ethers.getSigners();

    const MicroDonationSplitter = await ethers.getContractFactory(
      "MicroDonationSplitter"
    );
    const contractInstance = await MicroDonationSplitter.deploy();
    await contractInstance.waitForDeployment();

    return {
      contract: contractInstance,
      owner: ownerAddr,
      donor1: addr1,
      donor2: addr2,
      ngo1: addr3,
      ngo2: addr4,
      ngo3: addr5,
      nonRegistered: addr6,
    };
  }

  beforeEach(async function () {
    const fixture = await deployContractFixture();
    contract = fixture.contract;
    owner = fixture.owner;
    donor1 = fixture.donor1;
    donor2 = fixture.donor2;
    ngo1 = fixture.ngo1;
    ngo2 = fixture.ngo2;
    ngo3 = fixture.ngo3;
    nonRegistered = fixture.nonRegistered;
  });

  // ========== DEPLOYMENT TESTS ==========

  describe("Deployment", function () {
    it("Should deploy contract successfully", async function () {
      const contractAddr = await contract.getAddress();
      expect(contractAddr).to.not.equal(ethers.ZeroAddress);
    });

    it("Should set the correct owner", async function () {
      const contractOwner = await contract.owner();
      expect(contractOwner).to.equal(owner.address);
    });

    it("Should initialize with zero donations", async function () {
      const totalDonations = await contract.totalDonationsReceived();
      expect(totalDonations).to.equal(0);
    });

    it("Should initialize with zero NGOs", async function () {
      const ngoCount = await contract.getNGOCount();
      expect(ngoCount).to.equal(0);
    });
  });

  // ========== NGO REGISTRATION TESTS ==========

  describe("NGO Registration", function () {
    it("✅ Should register a single NGO successfully", async function () {
      await contract.registerNGO(ngo1.address, 100);
      const count = await contract.getNGOCount();
      expect(count).to.equal(1);
    });

    it("✅ Should register multiple NGOs", async function () {
      await contract.registerNGO(ngo1.address, 50);
      await contract.registerNGO(ngo2.address, 50);

      const count = await contract.getNGOCount();
      expect(count).to.equal(2);
    });

    it("✅ Should not allow registration with allocation > 100%", async function () {
      await expect(contract.registerNGO(ngo1.address, 101)).to.be.revertedWith(
        "Allocation percent cannot exceed 100"
      );
    });

    it("✅ Should not allow registration with 0% allocation", async function () {
      await expect(contract.registerNGO(ngo1.address, 0)).to.be.revertedWith(
        "Allocation percent must be greater than 0"
      );
    });

    it("✅ Should prevent duplicate NGO registration", async function () {
      await contract.registerNGO(ngo1.address, 100);
      await expect(contract.registerNGO(ngo1.address, 50)).to.be.revertedWith(
        "NGO already registered"
      );
    });

    it("✅ Should reject zero address registration", async function () {
      await expect(
        contract.registerNGO(ethers.ZeroAddress, 100)
      ).to.be.revertedWith("Invalid address: cannot be zero address");
    });

    it("✅ Should only allow admin to register NGOs", async function () {
      await expect(
        contract.connect(donor1).registerNGO(ngo1.address, 100)
      ).to.be.revertedWith("Only owner can call this function");
    });

    it("✅ Should emit NGORegistered event", async function () {
      await expect(contract.registerNGO(ngo1.address, 100))
        .to.emit(contract, "NGORegistered")
        .withArgs(ngo1.address, 100);
    });

    it("✅ Should enforce total allocation <= 100%", async function () {
      await contract.registerNGO(ngo1.address, 60);
      await contract.registerNGO(ngo2.address, 40);

      // This should fail because total would be 60 + 40 + 50 = 150%
      await expect(contract.registerNGO(ngo3.address, 50)).to.be.revertedWith(
        "Total allocation would exceed 100%"
      );
    });
  });

  // ========== ALLOCATION UPDATE TESTS ==========

  describe("Allocation Updates", function () {
    beforeEach(async function () {
      await contract.registerNGO(ngo1.address, 50);
      await contract.registerNGO(ngo2.address, 50);
    });

    it("✅ Should update allocation percentages", async function () {
      await contract.updateAllocation(ngo1.address, 60);
      const details = await contract.getNGODetails(ngo1.address);
      expect(details.allocationPercent).to.equal(60);
    });

    it("✅ Should verify total allocation <= 100%", async function () {
      // ngo1 = 50%, ngo2 = 50%
      // Try to set ngo1 to 60% (would make total 110%)
      await expect(contract.updateAllocation(ngo1.address, 60)).to.be.revertedWith(
        "Total allocation would exceed 100%"
      );
    });

    it("✅ Should not allow 0% allocation", async function () {
      await expect(contract.updateAllocation(ngo1.address, 0)).to.be.revertedWith(
        "Allocation must be greater than 0"
      );
    });

    it("✅ Should emit AllocationUpdated event", async function () {
      await expect(contract.updateAllocation(ngo1.address, 60))
        .to.emit(contract, "AllocationUpdated")
        .withArgs(ngo1.address, 60);
    });

    it("✅ Should only allow owner to update allocations", async function () {
      await expect(
        contract.connect(donor1).updateAllocation(ngo1.address, 60)
      ).to.be.revertedWith("Only owner can call this function");
    });
  });

  // ========== NGO STATUS TESTS ==========

  describe("NGO Status Management", function () {
    beforeEach(async function () {
      await contract.registerNGO(ngo1.address, 50);
      await contract.registerNGO(ngo2.address, 50);
    });

    it("✅ Should deactivate NGO", async function () {
      await contract.deactivateNGO(ngo1.address);
      const isActive = await contract.isNGOActive(ngo1.address);
      expect(isActive).to.be.false;
    });

    it("✅ Should reactivate NGO", async function () {
      await contract.deactivateNGO(ngo1.address);
      await contract.reactivateNGO(ngo1.address);
      const isActive = await contract.isNGOActive(ngo1.address);
      expect(isActive).to.be.true;
    });

    it("✅ Should emit NGOStatusChanged event", async function () {
      await expect(contract.deactivateNGO(ngo1.address))
        .to.emit(contract, "NGOStatusChanged")
        .withArgs(ngo1.address, false);
    });

    it("✅ Should prevent deactivating already inactive NGO", async function () {
      await contract.deactivateNGO(ngo1.address);
      await expect(contract.deactivateNGO(ngo1.address)).to.be.revertedWith(
        "NGO already inactive"
      );
    });
  });

  // ========== DONATION PROCESSING TESTS ==========

  describe("Donation Processing", function () {
    beforeEach(async function () {
      await contract.registerNGO(ngo1.address, 50);
      await contract.registerNGO(ngo2.address, 50);
    });

    it("✅ Should accept donations (happy path)", async function () {
      const donationAmount = ethers.parseEther("1.0");
      await expect(
        donor1.sendTransaction({
          to: await contract.getAddress(),
          value: donationAmount,
          data: contract.interface.encodeFunctionData("donate"),
        })
      )
        .to.emit(contract, "DonationReceived")
        .withArgs(donor1.address, donationAmount);
    });

    it("✅ Should correctly split funds among 2 NGOs", async function () {
      const donationAmount = ethers.parseEther("1.0");
      await contract.connect(donor1).donate({ value: donationAmount });

      const balance1 = await contract.getNGOBalance(ngo1.address);
      const balance2 = await contract.getNGOBalance(ngo2.address);

      expect(balance1).to.equal(ethers.parseEther("0.5"));
      expect(balance2).to.equal(ethers.parseEther("0.5"));
    });

    it("✅ Should correctly split funds among 3 NGOs", async function () {
      // Reset and register 3 NGOs
      const newNGO1 = (await ethers.getSigners())[3];
      const newNGO2 = (await ethers.getSigners())[4];
      const newNGO3 = (await ethers.getSigners())[5];

      const MicroDonationSplitter = await ethers.getContractFactory(
        "MicroDonationSplitter"
      );
      contract = await MicroDonationSplitter.deploy();

      await contract.registerNGO(newNGO1.address, 40);
      await contract.registerNGO(newNGO2.address, 35);
      await contract.registerNGO(newNGO3.address, 25);

      const donationAmount = ethers.parseEther("100.0");
      await contract.connect(donor1).donate({ value: donationAmount });

      const balance1 = await contract.getNGOBalance(newNGO1.address);
      const balance2 = await contract.getNGOBalance(newNGO2.address);
      const balance3 = await contract.getNGOBalance(newNGO3.address);

      expect(balance1).to.equal(ethers.parseEther("40.0"));
      expect(balance2).to.equal(ethers.parseEther("35.0"));
      expect(balance3).to.equal(ethers.parseEther("25.0"));
    });

    it("✅ Should handle fractional amounts with proper rounding", async function () {
      const donationAmount = ethers.parseEther("1.0");
      await contract.connect(donor1).donate({ value: donationAmount });

      const balance1 = await contract.getNGOBalance(ngo1.address);
      const balance2 = await contract.getNGOBalance(ngo2.address);
      const total = balance1 + balance2;

      expect(total).to.equal(donationAmount);
    });

    it("✅ Should accumulate balance correctly", async function () {
      const donationAmount = ethers.parseEther("1.0");

      await contract.connect(donor1).donate({ value: donationAmount });
      let balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(ethers.parseEther("0.5"));

      await contract.connect(donor2).donate({ value: donationAmount });
      balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(ethers.parseEther("1.0"));
    });

    it("✅ Should reject zero donations", async function () {
      await expect(
        contract.connect(donor1).donate({ value: 0 })
      ).to.be.revertedWith("Donation amount must be greater than 0");
    });

    it("✅ Should emit DonationReceived event", async function () {
      const donationAmount = ethers.parseEther("1.0");
      await expect(contract.connect(donor1).donate({ value: donationAmount }))
        .to.emit(contract, "DonationReceived")
        .withArgs(donor1.address, donationAmount, /* timestamp */ expect.any(Number), 0);
    });

    it("✅ Should increment donation count", async function () {
      const donationAmount = ethers.parseEther("1.0");

      let count = await contract.donationCount();
      expect(count).to.equal(0);

      await contract.connect(donor1).donate({ value: donationAmount });
      count = await contract.donationCount();
      expect(count).to.equal(1);

      await contract.connect(donor2).donate({ value: donationAmount });
      count = await contract.donationCount();
      expect(count).to.equal(2);
    });

    it("✅ Should reject donation when no active NGOs", async function () {
      await contract.deactivateNGO(ngo1.address);
      await contract.deactivateNGO(ngo2.address);

      await expect(
        contract.connect(donor1).donate({ value: ethers.parseEther("1.0") })
      ).to.be.revertedWith("No active NGOs to receive donations");
    });

    it("✅ Should reject donation when no NGOs registered", async function () {
      const MicroDonationSplitter = await ethers.getContractFactory(
        "MicroDonationSplitter"
      );
      const emptyContract = await MicroDonationSplitter.deploy();

      await expect(
        donor1.sendTransaction({
          to: await emptyContract.getAddress(),
          value: ethers.parseEther("1.0"),
          data: emptyContract.interface.encodeFunctionData("donate"),
        })
      ).to.be.revertedWith("No NGOs registered");
    });
  });

  // ========== WITHDRAWAL TESTS ==========

  describe("Withdrawal System", function () {
    beforeEach(async function () {
      await contract.registerNGO(ngo1.address, 50);
      await contract.registerNGO(ngo2.address, 50);
      const donationAmount = ethers.parseEther("2.0");
      await contract.connect(donor1).donate({ value: donationAmount });
    });

    it("✅ Should allow NGO to withdraw funds", async function () {
      const initialBalance = await ethers.provider.getBalance(ngo1.address);
      const withdrawAmount = await contract.getNGOBalance(ngo1.address);

      await contract.connect(ngo1).withdrawFunds();

      const finalBalance = await ethers.provider.getBalance(ngo1.address);
      expect(finalBalance).to.be.gt(initialBalance);
    });

    it("✅ Should decrease balance after withdrawal", async function () {
      let balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(ethers.parseEther("1.0"));

      await contract.connect(ngo1).withdrawFunds();

      balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(0);
    });

    it("✅ Should handle multiple withdrawals", async function () {
      // First withdrawal
      await contract.connect(ngo1).withdrawFunds();
      let balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(0);

      // Make another donation
      await contract.connect(donor2).donate({ value: ethers.parseEther("2.0") });
      balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(ethers.parseEther("1.0"));

      // Second withdrawal
      await contract.connect(ngo1).withdrawFunds();
      balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(0);
    });

    it("✅ Should reject withdrawal with insufficient balance", async function () {
      await contract.connect(ngo1).withdrawFunds();
      await expect(contract.connect(ngo1).withdrawFunds()).to.be.revertedWith(
        "No funds available for withdrawal"
      );
    });

    it("✅ Should emit WithdrawalCompleted event", async function () {
      const balance = await contract.getNGOBalance(ngo1.address);
      await expect(contract.connect(ngo1).withdrawFunds())
        .to.emit(contract, "WithdrawalCompleted")
        .withArgs(ngo1.address, balance);
    });

    it("✅ Should prevent non-registered addresses from withdrawing", async function () {
      await expect(contract.connect(nonRegistered).withdrawFunds()).to.be.revertedWith(
        "You are not a registered NGO"
      );
    });
  });

  // ========== VIEW FUNCTIONS TESTS ==========

  describe("View Functions", function () {
    beforeEach(async function () {
      await contract.registerNGO(ngo1.address, 40);
      await contract.registerNGO(ngo2.address, 60);
    });

    it("✅ Should return correct NGO count", async function () {
      const count = await contract.getNGOCount();
      expect(count).to.equal(2);
    });

    it("✅ Should get NGO details", async function () {
      const details = await contract.getNGODetails(ngo1.address);
      expect(details.wallet).to.equal(ngo1.address);
      expect(details.allocationPercent).to.equal(40);
      expect(details.isActive).to.be.true;
    });

    it("✅ Should get NGO balance", async function () {
      const donationAmount = ethers.parseEther("1.0");
      await contract.connect(donor1).donate({ value: donationAmount });

      const balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(ethers.parseEther("0.4"));
    });

    it("✅ Should get all NGOs", async function () {
      const allNGOs = await contract.getAllNGOs();
      expect(allNGOs.length).to.equal(2);
      expect(allNGOs[0]).to.equal(ngo1.address);
      expect(allNGOs[1]).to.equal(ngo2.address);
    });

    it("✅ Should get allocation percentage", async function () {
      const allocation = await contract.getAllocationPercent(ngo1.address);
      expect(allocation).to.equal(40);
    });

    it("✅ Should get total allocation of active NGOs", async function () {
      let total = await contract.getTotalAllocation();
      expect(total).to.equal(100);

      await contract.deactivateNGO(ngo1.address);
      total = await contract.getTotalAllocation();
      expect(total).to.equal(60);
    });

    it("✅ Should return correct contract balance", async function () {
      const donationAmount = ethers.parseEther("1.0");
      await contract.connect(donor1).donate({ value: donationAmount });

      const balance = await contract.getContractBalance();
      expect(balance).to.equal(donationAmount);
    });

    it("✅ Should check if address is registered NGO", async function () {
      let isNGO = await contract.isRegisteredNGO(ngo1.address);
      expect(isNGO).to.be.true;

      isNGO = await contract.isRegisteredNGO(donor1.address);
      expect(isNGO).to.be.false;
    });

    it("✅ Should check if NGO is active", async function () {
      let isActive = await contract.isNGOActive(ngo1.address);
      expect(isActive).to.be.true;

      await contract.deactivateNGO(ngo1.address);
      isActive = await contract.isNGOActive(ngo1.address);
      expect(isActive).to.be.false;
    });

    it("✅ Should retrieve donation record", async function () {
      const donationAmount = ethers.parseEther("1.0");
      await contract.connect(donor1).donate({ value: donationAmount });

      const record = await contract.getDonationRecord(0);
      expect(record.donor).to.equal(donor1.address);
      expect(record.totalAmount).to.equal(donationAmount);
      expect(record.processed).to.be.true;
    });
  });

  // ========== EDGE CASES & NEGATIVE TESTS ==========

  describe("Edge Cases & Negative Tests", function () {
    it("✅ Should handle donations with remainder distribution", async function () {
      // Setup: 3 NGOs with uneven percentages
      const newContract = await ethers.getContractFactory(
        "MicroDonationSplitter"
      ).then(f => f.deploy());

      const [owner, donor, ngo1, ngo2, ngo3] = await ethers.getSigners();
      const ngo4 = (await ethers.getSigners())[5];

      await newContract.registerNGO(ngo1.address, 33);
      await newContract.registerNGO(ngo2.address, 33);
      await newContract.registerNGO(ngo3.address, 34);

      // Donate 1 wei (smallest unit)
      const tiny = ethers.toBigInt(1);
      await newContract.connect(donor).donate({ value: tiny });

      const total = 
        (await newContract.getNGOBalance(ngo1.address)) +
        (await newContract.getNGOBalance(ngo2.address)) +
        (await newContract.getNGOBalance(ngo3.address));

      expect(total).to.equal(tiny);
    });

    it("✅ Should handle large donation amounts", async function () {
      await contract.registerNGO(ngo1.address, 100);

      const largeAmount = ethers.parseEther("1000000.0");
      await contract.connect(donor1).donate({ value: largeAmount });

      const balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(largeAmount);
    });

    it("✅ Should prevent deactivated NGOs from receiving donations", async function () {
      await contract.registerNGO(ngo1.address, 100);
      await contract.deactivateNGO(ngo1.address);

      await expect(
        contract.connect(donor1).donate({ value: ethers.parseEther("1.0") })
      ).to.be.revertedWith("No active NGOs to receive donations");
    });

    it("✅ Should maintain state integrity across operations", async function () {
      await contract.registerNGO(ngo1.address, 50);
      await contract.registerNGO(ngo2.address, 50);

      const donation1 = ethers.parseEther("1.0");
      await contract.connect(donor1).donate({ value: donation1 });

      let total = await contract.totalDonationsReceived();
      expect(total).to.equal(donation1);

      const donation2 = ethers.parseEther("2.0");
      await contract.connect(donor2).donate({ value: donation2 });

      total = await contract.totalDonationsReceived();
      expect(total).to.equal(donation1 + donation2);
    });

    it("✅ Should handle concurrent donations from multiple donors", async function () {
      await contract.registerNGO(ngo1.address, 50);
      await contract.registerNGO(ngo2.address, 50);

      const amount = ethers.parseEther("1.0");
      await contract.connect(donor1).donate({ value: amount });
      await contract.connect(donor2).donate({ value: amount });

      const balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(ethers.parseEther("1.0"));
    });
  });

  // ========== INTEGRATION TESTS ==========

  describe("Integration Tests", function () {
    it("✅ Complete workflow: register → donate → withdraw", async function () {
      // Setup
      await contract.registerNGO(ngo1.address, 100);

      // Initial state
      let balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(0);

      // Donate
      const donationAmount = ethers.parseEther("10.0");
      await contract.connect(donor1).donate({ value: donationAmount });

      balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(donationAmount);

      // Withdraw
      const initialBalance = await ethers.provider.getBalance(ngo1.address);
      await contract.connect(ngo1).withdrawFunds();

      balance = await contract.getNGOBalance(ngo1.address);
      expect(balance).to.equal(0);

      const finalBalance = await ethers.provider.getBalance(ngo1.address);
      expect(finalBalance).to.be.gt(initialBalance);
    });

    it("✅ Multiple donors and multiple NGOs scenario", async function () {
      await contract.registerNGO(ngo1.address, 40);
      await contract.registerNGO(ngo2.address, 35);
      await contract.registerNGO(ngo3.address, 25);

      const donation1 = ethers.parseEther("100.0");
      const donation2 = ethers.parseEther("50.0");

      await contract.connect(donor1).donate({ value: donation1 });
      await contract.connect(donor2).donate({ value: donation2 });

      const balance1 = await contract.getNGOBalance(ngo1.address);
      const balance2 = await contract.getNGOBalance(ngo2.address);
      const balance3 = await contract.getNGOBalance(ngo3.address);

      expect(balance1).to.equal(ethers.parseEther("56.0")); // 40 + 16
      expect(balance2).to.equal(ethers.parseEther("52.5")); // 35 + 17.5
      expect(balance3).to.equal(ethers.parseEther("41.5")); // 25 + 12.5
    });

    it("✅ Admin management operations", async function () {
      // Register
      await contract.registerNGO(ngo1.address, 50);
      await contract.registerNGO(ngo2.address, 50);

      let ngo1Details = await contract.getNGODetails(ngo1.address);
      expect(ngo1Details.allocationPercent).to.equal(50);

      // Update allocation
      await contract.updateAllocation(ngo1.address, 60);
      await contract.updateAllocation(ngo2.address, 40);

      ngo1Details = await contract.getNGODetails(ngo1.address);
      expect(ngo1Details.allocationPercent).to.equal(60);

      // Deactivate
      await contract.deactivateNGO(ngo1.address);
      let isActive = await contract.isNGOActive(ngo1.address);
      expect(isActive).to.be.false;

      // Reactivate
      await contract.reactivateNGO(ngo1.address);
      isActive = await contract.isNGOActive(ngo1.address);
      expect(isActive).to.be.true;
    });

    it("✅ Historical data tracking", async function () {
      await contract.registerNGO(ngo1.address, 100);

      const donation1 = ethers.parseEther("1.0");
      const donation2 = ethers.parseEther("2.0");

      await contract.connect(donor1).donate({ value: donation1 });
      await contract.connect(donor2).donate({ value: donation2 });

      const record1 = await contract.getDonationRecord(0);
      const record2 = await contract.getDonationRecord(1);

      expect(record1.donor).to.equal(donor1.address);
      expect(record1.totalAmount).to.equal(donation1);

      expect(record2.donor).to.equal(donor2.address);
      expect(record2.totalAmount).to.equal(donation2);
    });
  });

  // ========== RECEIVE FUNCTION TEST ==========

  describe("Receive Function", function () {
    it("✅ Should accept Ether via receive function", async function () {
      const amount = ethers.parseEther("1.0");
      await expect(
        donor1.sendTransaction({
          to: await contract.getAddress(),
          value: amount,
        })
      )
        .to.emit(contract, "ContractFunded")
        .withArgs(donor1.address, amount);
    });
  });
});
