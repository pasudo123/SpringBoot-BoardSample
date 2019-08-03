<template>
    <div id="articleEdit">
        <v-form @submit.prevent="{submit, cancel}">
            <v-container>
                <v-layout>
                    <v-flex>
                        <div class="articleWrapper">
                            <div class="title">
                                Article
                            </div>

                            <div class="articleMetaWrapper">
                                <v-app style="height: 0">
                                    <div>
                                        <v-select
                                                color="2F4F4F"
                                                v-model="select"
                                                :hint="'Article TYPE'"
                                                :items="types"
                                                item-text="name"
                                                item-value="type"
                                                persistent-hint
                                                single-line
                                        ></v-select>
                                    </div>
                                </v-app>

                                <div>
                                    <v-text-field
                                            @keypress.enter.prevent
                                            v-model="articleEdit.title"
                                            label="제목"
                                    ></v-text-field>
                                </div>
                            </div>

                            <VueTrix
                                    style="min-height: 250px;"
                                    height="250px"
                                    v-model="articleEdit.content"
                                    placeholder="내용 작성"/>

                            <div class="buttonWrapper">
                                <button @click="cancel" class="customButton">취소</button>
                                <v-spacer></v-spacer>
                                <button v-if="!isUpdate" @click="submit" class="customButton">작성</button>
                                <button v-else @click="update" class="customButton">업데이트</button>
                            </div>
                        </div>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-form>
    </div>
</template>

<script>

    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import VueTrix from 'vue-trix'

    export default {
        name: "Article",
        components: {VueTrix},
        computed: {
            ...mapGetters(`articleModule`, {
                article: `article`,
                isUpdate: `isUpdate`
            })
        },
        data() {

            return {
                articleEdit: {type: '', title: '', content: '',},
                select: {name:'일상', type:'LIFE'},
                types: [
                    {name:'일상', type:'LIFE'},
                    {name:'영화', type:'MOVIE'},
                    {name:'음악', type:'MUSIC'},
                ],
            }
        },
        methods: {

            ...mapActions(`articleModule`, [
                'createArticle', `updateOneArticle`
            ]),

            ...mapMutations(`articleModule`, [
                `toggleIsUpdate`, `fetchOneArticle`
            ]),

            offUpdate(){
                if (this.isUpdate){
                    this.toggleIsUpdate();
                }
            },

            cancel() {
                this.offUpdate();
                this.routingHome();
            },

            submit() {
                const payload = {
                    title: this.articleEdit.title,
                    articleType: (this.select.type === "LIFE") ? "LIFE" : this.select,
                    content: this.articleEdit.content
                };

                this.createArticle(payload).then((data) => {
                    this.routingHome();
                });
            },

            update() {
                const payload = {
                    title: this.articleEdit.title,
                    articleType: this.select,
                    content: this.articleEdit.content
                };

                this.updateOneArticle(payload).then((data) => {
                    this.routingHome();
                });
            },

            routingHome() {
                this.offUpdate();
                this.$router.push({path: '/'});
            },
        },
        created() {
            if (this.isUpdate === true){

                this.articleEdit = this.article;

                if (this.article.articleType === `LIFE`){
                    this.select = this.types[0];
                    return;
                }

                if (this.article.articleType === `MOVIE`){
                    this.select = this.types[1];
                    return;
                }
                this.select = this.types[2];
            }
        }
    }
</script>

<style>
    trix-editor {
        min-height: 250px;
    }

    div.application--wrap {
        min-height: 0;
    }

</style>

<style scoped>

    div.articleWrapper {
        margin: 40px auto 0 auto;
        width: 800px;
        padding: 8px;
        border: 2px solid darkslategray;
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

    div.articleMetaWrapper > div:nth-child(1) {
        margin-right: 10px;
        flex: 1;
        text-align: center;
    }

    div.articleMetaWrapper > div:nth-child(2) {
        margin-left: 10px;
        flex: 5;
    }

    div.buttonWrapper {
        margin-top: 8px;
        display: flex;
    }

    button.customButton {
        background-color: darkslategrey;
        width: 100px;
        color: white;
        padding: 5px 10px;
        outline: none;
    }

    button.customButton:hover {
        background-color: #243d3d;
        box-shadow: 1px 0 0 1px #525252;
    }

</style>