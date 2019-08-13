<template>
    <div id="article">
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
            }
        },
    }
</script>

<style scoped>

</style>