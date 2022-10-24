<template>
    <div>
        <div class="mb-3">
            <label for="name" class="form-label">connect name(可选)</label>
            <input v-model="name" type="text" class="form-control" id="name">
        </div>
        <div class="mb-3">
            <label for="host" class="form-label">host</label>
            <input v-model="host" type="text" class="form-control" id="host">
        </div>
        <div class="mb-3">
            <label for="port" class="form-label">port</label>
            <input v-model="port" type="number" class="form-control" id="port">
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">username</label>
            <input v-model="username" type="text" class="form-control" id="username">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">password</label>
            <input v-model="password" type="password" class="form-control" id="password">
        </div>
        <button class="btn btn-primary" style="margin-right: 20px" @click="saveConnect">connect</button>
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
import {useStore} from "vuex";

export default {
    name: "ConnectView",
    setup(){
        let host = ref("");
        let port = ref("22");
        let username = ref("");
        let password = ref("");
        let name = ref("");
        const store = useStore();

        let saveConnect = () => {
            if(store.state.user.is_login){
                $.ajax({
                    url:"http://localhost:8000/history/save/message",
                    type:"post",
                    data:{
                        host:host.value,
                        port:port.value,
                        username:username.value,
                        password:password.value,
                        name:name.value
                    },
                    headers:{
                        Authorization:"Bearer "+store.state.user.token
                    },
                    success(resp){
                        console.log(resp)
                    },
                    error(resp){
                        console.log(resp)
                    },
                })
            }
            doConnect();
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
            name,
            saveConnect,
            doConnect,
        }
    },
}
</script>

<style scoped>

</style>