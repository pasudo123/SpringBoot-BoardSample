<template>
    <div id="comment">
        <v-list>
            <v-list-tile
                    v-for="comment of this.commentList"
                    :key="comment.id"
                    avatar
                    @click="commentMore"
                    class="commentElement"
            >
                <v-list-tile-avatar>
                    <img :src="comment.writer.image">
                </v-list-tile-avatar>

                <v-list-tile-content>
                    <v-list-tile-title>{{comment.comment}}</v-list-tile-title>
                    <v-list-tile-sub-title>{{ comment.createDate }}</v-list-tile-sub-title>
                </v-list-tile-content>
                <div class="settingWrapper">
                    <span class="modifyBtn" @click="modifyProcess">수정</span>
                    <br>
                    <span class="deleteBtn" @click="deleteProcess">삭제</span>
                </div>
            </v-list-tile>
        </v-list>
    </div>
</template>

<script>
    export default {
        name: "Comment",
        props: {
            commentList: {type: Array, required: false, default: []}
        },
        data() {
            return {
                avatarSrc: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
            }
        },
        methods: {
            commentMore() {

            },

            modifyProcess() {
                console.debug("수정");
            },

            deleteProcess() {
                console.debug("삭제");
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

    span.modifyBtn{
        display: inline-block;
        padding: 2px 7px 2px 7px;
        background-color: darkgreen;
        color: white;
        font-size: 14px;
        margin-bottom: 2px;
    }

    span.deleteBtn{
        display: inline-block;
        padding: 2px 7px 2px 7px;
        background-color: darkgreen;
        color: white;
        font-size: 14px;
        margin-top: 2px;
    }
</style>