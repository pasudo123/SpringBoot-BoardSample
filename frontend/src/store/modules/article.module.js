
import instance from '@/config/request'

export default {

    namespaced: true,

    state: {
        article: {},
        articleList: [],
        isUpdate: false
    },

    actions: {
        createArticle({commit}, payload) {
            return new Promise((resolve, reject) => {
                instance.post('/api/article', payload).then((response) => {
                    resolve(response.data);
                }).catch((error) => {
                    reject(error);
                })
            });
        },

        fetchAllArticle({commit}, page) {

            const params = {
                page: page,
                size: 5,
                direction: 'DESC'
            };

            return new Promise((resolve, reject) => {
                instance.get('/api/article', {params}).then((response) => {
                    commit('setArticleList', response.data.content);
                    resolve(response.data);
                }).catch((error) => {
                    console.error(`전체 아티클 조회 시 에러 발생 : ${error.response}`);
                })
            });
        },

        fetchOneArticle({commit}, id){

            const requestUri = `/api/article/${id}`;

            return new Promise((resolve, reject) => {
                instance.get(requestUri).then((response) => {
                    commit('setOneArticle', response.data);
                    resolve(response);
                }).catch((error) => {
                    console.debug(`하나의 아티클 조회 시 에러 발생 : ${error.response}`);
                    reject(error);
                });
            });
        },

        updateOneArticle({commit, state}, payload){

            const requestUri = `/api/article/${state.article.id}`;

            return new Promise((resolve, reject) => {
                instance.patch(requestUri, payload).then((response) => {
                    resolve();
                }).catch((error) => {
                    console.debug(`아티클 업데이트 시 에러 발생 : ${error.response}`);
                });
            });
        },

        deleteOneArticle({commit, state}){

            const id = state.article.id;
            const requestUri = `/api/article/${id}`;

            return new Promise((resolve, reject) => {
                instance.delete(requestUri).then((response) => {
                    commit(`cleanOneArticle`);
                    resolve();
                }).catch((error) => {
                    console.debug(`하나의 아티클 삭제 시 에러 발생 : ${error.response}`);
                    reject(error);
                });
            });
        }
    },

    mutations: {
        setArticleList(state, articleList){
            state.articleList = articleList;
        },

        setOneArticle(state, article){
            state.article = article;
        },

        cleanOneArticle(state) {
            state.article = {};
        },

        cleanAllArticle(state) {
            state.articleList = [];
        },

        toggleIsUpdate(state){
            state.isUpdate = !state.isUpdate;
        }
    },

    getters: {
        articleList: state => state.articleList,
        article: state => state.article,
        isUpdate: state => state.isUpdate,
    }
}