
import { createRouter, createWebHistory } from 'vue-router';
import AddPackage from '@/components/AddPackage.vue';
import PackagesView from '@/components/PackagesView.vue';
import MyPackages from '@/components/MyPackages.vue';
import AssignPackage from '@/components/AssignPackage.vue';

const routes = [
  { path: '/', component: PackagesView },
  { path: '/add', component: AddPackage },
  { path: '/my-packages', component: MyPackages },
  { path: '/assign', component: AssignPackage },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
