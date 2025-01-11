<template>
  <v-container>
    <!-- Couriers Table -->
    <v-data-table
      :headers="courierHeaders"
      :items="couriers"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Select a Courier</v-toolbar-title>
        </v-toolbar>
      </template>

      <template v-slot:[`item.actions`]="{ item }">
        <v-btn
          color="primary"
          @click="selectCourier(item)"
        >
          Select
        </v-btn>
      </template>
    </v-data-table>

    <v-divider class="my-4"></v-divider>

    <!-- Packages Table -->
    <v-data-table
      :headers="packageHeaders"
      :items="packages"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Packages for Selected Courier</v-toolbar-title>
        </v-toolbar>
      </template>
    </v-data-table>
  </v-container>
</template>


<script>
import api from '../api';

export default {
  name: 'MyPackages',
  data() {
    return {
      couriers: [], // List of couriers
      packages: [], // List of packages for the selected courier
      selectedCourier: null, // Store selected courier object
      courierHeaders: [
        { text: 'ID', value: 'id' },
        { text: 'Name', value: 'name' },
        { text: 'Email', value: 'email' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      packageHeaders: [
        { text: 'ID', value: 'id' },
        { text: 'Delivery Address', value: 'deliveryAddress' },
        { text: 'Pay On Delivery', value: 'payOnDelivery' },
        { text: 'Created On', value: 'createdOn' },
        { text: 'Status', value: 'status' },
      ],
    };
  },
  methods: {
    // Fetch couriers from the server
    fetchCouriers() {
      api.get('/couriers')
        .then(response => {
          console.log('Couriers response:', response.data); // Debugging
          this.couriers = response.data; // Set couriers
        })
        .catch(error => {
          console.error('Error fetching couriers:', error);
        });
    },
    // Select a courier and fetch their packages
    selectCourier(courier) {
      console.log('Selected courier:', courier); // Debugging
      this.selectedCourier = courier; // Set the selected courier
      this.packages = courier.packages || []; // Load the packages of the selected courier
    },
  },
  mounted() {
    this.fetchCouriers(); // Fetch couriers when the component is mounted
  },
};

</script>
