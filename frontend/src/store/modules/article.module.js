import instance from '@/config/request'

const state = {
    article: {},
    articleList: [],

};

const actions = {
    createArticle({commit}, payload) {
        return new Promise((resolve, reject) => {
            instance.post('/api/article', payload).then((response) => {
                resolve(response.data);
            }).catch((error) => {
                console.error(error);
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

            /** 객체 프로퍼티 축약형으로 가능 **/
            instance.get('/api/article', {params: params}).then((response) => {
                commit('setArticleList', response.data.content);
                resolve(response.data);
            }).catch((error) => {
                console.error(error);
            })
        });
    },

    fetchOneArticle({commit}, id){

        const requestUri = `/api/article/${id}`

        return new Promise((resolve, reject) => {
            instance.get(requestUri).then((response) => {
                commit('setOneArticle', response.data);
                resolve();
            }).catch((error) => {
                console.error(error);
            })
        });
    }
};

const mutations = {
    setArticleList(state, articleList){
        state.articleList = articleList;
    },

    setOneArticle(state, article){
        state.article = article;
    },

    cleanUp(state){
        state.articleList = [];
    }
};

const getters = {
    articleList: state => state.articleList,
    article: state => state.article,
};

export default {
    state,
    actions,
    mutations,
    getters
}