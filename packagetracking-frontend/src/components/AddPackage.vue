<template>
  <v-container>
    <!-- Add Package Form -->
    <v-form ref="form" v-model="valid">
      <v-text-field
        v-model="deliveryAddress"
        label="Delivery Address"
        required
      ></v-text-field>

      <v-checkbox
        v-model="payOnDelivery"
        label="Pay on Delivery"
      ></v-checkbox>

      <!-- Couriers Table -->
      <v-data-table
        :headers="courierHeaders"
        :items="couriers"
        class="elevation-1"
        item-value="id"
        :single-select="true"
        show-select
        v-model="selectedCourier"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>Select a Courier</v-toolbar-title>
          </v-toolbar>
        </template>
      </v-data-table>

      <v-btn
        :disabled="!valid || !selectedCourier"
        color="primary"
        class="mt-3"
        @click="addPackage"
      >
        Add Package
      </v-btn>
    </v-form>
  </v-container>
</template>


<script>
import api from '../api';

export default {
  name: 'AddPackage',
  data() {
    return {
      deliveryAddress: '', // Package delivery address
      payOnDelivery: false, // Pay on delivery checkbox
      couriers: [], // List of couriers
      selectedCourier: null, // The selected courier
      courierHeaders: [
        { text: 'ID', value: 'id' },
        { text: 'Name', value: 'name' },
        { text: 'Email', value: 'email' },
      ],
      valid: false, // Form validation state
    };
  },
  methods: {
    // Fetch couriers from the server
    fetchCouriers() {
      api.get('/couriers')
        .then(response => {
          console.log('Couriers response:', response.data);
          this.couriers = response.data;
        })
        .catch(error => {
          console.error('Error fetching couriers:', error);
        });
    },
    // Add a package and assign it to the selected courier
    addPackage() {
      if (!this.selectedCourier) {
        alert('Please select a courier to assign the package.');
        return;
      }

      console.log('Selected courier:', this.selectedCourier);

      const newPackage = {
        deliveryAddress: this.deliveryAddress,
        payOnDelivery: this.payOnDelivery,
        status: 'NEW', // Default status
        courier: {
          id: this.selectedCourier[0], // Nested courier object with ID
        },
      };

      console.log('Submitting package:', newPackage);

      api.post('/package', newPackage)
        .then(() => {
          alert('Package added successfully!');
          this.resetForm();
        })
        .catch(error => {
          console.error('Error adding package:', error);
          alert('Failed to add package.');
        });
    },
    // Reset the form after submission
    resetForm() {
      this.deliveryAddress = '';
      this.payOnDelivery = false;
      this.selectedCourier = null;
      this.$refs.form.reset();
    },
  },
  mounted() {
    this.fetchCouriers(); // Fetch couriers when the component is mounted
  },
};

</script>
