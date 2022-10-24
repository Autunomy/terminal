import { createRouter, createWebHistory } from 'vue-router'
import ConnectView from "@/views/ConnectView";
import HistoryView from "@/views/HistoryView";
import LoginView from "@/views/LoginView";
import RegisterView from "@/views/RegisterView";
import store from "@/store";

const routes = [
  {
    path:"/",
    name:"home",
    redirect:"/connect",
    meta:{
      //是否需要授权
      requestAuth:false,
    }
  },
  {
    path:"/connect",
    name:"connect",
    component:ConnectView,
    meta:{
      //是否需要授权
      requestAuth:false,
    }
  },
  {
    path: "/history",
    name:"history",
    component: HistoryView,
    meta:{
      //是否需要授权
      requestAuth:false,
    }
  },
  {
    path: "/login",
    name:"login",
    component: LoginView,
    meta:{
      //是否需要授权
      requestAuth:false,
    }
  },
  {
    path: "/register",
    name:"register",
    component: RegisterView,
    meta:{
      //是否需要授权
      requestAuth:false,
    }
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

//在路由跳转之前执行的函数
router.beforeEach((to,from,next) => {
  //判断是否需要授权 以及当前的登陆状态
  if(to.meta.requestAuth && !store.state.user.is_login){
    next({name:"login"});//跳转到登陆页面
  }else{
    next();//放行
  }
})


export default router
