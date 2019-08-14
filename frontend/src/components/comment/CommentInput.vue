<template>
    <div id="commentInput">
        <div class="commentWrapper">
            <div v-if="isModify">
                <v-text-field
                        color="blue"
                        label="댓글수정"
                        type="text"
                        @keyup.enter="addNewComment"
                        v-model="modifyComment"
                ></v-text-field>
            </div>
            <div v-else>
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
    </div>
</template>

<script>

    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: "CommentInput",
        props: {
            isModify: {type: Boolean, required: false, default: false},
            modifyComment: {type: String, required: false, default: ''},
            modifyIndex: {type: Number, required: false, default: 0}
        },
        data() {
            return {
                comment: '',
            }
        },
        computed: {
            ...mapGetters([`article`])
        },
        methods: {

            ...mapActions([`createComment`]),
            ...mapActions([`fetchOneArticle`]),

            addNewComment() {
                const payload = {};
                payload.articleId = this.article.id;
                payload.comment = this.comment;

                this.createComment(payload).then(() => {
                    this.fetchOneArticle(payload.articleId);
                    this.comment = '';
                })
            }
        }
    }
</script>

<style scoped>
    div.commentWrapper {
        margin-top: 50px;
        margin-bottom: 10px;
    }


    div.commentButtonWrapper {
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
</style>