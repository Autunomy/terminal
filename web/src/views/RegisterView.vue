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
        <div class="mb-3">
            <label for="confirmedPassword" class="form-label">confirm</label>
            <input autocomplete="off" v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword">
        </div>
        <span class="error_message">{{message}}</span>
        <br>
        <button class="btn btn-primary" style="margin-right: 1vw" @click="register">register</button>
        <button class="btn btn-danger" @click="reset">reset</button>
    </div>
</template>

<script>
import {ref} from "vue";
import {useStore} from "vuex";
import router from "@/router";
import $ from 'jquery';

export default {
    name: "RegisterView",
    setup(){
        let message = ref("");
        let username = ref("");
        let password = ref("");
        let confirmedPassword = ref("");
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
            confirmedPassword.value = "";
        }
        let register = () => {
            message.value = "";
            $.ajax({
                url:"http://127.0.0.1:8000/user/register",
                type:"post",
                data:{
                    username:username.value,
                    password:password.value,
                    confirmedPassword:confirmedPassword.value,
                },
                success(resp){
                    if(resp.message === "success"){
                        router.push({name:"login"})
                    }else{
                        message.value = resp.message;
                    }
                },
                error(resp){
                    message.value = resp.message;
                }
            })
        }

        return{
            message,
            username,
            password,
            confirmedPassword,
            reset,
            register
        }
    }
}
</script>

<style scoped>
.error_message{
    color: red;
}
</style>