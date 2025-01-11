<template>
  <v-container>
    <v-data-table :headers="headers" :items="packages" class="elevation-1">
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Packages</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn @click="fetchPackages" color="primary">Refresh</v-btn>
        </v-toolbar>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import api from '../api';

export default {
  data() {
    return {
      packages: [],
      headers: [
        { text: 'ID', value: 'id' },
        { text: 'Delivery Address', value: 'deliveryAddress' },
        { text: 'Courier', value: 'courier.name' },
        { text: 'Status', value: 'status' },
      ],
    };
  },
  methods: {
    fetchPackages() {
      api.get('/package')
        .then(response => (this.packages = response.data))
        .catch(error => console.error('Error fetching packages:', error));
    },
  },
  mounted() {
    this.fetchPackages();
  },
};
</script>
