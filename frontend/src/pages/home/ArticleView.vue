<template>
    <div id="articleView">
        <v-container>
            <v-layout>
                <v-flex>

                    <div class="articleWrapper">
                        <div class="buttonWrapper">
                            <button @click="goList()" class="customButton">Back</button>
                        </div>

                        <div class="title">
                            Article
                        </div>

                        <div class="articleMetaWrapper">
                            <div>
                                <div class="titleWrapper">
                                    <span class="typeWrapper">[ {{this.parseType(this.article.articleType)}} ]</span>
                                    <br>
                                    <h2 class="articleTitle">{{this.article.title}}</h2>
                                </div>
                            </div>
                        </div>

                        <div class="contentWrapper">
                            <div class="contentArea" v-html="this.article.content">
                            </div>
                        </div>

                        <div class="buttonWrapper">
                            <div v-if="hasCurrentUser">
                                <button @click="updateProcess" class="updateButton">Update</button>
                                <button @click="deleteProcess" class="deleteButton">Delete</button>
                            </div>
                        </div>

                        <comment-input />

                        <Comment
                                :commentList="article.commentList"
                        ></Comment>
                    </div>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script>

    import Comment from '@/components/comment/Comment'
    import CommentInput from '@/components/comment/CommentInput'

    import {createHelpers} from 'vuex-map-fields'
    import {mapActions, mapGetters, mapMutations} from 'vuex'

    const {mapFields: mapCommentFields} = createHelpers({
        getterType: `getCommentFields`,
        mutationType: `updateCommentFields`
    });

    export default {
        name: "ArticleView",
        components: {Comment, CommentInput},
        computed: {
            ...mapGetters([`currentUser`]),
            ...mapGetters([`article`]),
            hasCurrentUser() {

                if (Object.keys(this.article).length === 0) {
                    return false;
                }

                return (this.article.writer.registrationId === this.currentUser.registrationId)
            },
        },
        methods: {
            ...mapActions([`createComment`]),
            ...mapActions([`fetchOneArticle`, 'deleteOneArticle']),
            ...mapMutations([`toggleIsUpdate`]),

            parseType(type) {
                if (type === 'LIFE') {
                    return '일상';
                } else if (type === 'MOVIE') {
                    return '영화';
                } else {
                    return '음악';
                }
            },

            goList() {
                this.$router.push({name: 'home'});
            },

            updateProcess() {
                this.toggleIsUpdate();
                this.$router.push({name: `articleEdit`})
            },

            deleteProcess() {
                this.deleteOneArticle().then(() => {
                    this.goList();
                })
            },
        },
        created() {
            this.fetchOneArticle(this.article.id);
        }
    }
</script>

<style scoped>
    div.articleWrapper {
        margin: 10px auto 0 auto;
        width: 800px;
        padding: 8px;
    }

    div.title {
        background-color: darkslategrey;
        color: white;
        font-size: 20px;
        padding-top: 15px;
        padding-bottom: 15px;
        padding-left: 20px;
        margin-bottom: 10px;
    }

    div.articleMetaWrapper {
        display: flex;
        margin: 5px 0 20px 0;
    }

    span.typeWrapper {
        padding: 6px 0 6px 0;
        color: darkgreen;
        font-weight: bold;
        display: inline-block;
    }

    h2.articleTitle {
        padding-left: 10px;
    }

    div.contentWrapper {
        margin: 10px 0 10px 0;
        padding: 15px;
        border-top: 1px solid grey;
        border-bottom: 1px solid grey;
    }

    div.buttonWrapper {
        text-align: right;
        margin: 5px 0 20px 0;
        height: 35px;
    }

    button.customButton {
        color: white;
        background-color: green;
        text-decoration: none;
        border-radius: 5px;
        padding: 8px 12px 8px 12px;
    }

    button.customButton:hover {
        background-color: #006e00;
    }

    button.updateButton {
        color: white;
        background-color: #3d4692;
        text-decoration: none;
        border-radius: 5px;
        padding: 8px 12px 8px 12px;
        margin: 0 15px 0 15px;
    }

    button.updateButton:hover {
        background-color: #313692;
    }

    button.deleteButton {
        color: white;
        background-color: #803b33;
        text-decoration: none;
        border-radius: 5px;
        padding: 8px 12px 8px 12px;
    }

    button.deleteButton:hover {
        background-color: #6f2b29;
    }
</style>