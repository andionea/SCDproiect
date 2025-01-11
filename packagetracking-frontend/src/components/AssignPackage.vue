<template>
  <v-container>
    <!-- Table of Packages -->
    <v-data-table
      :headers="packageHeaders"
      :items="packages"
      item-value="id"
      show-select
      v-model="selectedPackages"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Assign Package</v-toolbar-title>
        </v-toolbar>
      </template>
    </v-data-table>

    <!-- Status Update Dropdown -->
    <v-select
      v-model="newStatus"
      :items="statusOptions"
      label="Select New Status"
      required
    ></v-select>

    <!-- Update Status Button -->
    <v-btn
      :disabled="!selectedPackages.length || !newStatus"
      color="primary"
      class="mt-3"
      @click="updatePackageStatus"
    >
      Update Status
    </v-btn>
  </v-container>
</template>

  
  <script>
  import api from "../api";

export default {
  name: "AssignPackage",
  data() {
    return {
      packages: [], // List of packages
      selectedPackages: [], // Selected package IDs
      newStatus: "", // New status for the selected packages
      statusOptions: ["NEW", "PENDING", "DELIVERED"], // Available status options
      packageHeaders: [
        { text: "ID", value: "id" },
        { text: "Delivery Address", value: "deliveryAddress" },
        { text: "Status", value: "status" },
        { text: "Pay on Delivery", value: "payOnDelivery" },
      ],
    };
  },
  methods: {
    // Fetch packages from the server
    fetchPackages() {
      api
        .get("/package")
        .then((response) => {
          this.packages = response.data;
        })
        .catch((error) => {
          console.error("Error fetching packages:", error);
        });
    },
    // Update the status of selected packages
    updatePackageStatus() {
      const promises = this.selectedPackages.map((packageId) => {
        return api.put(`/package/${packageId}/status`, null, {
          params: {
            status: this.newStatus, // Pass the new status as a query parameter
          },
        });
      });

      // Handle all promises
      Promise.all(promises)
        .then(() => {
          alert("Status updated successfully for selected packages!");
          this.fetchPackages(); // Refresh the packages list
          this.selectedPackages = []; // Clear the selection
          this.newStatus = ""; // Clear the status field
        })
        .catch((error) => {
          console.error("Error updating package status:", error);
          alert("Failed to update package status.");
        });
    },
  },
  mounted() {
    this.fetchPackages(); // Fetch packages when the component is mounted
  },
};
  </script>
  