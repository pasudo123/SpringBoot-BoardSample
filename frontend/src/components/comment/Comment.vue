<template>
    <div id="comment">
        <v-list-tile-content>
            <v-list-tile-title v-text=""></v-list-tile-title>
        </v-list-tile-content>
    </div>
</template>

<script>
    export default {
        name: "Comment",
        props: {
            Object: { type: Object, required: false, default: {} }
        },

    }
</script>

<style scoped>

</style>