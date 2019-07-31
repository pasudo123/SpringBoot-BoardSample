<template>
    <header>
        <div class="headerWrapper">
            <div class="subHeaderWrapper">
                <span class="TitleWrapper">Real World</span>
            </div>
        </div>
    </header>
</template>

<script>
    export default {
        name: "Header"
    }
</script>

<style scoped>
    header{
        height: 60px;
        background-color: #c3eec3;
    }

    div.headerWrapper{
        width: 100%;
        height: 100%;
        display:table;
    }

    div.subHeaderWrapper {
        display: table-cell;
        vertical-align: middle;
    }

    span.TitleWrapper{
        margin-left: 80px;
        font-size: 18px;
        font-weight: bold;
    }
</style>