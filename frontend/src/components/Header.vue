<template>
    <div id="header">
        <v-toolbar dark color="#c3eec3">
            <v-toolbar-title class="black--text">Real World</v-toolbar-title>

            <v-spacer></v-spacer>

            <div class="userInfoWrapper">
                <v-avatar
                        :tile="false"
                        :size="40"
                        color="grey lighten-4"
                >
                    <img :src="this.currentUserImage" alt="avatar">
                </v-avatar>
                <span class="emailWrapper">{{this.currentUserEmail}}</span>
            </div>
        </v-toolbar>
    </div>
</template>

<script>

    import {mapGetters} from 'vuex'

    export default {
        name: "Header",
        computed: {
            ...mapGetters(['currentUser']),

            currentUserImage() {
                if(this.currentUser === undefined) {
                    return 'https://vuetifyjs.com/apple-touch-icon-180x180.png';
                }

                return this.currentUser.image;
            },

            currentUserEmail() {
                if(this.currentUser === undefined) {
                    return 'Not Found User';
                }

                return this.currentUser.email;
            }
        }
    }
</script>

<style scoped>
    header {
        height: 60px;
        background-color: #c3eec3;
    }

    .black--text {
        padding: 0 0 0 30px;
    }

    .emailWrapper{
        padding: 10px 15px 10px 15px;
        background-color: #6d6d6d;
        color:white;
        border-radius: 8px;
        margin-left: 15px;
    }
</style>