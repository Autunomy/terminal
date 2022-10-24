<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <router-link class="navbar-brand" to="/">Online Terminal</router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <router-link class="nav-link" to="/connect">Connect</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" to="/history">History</router-link>
                    </li>
                </ul>
                <ul class="navbar-nav" v-if="!$store.state.user.is_login">
                    <li class="nav-item">
                        <router-link class="nav-link" to="/login">Login</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" to="/register">Register</router-link>
                    </li>
                </ul>
                <ul class="navbar-nav" v-if="$store.state.user.is_login">
                    <li class="nav-item">
                        <a class="nav-link" href="#" @click="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

</template>
<script>
import {useStore} from "vuex";
export default {
    name: "NavBar",
    setup() {
        const store = useStore();
        let jwt_token = localStorage.getItem("jwt_token");
        if(jwt_token){
            store.commit("updateStatus",true);
            store.commit("updateToken",jwt_token);
            store.dispatch("getInfo",{
                success(){},
                error(resp){
                    console.log(resp)
                },
            })
        }
        const logout = () => {
            store.dispatch("logout");
        }

        return {
            logout,
        }
    }
}
</script>

<style scoped>

</style>