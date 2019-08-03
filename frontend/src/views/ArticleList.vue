<template>
    <div id="articleList">

        <div class="settingWrapper">
            <router-link
                    :to="{ name: 'articleEdit' }"
                    class="writeLink"
                    active-class="active"
            >
                New Article
            </router-link>
        </div>

        <div
                v-if="loading"
                class="loadingWrapper"
        >
            <v-progress-circular
                    indeterminate
                    color="green"
            ></v-progress-circular>
        </div>
        <div
                v-else
                class="listWrapper"
        >
            <v-layout>
                <v-flex>
                    <v-list two-line>
                        <template v-for="(article, index) in articleList">
                            <v-list-tile
                                    :key="index.id"
                                    avatar
                                    @click="into(article)"
                            >
                                <v-list-tile-avatar>
                                    <img :src="avatar">
                                </v-list-tile-avatar>

                                <v-list-tile-content>
                                    <v-list-tile-title v-html="article.title"></v-list-tile-title>
                                    <!--<v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>-->
                                </v-list-tile-content>
                            </v-list-tile>
                            <hr>
                        </template>
                    </v-list>
                </v-flex>
            </v-layout>

            <div class="pagiWrapper">
                <v-pagination
                        color="green"
                        v-model="pagination.page"
                        :length="pagination.pages"
                        @input="move"
                ></v-pagination>
            </div>
        </div>
    </div>
</template>

<script>

    import * as Cookies from 'js-cookie'
    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: "ArticleList",
        data() {
            return {
                loading: false,
                pagination: {page: 1, pages: 5},
                avatar: 'https://avatars0.githubusercontent.com/u/17797352?s=460&v=4',
            }
        },
        computed: {
            ...mapGetters(`articleModule`, {
                articleList: `articleList`,
            })
        },
        methods: {

            ...mapActions(`articleModule`, [
                `fetchAllArticle`, `fetchOneArticle`
            ]),

            into(article){
                this.fetchOneArticle(article.id).then((response) => {
                    this.$router.push({name: 'articleView'});
                });
            },

            move(page) {
                this.fetchPagination(page);
            },

            fetchPagination(page){
                this.loading = true;
                this.fetchAllArticle(page).then((data) => {
                    this.pagination.pages = data.totalPages;
                    this.loading = false;
                });
            }
        },
        created() {
            this.fetchPagination(1);
        }
    }
</script>

<style scoped>

    div.settingWrapper {
        margin: 30px auto 20px auto;
        width: 700px;
        text-align: right;
    }

    div.loadingWrapper{
        margin-top: 200px;
        text-align: center;
    }

    div.listWrapper {
        margin: 0 auto;
        width: 700px;
    }

    div.pagiWrapper {
        margin: 10px 0 0 0;
        text-align: center;
    }

    .writeLink {
        color: white;
        background-color: green;
        text-decoration: none;
        border-radius: 5px;
        padding: 8px 12px 8px 12px;
    }

    .writeLink:hover {
        background-color: #006e00;
    }
</style>