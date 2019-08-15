<template>
    <div id="header" data-app>
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

                    <v-menu offset-y>
                        <template v-slot:activator="{ on }">
                            <span v-on="on" class="emailWrapper">
                                {{currentUserEmail}}
                            </span>
                        </template>
                        <v-list>
                            <v-list-tile
                                    v-for="(item, index) in items"
                                    :key="index"
                                    @click="menuClickEvent(item)"
                            >
                                <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                            </v-list-tile>
                        </v-list>
                    </v-menu>
            </div>
        </v-toolbar>
    </div>
</template>

<script>

    import {mapGetters} from 'vuex'

    export default {
        name: "Header",
        data() {
            return {
                items: [
                    {title: 'Logout'},
                ]
            }
        },
        computed: {
            ...mapGetters(['currentUser']),

            currentUserImage() {
                if (this.currentUser === undefined) {
                    return 'https://vuetifyjs.com/apple-touch-icon-180x180.png';
                }

                return this.currentUser.image;
            },

            currentUserEmail: {
                get() {
                    if (this.currentUser === null) {
                        return 'Not Found User';
                    }

                    if (this.currentUser === undefined) {
                        return 'Not Found User';
                    }

                    return this.currentUser.email;
                },
            },
        },
        methods: {
            menuClickEvent(item){
                this.logout();
            },

            logout() {

                const API_BASE_URI = process.env.VUE_APP_BASE_API;
                const LOGOUT_URI = `${API_BASE_URI}/logout`;

                window.location.href = LOGOUT_URI;
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

    .emailWrapper {
        cursor:pointer;
        padding: 10px 15px 10px 15px;
        background-color: #6d6d6d;
        color: white;
        border-radius: 8px;
        margin-left: 15px;
    }
</style>