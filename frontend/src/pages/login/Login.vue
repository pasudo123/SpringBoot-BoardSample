<template>
    <div id="login">
        <div class="loginWrapper">

            <h2>Login Page</h2>

            <a
                    @click="loginProcess">
                <img class="googleLogo"
                     :src="googleLoginLogo"/>
            </a>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                googleLoginLogo: require('@/assets/logo/google_logo.png'),
            }
        },
        methods: {
            goHome() {

                const backendHref = `http://localhost:8080/home`;

                window.location.href = backendHref
            },

            loginProcess() {

            }
        }
    }
</script>

<style scoped>
    div#login {
        margin: 0 auto;
        text-align: center;
    }

    div.loginWrapper{
        padding: 100px 0 100px 0;
    }

    h2{
        padding: 30px 0 30px 0;
    }

    img.googleLogo {
        width: 200px;
        height: auto;
        cursor: pointer;
    }

</style>