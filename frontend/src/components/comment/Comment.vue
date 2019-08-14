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

                    <comment-input
                            v-if="isModify(comment)"
                            :isModify="true"
                            :modifyComment="comment.comment"
                            :modifyIndex="index"
                    />

                    <div v-if="hasCurrentUser(comment.writer)" class="settingWrapper">
                        <span class="spanBtn modifyBtn" @click="modifyProcess(comment, index)">수정</span>
                        <br>
                        <span class="spanBtn deleteBtn" @click="deleteProcess(comment.id)">삭제</span>
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
            ...mapActions([`deleteComment`]),

            hasCurrentUser(writer) {
                return (writer.registrationId === this.currentUser.registrationId)
            },

            isModify(comment) {
                /** 수정 버튼을 한번씩 누를 때마다 작동 **/
                if (comment.isModify === undefined){
                    return false;
                }

                return (comment.isModify);
            },

            modifyProcess(comment, index) {
                this.$set(this.commentList[index], 'isModify', true);
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

    span.spanBtn:hover {
        cursor: pointer;
        background-color: darkgreen;
        color: white;
    }

    span.modifyBtn {
        display: inline-block;
        padding: 2px 7px 2px 7px;
        border: 1px solid darkgreen;
        font-size: 14px;
        margin-bottom: 2px;
    }

    span.deleteBtn {
        display: inline-block;
        padding: 2px 7px 2px 7px;
        border: 1px solid darkgreen;
        font-size: 14px;
        margin-top: 2px;
    }
</style>