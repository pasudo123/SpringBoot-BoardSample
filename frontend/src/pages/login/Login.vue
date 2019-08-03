<template>
    <div id="login">
        <h1 @click="goHome">Login</h1>
    </div>
</template>

<script>
    export default {
        name: "Login",
        methods: {
            goHome(){

                const backendHref = `http://localhost:8080/home`;

                window.location.href = backendHref
            }
        }
    }
</script>

<style scoped>
    div#login{
        margin:0 auto;
        text-align: center;
    }
</style>