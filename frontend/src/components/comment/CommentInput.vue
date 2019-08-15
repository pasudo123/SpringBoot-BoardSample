<template>
    <div id="commentInput">
        <div v-if="isModify" class="commentModifyWrapper">
            <v-text-field
                    color="blue"
                    label="댓글수정"
                    type="text"
                    @keyup.enter="modifyEmit"
                    v-model="comment"
            ></v-text-field>
            <div class="modifyButtonWrapper">
                <span class="spanBtn-effect upperBtn" @click="modifyEmit">수정하기</span>
                <br>
                <span class="spanBtn-effect downBtn" @click="settingEmit">수정취소</span>
            </div>
        </div>
        <div v-else class="commentWrapper">
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
</template>

<script>

    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: "CommentInput",
        props: {
            isModify: {type: Boolean, required: false, default: false},
            propsComment: {type: String, required: false, default: ''},
            propsCommentId: {type: Number, required: false, default: 0},
            propsIndex: {type: Number, required: false, default: 0}
        },
        data() {
            return {
                comment: (this.propsComment === '') ? '' : this.propsComment
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
            },

            settingEmit() {
                const params = {};
                params.index = this.propsIndex;
                this.$emit(`settingModify`, params);
            },

            modifyEmit() {
                const params = {};
                params.index = this.propsIndex;
                params.comment = this.comment;
                this.$emit(`updatingComment`, params);
            }
        }
    }
</script>

<style scoped>

    div#commentInput {
        width: 100%;
    }

    div.commentModifyWrapper {
        margin-top: 18px;
        width: 600px;
        display: flex;
    }

    div.modifyButtonWrapper{
        position: absolute;
        right: 16px;
    }

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

    span.spanBtn-effect {
        display: inline-block;
        padding: 2px 7px 2px 7px;
        border: 1px solid mediumblue;
        font-size: 14px;
        cursor: pointer;
    }

    span.spanBtn-effect:hover {
        background-color: mediumblue;
        color: white;
    }

    span.upperBtn {
        margin-bottom: 2px;
    }

    span.downBtn {
        margin-top: 2px;
    }
</style>