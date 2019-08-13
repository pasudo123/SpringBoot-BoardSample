
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

            console.debug(`[ACTIONS] createComment`);

            return new Promise((resolve, reject) => {
                instance.post(`/api/comment`, payload).then((response) => {
                    resolve(response);
                }).catch((error) => {
                    console.error(`댓글 등록 시 에러 발생 : `);
                    console.error(error.response.data);
                })
            });
        },

        deleteComment({commit}, commentId){

            return new Promise((resolve, reject) => {
                instance.delete(`/api/comment/${commentId}`).then((response) => {
                    resolve(response);
                }).catch((error) => {
                    console.error(`댓글 삭제 시 에러 발생 : `);
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
