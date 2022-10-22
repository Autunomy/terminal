<template>
    <div>
        <NavBar/>
        <ContentMessage>
            <router-view/>
        </ContentMessage>
    </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';
import NavBar from "@/components/NavBar";
import ContentMessage from "@/components/ContentMessage";
export default {
    name: "APP",
    components:{
        NavBar,
        ContentMessage,
    }
}
</script>
<style>

</style>
