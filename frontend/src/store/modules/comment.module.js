
import instance from '@/config/request'

import {createHelpers} from 'vuex-map-fields'

const {getCommentFields, updateCommentFields} = createHelpers({
    getterType: `getCommentFields`,
    mutationType: `updateCommentFields`
});

export default {
    state: {
    },

    actions: {

        createComment({commit}, payload){

            return new Promise((resolve) => {
                instance.post(`/api/comment`, payload).then((response) => {
                    resolve(response);
                }).catch((error) => {
                    console.error(`댓글 등록 시 에러 발생 : `);
                    console.error(error.response.data);
                })
            });
        },

        deleteComment({commit}, commentId){

            return new Promise((resolve) => {
                instance.delete(`/api/comment/${commentId}`).then((response) => {
                    resolve(response);
                }).catch((error) => {
                    console.error(`댓글 삭제 시 에러 발생 : `);
                    console.error(error.response.data);
                })
            });
        },

        modifyComment({commit}, params){

            let commentId = params.commentId;
            const payload = {};
            payload.articleId = params.articleId;
            payload.comment = params.comment;

            return new Promise((resolve) => {
                instance.patch(`/api/comment/${commentId}`, payload).then((response) => {
                    resolve(response);
                }).catch((error) => {
                    console.error(`댓글 수정 시 에러 발생 : `);
                    console.error(error.response.data);
                })
            });
        }
    },

    mutations: {
        updateCommentFields
    },

    getters: {
        getCommentFields
    }
}
