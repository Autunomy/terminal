import { createRouter, createWebHistory } from 'vue-router'
import ConnectView from "@/views/ConnectView";
import HistoryView from "@/views/HistoryView";

const routes = [
  {
    path:"/",
    name:"home",
    redirect:"/connect",
  },
  {
    path:"/connect",
    name:"connect",
    component:ConnectView,
  },
  {
    path: "/history",
    name:"history",
    component: HistoryView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
