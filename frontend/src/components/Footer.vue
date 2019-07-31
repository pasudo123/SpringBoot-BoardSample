<template>
    <footer>
        <div class="footerWrapper">
            <div class="subFooterWrapper">
                <span class="TitleWrapper">Real World</span>
            </div>
        </div>
    </footer>
</template>

<script>
    export default {
        name: "Footer"
    }
</script>

<style scoped>
    footer{
        width:100%;
        position: absolute;
        bottom:0;
        height: 60px;
        background-color: #eeeeea;
    }

    div.footerWrapper{
        width: 100%;
        height: 100%;
        display:table;
    }

    div.subFooterWrapper {
        display: table-cell;
        vertical-align: middle;
    }

    span.TitleWrapper{
        margin-left: 80px;
        font-size: 12px;
    }
</style>