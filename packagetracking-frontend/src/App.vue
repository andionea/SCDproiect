<template>
  <v-app>
    <v-main>
      <v-toolbar app dense color="primary" dark>
        <v-toolbar-title>Package Tracking App</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn text to="/">Home</v-btn>
        <v-btn text to="/add">Add Package</v-btn>
        <v-btn text to="/my-packages">My Packages</v-btn>
        <v-btn text to="/assign">Assign Package</v-btn>
      </v-toolbar>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
};
</script>
