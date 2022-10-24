<template>
    <div v-if="$store.state.user.is_login">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">name</th>
                <th scope="col">host:port</th>
                <th scope="col">ops</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="history in historyList" :key="history.id">
                <td>{{ history.name}}</td>
                <td>{{history.host}}:{{history.port}}</td>
                <td>
                    <button class="btn btn-primary" @click="connect(history)">连接</button>
                    &nbsp;&nbsp;
                    <button class="btn btn-danger" @click="delHistory(history.id)">删除</button>
                </td>
            </tr>
            </tbody>
        </table>
<!--        <nav aria-label="Page navigation example">-->
<!--            <ul class="pagination justify-content-center">-->
<!--                <li class="page-item">-->
<!--                    <a class="page-link">Previous</a>-->
<!--                </li>-->
<!--                <li class="page-item"><a class="page-link" href="#">1</a></li>-->
<!--                <li class="page-item"><a class="page-link" href="#">2</a></li>-->
<!--                <li class="page-item"><a class="page-link" href="#">3</a></li>-->
<!--                <li class="page-item">-->
<!--                    <a class="page-link" href="#">Next</a>-->
<!--                </li>-->
<!--            </ul>-->
<!--        </nav>-->
    </div>
    <div v-if="!$store.state.user.is_login">
        当前未登录，请先登录再查看历史记录&nbsp;&nbsp;
        <router-link to="/login">点击此处进行登陆</router-link>
    </div>
</template>

<script>
import {ref} from "vue";
import {useStore} from "vuex";
import $ from 'jquery';
import {Base64} from "js-base64";

export default {
    name: "HistoryView",
    setup(){
        let historyList = ref([]);
        const store = useStore();
        const getList = ()=>{
            $.ajax({
                url:"http://localhost:8000/history/list",
                type:"get",
                headers:{
                    Authorization:"Bearer "+ store.state.user.token
                },
                success(resp){
                    historyList.value = resp;
                },
                error(resp){
                    console.log(resp);
                }
            })
        }
        if(store.state.user.is_login){
            getList();
        }
        const connect = history => {
            window.open("http://180.76.97.59:8888/?hostname="+
                history.host+"&port="+
                history.port+"&username="+
                history.username+"&password="+
                Base64.encode(history.password));
        }

        const delHistory = id => {
            $.ajax({
                url:"http://localhost:8000/history/del",
                type:"post",
                data:{
                    id,
                },
                headers:{
                    Authorization:"Bearer "+ store.state.user.token
                },
                success(resp){
                    if(resp.message === "success") getList();
                },
                error(resp){
                    console.log(resp);
                }
            })
        }
        return{
            historyList,
            connect,
            delHistory
        }
    }
}
</script>

<style scoped>

</style>