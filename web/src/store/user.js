import router from "@/router";
import $ from 'jquery';

export default {
    state: {
        id:"",
        username:"",
        token:"",
        is_login:false,
    },
    getters: {
    },
    mutations: {
        updateUser(state,user){
            state.id = user.id;
            state.username = user.username;
            state.is_login = user.is_login;
        },
        updateToken(state,token){
            state.token = token;
        },
        updateStatus(state,is_login){
            state.is_login = is_login;
        },
        logout(state){
            state.id = "";
            state.username = "";
            state.token = "";
            state.is_login = false;
            router.push({name:"login"})
        }
    },
    actions: {
        login(context,data){
            $.ajax({
                url: "http://127.0.0.1:8000/user/login",
                type: "post",
                data: {
                    username: data.username,
                    password: data.password,
                },
                success(resp){
                    if(resp.message === 'success'){
                        console.log(resp)
                        localStorage.setItem("jwt_token",resp.token);
                        context.commit('updateToken',resp.token);
                        context.commit('updateStatus',true);
                        data.success(resp);
                    }else{
                        data.error(resp);
                    }
                },
                error(resp){

                    data.error(resp);
                }
            })
        },
        getInfo(context,data){
            $.ajax({
                url:"http://127.0.0.1:8000/user/info",
                type:"get",
                headers:{
                    Authorization:"Bearer "+context.state.token
                },
                success(resp){
                    if(resp.message === "success"){
                        context.commit("updateUser",{
                            ...resp,
                            is_login:true,
                        })
                        data.success(resp);
                    }else{
                        data.error(resp);
                    }
                },
                error(resp){
                    data.error(resp);
                }
            })
        },
        logout(context){
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        }
    },
    modules: {
    }
}