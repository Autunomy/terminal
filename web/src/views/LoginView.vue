<template>
<div style="width: 20vw;margin: 0 auto">
    <div class="mb-3">
        <label for="username" class="form-label">username</label>
        <input autocomplete="off" v-model="username" type="text" class="form-control" id="username">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">password</label>
        <input autocomplete="off" v-model="password" type="password" class="form-control" id="password">
    </div>
    <span class="error_message">{{message}}</span>
    <br>
    <button class="btn btn-primary" style="margin-right: 1vw" @click="login">login</button>
    <button class="btn btn-danger" @click="reset">reset</button>
</div>
</template>

<script>
import {ref} from "vue";
import {useStore} from "vuex";
import router from "@/router";
export default {
    name: "LoginView",
    setup(){
        let message = ref("");
        let username = ref("");
        let password = ref("");
        const store = useStore();
        const jwt_token = localStorage.getItem("jwt_token");
        if(jwt_token){//将当前浏览器中的token更新到全局变量中
            store.commit("updateToken",jwt_token);
            store.dispatch("getInfo",{
                success(){
                    router.push({name:"home"});
                },
                error(){

                }
            })
        }
        let reset = () => {
            message.value = "";
            username.value = "";
            password.value = "";
        }
        let login = () => {
            message.value = "";
            store.dispatch("login",{
                username:username.value,
                password:password.value,
                success() {
                    store.dispatch("getInfo", {
                        success() {
                            console.log("getInfo success");
                            router.push({name: "home"});
                        },
                        error(resp) {
                            message.value = "获取用户信息失败:"+resp.message;
                        }
                    })
                },
                error(){
                    message.value = "用户名或密码错误"
                }
            })
        }

        return{
            message,
            username,
            password,
            reset,
            login
        }
    }
}
</script>

<style scoped>
.error_message{
    color: red;
}
</style>