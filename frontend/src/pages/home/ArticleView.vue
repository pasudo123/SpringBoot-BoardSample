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
                                    <span class="typeWrapper">[ {{this.parseType(this.myArticle.articleType)}} ]</span>
                                    <br>
                                    <h2 class="articleTitle">{{this.myArticle.title}}</h2>
                                </div>
                            </div>
                        </div>

                        <div class="contentWrapper">
                            <div class="contentArea" v-html="this.myArticle.content">
                            </div>
                        </div>

                        <div class="buttonWrapper">
                            <button @click="updateProcess" class="updateButton">Update</button>

                            <button @click="deleteProcess" class="deleteButton">Delete</button>
                        </div>

                        <div class="commentWrapper">
                            <v-text-field
                                    color="blue"
                                    label="댓글작성"
                                    type="text"
                                    @keyup.enter="addNewComment"
                                    v-model="comment"
                            ></v-text-field>
                            <div class="commentButtonWrapper">
                                <button @click="addNewComment" class="commentBtn">
                                    등록
                                </button>
                            </div>
                        </div>
                    </div>

                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script>

    import {createHelpers} from 'vuex-map-fields'

    const {mapFields: mapCommentFields} = createHelpers({
        getterType: `getCommentFields`,
        mutationType: `updateCommentFields`
    });

    import {mapActions, mapGetters, mapMutations} from 'vuex'

    export default {
        name: "ArticleView",
        data() {
            return {
                myArticle: {},
                comment: '',
            }
        },
        computed: {

            ...mapGetters(`articleModule`, {
                article: `article`,
            }),
        },
        methods: {

            ...mapActions([`createComment`]),

            ...mapActions(`articleModule`, [
                'deleteOneArticle'
            ]),

            ...mapMutations(`articleModule`, [
                `toggleIsUpdate`
            ]),

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

            addNewComment(){
                const payload = {};
                payload.articleId = this.myArticle.id;
                payload.comment = this.comment;

                this.createComment(payload).then((response) => {
                    this.comment = '';
                    console.debug(response.data);
                })
            }
        },
        created() {
            this.myArticle = this.article;
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

    div.commentWrapper {
        margin-top: 50px;
        margin-bottom: 10px;
    }


    div.commentButtonWrapper{
        text-align: right;
    }

    button.commentBtn {
        padding: 5px 12px 5px 12px;
        border-radius: 5px;
        background-color: #b26138;
        color: white;
        position: relative;
    }

    button.commentBtn:hover {
        background-color: #7e4528;
    }

    div.buttonWrapper {
        text-align: right;
        margin-bottom: 20px;
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
        margin: 5px 15px 0 15px;
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
        margin: 5px 0 0 0;
    }

    button.deleteButton:hover {
        background-color: #6f2b29;
    }
</style>