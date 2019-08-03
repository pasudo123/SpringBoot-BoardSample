<template>
    <div id="article">
        <h2>{{this.url}}</h2>
        <ArticleList></ArticleList>
    </div>
</template>

<script>

    import ArticleList from "@/pages/home/ArticleList";
    import Header from '@/components/Header'
    import Footer from '@/components/Footer'

    export default {
        name: "Article",
        components: {Footer, Header, ArticleList},
        data() {
            return {
                url: process.env.VUE_APP_URL
            }
        }
    }
</script>

<style scoped>

</style>