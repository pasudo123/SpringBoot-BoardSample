<template>
    <div id="comment">

        <div v-if="loading">
            <v-progress-circular
                    indeterminate
                    color="primary"
            ></v-progress-circular>
        </div>

        <div v-else>
            <v-list>
                <v-list-tile
                        v-for="(comment, index) in this.commentList"
                        :key="comment.id"
                        avatar
                        class="commentElement"
                >
                    <v-list-tile-avatar>
                        <img :src="comment.writer.image">
                    </v-list-tile-avatar>

                    <v-list-tile-content v-if="!isModify(comment)">
                        <v-list-tile-title>{{comment.comment}}</v-list-tile-title>
                        <v-list-tile-sub-title>{{ comment.createDate }}</v-list-tile-sub-title>
                    </v-list-tile-content>

                    <!-- 코멘트 수정 시 변경내용 -->
                    <v-list-tile-content v-else>
                        <comment-input
                                :isModify="true"
                                :propsComment="comment.comment"
                                :propsCommentId="comment.id"
                                :propsIndex="index"
                                @settingModify="settingModify"
                                @updatingComment="updatingComment"
                        />
                    </v-list-tile-content>

                    <div v-if="hasCurrentUser(comment.writer)">
                        <div v-if="!isModify(comment)">
                            <span class="spanBtn-origin upperBtn modifyBtn" @click="settingModify({index: index})">수정</span>
                            <br>
                            <span class="spanBtn-origin downBtn deleteBtn" @click="deleteProcess(comment.id)">삭제</span>
                        </div>
                    </div>
                </v-list-tile>
            </v-list>
        </div>
    </div>
</template>

<script>

    import CommentInput from '@/components/comment/CommentInput'
    import {mapActions, mapGetters} from 'vuex'

    const map = new Map();

    export default {
        name: "Comment",
        props: {
            commentList: {type: Array, required: false, default: []}
        },
        components: {CommentInput},
        computed: {
            ...mapGetters([`currentUser`]),
            ...mapGetters([`article`]),
        },
        data() {
            return {
                loading: false,
                avatarSrc: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
            }
        },
        methods: {
            ...mapActions([`fetchOneArticle`]),
            ...mapActions([`deleteComment`, `modifyComment`]),

            hasCurrentUser(writer) {
                return (writer.registrationId === this.currentUser.registrationId)
            },

            deleteProcess(commentId) {

                if (map.has(commentId)) {
                    return;
                }

                map.set(commentId, 1);
                this.deleteComment(commentId).then(() => {
                    this.fetchOneArticle(this.article.id).then(() => {
                        map.delete(commentId);
                    })
                });
            },

            isModify(comment) {
                /** 수정 버튼을 한번씩 누를 때마다 작동 **/
                if (comment.isModify === undefined) {
                    return false;
                }

                return (comment.isModify);
            },

            settingModify(params) {

                if (this.commentList[params.index].isModify === undefined){
                    this.$set(this.commentList[params.index], 'isModify', true);
                    return;
                }

                let isModified = this.commentList[params.index].isModify;
                this.$set(this.commentList[params.index], 'isModify', !isModified);
            },

            updatingComment(params) {
                /** 코멘트 수정시 전달 받은 내용 **/
                this.$set(this.commentList[params.index], 'comment', params.comment);
                this.modifyProcess(this.commentList[params.index], params.index);
            },

            modifyProcess(comment, index) {
                const params = {};
                params.index = index;
                params.articleId = this.article.id;
                params.comment = comment.comment;
                params.commentId = comment.id;

                this.modifyComment(params).then((response) => {
                    this.settingModify(params);
                });
            }
        }

    }
</script>

<style scoped>
    div#comment {
        width: 100%;
        padding: 10px 0 10px 0;
    }

    .commentElement {
        border: 1px solid gainsboro;
        padding: 5px 0 5px 0;
    }

    span.spanBtn-origin {
        display: inline-block;
        padding: 2px 7px 2px 7px;
        border: 1px solid darkgreen;
        font-size: 14px;
        cursor: pointer;
    }

    span.spanBtn-origin:hover {
        background-color: darkgreen;
        color: white;
    }

    span.upperBtn {
        margin-bottom: 2px;
    }

    span.downBtn {
        margin-top: 2px;
    }
</style>