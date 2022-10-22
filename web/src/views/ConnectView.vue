<template>
    <div>
        <div class="mb-3">
            <label for="host" class="form-label">host</label>
            <input v-model="host" type="text" class="form-control" id="host">
        </div>
        <div class="mb-3">
            <label for="port" class="form-label">port</label>
            <input v-model="port" type="text" class="form-control" id="port">
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">username</label>
            <input v-model="username" type="text" class="form-control" id="username">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">password</label>
            <input v-model="password" type="password" class="form-control" id="password">
        </div>
        <button class="btn btn-primary" style="margin-right: 20px" @click="doConnect">connect</button>
        <button class="btn btn-danger">reset</button>
        <br>
        <br>
        <span style="color:gray">注：建议<router-link to="/login">登录</router-link>后操作，登陆后可以自动对连接信息进行保存，方便下次登陆</span>

        <div class="container">
            <div id="status" style="color: red;"></div>
            <div id="terminal"></div>
        </div>
    </div>
</template>

<script>
import {ref} from "vue";
import $ from 'jquery';
import {Base64} from "js-base64";


export default {
    name: "ConnectView",
    setup(){
        let host = ref("120.48.149.8");
        let port = ref("22");
        let username = ref("root");
        let password = ref("hty15336132318@");

        $.ajax({
            url:"http://180.76.97.59:8888/?hostname="+
                host.value+"&port="+
                port.value+"&username="+
                username.value+"&password="+
                Base64.encode(password.value),
            type:"get",
            success(resp){
                console.log(resp);
            },
            error(resp){
                console.log(resp)
            }
        })

        let saveConnect = () => {
            $.ajax({
                url:"http://localhost:8080/connect/save/message",
                type:"post",
                data:{
                    host,
                    port,
                    username,
                    password,
                },
                success(resp){
                    console.log(resp)
                    // doConnect();
                },
                error(resp){
                    console.log(resp)
                }
            })
        }

        let doConnect = () => {
            window.open("http://180.76.97.59:8888/?hostname="+
                host.value+"&port="+
                port.value+"&username="+
                username.value+"&password="+
                Base64.encode(password.value));
        }


        return{
            host,
            port,
            username,
            password,
            saveConnect,
            doConnect,
        }
    },
}
</script>

<style scoped>

</style>